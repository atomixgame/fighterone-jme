package sg.games.fighter.entities.controls;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.animation.Skeleton;
import java.util.HashMap;

/**
 *
 * @author cuongnguyen
 */
public class CharacterAnimStateControl {

    public AnimControl animControl;
    public AnimChannel animChannel;
    public HashMap<String, String> animMap;
    public Skeleton skeleton;
    public AnimEventListener animEvent;
    public float animSpeed;

    //Animations----------------------------------------------------------------
    public void playAnim(String animName, float blendTime, float animSpeed) {
        if (this.animControl.getAnimationNames().contains(animName)) {
            this.animChannel.setAnim(animName, blendTime);
            this.animChannel.setLoopMode(LoopMode.DontLoop);
            this.animChannel.setSpeed(animSpeed);
        }
    }
}
