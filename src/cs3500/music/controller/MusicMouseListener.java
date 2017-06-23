package cs3500.music.controller;

import cs3500.music.view.IGUIView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Class used to add notes on mouse press to given view and to the model represented in given
 * controller.
 */
class MusicMouseListener implements MouseListener {

  private final MusicController controller;
  private IGUIView view;

  /**
   * Creates this MusicMouseListener and adds it to given view.
   *
   * @param view the view to add listener to.
   * @param controller the controller the view should send the data back to.
   */
  MusicMouseListener(IGUIView view, MusicController controller) {
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
