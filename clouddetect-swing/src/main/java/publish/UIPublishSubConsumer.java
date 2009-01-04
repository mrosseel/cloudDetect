package publish;


import media.image.CloudImageResult;
import media.image.consumer.SubConsumer;
import ui.commands.ImageCommand;

/**
 * Class UIPublishSubConsumer
 * 
 */
public class UIPublishSubConsumer implements SubConsumer<CloudImageResult> {

    public void consume(CloudImageResult image) {
        // TODO UI reference, maybe remove to the ui package for future separate
        // build?
        ImageCommand command = new ImageCommand(image.getImage());
        command.execute();
    }
}
