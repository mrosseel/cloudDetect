/*
 * Copyright 2003 (C) Real Software NV - Retail Division.  All rights reserved.
 *
 * $Id: StatusCommand.java,v 1.1 2004/01/26 08:53:23 mrs Exp $
 */
package ui.commands;

import org.werx.framework.bus.ReflectionBus;

import ui.signal.StatusSignal;

/**
 * Class
 * 
 * @author mikers
 */
public class StatusCommand implements ICommand {
    // ~ Instance fields
    // ************************************************************************************************

    /** ? */
    StatusSignal signal;

    // ~ Constructors
    // ***************************************************************************************************

    /**
     * Ctor for StatusCommand.java.
     */
    public StatusCommand() {}

    /**
     * Creates a new StatusCommand object.
     * 
     * @param signal ?
     */
    public StatusCommand(StatusSignal signal) {
        this.signal = signal;
    }

    /**
     * Creates a new StatusCommand object.
     * 
     * @param message ?
     */
    public StatusCommand(String message) {
        this.signal = new StatusSignal(message);
    }

    // ~ Methods
    // ********************************************************************************************************

    /*
     * (non-Javadoc)
     * 
     * @see be.realsoftware.ivs.ui.commands.ICommand#execute()
     */
    public void execute() {
        ReflectionBus.broadcast(this.signal);
    }
}
