/*
 * Created on 23-sep-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
/**
 * @author Mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebcamSettingsPanel extends JPanel {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	/**
	 * This method initializes 
	 * 
	 */
	public WebcamSettingsPanel() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
        java.awt.GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        java.awt.GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.insets = new java.awt.Insets(2,2,1,2);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.weightx = 1.0D;
        gridBagConstraints4.weighty = 1.0D;
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.insets = new java.awt.Insets(1,2,2,2);
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints5.weightx = 1.0D;
        gridBagConstraints5.weighty = 1.0D;
        this.add(getJPanel(), gridBagConstraints4);
        this.add(getJPanel1(), gridBagConstraints5);
			
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED));
		}
		return jPanel1;
	}
  }
