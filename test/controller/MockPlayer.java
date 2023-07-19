package controller;

import java.util.Map;
import model.Ilocation;
import model.Iplayer;
import model.Itreasure;
import model.SmellType;
import model.TreasureType;

/**
 * This is a mock player class for testing purposes.
 */
public class MockPlayer implements Iplayer {
  @Override public String getName() {
    return null;
  }

  @Override public void addTreasures(Itreasure treasure) {

  }

  @Override public Map<TreasureType, Integer> getTreasureBag() {
    return null;
  }

  @Override public Ilocation getCurrentLocation() {
    return null;
  }

  @Override public void setCurrentLocation(Ilocation location) {

  }

  @Override public String toStringStatus() {
    return null;
  }

  @Override public int getHealth() {
    return 1;
  }

  @Override public void reduceHealth() {

  }

  @Override public boolean isAlive() {
    return false;
  }

  @Override public void addArrows(int n) {

  }

  @Override public void addTreasure() {

  }

  @Override public int arrowCount() {
    return 0;
  }

  @Override public void setSmell(SmellType smell) {

  }

  @Override public SmellType getSmell() {
    return null;
  }
}
