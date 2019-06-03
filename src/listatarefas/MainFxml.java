package listatarefas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import listatarefas.control.JanelaBase;
import listatarefas.model.Agenda;

import java.io.IOException;

public class MainFxml extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Pane root = loadMainPane();

        stage.setScene(new Scene(root,800,600));

        stage.setTitle("Minha Agenda");
        stage.show();

    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        NavegadorJanelas.BASE
                )
        );

        JanelaBase controller = loader.getController();

        NavegadorJanelas.setControlador(controller);
        NavegadorJanelas.loadJanela(NavegadorJanelas.PRINCIPAL);

        return mainPane;
    }


    @Override
    public void init() throws Exception {
        super.init();

        try{
            Agenda.getInstance().carregaDados();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        try{
            Agenda.getInstance().salvaDados();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
