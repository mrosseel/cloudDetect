package media.image.consumer;

/**
 * Interface ImageConsumer
 * 
 */
public interface ImageConsumer extends Runnable {
    // Methods
    // Constructors
    // Accessor Methods
    // Operations
    /**
     * 
     * @param subConsumer
     * @return void
     */
    public void addSubConsumer(ImageSubConsumer subConsumer);
}
