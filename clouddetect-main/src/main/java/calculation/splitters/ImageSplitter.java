/*
 * Created on 5-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package calculation.splitters;

import media.image.CloudImageResult;
import calculation.splitters.splittermetric.SplitterMetric;

/**
 * @author Mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ImageSplitter extends Splitter {

    public void split(CloudImageResult data);

    public void setSplitterMetric(SplitterMetric metric);

    public SplitterMetric getSplitterMetric();
}
