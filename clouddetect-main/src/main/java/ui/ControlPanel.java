/*
 * Created on 20-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui;

import javax.swing.JPanel;

/**
 * @author mikers
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ControlPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private javax.swing.JPanel jPanel = null;

    private ui.ImagePanel imagePanel = null;

    /**
     * This is the default constructor
     */
    public ControlPanel() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        java.awt.GridBagConstraints consGridBagConstraints5 = new java.awt.GridBagConstraints();
        java.awt.GridBagConstraints consGridBagConstraints4 = new java.awt.GridBagConstraints();
        java.awt.GridBagConstraints consGridBagConstraints6 = new java.awt.GridBagConstraints();
        consGridBagConstraints4.gridx = 0;
        consGridBagConstraints4.gridy = 1;
        consGridBagConstraints4.weightx = 1.0D;
        consGridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
        consGridBagConstraints4.insets = new java.awt.Insets(2, 2, 2, 2);
        consGridBagConstraints5.gridx = 0;
        consGridBagConstraints5.gridy = 0;
        consGridBagConstraints5.weightx = 1.0D;
        consGridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        consGridBagConstraints5.insets = new java.awt.Insets(2, 2, 1, 2);
        consGridBagConstraints6.gridx = 0;
        consGridBagConstraints6.gridy = 2;
        consGridBagConstraints6.weighty = 1.0D;
        consGridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
        consGridBagConstraints6.weightx = 1.0D;
        consGridBagConstraints6.ipadx = 10;
        consGridBagConstraints6.ipady = 10;
        consGridBagConstraints6.anchor = java.awt.GridBagConstraints.CENTER;
        consGridBagConstraints4.ipadx = 1;
        consGridBagConstraints4.ipady = 1;
        this.setLayout(new java.awt.GridBagLayout());
        this.add(getImagePanel(), consGridBagConstraints4);
        this.add(getJPanel(), consGridBagConstraints6);
        this.setSize(300, 200);
    }

    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJPanel() {
        if (jPanel == null) {
            jPanel = new javax.swing.JPanel();
            jPanel.setName("filler");
        }
        return jPanel;
    }

    /**
     * This method initializes algorithmPanel
     * 
     * @return be.realsoftware.ivs.ui.AlgorithmPanel
     */
    private ui.ImagePanel getImagePanel() {
        if (imagePanel == null) {
            imagePanel = new ui.ImagePanel();
            imagePanel
                    .setBorder(javax.swing.BorderFactory
                            .createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
        }
        return imagePanel;
    }
}
