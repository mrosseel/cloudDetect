/*
 * Created on 26-sep-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
/**
 * @author Mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebImageSettingsPanel extends JPanel {

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JCheckBox jCheckBox = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField2 = null;
	private JPanel jPanel4 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	/**
	 * This is the default constructor
	 */
	public WebImageSettingsPanel() {
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		java.awt.GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		java.awt.GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		java.awt.GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setSize(467, 222);
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.insets = new java.awt.Insets(2,2,1,2);
		gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints1.weightx = 1.0D;
		gridBagConstraints1.weighty = 1.0D;
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 1;
		gridBagConstraints2.insets = new java.awt.Insets(1,2,2,2);
		gridBagConstraints2.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints2.weightx = 1.0D;
		gridBagConstraints2.weighty = 1.0D;
		gridBagConstraints16.gridx = 0;
		gridBagConstraints16.gridy = 2;
		gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
		this.add(getJPanel(), gridBagConstraints1);
		this.add(getJPanel1(), gridBagConstraints2);
		this.add(getJPanel4(), gridBagConstraints16);
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel3 = new JLabel();
			java.awt.GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			java.awt.GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Image Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jLabel3.setText("Web address of image");
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.gridy = 0;
			gridBagConstraints14.insets = new java.awt.Insets(2,2,2,2);
			gridBagConstraints14.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints15.gridx = 1;
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.insets = new java.awt.Insets(2,2,2,2);
			jPanel.add(jLabel3, gridBagConstraints14);
			jPanel.add(getJTextField2(), gridBagConstraints15);
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
			java.awt.GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			java.awt.GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			jLabel = new JLabel();
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proxy Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jLabel.setText("Use proxy");
			gridBagConstraints51.gridx = 0;
			gridBagConstraints51.gridy = 0;
			gridBagConstraints51.gridwidth = 3;
			gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints51.weightx = 1.0D;
			gridBagConstraints51.weighty = 1.0D;
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.weightx = 1.0D;
			gridBagConstraints6.weighty = 1.0D;
			gridBagConstraints6.fill = java.awt.GridBagConstraints.BOTH;
			jPanel1.add(getJPanel2(), gridBagConstraints51);
			jPanel1.add(getJPanel32(), gridBagConstraints6);
		}
		return jPanel1;
	}
	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */    
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		}
		return jCheckBox;
	}
	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jLabel1.setText("Proxy address (http://example.com)");
			jLabel1.setEnabled(false);
			jLabel2.setText("Proxy port");
			jLabel2.setEnabled(false);
			jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return jPanel3;
	}
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setEnabled(false);
		}
		return jTextField;
	}
	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setEnabled(false);
		}
		return jTextField1;
	}
	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}
	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.add(getJButton(), null);
			jPanel4.add(getJButton1(), null);
		}
		return jPanel4;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("OK");
			jButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return jButton;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Cancel");
			jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return jButton1;
	}
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.add(getJCheckBox(), null);
			jPanel2.add(jLabel, null);
		}
		return jPanel2;
	}
	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel32() {
		if (jPanel3 == null) {
			java.awt.GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			java.awt.GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			java.awt.GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			java.awt.GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.insets = new java.awt.Insets(7,5,7,2);
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.insets = new java.awt.Insets(5,3,5,2);
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.gridy = 1;
			gridBagConstraints9.insets = new java.awt.Insets(7,3,7,2);
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.insets = new java.awt.Insets(5,3,5,5);
			jPanel3.add(jLabel1, gridBagConstraints7);
			jPanel3.add(getJTextField(), gridBagConstraints8);
			jPanel3.add(jLabel2, gridBagConstraints9);
			jPanel3.add(getJTextField1(), gridBagConstraints10);
		}
		return jPanel3;
	}
             }  //  @jve:decl-index=0:visual-constraint="10,10"
