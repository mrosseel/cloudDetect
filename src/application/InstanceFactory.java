/*
 * Created on 27-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package application;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import ui.ImagePanel;
import util.SoundUtil;

/**
 * @author Mike
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InstanceFactory {
	private static XmlBeanFactory beanFactory;

	static {
		beanFactory = new XmlBeanFactory(InstanceFactory.class.getResourceAsStream("/conf/components/algorithms.xml"));
	}

	/**
	 * 
	 */
	private InstanceFactory() {
	}
	
	public static ImagePanel getImagePanel() {
		return (ImagePanel) beanFactory.getBean("imagepanel");
		
	}

	public static SoundUtil getSoundUtil() {
		return (SoundUtil) beanFactory.getBean("soundutil");
	}

	
	public static void main(String[] args) {
		InstanceFactory.getImagePanel();
		System.out.println("teste");
	}

}
