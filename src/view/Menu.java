package view;

import controller.IviewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


/**
 * This class defines the menu that takes in the game state details to design it accordingly.
 */
public class Menu extends JFrame implements Imenu {

  private JMenuItem newGame;
  private JMenuItem restart;
  private JMenuItem exit;

  /**
   * This is the constructor for the Menu class.
   */
  public Menu() {
    super("Dungeon Game");
    this.setBackground(Color.WHITE);

    JMenu start = new JMenu("File");
    this.add(start);
    newGame = new JMenuItem("New Game");
    start.add(newGame);
    restart = new JMenuItem("Restart");
    start.add(restart);

    JMenu file = new JMenu("Quit");
    this.add(file);
    exit = new JMenuItem("Quit Game");
    file.add(exit);

    JMenu help = new JMenu("Help");
    this.add(help);
    JMenuItem move = new JMenuItem("For moving: (Arrow keys)");
    JMenuItem pickup = new JMenuItem("For picking up ");
    help.add(move);
    help.add(pickup);

  }

  @Override public void setFeatures(IviewController viewController) {

    newGame.addActionListener(e -> {
      JMenu start = new JMenu("File");
      this.add(start);
      newGame = new JMenuItem("New Game");
      start.add(newGame);
      restart = new JMenuItem("Restart");
      start.add(restart);

      JMenu file = new JMenu("Quit");
      this.add(file);
      exit = new JMenuItem("Quit Game");
      file.add(exit);

      JMenu help = new JMenu("Help");
      this.add(help);
      JMenuItem move = new JMenuItem("For moving: (Arrow keys)");
      JMenuItem pickup = new JMenuItem("For picking up ");
      help.add(move);
      help.add(pickup);

      this.setSize(250, 400);
      JComboBox<Integer> row = new JComboBox<>();
      JComboBox<Integer> col = new JComboBox<>();
      JComboBox<Integer> inter = new JComboBox<>();
      JComboBox<Double> treasure = new JComboBox<>();
      JComboBox<Integer> monster = new JComboBox<>();
      JComboBox<Integer> arrow = new JComboBox<>();

      JPanel panel = new JPanel();
      panel.setSize(500, 30);
      setLayout(new BorderLayout(10, 10));

      for (int i = 7; i <= 100; i++) {
        row.addItem(i);
        col.addItem(i);
      }
      for (int i = 1; i <= 100; i++) {
        treasure.addItem((double) i);
        monster.addItem(i);
        arrow.addItem(i);
      }
      JComboBox<Integer> player = new JComboBox<>();
      for (int i = 1; i <= 2; i++) {
        player.addItem(i);
      }
      for (int i = 0; i <= 100000; i++) {
        inter.addItem(i);
      }

      row.setSize(new Dimension(50, 30));
      col.setSize(new Dimension(100, 30));
      inter.setSize(new Dimension(100, 30));
      treasure.setSize(new Dimension(100, 30));
      monster.setSize(new Dimension(100, 30));
      //      player.setSize(new Dimension(100, 30));
      arrow.setSize(new Dimension(100, 30));


      panel.add(new JLabel("Row "));
      panel.add(row);
      panel.add(Box.createVerticalStrut(100));

      panel.add(new JLabel("Column "));
      panel.add(col);
      panel.add(Box.createVerticalStrut(100));

      panel.add(new JLabel("Interconnectivity "));
      panel.add(inter);
      panel.add(Box.createVerticalStrut(100));

      panel.add(new JLabel("Treasure % "));
      panel.add(treasure);
      panel.add(Box.createVerticalStrut(100));

      panel.add(new JLabel("Monsters "));
      panel.add(monster);
      //panel.add(Box.createVerticalStrut(15));

      panel.add(new JLabel("Players "));
      panel.add(player);
      //panel.add(Box.createVerticalStrut(15));

      panel.add(new JLabel("Arrows "));
      panel.add(arrow);
      //panel.add(Box.createVerticalStrut(15));

      JRadioButton isWrapped = new JRadioButton();
      panel.add(new JLabel("Is Wrappped "));
      panel.add(isWrapped);
      //panel.add(Box.createVerticalStrut(15));

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

        this.setBackground(Color.WHITE);

      }
    });

    restart.addActionListener(e -> viewController.restartGame());
    exit.addActionListener(e -> System.exit(0));

  }
}
