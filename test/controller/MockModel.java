package controller;

import java.io.IOException;
import java.util.List;
import model.Direction;
import model.IgameState;
import model.Ilocation;
import model.Iplayer;
import model.SmellType;

/**
 * This class is the mock model for testing purposes.
 */
public class MockModel implements IgameState {

  private Appendable output;

  public MockModel(Appendable output) {
    this.output = output;
  }

  @Override public Iplayer getPlayer() {
    try {
      output.append("getPlayer method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new MockPlayer();
  }

  @Override public boolean isGameOver() {
    try {
      output.append("isGameOver method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override public List<Direction> getAvailableStartPosition() {
    try {
      output.append("getAvailableStartPosition method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Ilocation getPlayerStartPosition() {
    try {
      output.append("getPlayerStartPosition method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Ilocation getPlayerEndPosition() {
    try {
      output.append("getPlayerEndPosition method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Ilocation getPlayerCurrentPosition() {
    try {
      output.append("getPlayerCurrentPosition method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Ilocation movePlayer(Direction direction) {
    try {
      output.append("movePlayer method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Ilocation[][] getDungeon() {
    try {
      output.append("getDungeon method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Ilocation[0][];
  }

  @Override public String toStringStatus() {
    try {
      output.append("toStringStatus method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Direction helperDirection(String input) {
    try {
      output.append("helperDirection method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Ilocation shootArrows(Direction direction, int n, Ilocation currentLocation) {
    try {
      output.append("shootArrows method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void pickTreasure() {
    try {
      output.append("pickTreasure method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void pickArrows() {
    try {
      output.append("pickArrows method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public boolean playerWins() {
    try {
      output.append("playerWins method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override public SmellType getSmellType() {
    try {
      output.append("getSmellType method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public boolean updatePlayerHealth() {
    try {
      output.append("updatePlayerHealth method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean updateMonsterHealth() {
    try {
      output.append("updateMonsterHealth method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override public List<Ilocation> getPlayerLocations() {
    try {
      output.append("getPlayerLocations method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public boolean isGameSetUp() {
    try {
      output.append("isGameSetUp method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override public int getTurn() {
    try {
      output.append("getTurn method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override public int getLastPlayerTurn() {
    try {
      output.append("getLastPlayerTurn method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override public Iplayer[] getPlayers() {
    try {
      output.append("getPlayers method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Iplayer[0];
  }

  @Override public String getMonsterMessage() {
    return null;
  }

  @Override public void setMonsterMessage(String s) {

  }

}
