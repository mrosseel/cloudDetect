/*
 * Copyright 2003 (C) Real Software NV - Retail Division.  All rights reserved.
 *
 * $Id: StatusPanel.java,v 1.1 2004/01/26 08:53:23 mrs Exp $
 */
package ui;

import javax.swing.JPanel;

import ui.handlers.StatusHandler;

/**
 * Class 
 * @author mikers
 */
public class StatusPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String statusMessage =" ";
	private javax.swing.JProgressBar jProgressBar = null;
	private javax.swing.JLabel jLabel = null;
    /**
     * This is the default constructor
     */
    public StatusPanel() {
        super();
        initialize();
        new StatusHandler(this);
    }
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        java.awt.GridBagConstraints consGridBagConstraints2 = new java.awt.GridBagConstraints();
        java.awt.GridBagConstraints consGridBagConstraints1 = new java.awt.GridBagConstraints();
        consGridBagConstraints2.gridx = 1;
        consGridBagConstraints2.gridy = 0;
        consGridBagConstraints2.insets = new java.awt.Insets(1,2,1,2);
        consGridBagConstraints2.weightx = 1.0D;
        consGridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        consGridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        consGridBagConstraints1.gridx = 0;
        consGridBagConstraints1.gridy = 0;
        consGridBagConstraints1.insets = new java.awt.Insets(2,2,2,4);
        consGridBagConstraints1.anchor = java.awt.GridBagConstraints.EAST;
        consGridBagConstraints1.weightx = 0.0D;
        this.setLayout(new java.awt.GridBagLayout());
        this.add(getJProgressBar(), consGridBagConstraints1);
        this.add(getJLabel(), consGridBagConstraints2);
        this.setSize(402, 40);
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
    }
	/**
	 * This method initializes jProgressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	private javax.swing.JProgressBar getJProgressBar() {
		if(jProgressBar == null) {
			jProgressBar = new javax.swing.JProgressBar();
			jProgressBar.setEnabled(true);
			jProgressBar.setVisible(false);
		}
		return jProgressBar;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel() {
		if(jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setText(statusMessage);
			jLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
		}
		return jLabel;
	}
    
    /**
     * @return
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param string
     */
    public void setStatusMessage(String string) {
        statusMessage = string;
        jLabel.setText(statusMessage);
    }

}  //  @jve:visual-info  decl-index=0 visual-constraint="14,55"
