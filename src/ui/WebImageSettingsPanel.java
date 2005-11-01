*
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

	private JCheckBox jCheckBox = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField2 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel5 = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
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
		java.awt.GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		java.awt.GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setSize(467, 222);
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.gridy = 0;
		gridBagConstraints14.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints14.weightx = 1.0D;
		gridBagConstraints14.weighty = 1.0D;
		gridBagConstraints15.gridx = 0;
		gridBagConstraints15.gridy = 1;
		gridBagConstraints15.weightx = 1.0D;
		gridBagConstraints15.weighty = 1.0D;
		gridBagConstraints15.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints16.gridx = 0;
		gridBagConstraints16.gridy = 2;
		gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints16.weightx = 1.0D;
		this.add(getJPanel5(), gridBagConstraints14);
		this.add(getJPanel6(), gridBagConstraints15);
		this.add(getJPanel12(), gridBagConstraints16);
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Web address of image");
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
			jLabel = new JLabel();
			jLabel.setText("Use proxy");
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
	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.add(jLabel3, null);
			jPanel5.add(getJTextField2(), null);
		}
		return jPanel5;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel6() {
		if (jPanel == null) {
			java.awt.GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			java.awt.GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.gridy = 0;
			gridBagConstraints17.insets = new java.awt.Insets(19,74,63,2);
			gridBagConstraints18.gridx = 0;
			gridBagConstraints18.gridy = 1;
			gridBagConstraints18.insets = new java.awt.Insets(5,3,48,74);
			gridBagConstraints18.fill = java.awt.GridBagConstraints.BOTH;
			jPanel.add(getJPanel2(), gridBagConstraints17);
			jPanel.add(getJPanel3(), gridBagConstraints18);
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getJPanel12() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton1(), null);
		}
		return jPanel1;
	}
                }  //  @jve:decl-index=0:visual-constraint="10,10"
