package br.com.fiap.dao;

import br.com.fiap.classes.Tarefa;
import br.com.fiap.factory.DbConnectionFactory;

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

}
