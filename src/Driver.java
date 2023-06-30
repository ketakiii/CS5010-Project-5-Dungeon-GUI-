import controller.Controller;
import controller.Icontroller;
import controller.IviewController;
import controller.ViewController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
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
    if (args.length == 0) {
      System.out.println("Please Provide args - gui/text");
      return;
    }
    if ("--gui".equals(args[0])) {
      Random random = new Random(12);
      GameState gameState = new GameState(9, 10, 20, 30, 4, 4, false, 2, random);
      Iview view = new View(gameState);
      IviewController viewcontroller = new ViewController(gameState, view);
      viewcontroller.playGame(gameState);
    } else if ("--text".equals(args[0])) {
      Readable scanner = new InputStreamReader(System.in);
      Appendable output = System.out;
      Random random1 = new Random(12);
      IgameState gameState1 = new GameState(9, 10, 20, 30, 4, 4, false, random1);
      Icontroller controller = new Controller(scanner, output, gameState1);
      controller.playGame();
    } else {
      throw new IllegalArgumentException("Please specify a valid argument");
    }

  }
}



