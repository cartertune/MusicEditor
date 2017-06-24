package cs3500.music.controller;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import cs3500.music.view.IGUIView;
import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewInterface;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.Assert.*;

/**
 * Tests for controller.
 */
public class ControllerTest {
    @Test
    public void jumpToEndTest() {
        MusicOperations model = new MusicModel();
        ViewFactory factory = new ViewFactory();
        IGUIView view = (IGUIView) factory.makeView(model, "gui");
    
        KeyListener mockListener = new MockMusicKeyListener(view);
        Controller controller = new Controller(view, model, mockListener);

        controller.addNote(4, 3,"C");


        mockListener.keyPressed(new MockKeyEvent(36));

        assertEquals("Key Listener Log: \n"
            + "Jump to End \n"
            + "Mouse Listener Log: \n", controller.toString());
    }


    /**
     * For convenient construction of Key Events in tests.
     *
     */
    private static class MockKeyEvent extends KeyEvent{
        public MockKeyEvent(int keyCode) {
            super(new Canvas(), 0 , 0, 0, keyCode);
        }
    }

}