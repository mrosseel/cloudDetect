package application;

/*
 * @(#)JMFRegistry.java	1.4 01/03/13
 *
 * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import java.awt.*;
import java.awt.event.*;

import com.sun.media.util.JMFI18N;
import com.sun.media.ui.TabControl;

import jmapps.ui.*;
import jmapps.registry.*;


public class JMFRegistry extends JMFrame {

    private TabControl  tabs;
    private Panel       panelPM;
    private Panel       panelPIM;
    private Panel       panelCDM;
    private Panel       panelMime;
    private Panel       panelOther;


    public JMFRegistry () {
        super(JMFI18N.getResource("jmfregistry.title"));

        this.setLayout ( new BorderLayout() );
        tabs = new TabControl();
        this.add ( tabs, BorderLayout.CENTER );

        panelOther = new SettingsPanel();
        tabs.addPage ( panelOther, JMFI18N.getResource("jmfregistry.settings") );

        panelCDM = new CDMPanel();
        tabs.addPage ( panelCDM, JMFI18N.getResource("jmfregistry.capture") );

        panelPIM = new PIMPanel();
        tabs.addPage ( panelPIM, JMFI18N.getResource("jmfregistry.plugins") );

        panelMime = new MimePanel();
        tabs.addPage ( panelMime, JMFI18N.getResource("jmfregistry.mimetypes") );

        panelPM = new PMPanel();
        tabs.addPage ( panelPM, JMFI18N.getResource("jmfregistry.package") );

        setSize ( 700, 400 );
    }

    public void windowClosing ( WindowEvent event ) {
        this.setVisible ( false );
    }

    public static void main ( String [] args ) {
        JMFRegistry jmfr = new JMFRegistry ();
        jmfr.addWindowListener (
            new WindowAdapter () {
                public void windowClosing ( WindowEvent event ) {
                    event.getWindow().dispose ();
                    System.exit ( 0 );
                }
            }
        );
        jmfr.setVisible(true);
    }

}


