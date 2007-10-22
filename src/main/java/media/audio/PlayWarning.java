/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.audio;

import java.io.File;

import util.SoundUtil;

/**
 * @author Mike
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PlayWarning {

    public static void playRooster() {
        SoundUtil util = new SoundUtil();
        File sound = new File((PlayWarning.class
                .getResource("/conf/sounds/rooster.wav")).getFile());
        util.play(sound, 1);
    }
}
