package view;

import controller.IviewController;
import controller.ViewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import model.GameState;
import model.IgameState;


/**
 * This class defines the view of the dungeon.
 */
public class View extends JFrame implements Iview {

  private Imenu menu;
  private DungeonPanel dungeonPanel;
  private JLabel title;
  private IgameState gameState;
  private IviewController controller;
  private JScrollPane dungeonScrollPane;
  private DungeonKeyControl dungeonKeyControl;

  /**
   * Constructor of the view class.
   * @param gameState model
   */
  public View(IgameState gameState) {
    super("Dungeon Game");
    if (gameState == null) {
      throw new IllegalArgumentException("Game state can not be null");
    }
    this.gameState = gameState;
    this.controller = null;
    this.menu = new Menu();
    this.dungeonPanel = null;
    this.title = null;

    this.setSize(700, 1000);
    this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,
        Toolkit.getDefaultToolkit().getScreenSize().height));
    this.setLocation(0, 0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override public void createView(IviewController viewController) {
    if (viewController == null) {
      throw new IllegalArgumentException("Controller is null");
    }
    this.controller = viewController;
    this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    this.getContentPane().setBackground(Color.WHITE);

    this.dungeonPanel = new DungeonPanel(gameState, controller);
    this.dungeonPanel.setPreferredSize(new Dimension(700, 1000));
    //this.dungeonPanel.setLayout(new BorderLayout());

    this.dungeonScrollPane = new JScrollPane(dungeonPanel,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.dungeonScrollPane.setPreferredSize(new Dimension(700, 900));

    this.add(dungeonScrollPane);
    this.setVisible(true);
  }

  @Override public void updateModel(IgameState gameState) {
    this.gameState = gameState;
  }

  @Override public void addClickListener(IviewController viewController) {
    DungeonMouse dungeonMouse = new DungeonMouse(viewController);
    this.dungeonPanel.addMouseListener(dungeonMouse);
  }

  @Override public void addKeyListener(IviewController viewController) {
    this.dungeonKeyControl = new DungeonKeyControl(viewController);
    this.addKeyListener(dungeonKeyControl);
    this.requestFocus();
  }

  @Override public void refresh() {
    this.dungeonPanel.updateBoard(gameState);
    this.repaint();
  }

  @Override public void makeVisible() {
    this.setVisible(true);
  }

  @Override public void setDefaultScreen(IviewController iviewController) {

    JComboBox<Integer> row = new JComboBox<>();
    JComboBox<Integer> col = new JComboBox<>();
    JComboBox<Integer> inter = new JComboBox<>();
    JComboBox<Double> treasure = new JComboBox<>();
    JComboBox<Integer> monster = new JComboBox<>();
    JComboBox<Integer> player = new JComboBox<>();
    JComboBox<Integer> arrow = new JComboBox<>();

    for (int i = 8; i <= 100; i++) {
      row.addItem(i);
      col.addItem(i);
    }
    for (int i = 1; i <= 100; i++) {
      treasure.addItem((double) i);
      monster.addItem(i);
      arrow.addItem(i);
    }
    for (int i = 1; i <= 2; i++) {
      player.addItem(i);
    }
    for (int i = 0; i <= 100000; i++) {
      inter.addItem(i);
    }

    JPanel panel = new JPanel(new GridLayout(9, 2, 3, 15));
    panel.setSize(500, 30);
    setLayout(new BorderLayout(10, 10));

    panel.add(new JLabel("Row "));
    panel.add(row);
    panel.add(Box.createVerticalStrut(15));

    panel.add(new JLabel("Column "));
    panel.add(col);
    panel.add(Box.createVerticalStrut(15));

    panel.add(new JLabel("Interconnectivity "));
    panel.add(inter);
    panel.add(Box.createVerticalStrut(15));

    panel.add(new JLabel("Treasure % "));
    panel.add(treasure);
    panel.add(Box.createVerticalStrut(15));

    panel.add(new JLabel("Monsters "));
    panel.add(monster);
    panel.add(Box.createVerticalStrut(15));

    panel.add(new JLabel("Players "));
    panel.add(player);
    panel.add(Box.createVerticalStrut(15));

    panel.add(new JLabel("Arrows "));
    panel.add(arrow);
    panel.add(Box.createVerticalStrut(15));

    JRadioButton isWrapped = new JRadioButton();
    panel.add(new JLabel("Is Wrappped "));
    panel.add(isWrapped);
    panel.add(Box.createVerticalStrut(15));

    int textBox = JOptionPane.showConfirmDialog(null, panel,
        "Enter game details", JOptionPane.OK_CANCEL_OPTION);
    if (textBox == JOptionPane.OK_OPTION) {
      int rowInt = (int) row.getSelectedItem();
      int colInt = (int) col.getSelectedItem();
      int interInt = (int) inter.getSelectedItem();
      double treasureInt = (double) treasure.getSelectedItem();
      int monsterInt = (int) monster.getSelectedItem();
      int playerInt = (int) player.getSelectedItem();
      int arrowInt = (int) arrow.getSelectedItem();
      boolean isWrappedbool = isWrapped.isSelected();
      Random random = new Random(12);

      iviewController.newGame(rowInt, colInt, interInt, treasureInt, monsterInt,
          playerInt, arrowInt, isWrappedbool, random);

    } else {
      System.exit(0);
    }
  }

  @Override public String setGameStatus() {
    return this.dungeonKeyControl.getGameLog();
  }

  @Override public void newGame() {
    this.dispose();
    Random random = new Random(12);
    GameState gameState = new GameState(9, 10, 20, 30, 4, 4, false, 2, random);
    Iview view = new View(gameState);
    IviewController controller = new ViewController(gameState, view);
    controller.playGame(gameState);
  }
}


