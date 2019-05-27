package aulalayouts;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Agenda;
import model.Tarefa;

public class MainJavaFx extends Application {

    private Agenda agenda;



    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage) {

        agenda = new Agenda();

        Text txtTitulo = new Text("Título:");
        TextField tfTitulo = new TextField();

        Text txtDescricao = new Text("Descricao:");
        TextField tfDescricao = new TextField();

        Button btSalvar = new Button("Salvar");

        Text txtTarefas = new Text("");

        btSalvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String titulo = tfTitulo.getText();
                String descricao = tfDescricao.getText();

                Tarefa tarefa = new Tarefa(titulo,descricao,null);

                agenda.adicionar(tarefa);

                txtTarefas.setText(agenda.lista());

                tfTitulo.clear();
                tfDescricao.clear();


            }
        });


        VBox vBox = new VBox();

        vBox.getChildren().add(txtTitulo);
        vBox.getChildren().add(tfTitulo);
        vBox.getChildren().add(txtDescricao);
        vBox.getChildren().add(tfDescricao);
        vBox.getChildren().add(btSalvar);
        vBox.getChildren().add(txtTarefas);


        Scene scene = new Scene(vBox,200,200);

        stage.setScene(scene);
        stage.setTitle("Minha Janela");
        stage.setX(100);
        stage.setY(200);

        stage.show();



    }
}
