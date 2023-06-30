package view;

import controller.IviewController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class defines the Dungeon Mouse which tells us how mouse clicks operate the game.
 */
public class DungeonMouse extends MouseAdapter {

  private IviewController listener;

  /**
   * Constructor of the dungeon mouse class.
   * @param controller controller
   */
  public DungeonMouse(IviewController controller) {
    this.listener = controller;
  }

  @Override public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    int row = ((e.getY() - DungeonPanel.offset) / DungeonPanel.cellSize);
    int col = ((e.getX() - DungeonPanel.offset) / DungeonPanel.cellSize);
    this.listener.handleCellClick(row, col);
  }
}
