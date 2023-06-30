package view;

import controller.Icommand;
import controller.IviewController;
import controller.Shoot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Direction;
import model.IgameState;

/**
 * This class defines the dungeon key control we require to operate the game.
 */
public class DungeonKeyControl implements KeyListener {
  private IviewController controller;
  private int pressedNumber;
  private IgameState gameState;
  private Icommand shoot;
  private String gameLog;

  /**
   * Constructor of the DungeonKeyControl class.
   * @param controller controller
   */
  public DungeonKeyControl(IviewController controller) {
    this.controller = controller;
    this.pressedNumber = 0;
    this.gameLog = "";

  }

  @Override public void keyTyped(KeyEvent e) {
  }

  @Override public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (pressedNumber == 0) {
          controller.movePlayer(Direction.NORTH);
        } else {
          controller.shootArrows(Direction.NORTH, pressedNumber);
          pressedNumber = 0;
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (pressedNumber == 0) {
          controller.movePlayer(Direction.EAST);
        } else {
          controller.shootArrows(Direction.EAST, pressedNumber);
          pressedNumber = 0;
        }
        break;
      case KeyEvent.VK_LEFT:
        if (pressedNumber == 0) {
          controller.movePlayer(Direction.WEST);
        } else {
          controller.shootArrows(Direction.WEST, pressedNumber);
          pressedNumber = 0;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (pressedNumber == 0) {
          controller.movePlayer(Direction.SOUTH);
        } else {
          controller.shootArrows(Direction.SOUTH, pressedNumber);
          pressedNumber = 0;
        }
        break;
      case KeyEvent.VK_A:
        controller.pickArrows();
        break;
      case KeyEvent.VK_T:
        controller.pickTreasure();
        break;
      case KeyEvent.VK_1:
        pressedNumber = 1;
        break;
      case KeyEvent.VK_2:
        pressedNumber = 2;
        break;
      case KeyEvent.VK_3:
        pressedNumber = 3;
        break;
      case KeyEvent.VK_4:
        pressedNumber = 4;
        break;
      case KeyEvent.VK_5:
        pressedNumber = 5;
        break;
      default:
        break;
    }
    //pressedNumber = 0;
  }

  @Override public void keyReleased(KeyEvent e) {

  }

  public String getGameLog() {
    return this.gameLog;
  }
}
