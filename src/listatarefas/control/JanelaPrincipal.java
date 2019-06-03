package listatarefas.control;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Callback;
import listatarefas.NavegadorJanelas;
import listatarefas.model.Agenda;
import listatarefas.model.Tarefa;


import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class JanelaPrincipal extends Controlador {

    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");


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


    private ObservableList<Tarefa> listaTarefas;




    //o método initialize é executado sempre quando a janela é carregada
    //utilizado para inicializar os componentes 
    @FXML
    public void initialize(){


        //utilizado para mostrar em que momento o método initialize é chamado
        System.out.println("[Principal] Initialize...");


        //pegamos uma referência para a lista de tarefas, para que seja possível
        //utilizarmos aqui
        listaTarefas = Agenda.getInstance().getLista();


        txtQtdeTarefas.setText("Total de Tarefas:"+listaTarefas.size());



        //Uma ObservableList emite sinais quando ocorrem modificações(inclusão, remoção)
        //podemos escutar estes sinais e reagir a eles conforme o necessário.
        //Aqui, toda vez que a lista for modificada, iremos atualizar o texto da quantidade
        //de tarefas
        listaTarefas.addListener(new ListChangeListener<Tarefa>() {
            @Override
            public void onChanged(Change<? extends Tarefa> change) {
                txtQtdeTarefas.setText("Total de Tarefas:"+listaTarefas.size());
            }
        });


        //o listwiew recebe uma ObservableList e a qualquer mudança na lista atualiza o conteúdo
        //automáticamente
        ltvTarefas.setItems(listaTarefas);


        //podemos configurar como um item da lista irá aparecer. Podemos mudar a cor da letra, o que será escrito
        ltvTarefas.setCellFactory(new Callback<ListView<Tarefa>, ListCell<Tarefa>>() {
            @Override
            public ListCell<Tarefa> call(ListView<Tarefa> tarefaListView) {
                return new ListCell<Tarefa>(){
                    @Override
                    protected void updateItem(Tarefa tarefa, boolean vazio) {
                        super.updateItem(tarefa, vazio);

                        if(vazio){
                            setText(null);
                        }else{

                            setText(tarefa.getTitulo());

                            if(tarefa.isConcluida()) {
                                setTextFill(Color.RED);
                            }else{
                                setTextFill(Color.BLACK);
                            }

                        }


                    }
                };
            }
        });

    }


    @FXML
    private void acaoClick(MouseEvent e){
        Tarefa t = ltvTarefas.getSelectionModel().getSelectedItem();
        if (t != null) {
            if (e.getClickCount() == 1) {

                taVerDescricao.setText(t.getDescricao());

                chkConcluida.setSelected(t.isConcluida());
                chkConcluida.setDisable(false);

                txtPrazo.setText(df.format(t.getPrazo()));
            } else if (e.getClickCount() == 2) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover a tarefa " + t.getTitulo() + "?", ButtonType.OK, ButtonType.CANCEL);

                Optional<ButtonType> resultado = alert.showAndWait();

                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Agenda.getInstance().remove(t);
                }
            }
        }
    }


    @FXML
    private void acaoConcluida(){
        Tarefa t = ltvTarefas.getSelectionModel().getSelectedItem();
        if(t!=null){
            t.setConcluida(chkConcluida.isSelected());
            ltvTarefas.refresh();
        }
    }

    @FXML
    private void sair(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Você deseja realmente sair?");

        Optional<ButtonType> resultado = alert.showAndWait();

        if(resultado.isPresent() && resultado.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    @FXML
    private void adicionar(){
        NavegadorJanelas.loadJanela(NavegadorJanelas.JANELA_CADASTRO);
    }


}
