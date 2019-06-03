package listatarefas.control;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;


//classe base utilizada para a navegação de janelas
//não precisa mexer nada aqui...
public class JanelaBase {

    @FXML
    private StackPane painelBase;

    public void setJanela(Node node){
        painelBase.getChildren().setAll(node);
    }

}