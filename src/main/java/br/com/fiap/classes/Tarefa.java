package br.com.fiap.classes;

public class Tarefa {
   public int id;
   public String titulo;

    public Tarefa(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Tarefa(String titulo) {
        this.titulo = titulo;
    }
}
