package media.image.producer;

import persistence.model.ProducerType;
import application.InstanceFactory;

public class ProducerFactory {

	public static ImageProducer getImageProducer(ProducerType type) {
		return (ImageProducer) InstanceFactory.getBean(type.toString());
	}
}
