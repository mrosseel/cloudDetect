/*
 * Copyright 2003 (C) Real Software NV - Retail Division.  All rights reserved.
 *
 * $Id: StatusHandler.java,v 1.1 2004/01/26 08:53:25 mrs Exp $
 */
package ui.handlers;

import org.werx.framework.bus.ReflectionBus;

import ui.StatusPanel;
import ui.signal.StatusSignal;

/**
 * Class
 * 
 * @author mikers
 */
public class StatusHandler {
    private StatusPanel panel;

    /**
     * Ctor for StatusHandler.java.
     * 
     * 
     */
    public StatusHandler(StatusPanel panel) {
        this.panel = panel;
        ReflectionBus.plug(this);
    }

    public void channel(StatusSignal signal) {
        panel.setStatusMessage(signal.getMessage());
    }
}
