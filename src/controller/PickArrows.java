package controller;

import model.IgameState;

/**
 * This class implements the pick command for picking an arrow.
 */
public class PickArrows implements Icommand {

  /**
   * Constructor of the pick arrow class.
   */
  public PickArrows() {

  }

  @Override public void playGame(IgameState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    model.pickArrows();
  }
}
