/*
 * Created on 22-jan-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
