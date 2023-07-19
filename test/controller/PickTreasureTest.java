package controller;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * This class tests the Pick class and its methods.
 */
public class PickTreasureTest {

  Icommand pick = new PickTreasure();

  @Test public void testPlay() {
    assertThrows(IllegalArgumentException.class, () -> pick.playGame(null));
  }

}