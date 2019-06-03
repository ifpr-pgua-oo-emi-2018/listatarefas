package listatarefas.model;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class Agenda {

    private static String ARQUIVO="tarefas.bin";

    private ObservableList<Tarefa> lista;

    private static Agenda instance = new Agenda();

    private Agenda(){
        lista = FXCollections.observableArrayList();
    }

    public static Agenda getInstance(){
        return  instance;
    }

    public void adicionar(Tarefa t){
        lista.add(t);
    }

    public void remove(Tarefa t){
        lista.remove(t);
    }


    public String lista(){

        String str="";

        for(Tarefa t:lista){
            str += t.getTitulo()+"->"+t.getDescricao()+"\n";
        }

        return str;
    }

    public ObservableList<Tarefa> getLista(){
        return FXCollections.unmodifiableObservableList(lista);
    }


    public void carregaDados() throws IOException,ClassNotFoundException{

        lista.clear();

        ObjectInputStream ois = new ObjectInputStream(
                                    new FileInputStream(
                                            new File(ARQUIVO)
                                        )
                                    );

        ArrayList<Tarefa> temp = (ArrayList)ois.readObject();

        lista.addAll(temp);

        ois.close();

    }

    public void salvaDados() throws IOException{


        ObjectOutputStream oos = new ObjectOutputStream(
                                    new FileOutputStream(
                                            new File(ARQUIVO))
                                    );

        ArrayList<Tarefa> temp = new ArrayList<>();
        temp.addAll(lista);

        oos.writeObject(temp);

        oos.close();


    }



}
