package view;

import controller.IviewController;
import model.IgameState;


/**
 * This interface defines the menu of the dungeon.
 */
public interface Imenu {

  /**
   * This method sets the features of the view shown.
   * @param viewController view controller
   */
  public void setFeatures(IviewController viewController);

}
