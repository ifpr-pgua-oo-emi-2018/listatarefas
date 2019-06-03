package listatarefas.control;

import javafx.scene.control.Alert;


//classe que pode ser extendida para que seja possível
//chamar o método  mensagem
public class Controlador {

    protected void mensagem(String msg){
        Alert a = new Alert(Alert.AlertType.INFORMATION,msg);
        a.showAndWait();
    }
}
