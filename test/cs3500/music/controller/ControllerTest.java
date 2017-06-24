package cs3500.music.controller;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;
import cs3500.music.view.ViewFactory;

import java.awt.Component;
import org.junit.Test;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



/**
 * Tests for controller.
 */
public class ControllerTest {

  @Test
  public void mockTest() {
    MusicOperations model = new MusicModel();
    ViewFactory factory = new ViewFactory();
    IGUIView view = (IGUIView) factory.makeView(model, "gui");

    KeyListener mockListener = new MockMusicKeyListener(view);
    Controller controller = new Controller(view, model, mockListener);

    controller.addNote(4, 3, "C");

    //mockListener.keyPressed(new KeyEvent());

    System.out.print(controller.toString());

    //assertEquals();
  }


  /**
   * For constructor convenience
   */
  private static class MockKeyEvent extends KeyEvent {

    public MockKeyEvent(Component source, int id, long when, int modifiers, int keyCode,
        char keyChar, int keyLocation) {
      super(source, id, when, modifiers, keyCode, keyChar, keyLocation);
    }

//        public MockKeyEvent(int id) {
//            super(new Component(), 0 , 0, 0, keyCode);
//        }
  }

}