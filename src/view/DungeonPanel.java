package view;

import controller.IviewController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import model.Direction;
import model.IgameState;
import model.Ilocation;
import model.Iplayer;
import model.LocationType;
import model.SmellType;
import model.TreasureType;

/**
 * This class defines the dungeon panel of the game.
 */
public class DungeonPanel extends JPanel implements ActionListener {

  public static final int cellSize = 80;
  public static final int offset = 40;
  private IgameState gameState;
  private IviewController controller;
  private JMenuItem newGame;
  private JMenuItem restart;
  private JMenuItem exit;
  private JMenu help;
  private JLabel gameStatus;
  private JLabel gameStatus2;

  /**
   * This is the constructor of the dungeon panel class.
   * @param gameState game state
   * @param controller view controller
   */
  public DungeonPanel(IgameState gameState, IviewController controller) {
    this.controller = controller;
    this.gameState = gameState;
    this.setBackground(Color.WHITE);

    exit = new JMenuItem("Quit");
    this.exit.setName("Quit");
    this.exit.addActionListener(this);
    this.add(exit);

    newGame = new JMenuItem("New Game");
    this.newGame.setName("newGame");
    this.newGame.addActionListener(this);
    this.add(newGame);

    restart = new JMenuItem("Restart");
    this.restart.setName("Restart");
    this.restart.addActionListener(this);
    this.add(restart);

    this.gameStatus = new JLabel("              Test");
    this.add(gameStatus);

    this.gameStatus2 = new JLabel("              ");
    this.add(gameStatus2);

    this.setOpaque(false);
    this.setVisible(true);
  }

  /**
   * Resets the board - refresh.
   * @param gameState read only game state
   */
  public void updateBoard(IgameState gameState) {
    if (gameState == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.gameState = gameState;
    this.repaint();
  }

  @Override public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    Ilocation[][] board = gameState.getDungeon();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        Ilocation location = board[i][j];
        BufferedImage image = null;
        try {
          if (location.getLocationType() == LocationType.CAVE) {
            image = drawCave(location);
          } else {
            image = drawTunnel(location);
          }
          if (location.getIsVisited()) {
            g2D.drawImage(image, j * cellSize + offset, i * cellSize
                    + offset, cellSize, cellSize,
                null);
            drawTreasures(location, g2D);
            drawArrows(location, g2D);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    try {
      drawPlayer(g2D);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String output = "Player : " + this.gameState.getTurn() + "'s Turn      ";
    this.gameStatus.setText(output);
    this.gameStatus2.setText("Game Status : " + this.controller.gameLog());
  }

  private void drawPlayer(Graphics2D g2D) throws IOException {
    BufferedImage image = null;
    try {
      Iplayer[] players = this.gameState.getPlayers();
      for (Iplayer player : players) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Players/player-original.png")));
        if (!player.getCurrentLocation().hasMonster()) {
          drawSmell(player, g2D);
        }
        g2D.drawImage(image, player.getCurrentLocation().getColumnNumber() * cellSize
                + (int) (cellSize * 0.85), player.getCurrentLocation().getRowNumber()
                * cellSize + (int) (cellSize * 0.85),
            cellSize / 4, cellSize / 3, null);
        drawMonsters(player.getCurrentLocation(), g2D);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private BufferedImage drawCave(Ilocation location) throws IOException {
    Map<Direction, Ilocation> neighbours = location.getNeighbours();
    BufferedImage image = null;
    switch (neighbours.size()) {
      case 1:
        if (neighbours.containsKey(Direction.NORTH)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/one/N.png")));
        } else if (neighbours.containsKey(Direction.SOUTH)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/one/S.png")));
        } else if (neighbours.containsKey(Direction.EAST)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/one/E.png")));
        } else if (neighbours.containsKey(Direction.WEST)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/one/W.png")));
        }
        break;

      case 3:
        if (neighbours.containsKey(Direction.NORTH) && neighbours.containsKey(Direction.EAST)
            && neighbours.containsKey(Direction.WEST)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/three/NEW.png")));
        } else if (neighbours.containsKey(Direction.NORTH)
            && neighbours.containsKey(Direction.SOUTH) && neighbours.containsKey(Direction.EAST)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/three/NSE.png")));
        } else if (neighbours.containsKey(Direction.NORTH)
            && neighbours.containsKey(Direction.SOUTH) && neighbours.containsKey(Direction.WEST)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/three/NSW.png")));
        } else if (neighbours.containsKey(Direction.SOUTH)
            && neighbours.containsKey(Direction.WEST) && neighbours.containsKey(Direction.EAST)) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/three/SEW.png")));
        }
        break;

      case 4:
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Caves/four/NSEW.png")));
        break;
      default:
    }
    return image;
  }

  private BufferedImage drawTunnel(Ilocation location) throws IOException {
    Map<Direction, Ilocation> neighbours = location.getNeighbours();
    BufferedImage image = null;
    try {
      if (neighbours.containsKey(Direction.EAST) && neighbours.containsKey(Direction.WEST)) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Tunnels/EW.png")));
      } else if (neighbours.containsKey(Direction.NORTH)
          && neighbours.containsKey(Direction.EAST)) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Tunnels/NE.png")));
      } else if (neighbours.containsKey(Direction.NORTH)
          && neighbours.containsKey(Direction.SOUTH)) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Tunnels/NS.png")));
      } else if (neighbours.containsKey(Direction.NORTH)
          && neighbours.containsKey(Direction.WEST)) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Tunnels/NW.png")));
      } else if (neighbours.containsKey(Direction.SOUTH)
          && neighbours.containsKey(Direction.EAST)) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Tunnels/SE.png")));

      } else if (neighbours.containsKey(Direction.SOUTH)
          && neighbours.containsKey(Direction.WEST)) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Tunnels/SW.png")));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }

  private void drawTreasures(Ilocation location, Graphics2D g2D) throws IOException {
    BufferedImage image = null;
    try {
      if (location.getTreasure() != null) {
        if (location.getTreasure().getTreasureType() == TreasureType.DIAMONDS) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Treasures/diamond.png")));
        } else if (location.getTreasure().getTreasureType() == TreasureType.RUBIES) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Treasures/ruby.png")));
        } else if (location.getTreasure().getTreasureType() == TreasureType.SAPPHIRES) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Treasures/sapphire.png")));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(image, location.getColumnNumber() * cellSize + (cellSize),
        location.getRowNumber() * cellSize + (cellSize),
        cellSize / 4, cellSize / 4, null);
  }

