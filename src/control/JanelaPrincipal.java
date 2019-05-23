package control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Agenda;
import model.Tarefa;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JanelaPrincipal {

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

    private Agenda agenda = new Agenda();

    @FXML
    private Text txtQtdeTarefas;

    @FXML
    private ListView<Tarefa> ltvTarefas;

    @FXML
    private TextArea taVerDescricao;

    @FXML
    private Text txtPrazo;

    @FXML
    private CheckBox chkConcluida;



    @FXML
    private void acaoCadastrar(){
        Dialog<ButtonType> dialog = new Dialog<>();

        try{

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass()
                    .getResource("../view/cadastro.fxml"));

            Parent content = loader.load();

            dialog.getDialogPane()
                    .setContent(content);

            dialog.getDialogPane()
                    .getButtonTypes().add(ButtonType.APPLY);
            dialog.getDialogPane()
                    .getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> resultado = dialog.showAndWait();

            if(resultado.isPresent() &&
                    resultado.get() == ButtonType.APPLY){
                Cadastro controle = loader.getController();

                Tarefa t = controle.pegaResultado();
                if(t != null){
                    agenda.adicionar(t);
                    atualizaTela();
                }
            }


        }catch (IOException e){

        }
    }

    @FXML
    private void sair(){
        Platform.exit();
    }


    @FXML
    private void acaoClickLista(){
        Tarefa t = ltvTarefas.getSelectionModel().getSelectedItem();
        if(t != null){
            taVerDescricao.setText(t.getDescricao());

            chkConcluida.setSelected(t.isConcluida());
            chkConcluida.setDisable(false);

            txtPrazo.setText(df.format(t.getPrazo()));
        }
    }


    @FXML
    private void acaoConcluida(){
        Tarefa t = ltvTarefas.getSelectionModel().getSelectedItem();
        if(t!=null){
            t.setConcluida(chkConcluida.isSelected());
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



    private void mensagem(String msg){
        Alert a = new Alert(Alert.AlertType.INFORMATION,msg);
        a.showAndWait();
    }


}
