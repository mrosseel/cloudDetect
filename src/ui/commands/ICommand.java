package ui.commands;

/**
 * WERX - Java Swing RAD Framework
 * Copyright 2002 Bradlee Johnson
 * Released under LGPL license
 *
 * @version 0.1
 * @author Bradlee Johnson
 */

public interface ICommand
{
    /**
     * All command objects must implement an execute method.
     */
    public void execute();
}