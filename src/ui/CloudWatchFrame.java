package ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


/*
 * Created on 20-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * DOCUMENT ME!
 *
 * @author mikers To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 *         Generation&gt;Code and Comments
 */
public class CloudWatchFrame extends JFrame {
    //~ Instance fields ************************************************************************************************

    /** ? */
    private ui.ControlPanel controlPanel = null;

    /** ? */
    private JFrame thisFrame;

    /** ? */
    private javax.swing.JMenu jMenu = null;

    /** ? */
    private javax.swing.JMenuBar jJMenuBar1 = null;

    /** ? */
    private javax.swing.JMenuItem jMenuItem = null;

    /** ? */
    private javax.swing.JMenuItem jMenuItem1 = null;

    /** ? */
    private javax.swing.JMenuItem jMenuItem2 = null;

    /** ? */
    private javax.swing.JMenuItem jMenuItem4 = null;

    /** ? */
    private javax.swing.JPanel jPanel1 = null;

    /** ? */
    private javax.swing.JSeparator jSeparator = null;

    /** ? */
    private javax.swing.JSplitPane jSplitPane = null;

    /** ? */
    private ui.StatusPanel statusPanel = null;

	private ui.ContrastChart ContrastChart = null;
    //~ Constructors ***************************************************************************************************

    //~ Constructors ***************************************************************************************************

    /**
     * This is the default constructor
     */
    public CloudWatchFrame() {
        super();
        thisFrame = this;
        initialize();
    }

    //~ Methods ********************************************************************************************************

    /**
     * DOCUMENT ME!
     *
     * @param args ?
     */
    public static void main(String[] args) {
        JFrame jframe = new CloudWatchFrame();
        jframe.pack();
        jframe.show();
    }

    /**
     * This method initializes controlPanel
     *
     * @return be.realsoftware.ivs.ui.ControlPanel
     */
    private ui.ControlPanel getControlPanel() {
        if (controlPanel == null) {
            controlPanel = new ui.ControlPanel();
        }

        return controlPanel;
    }

    /**
     * This method initializes jJMenuBar1
     *
     * @return javax.swing.JMenuBar
     */
    private javax.swing.JMenuBar getJJMenuBar1() {
        if (jJMenuBar1 == null) {
            jJMenuBar1 = new javax.swing.JMenuBar();
            jJMenuBar1.add(getJMenu());
            jJMenuBar1.setPreferredSize(new java.awt.Dimension(0, 20));
        }

        return jJMenuBar1;
    }

    /**
     * This method initializes jMenu
     *
     * @return javax.swing.JMenu
     */
    private javax.swing.JMenu getJMenu() {
        if (jMenu == null) {
            jMenu = new javax.swing.JMenu();
            jMenu.add(getJMenuItem());
            jMenu.add(getJMenuItem4());
            jMenu.add(getJMenuItem2());
            jMenu.add(getJSeparator());
            jMenu.add(getJMenuItem1());
            jMenu.setName("File");
            jMenu.setText("Settings");
            jMenu.setMnemonic(java.awt.event.KeyEvent.VK_F);
        }

        return jMenu;
    }

    /**
     * This method initializes jMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private javax.swing.JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new javax.swing.JMenuItem();
            jMenuItem.setText("Take Darkframe");
            jMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_X);
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        File           selFile = chooseFile();
                        //NewFileCommand command = new NewFileCommand(new NewXFileSignal(selFile));
                        //command.execute();
                    }
                });
        }

        return jMenuItem;
    }

    /**
     * This method initializes jMenuItem1
     *
     * @return javax.swing.JMenuItem
     */
    private javax.swing.JMenuItem getJMenuItem1() {
        if (jMenuItem1 == null) {
            jMenuItem1 = new javax.swing.JMenuItem();
            jMenuItem1.setText("Close");
        }

        return jMenuItem1;
    }

    /**
     * This method initializes jMenuItem2
     *
     * @return javax.swing.JMenuItem
     */
    private javax.swing.JMenuItem getJMenuItem2() {
        if (jMenuItem2 == null) {
            jMenuItem2 = new javax.swing.JMenuItem();
            jMenuItem2.setText("Save as...");
            jMenuItem2.setMnemonic(java.awt.event.KeyEvent.VK_S);
        }

        return jMenuItem2;
    }

