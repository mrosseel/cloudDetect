/*
 * Created on 23-jan-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.jmf;

/**
 * @author Mike
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JMFUI {
    // public void captureDialog() {
    // CaptureDialog dialogCapture;
    // DataSource dataSource;
    // CaptureDeviceInfo cdi;
    //
    // JMAppsCfg cfgJMApps = new JMAppsCfg ();
    // dialogCapture = new CaptureDialog ( this, cfgJMApps );
    // dialogCapture.show ();
    // if (dialogCapture.getAction() == CaptureDialog.ACTION_CANCEL)
    // return;
    // }

    // Control[] controls = (Control[]) videoTrack.getControls();
    // for (int j = 0; j < controls.length; j++) {
    // System.out.println(controls[j].getClass());
    // }
    //
    // // Display the visual & control component if there's one.
    //
    // setLayout(new BorderLayout()0);
    //
    // Component cc;
    //
    // Component vc;
    // if ((vc = p.getVisualComponent()) != null) {
    // add("Center", vc);
    // }
    //
    // if ((cc = p.getControlPanelComponent()) != null) {
    // add("South", cc);
    // }

    /*
     * private void captureMedia() { CaptureDialog dialogCapture;
     * 
     * CaptureDeviceInfo cdi;
     * 
     * String nameCaptureDeviceAudio = null; String nameCaptureDeviceVideo =
     * null;
     * 
     * System.out.println("In capture media");
     * 
     * JMAppsCfg cfgJMApps = new JMAppsCfg(); dialogCapture = new
     * CaptureDialog(this, cfgJMApps); dialogCapture.show(); if
     * (dialogCapture.getAction() == CaptureDialog.ACTION_CANCEL) return;
     * 
     * cdi = dialogCapture.getAudioDevice(); if (cdi != null &&
     * dialogCapture.isAudioDeviceUsed()) nameCaptureDeviceAudio =
     * cdi.getName(); cdi = dialogCapture.getVideoDevice(); if (cdi != null &&
     * dialogCapture.isVideoDeviceUsed()) nameCaptureDeviceVideo =
     * cdi.getName(); // dataSource = JMFUtils.createCaptureDataSource ( //
     * nameCaptureDeviceAudio, // dialogCapture.getAudioFormat(), //
     * nameCaptureDeviceVideo, // dialogCapture.getVideoFormat() );
     * 
     * //dataSource = p.getDataOutput(); if (dataSource != null) {
     * 
     * if (dataSource instanceof CaptureDevice && dataSource instanceof
     * PushBufferDataSource) { DataSource cdswrapper = new CDSWrapper(
     * (PushBufferDataSource) dataSource); dataSource = cdswrapper; try {
     * cdswrapper.connect(); } catch (IOException ioe) { dataSource = null;
     * nameCaptureDeviceAudio = null; nameCaptureDeviceVideo = null;
     * MessageDialog.createErrorDialog(this, JMFI18N
     * .getResource("jmstudio.error.captureds")); } }
     * 
     * //open ( dataSource );
     * 
     * if (dataSource != null) { dlgCaptureControls = new
     * CaptureControlsDialog(this, dataSource); if
     * (dlgCaptureControls.isEmpty()) { dlgCaptureControls = null; } else {
     * dlgCaptureControls.setVisible(true); } } } else { nameCaptureDeviceAudio =
     * null; nameCaptureDeviceVideo = null;
     * MessageDialog.createErrorDialog(this, JMFI18N
     * .getResource("jmstudio.error.captureds")); } }
     */

}
