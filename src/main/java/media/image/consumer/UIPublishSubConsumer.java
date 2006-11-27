package media.image.consumer;

import media.image.CloudImage;
import ui.commands.ImageCommand;


/**
 * Class UIPublishSubConsumer
 * 
 */
public class UIPublishSubConsumer implements ImageSubConsumer {

	public void consume(CloudImage image) {
		// TODO UI reference, maybe remove to the ui package for future separate build?
		ImageCommand command = new ImageCommand(image.getImage());
		command.execute();
	}
}