    /**
     * This method initializes jMenuItem4
     *
     * @return javax.swing.JMenuItem
     */
    private javax.swing.JMenuItem getJMenuItem4() {
        if (jMenuItem4 == null) {
            jMenuItem4 = new javax.swing.JMenuItem();
            jMenuItem4.setText("Configure Image source");

            jMenuItem4.setMnemonic(java.awt.event.KeyEvent.VK_Y);
          //  NewXFileSignal signal = new NewXFileSignal(new File("ypy"));
            jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        File           selFile = chooseFile();
                        WebImageSettingsPanel webImage = new WebImageSettingsPanel();
                        //webImage.show();
                       // NewFileCommand command = new NewFileCommand(new NewYFileSignal(selFile));
                        //command.execute();
                    }
                });
        }

        return jMenuItem4;
    }

    /**
     * This method initializes jPanel1
     *
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJPanel1() {
        if (jPanel1 == null) {
            jPanel1 = new javax.swing.JPanel();

            java.awt.GridBagConstraints consGridBagConstraints4 = new java.awt.GridBagConstraints();
            java.awt.GridBagConstraints consGridBagConstraints5 = new java.awt.GridBagConstraints();
            consGridBagConstraints4.gridx       = 0;
            consGridBagConstraints4.gridy       = 1;
            consGridBagConstraints4.weightx     = 1.0D;
            consGridBagConstraints4.weighty     = 1.0D;
            consGridBagConstraints4.fill        = java.awt.GridBagConstraints.BOTH;
            consGridBagConstraints4.insets      = new java.awt.Insets(2, 2, 2, 2);
            consGridBagConstraints5.gridx       = 0;
            consGridBagConstraints5.gridy       = 2;
            consGridBagConstraints5.fill        = java.awt.GridBagConstraints.HORIZONTAL;
            consGridBagConstraints5.insets      = new java.awt.Insets(0, 2, 2, 2);
            jPanel1.setLayout(new java.awt.GridBagLayout());
            jPanel1.add(getJSplitPane(), consGridBagConstraints4);
            jPanel1.add(getStatusPanel(), consGridBagConstraints5);
            jPanel1.setVisible(true);
        }

        return jPanel1;
    }

    /**
     * This method initializes jSeparator
     *
     * @return javax.swing.JSeparator
     */
    private javax.swing.JSeparator getJSeparator() {
        if (jSeparator == null) {
            jSeparator = new javax.swing.JSeparator();
        }

        return jSeparator;
    }

    /**
     * This method initializes jSplitPane
     *
     * @return javax.swing.JSplitPane
     */
    private javax.swing.JSplitPane getJSplitPane() {
        if (jSplitPane == null) {
            jSplitPane = new javax.swing.JSplitPane();
            jSplitPane.setRightComponent(getControlPanel());
            jSplitPane.setLeftComponent(getContrastChart());
            jSplitPane.setDividerLocation(-1);
            jSplitPane.setPreferredSize(new java.awt.Dimension(640, 480));
            jSplitPane.setOneTouchExpandable(true);
            jSplitPane.setDividerSize(7);
            jSplitPane.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
            jSplitPane.setContinuousLayout(false);
            jSplitPane.setResizeWeight(1.0D);
        }

        return jSplitPane;
    }

    /**
     * This method initializes statusPanel
     *
     * @return be.realsoftware.ivs.ui.StatusPanel
     */
    private ui.StatusPanel getStatusPanel() {
        if (statusPanel == null) {
            statusPanel = new ui.StatusPanel();
        }

        return statusPanel;
    }

   /**
     *
     *
     * @return ?
     */
    private File chooseFile() {
        String       filename = File.separator + "tmp";
        JFileChooser fc = new JFileChooser(new File(filename));

        // Show open dialog; this method does not return until the dialog is closed
        fc.showOpenDialog(thisFrame);

        File selFile = fc.getSelectedFile();

        return selFile;
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setContentPane(getJPanel1());
        this.setJMenuBar(getJJMenuBar1());
        this.setSize(353, 249);
        // TODO this is what they call hard-coded!!!
        this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage("C:/home/programming/cloudDetect/conf/pics/webcam5.png"));
        this.setTitle("CloudWatcher v0.1");
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
	/**
	 * This method initializes ContrastChart
	 * 
	 * @return ui.ContrastChart
	 */
	public ui.ContrastChart getContrastChart() {
		if(ContrastChart == null) {
			ContrastChart = new ui.ContrastChart();
		}
		return ContrastChart;
	}
}


//  @jve:visual-info  decl-index=0 visual-constraint="36,6"
