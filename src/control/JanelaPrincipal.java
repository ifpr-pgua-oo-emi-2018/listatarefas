package control;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Agenda;
import model.Tarefa;

public class JanelaPrincipal {

    private Agenda agenda = new Agenda();

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private Text txtListaTarefas;


    @FXML
    private TextArea taResultado;

    @FXML
    private CheckBox chkOp1;

    @FXML
    private CheckBox chkOp2;

    @FXML
    private CheckBox chkOp3;


    @FXML
    private ComboBox<String> cbOpcoes;


    @FXML
    private void acaoSalvar(){

        String titulo = tfTitulo.getText();
        String descricao = tfDescricao.getText();

        Tarefa t = new Tarefa(titulo,descricao);

        agenda.adicionar(t);

        txtListaTarefas.setText(agenda.lista());


    }


    @FXML
    private void mostra(){

        taResultado.appendText("\nOpção1"+chkOp1.isSelected());
        taResultado.appendText("\nOpção2"+chkOp2.isSelected());
        taResultado.appendText("\nOpção3"+chkOp3.isSelected());
        taResultado.appendText("\n"+cbOpcoes
                                    .getSelectionModel()
                                    .getSelectedItem());

    }


}
