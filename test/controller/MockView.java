package controller;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.IgameState;
import view.DungeonKeyControl;
import view.DungeonMouse;
import view.Iview;

/**
 * This is a mock view class for testing purposes.
 */
public class MockView extends JFrame implements Iview {

  private IgameState gameState;
  private  Appendable output;

  public MockView(IgameState gameState, Appendable output) {
    this.gameState = gameState;
    this.output = output;
  }

  @Override public void createView(IviewController viewController) {
    try {
      JMenuBar menuBar = new JMenuBar();
      JPanel dungeonPanel = new JPanel();

      this.setJMenuBar(menuBar);
      this.add(dungeonPanel);

      output.append("Create View method called : dungeon panel and menu bar created");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void updateModel(IgameState gameState) {
    this.gameState = gameState;
    try {
      output.append("Update Model method called : Player starting positions "
          + this.gameState.getPlayerStartPosition() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void addClickListener(IviewController viewController) {
    JPanel dungeonPanel = new JPanel();
    DungeonMouse ml = new DungeonMouse(viewController);
    dungeonPanel.addMouseListener(ml);
    try {
      output.append("Add click Listener method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void addKeyListener(IviewController viewController) {
    DungeonKeyControl kl = new DungeonKeyControl(viewController);
    this.addKeyListener(kl);
    this.requestFocus();
    try {
      output.append("Add key listener method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void refresh() {
    try {
      output.append("Refresh method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void makeVisible() {
    try {
      output.append("make Visible method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void setDefaultScreen(IviewController iviewController) {
    try {
      output.append("set defualt screen method called");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public String setGameStatus() {
    return null;
  }

  @Override public void newGame() {

  }
}
