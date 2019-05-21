package model;

import java.util.ArrayList;

public class Agenda {

    private ArrayList<Tarefa> lista;

    public Agenda(){

        lista = new ArrayList<>();
    }

    public void adicionar(Tarefa t){
        lista.add(t);
    }

    public String lista(){

        String str="";

        for(Tarefa t:lista){
            str += t.getTitulo()+"->"+t.getDescricao()+"\n";
        }

        return str;
    }

    public ArrayList<Tarefa> getLista(){
        return new ArrayList<>(lista);
    }


}
