import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFxml extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                                  .getResource("view/principal.fxml"));

        stage.setScene(new Scene(root,800,600));

        stage.setTitle("Minha Agenda");
        stage.show();

    }
}
