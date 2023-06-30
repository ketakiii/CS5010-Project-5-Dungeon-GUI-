package controller;

import model.IgameState;

/**
 * This class implements the pick command for picking a treasure.
 */
public class PickTreasure implements Icommand {

  /**
   * Constructor of the pick treasure class.
   */
  public PickTreasure() {
  }

  @Override public void playGame(IgameState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }
    model.pickTreasure();
  }
}
