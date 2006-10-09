/*
 * Copyright 2003 (C) Real Software NV - Retail Division.  All rights reserved.
 *
 * $Id: StatusSignal.java,v 1.1 2004/01/26 08:53:24 mrs Exp $
 */
package ui.signal;

import org.werx.framework.signals.BusSignal;

/**
 * Class 
 * @author mikers
 */
public class StatusSignal extends BusSignal {
    private String message;

    /**
     * Ctor for StatusSignal.java.
     * 
     * 
     */
    public StatusSignal(String message) {
        this.message = message;
    }
  
    /**
     * @return
     */
    public String getMessage() {
        return message;
    }
}
