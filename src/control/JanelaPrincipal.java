package control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Agenda;
import model.Tarefa;

public class JanelaPrincipal {

    private Agenda agenda = new Agenda();

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tfDescricao;

    @FXML
    private Text txtQtdeTarefas;

    @FXML
    private ListView<Tarefa> ltvTarefas;


    @FXML
    private TextArea taVerDescricao;

    @FXML
    private void acaoSalvar(){

        String titulo = tfTitulo.getText();
        String descricao = tfDescricao.getText();

        Tarefa t = new Tarefa(titulo,descricao);

        agenda.adicionar(t);

        atualizaTela();
    }


    @FXML
    private void acaoClickLista(){
        Tarefa t = ltvTarefas.getSelectionModel().getSelectedItem();
        if(t != null){
            taVerDescricao.setText(t.getDescricao());
        }
    }


    private void atualizaTela(){
        atualizaLista();

        txtQtdeTarefas.setText("Total de Tarefas:"+agenda.getLista().size());
    }


    private void atualizaLista(){
        ltvTarefas.getItems().clear();
        ltvTarefas.getItems().addAll(agenda.getLista());
    }



}
