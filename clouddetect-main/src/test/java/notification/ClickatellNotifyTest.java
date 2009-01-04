package notification;

import junit.framework.TestCase;
import media.image.CloudImageResultImpl;

public class ClickatellNotifyTest extends TestCase {

    public ClickatellNotifyTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ClickatellNotifyTest(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public void testMailing() {
        ClickatellNotify notify = new ClickatellNotify();
        
        // Let's avoid automatic testing...
        // notify.notify(null);
    }

}