  private void drawArrows(Ilocation location, Graphics2D g2D) throws IOException {
    BufferedImage image = null;
    try {
      if (location.getArrows() != 0) {
        if (location.getArrows() == 1) {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Arrows/arrow.png")));
        } else {
          image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Arrows/arrow.png")));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(image, location.getColumnNumber() * cellSize + (int) (cellSize * 0.45),
        location.getRowNumber() * cellSize + (int) (cellSize * 0.45),
        cellSize, cellSize, null);
  }

  private void drawMonsters(Ilocation location, Graphics2D g2D) throws IOException {
    BufferedImage image = null;
    try {
      if (location.hasMonster()) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Otyugh/wumpus.png")));
        g2D.drawImage(image, location.getColumnNumber() * cellSize + (int) (cellSize * 0.85),
            location.getRowNumber() * cellSize + (int) (cellSize * 0.85),
            cellSize / 3, cellSize / 3, null);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void drawSmell(Iplayer player, Graphics2D g2D) throws IOException {
    BufferedImage image = null;
    try {
      if (player.getSmell() == SmellType.MOREPUNGENT) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Smell/more.jpeg")));
      } else if (player.getSmell() == SmellType.LESSPUNGENT) {
        image = ImageIO.read(Objects.requireNonNull(
            getClass().getResourceAsStream("/images/Smell/less.png")));
      }
      g2D.drawImage(image, player.getCurrentLocation().getColumnNumber() * (cellSize)
          + (int) (cellSize * 0.65), player.getCurrentLocation().getRowNumber() * cellSize
              + cellSize, cellSize / 2, cellSize / 2, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Component) {
      Component c = (Component) e.getSource();
      if (c.getName().equals("Quit")) {
        System.exit(0);
      }
      if (c.getName().equals("newGame")) {
        this.controller.resetGame();
      }
      if (c.getName().equals("Restart")) {
        controller.restartGame();
      }
      if (c.getName().equals("Help")) {
        // check
      }
    }
  }


  private String helperTreasure(TreasureType input) {
    Map<TreasureType, String> mapTreasures = new HashMap<>();
    mapTreasures.put(TreasureType.DIAMONDS, "diamond");
    mapTreasures.put(TreasureType.RUBIES, "ruby");
    mapTreasures.put(TreasureType.SAPPHIRES, "sapphire");
    return mapTreasures.get(input);
  }
}
