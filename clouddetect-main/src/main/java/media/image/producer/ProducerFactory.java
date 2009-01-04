package media.image.producer;

import persistence.model.ProducerType;
import application.InstanceFactory;

public class ProducerFactory {

	public static Producer<?> getProducer(ProducerType type) {
		return (Producer<?>) InstanceFactory.getBean(type.toString());
	}
}
