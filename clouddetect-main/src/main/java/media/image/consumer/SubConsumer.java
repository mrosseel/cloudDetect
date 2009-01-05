package media.image.consumer;


/**
 * Interface ImageSubConsumer
 * 
 */
public interface SubConsumer<T> {
    // Methods
    // Constructors
    // Accessor Methods
    // Operations
    /**
     * 
     * @param image
     * @return void
     */
    public void consume(T content);

}
