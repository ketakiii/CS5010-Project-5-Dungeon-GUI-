package controller;

import static org.junit.Assert.assertTrue;

import model.Direction;
import model.IgameState;
import org.junit.Before;
import org.junit.Test;
import view.Iview;

/**
 * This class is the view controller test class for testing the view controller.
 */
public class ViewControllerTest {

  private IgameState gameState;
  private IgameState gameState2;
  private IviewController controller;
  private IviewController controller2;
  private Iview view;
  private Appendable output;

  /**
   * This method is for setting up and initializing fields.
   */
  @Before
  public void setUp() {
    output = new StringBuilder();
    gameState = new MockModel(output);
    gameState2 = new MockModelFor2Players(output);
    view = new MockView(gameState, output);
    controller = new ViewController(gameState, view);
    controller2 = new ViewController(gameState2, view);
  }

  /**
   * Method for testing if players move.
   */
  @Test public void testMovePlayer() {
    controller.movePlayer(Direction.SOUTH);
    view.refresh();
    assertTrue(output.toString().contains("movePlayer method called"));
    assertTrue(output.toString().contains("Refresh method called"));
  }

  /**
   * Method for testing if treasure is picked up.
   */
  @Test public void testPickUpTreasure() {
    controller.pickTreasure();
    view.refresh();
    assertTrue(output.toString().contains("pickTreasure method called"));
    assertTrue(output.toString().contains("Refresh method called"));
  }

  /**
   * Method for testing if arrows are picked.
   */
  @Test public void testPickArrows() {
    controller.pickArrows();
    view.refresh();
    assertTrue(output.toString().contains("pickArrows method called"));
    assertTrue(output.toString().contains("Refresh method called"));
  }

  /**
   * Method for testing if player can shoot arrows.
   */
  @Test public void testShootPlayer() {
    controller.shootArrows(Direction.SOUTH, 1);
    view.refresh();
    assertTrue(output.toString().contains("shootArrows method called"));
    assertTrue(output.toString().contains("Refresh method called"));
  }

  /**
   * Method for testing if two players move alternatively in their turns.
   */
  @Test public void testMoveTwoPlayers() {
    controller2.movePlayer(Direction.SOUTH);
    view.refresh();
    assertTrue(output.toString().contains("getTurn method called player 1"));
    assertTrue(output.toString().contains("movePlayer method called"));
    assertTrue(output.toString().contains("Refresh method called"));

    controller2.movePlayer(Direction.SOUTH);
    view.refresh();
    assertTrue(output.toString().contains("getTurn method called player 0"));
    assertTrue(output.toString().contains("movePlayer method called"));
    assertTrue(output.toString().contains("Refresh method called"));
  }

  /**
   * Method for testing if two players shoot alternatively in their turns.
   */
  @Test public void testShootTwoPlayers() {
    controller2.shootArrows(Direction.SOUTH, 1);
    view.refresh();
    assertTrue(output.toString().contains("getTurn method called player 1"));
    assertTrue(output.toString().contains("shootArrows method called"));
    assertTrue(output.toString().contains("Refresh method called"));

    controller2.shootArrows(Direction.SOUTH, 1);
    view.refresh();
    assertTrue(output.toString().contains("getTurn method called player 0"));
    assertTrue(output.toString().contains("shootArrows method called"));
    assertTrue(output.toString().contains("Refresh method called"));
  }

}
