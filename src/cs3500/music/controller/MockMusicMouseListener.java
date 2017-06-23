package cs3500.music.controller;

import cs3500.music.view.IGUIView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mock for Music Mouse Listener.
 */
public class MockMusicMouseListener implements MouseListener {


    private final MusicController controller;
    private IGUIView view;
    private StringBuilder log;

    MockMusicMouseListener(IGUIView view, MusicController controller)
    {
        this.controller = controller;
        this.view = view;
        this.log = new StringBuilder();
        view.addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //normally: view.addNoteAt(e, controller);
        log.append("MouseListener: note added\n");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //not used
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //not used
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //not used
    }


    @Override
    public String toString() {
        return log.toString();
    }
}
