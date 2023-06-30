package controller;

import java.util.List;
import java.util.Random;
import model.Direction;
import model.GameState;
import model.IgameState;
import model.Ilocation;
import model.LocationType;
import view.Iview;

/**
 * This class defines the ViewController and its methods.
 */
public class ViewController implements IviewController {

  private IgameState gameState;
  private Iview view;

  private int row;
  private int col;
  private int inter;
  private double treasure;
  private int monster;
  private int player;
  private int arrow;
  private boolean isWrapped;
  private String gameStatus;

  /**
   * Constructor for the view controller class.
   * @param gameState game state
   * @param view view
   */
  public ViewController(IgameState gameState, Iview view) {
    if (gameState == null || view == null) {
      throw new IllegalArgumentException("Model or view can not be null");
    }
    this.gameState = gameState;
    this.view = view;
    this.gameStatus = "";
  }

  @Override public void playGame(IgameState gameState) {
    if (gameState == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    this.view.setDefaultScreen(this);
    this.view.makeVisible();
  }

  @Override public void newGame(int row, int col, int inter, double treasure, int monster,
      int player, int arrows, boolean isWrapped, Random random) {
    if (row < 1 || col < 1 || inter < 0 || treasure < 0 || monster < 0 || player < 0
        || random == null) {
      return;
    }
    Random rand = new Random(12);
    this.row = row;
    this.col = col;
    this.inter = inter;
    this.treasure = treasure;
    this.monster = monster;
    this.player = player;
    this.arrow = arrows;
    this.isWrapped = isWrapped;
    if (this.gameState.isGameSetUp()) {
      this.gameState = new GameState(row, col, inter, treasure, monster, arrows, isWrapped, player,
          rand);
      this.view.updateModel(gameState);
      this.view.refresh();
    } else {
      this.gameState = new GameState(row, col, inter, treasure, monster, arrows, isWrapped, player,
          rand);
      this.view.updateModel(this.gameState);
      this.view.createView(this);
      this.view.addClickListener(this);
      this.view.addKeyListener(this);
    }
  }

  @Override public void restartGame() {
    Random random = new Random(12);
    this.gameState = new GameState(row, col, inter, treasure, monster, arrow, isWrapped, player,
        random);
    this.view.updateModel(this.gameState);
    this.clearStatus();
    this.view.refresh();
  }

  @Override public void handleCellClick(int row, int col) {
    if (row < 0 || col < 0) {
      return;
    }
    try {
      this.moveHelper(row, col);
      this.view.refresh();
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Override public void movePlayer(Direction d) {
    if (d == null) {
      return;
    }
    try {
      Icommand move = new Move(d);
      move.playGame(this.gameState);
      if (this.gameState.getPlayer().getHealth() == 0) {
        this.gameStatus = "Chomp, chomp, chomp, the player is eaten by an Otyugh!\n"
            + "Better luck next time Player " + this.gameState.getTurn() + "dies";
      }
      if (this.gameState.playerWins()) {
        this.gameStatus = "Player " + this.gameState.getTurn() + " has Won!!!!!";
      }
      this.view.refresh();
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Override public void pickTreasure() {
    try {
      Icommand pickTreasure = new PickTreasure();
      pickTreasure.playGame(this.gameState);
      this.gameStatus = "Player picks up a treasure here";
      this.view.refresh();
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Override public void pickArrows() {
    try {
      Icommand pickArrow = new PickArrows();
      pickArrow.playGame(this.gameState);
      this.gameStatus = "Player picks up an arrow";
      this.view.refresh();
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Override public void shootArrows(Direction d, int distance) {
    try {
      Icommand shoot = new Shoot(d, distance);
      this.gameState.setMonsterMessage("");
      shoot.playGame(this.gameState);
      if (!"HIT".equals(this.gameState.getMonsterMessage())) {
        this.gameStatus = "Player shoots an arrow into the darkness";
      } else {
        this.gameStatus = "You hear a great howl in the distance";
      }
      this.view.refresh();
    } catch (IllegalArgumentException e) {
      //
    }
  }

  @Override public String gameLog() {
    return this.gameStatus;
  }

  @Override public void clearStatus() {
    this.gameStatus = "";
  }

  private void moveHelper(int row, int col) {
    List<Direction> neighbours = this.gameState.getAvailableStartPosition();
    Ilocation ilocation = this.gameState.getPlayerCurrentPosition();
    if (row == ilocation.getRowNumber() - 1 && col == ilocation.getColumnNumber()) {
      if (neighbours.contains(Direction.NORTH)) {
        this.movePlayer(Direction.NORTH);
      }
    } else if (row == ilocation.getRowNumber() + 1
        && col == ilocation.getColumnNumber()) {
      if (neighbours.contains(Direction.SOUTH)) {
        this.movePlayer(Direction.SOUTH);
      }
    } else if (row == ilocation.getRowNumber() && col == ilocation.getColumnNumber() + 1) {
      if (neighbours.contains(Direction.EAST)) {
        this.movePlayer(Direction.EAST);
      }
    } else if (row == ilocation.getRowNumber() && col == ilocation.getColumnNumber() - 1) {
      if (neighbours.contains(Direction.WEST)) {
        this.movePlayer(Direction.WEST);
      }
    }
  }

  public void resetGame() {
    this.view.newGame();
  }

}
