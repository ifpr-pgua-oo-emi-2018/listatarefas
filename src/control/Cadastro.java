package control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Tarefa;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cadastro {

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextArea tfDescricao;

    @FXML
    private DatePicker dpPrazo;

    @FXML
    private TextField tfHora;


    public Tarefa pegaResultado(){

        String titulo = tfTitulo.getText();
        String descricao = tfDescricao.getText();
        String strHora = tfHora.getText();

        if(!verificaHora(strHora)){
            mensagem("Formato de hora inválido!!");
            return null;
        }


        String[] tokens = strHora.split(":");
        int hora = Integer.valueOf(tokens[0]);
        int minuto = Integer.valueOf(tokens[1]);

        LocalDateTime prazo = LocalDateTime.from(dpPrazo.getValue().atTime(hora,minuto));
        Tarefa t = new Tarefa(titulo,descricao,prazo);

        return t;

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
