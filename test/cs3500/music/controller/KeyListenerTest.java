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
public class KeyListenerTest {
    @Test
    public void jumpToEndTest() {
        MusicOperations model = new MusicModel();
        ViewFactory factory = new ViewFactory();
        IGUIView view = (IGUIView) ViewFactory.makeView(model, "gui");
        KeyListener mockListener = new MockMusicKeyListener(view);
        Controller controller = new Controller(view, model, mockListener);

        mockListener.keyPressed(new MockKeyEvent(36));

        assertEquals("Key Listener Log: \n"
            + "Jump to End \n"
            + "Mouse Listener Log: \n", controller.toString());
    }

    @Test
    public void multipleCommandsTest() {
        MusicOperations model = new MusicModel();
        ViewFactory factory = new ViewFactory();
        IGUIView view = (IGUIView) ViewFactory.makeView(model, "gui");
        KeyListener mockListener = new MockMusicKeyListener(view);
        Controller controller = new Controller(view, model, mockListener);

        mockListener.keyPressed(new MockKeyEvent(39));
        mockListener.keyPressed(new MockKeyEvent(35));
        mockListener.keyPressed(new MockKeyEvent(37));

        assertEquals("Key Listener Log: \n"
            + "  Scroll Right\n"
            + "  Jump to Beginning\n"
            + "  Scroll Left\n"
            + "Mouse Listener Log: \n", controller.toString());
    }

    @Test
    public void invalidKeyPressedTest() {
        MusicOperations model = new MusicModel();
        ViewFactory factory = new ViewFactory();
        IGUIView view = (IGUIView) ViewFactory.makeView(model, "gui");
        KeyListener mockListener = new MockMusicKeyListener(view);
        Controller controller = new Controller(view, model, mockListener);

        mockListener.keyPressed(new MockKeyEvent(7));
        mockListener.keyPressed(new MockKeyEvent(275038));
        mockListener.keyPressed(new MockKeyEvent(-2));

        //nothing should be written to log
        assertEquals("Key Listener Log: \n"
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