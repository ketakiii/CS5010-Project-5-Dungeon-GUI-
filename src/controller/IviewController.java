package controller;

import java.util.Random;
import model.Direction;
import model.IgameState;

/**
 * This interface defines the view controller.
 */
public interface IviewController {

  /**
   * The method used for playing the game.
   * @param gameState model
   */
  public void playGame(IgameState gameState);

  /**
   * Method for creating a new game.
   * @param row row
   * @param col column
   * @param inter interconnectivity
   * @param treasure percentage of treasure
   * @param monster monsters
   * @param player players
   * @param arrows arrows
   * @param isWrapped is the dungeon wrapped
   * @param random random
   */
  public void newGame(int row, int col, int inter, double treasure, int monster,
      int player, int arrows, boolean isWrapped, Random random);

  /**
   * Method for restarting the game.
   */
  public void restartGame();

  /**
   * Method for handling a cell click.
   * @param row row
   * @param col column
   */
  public void handleCellClick(int row, int col);

  /**
   * Method for moving the player.
   * @param d direction
   */
  public void movePlayer(Direction d);

  /**
   * Method for picking up the treasure.
   */
  public void pickTreasure();

  /**
   * Method for picking up arrows.
   */
  public void pickArrows();

  /**
   * Method for shooting arrows.
   * @param d direction
   * @param distance distance
   */
  public void shootArrows(Direction d, int distance);

  /**
   * For displaying the current status of the game.
   * @return string
   */
  public String gameLog();

  /**
   * For clearing the screen after every status change.
   */
  public void clearStatus();

  /**
   * This method helps in resetting the game.
   */
  public void resetGame();

}
