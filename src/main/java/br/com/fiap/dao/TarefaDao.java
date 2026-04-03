package br.com.fiap.dao;

import br.com.fiap.classes.Tarefa;
import br.com.fiap.factory.DbConnectionFactory;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDao {

    // Listar tarefas:
    public List<Tarefa> listarTarefas() throws SQLException{
        List<Tarefa> tarefas = new ArrayList<>();

        // Abrir conexão com o banco de dados:
        Connection connection = DbConnectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tarefas");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Tarefa tarefa = new Tarefa(resultSet.getInt("id"), resultSet.getString("titulo"));
            tarefas.add(tarefa);
        }

        connection.close();
        return tarefas;

    }

    public static Tarefa criarTarefa(Tarefa tarefa) throws SQLException{

        // Abrir a conexão:
        Connection connection = DbConnectionFactory.createConnection();

        // INSERT INTO tarefas (titulo) values ('tarefa 3');
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tarefas (titulo) values (?)", new String[] {"id"});

        preparedStatement.setString(1, tarefa.titulo);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            tarefa.id = resultSet.getInt(1);
        }else {
            System.out.println("Ops, algo deu errado na criação da Tarefa!");
        }
        connection.close();
        return tarefa;

    }

    public Tarefa encontrarTarefa(int tarefaId) throws SQLException{
        Connection connection = DbConnectionFactory.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tarefas Where id = ?");
        preparedStatement.setInt(1, tarefaId);

        ResultSet resultSet = preparedStatement.executeQuery();

        Tarefa tarefa = null;
        if (resultSet.next()){
            tarefa = new Tarefa(resultSet.getInt("id"), resultSet.getString("titulo"));
        }

        connection.close();
        return tarefa;

    }

    public void atualizarTarefa(Tarefa tarefa) throws SQLException{
        Tarefa encontrarTarefa1 = this.encontrarTarefa(tarefa.id);

        if(encontrarTarefa1 == null){
            System.out.println("Tarefa não encontrada!");
            return;
        } else {
            Connection connection = DbConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tarefas SET titulo = ?, WHERE id = ?");
            preparedStatement.setString(1, tarefa.titulo);
            preparedStatement.setInt(2, tarefa.id);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows != 1){
                System.out.println("Opss! Algo errado aconteceu na criação da Tarefa.");
            }else{
                System.out.println("A Tarefa foi atualizada com sucesso!");
            }

            connection.close();

        }


    }

    public int deletarTarefa(int tarefaId) throws SQLException {
        int affectedRows = 0; // Inicializamos fora para o 'return' funcionar

        // 1. Validamos se a tarefa existe antes de abrir conexão (Economia de recursos)
        Tarefa encontrarTarefa = this.encontrarTarefa(tarefaId);
        if (encontrarTarefa == null) {
            System.out.println("Tarefa com ID " + tarefaId + " não encontrada!");
            return 0;
        }

        // 2. Usamos Try-with-resources para garantir o fechamento da conexão
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection connection = DbConnectionFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, tarefaId);
            affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Tarefa deletada com sucesso.");
            } else {
                System.out.println("Opss! Nenhuma linha foi afetada ao deletar.");
            }
        }
        // O connection.close() acontece automaticamente aqui pelo 'try'

        return affectedRows;
    }

}
