package listatarefas;

import listatarefas.control.JanelaBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Classe utilitaria para controlar a troca de janelas.
 *
 * Todos os métodos são estáticos para facilitar o acesso
 * em qualquer lugar da aplicação.
 */
public class NavegadorJanelas {

    /**
     * Aqui são declarados os fxml de cada janela
     */

    public static final String BASE    = "view/base.fxml";
    public static final String PRINCIPAL    = "view/principal.fxml";
    public static final String JANELA_CADASTRO = "view/cadastro.fxml";

    /** Aqui é declarado o controlador da janela principa. */
    private static JanelaBase controlador;

    public static void setControlador(JanelaBase controlador) {
        NavegadorJanelas.controlador = controlador;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadJanela(String fxml) {
        try {
            controlador.setJanela(
                    (Node) FXMLLoader.load(
                            NavegadorJanelas.class.getResource(
                                    fxml
                            )
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}