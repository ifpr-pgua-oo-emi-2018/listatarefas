package control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Agenda;
import model.Tarefa;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JanelaPrincipal {

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

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
    private DatePicker dpPrazo;

    @FXML
    private Text txtPrazo;

    @FXML
    private CheckBox chkConcluida;

    @FXML
    private TextField tfHora;

    @FXML
    private void acaoSalvar(){

        String titulo = tfTitulo.getText();
        String descricao = tfDescricao.getText();
        String strHora = tfHora.getText();

        if(!verificaHora(strHora)){
            mensagem("Formato de hora inválido!!");
            return;
        }


        String[] tokens = strHora.split(":");
        int hora = Integer.valueOf(tokens[0]);
        int minuto = Integer.valueOf(tokens[1]);

        LocalDateTime prazo = LocalDateTime.from(dpPrazo.getValue().atTime(hora,minuto));
        Tarefa t = new Tarefa(titulo,descricao,prazo);

        agenda.adicionar(t);

        atualizaTela();
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

    private boolean verificaHora(String hora){

        Pattern pattern = Pattern.compile("\\d\\d:\\d\\d");
        Matcher m = pattern.matcher(hora);

        return m.find();
    }

    private void mensagem(String msg){
        Alert a = new Alert(Alert.AlertType.INFORMATION,msg);
        a.showAndWait();
    }


}
