package br.com.fiap;


import br.com.fiap.classes.Tarefa;
import br.com.fiap.dao.TarefaDao;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        TarefaDao tarefaDao = new TarefaDao();

        // Listar Tarefas:
       try{
           for(Tarefa tarefa: tarefaDao.listarTarefas()) {
               System.out.println(tarefa.titulo);
           }
       } catch (SQLException e) {
           System.out.println("Erro ao salvar no banco: \" "+ e.getMessage());
       }

        // Criar nova tarefa:
        try {
            Tarefa novaTarefa = TarefaDao.criarTarefa(new Tarefa("Estudar Java."));

            Tarefa tarefaSalva = TarefaDao.criarTarefa(novaTarefa);

            if (tarefaSalva.id > 0) {
                System.out.println("Tarefa criada com sucesso! ID: " + tarefaSalva.id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar no banco: " + e.getMessage());
        }

        // Mostrar tarefa com id:
       try {
           Tarefa tarefa = tarefaDao.encontrarTarefa(52); // busca pelo ID

           if (tarefa != null) {
               System.out.println(tarefa.titulo); // só o título
           } else {
               System.out.println("Tarefa não encontrada!");
           }
       } catch (SQLException e) {
           System.out.println("Erro de conexão com o Oracle: " + e.getMessage());
       }


        // Deletar Tarefa:
        try {
            int tarefaDeletada = tarefaDao.deletarTarefa(45);

            if (tarefaDeletada != 0) {
                return;
            } else {
                System.out.println("Tarefa não encontrada ou erro ao deletar.");
            }
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o Oracle: " + e.getMessage());
        }


        // Listar Tarefas:
        try{
            for(Tarefa tarefa: tarefaDao.listarTarefas()) {
                System.out.println(tarefa.titulo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco: \" "+ e.getMessage());
        }

    }
}
