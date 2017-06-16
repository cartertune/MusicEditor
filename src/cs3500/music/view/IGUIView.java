package cs3500.music.view;

import java.awt.event.KeyListener;

/**
 * Created by cartertune on 6/16/17.
 */
public interface IGUIView extends ViewInterface {


  void moveRight();

  void moveLeft();

  void addKeyListener(KeyListener kl);

}
