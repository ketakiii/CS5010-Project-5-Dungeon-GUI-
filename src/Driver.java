import controller.Controller;
import controller.Icontroller;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

import controller.IviewController;
import controller.ViewController;
import model.GameState;
import model.IgameState;
import view.DungeonPanel;
import view.Iview;
import view.Menu;
import view.View;

/**
 * Main class.
 */

public class Driver {

  /**
   * Main method.
   * @param args String
   */
  public static void main(String[] args) throws IOException {
    Random random = new Random(12);
    GameState gameState = new GameState(9, 10, 20, 30, 4, 4, false, 2, random);
    //Menu menu = new Menu(gameState);
    Iview view = new View(gameState);
    IviewController controller = new ViewController(gameState, view);
//    controller.playGame();

//    Readable scanner = new InputStreamReader(System.in);
//    Appendable output = System.out;
//    Random random = new Random(12);
//    IgameState gameState = new GameState(9, 10, 20, 30, 4, 4, false, random);
//    Icontroller controller = new Controller(scanner, output, gameState);
    controller.playGame();
  }
}

// model initialization
// put values in the model

// create grid view
// call controller and put the grid and model in controller - ViewController

