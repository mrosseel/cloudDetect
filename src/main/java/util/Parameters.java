package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Parameters.java
 *
 * Global parameters.
 *
 * Created: Fri Jan 12 11:55:03 2001
 *
 * @author Mike Rosseel
 * @version
 */
public class Parameters 
{
  /** the default seed for to be used throughout the suite. Change the DEF_SEED init if
      you want to reproduce errors and you know the used seed. */
  public static long DEF_SEED;
 
  private static Log log = LogFactory.getLog(Parameters.class);
  
  // static initializers
  static {
    // the default init of DEF_SEED
    DEF_SEED = System.currentTimeMillis();
    // fixed seed for debugging purposes
    //    DEF_SEED = 17;
    log.info("Random seed has been set to : " + DEF_SEED);
  }
}// Parameters

