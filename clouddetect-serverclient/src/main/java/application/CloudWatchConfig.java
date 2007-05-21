package application;


/**
 * @author Mike
 * 
 * Encapsulates config of the application, now hard-coded, later maybe with
 * property-files/xml
 */
public class CloudWatchConfig {

    private boolean commandLine;

    public boolean isCommandLine() {
        return commandLine;
    }

    public void setCommandLine(boolean usesUI) {
        this.commandLine = usesUI;
    }

}
