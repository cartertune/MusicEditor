package cs3500.music.controller;

import cs3500.music.view.IGUIView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to add notes on mouse click.
 */
public class MusicMouseListener implements MouseListener {

  private final MusicController controller;
  private IGUIView view;

  MusicMouseListener(IGUIView view, MusicController controller)
  {
    this.controller = controller;
    this.view = view;
    view.addMouseListener(this);
  }

  /**
   * Invoked when the mouse button has been clicked (pressed
   * and released) on a component.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    //do nothing.
  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   */
  @Override
  public void mousePressed(MouseEvent e) {
    view.addNoteAt(e, controller);
  }

  /**
   * Invoked when a mouse button has been released on a component.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    //do nothing.
  }

  /**
   * Invoked when the mouse enters a component.
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    //do nothing.
  }

  /**
   * Invoked when the mouse exits a component.
   */
  @Override
  public void mouseExited(MouseEvent e) {
    //do nothing.
  }
}
