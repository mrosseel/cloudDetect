package media.image.consumer;

import media.image.producer.Producer;

public interface Consumer<T> {

    public void consume(Producer<?> producer);
    
    /**
     * 
     * @param subConsumer
     * @return void
     */
    public void addSubConsumer(SubConsumer<T> subConsumer);
}
