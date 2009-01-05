package media.image.producer;


/**
 * Interface ImageProvider
 * 
 */
public interface Producer<T> {

    public T produceContent();

    public String getProducerName();
    
    public void setSource(String location);
    
    public String getSource();
    
    public void setSourceId(int sourceId);
}    
