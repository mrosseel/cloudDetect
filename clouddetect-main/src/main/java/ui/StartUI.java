package ui;

import java.awt.Frame;

/**
 * Just starts the reflectionbus and sets the main frame visible. Didn't want to
 * clutter the main frame with this code.
 */
public class StartUI extends Frame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public boolean start() {
        CloudWatchFrame frame = new CloudWatchFrame();
        frame.pack();
        frame.setVisible(true);

        return true;
    }

}
