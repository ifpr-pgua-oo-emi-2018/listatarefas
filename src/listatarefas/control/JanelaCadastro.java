package listatarefas.control;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import listatarefas.NavegadorJanelas;
import listatarefas.model.Agenda;
import listatarefas.model.Tarefa;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JanelaCadastro extends Controlador{

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tfDescricao;

    @FXML
    private DatePicker dpPrazo;

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

        Agenda.getInstance().adicionar(t);


        NavegadorJanelas.loadJanela(NavegadorJanelas.PRINCIPAL);

    }

    @FXML
    private void acaoCancelar(){
        NavegadorJanelas.loadJanela(NavegadorJanelas.PRINCIPAL);
    }


    private boolean verificaHora(String hora){

        Pattern pattern = Pattern.compile("\\d\\d:\\d\\d");
        Matcher m = pattern.matcher(hora);

        return m.find();
    }

}
