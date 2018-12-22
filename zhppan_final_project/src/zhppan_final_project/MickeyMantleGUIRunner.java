/**
 * This game was very PANE-FUL to make, haha...
 * Run this one please. :)
 *
 */
package zhppan_final_project;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
/**
 *
 * @author zheny
 */
public class MickeyMantleGUIRunner extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CardGameGUI gui = new CardGameGUI();
        gui.start(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
