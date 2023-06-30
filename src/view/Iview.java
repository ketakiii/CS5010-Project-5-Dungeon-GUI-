package view;

import controller.IviewController;
import model.IgameState;

/**
 * This interface defines the view of the dungeon.
 */
public interface Iview {

  /**
   * Creates the view of the dungeon.
   * @param viewController view controller
   */
  public void createView(IviewController viewController);

  /**
   * Updates the model with a new one.
   * @param gameState game state
   */
  public void updateModel(IgameState gameState);

  /**
   * Adds a listener for the clicks.
   * @param viewController view controller
   */
  public void addClickListener(IviewController viewController);

  /**
   * Adds a listener for the keyboard.
   * @param viewController view controller
   */
  public void addKeyListener(IviewController viewController);

  /**
   * Refreshes the view panel.
   */
  public void refresh();

  /**
   * Makes the screen visible.
   */
  public void makeVisible();

  /**
   * Sets to the default screen.
   * @param iviewController view controller
   */
  public void setDefaultScreen(IviewController iviewController);

  /**
   * This method sets the game status.
   * @return string
   */
  public String setGameStatus();

  /**
   * This method helps us create a new game.
   */
  public void newGame();

}
