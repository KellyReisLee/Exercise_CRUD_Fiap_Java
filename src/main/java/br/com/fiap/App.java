package br.com.fiap;


import br.com.fiap.classes.Tarefa;
import br.com.fiap.dao.TarefaDao;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        System.out.println();
        TarefaDao tarefaDao = new TarefaDao();

        // Listar Tarefas:
       /* for(Tarefa tarefa: tarefaDao.listarTarefas()){
            System.out.println(tarefa.titulo);
        }*/

        // Criar nova tarefa:
        Tarefa novaTarefa = TarefaDao.criarTarefa(new Tarefa("Tarefa 4"));

        // Listar Tarefas:
        for(Tarefa tarefa: tarefaDao.listarTarefas()){
            System.out.println(tarefa.titulo);
        }

    }
}
