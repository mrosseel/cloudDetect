/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Sep 9, 2003
 * Time: 7:00:02 PM
 * To change this template use Options | File Templates.
 */
package processors;

import javax.media.Buffer;

public interface BufferProcessor extends Runnable {

    /**
     *
     * @param data frame data
     */
    public void process(short[] data);
}
