/*
 * Created on 26-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui;

import javax.swing.JPanel;

/**
 * @author Mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RightPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private javax.swing.JPanel jPanel = null;

    private ui.ImagePanel imagePanel = null;

    private javax.swing.JPanel jPanel1 = null;

    private javax.swing.JPanel jPanel11 = null;

    /**
     * This is the default constructor
     */
    public RightPanel() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        java.awt.GridBagConstraints consGridBagConstraints4 = new java.awt.GridBagConstraints();
        java.awt.GridBagConstraints consGridBagConstraints3 = new java.awt.GridBagConstraints();
        consGridBagConstraints3.gridx = 0;
        consGridBagConstraints3.gridy = 0;
        consGridBagConstraints3.insets = new java.awt.Insets(4, 4, 4, 4);
        consGridBagConstraints3.weightx = 1.0D;
        consGridBagConstraints3.weighty = 1.0D;
        consGridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        consGridBagConstraints4.gridx = 0;
        consGridBagConstraints4.gridy = 1;
        consGridBagConstraints4.insets = new java.awt.Insets(4, 4, 4, 4);
        consGridBagConstraints4.weighty = 0D;
        consGridBagConstraints4.weightx = 1D;
        consGridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        this.setLayout(new java.awt.GridBagLayout());
        this.add(getJPanel(), consGridBagConstraints3);
        this.add(getJPanel11(), consGridBagConstraints4);
        this.setPreferredSize(new java.awt.Dimension(160,140));

    }

    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJPanel() {
        if (jPanel == null) {
            jPanel = new javax.swing.JPanel();
            java.awt.GridBagConstraints consGridBagConstraints1 = new java.awt.GridBagConstraints();
            consGridBagConstraints1.gridx = 0;
            consGridBagConstraints1.gridy = 0;
            consGridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
            consGridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
            jPanel.setLayout(new java.awt.GridBagLayout());
            jPanel.add(getImagePanel(), consGridBagConstraints1);
            jPanel
                    .setBorder(javax.swing.BorderFactory
                            .createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
        }
        return jPanel;
    }

    /**
     * This method initializes imagePanel
     * 
     * @return ui.ImagePanel
     */
    public ui.ImagePanel getImagePanel() {
        if (imagePanel == null) {
            imagePanel = new ImagePanel();
            imagePanel.setLayout(new java.awt.GridBagLayout());
            // imagePanel.setPreferredSize(new java.awt.Dimension(160,140));
        }
        return imagePanel;
    }

    /**
     * This method initializes jPanel11
     * 
     * @return javax.swing.JPanel
     */
    private javax.swing.JPanel getJPanel11() {
        if (jPanel11 == null) {
            jPanel11 = new javax.swing.JPanel();
            java.awt.GridBagConstraints consGridBagConstraints1 = new java.awt.GridBagConstraints();
            consGridBagConstraints1.gridx = 0;
            consGridBagConstraints1.gridy = 0;
            consGridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
            consGridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
            jPanel11.setLayout(new java.awt.GridBagLayout());
            jPanel11.add(new javax.swing.JPanel(), consGridBagConstraints1);
            jPanel11
                    .setBorder(javax.swing.BorderFactory
                            .createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));

            // jPanel11.setPreferredSize(new java.awt.Dimension(1000,1000));
        }
        return jPanel11;
    }
}
