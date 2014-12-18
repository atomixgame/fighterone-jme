package sg.games.fighter.gameplay;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.animation.SkeletonControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import java.util.HashMap;
import sg.atom.core.spatial.SceneGraphUtils;

import sg.games.fighter.managers.StageManager;
import sg.games.fighter.world.GameLevel;

public class FightCharacterControl extends AbstractControl implements AnimEventListener {

    protected FightCharacter fightCharacter;
    protected FightGamePlay gamePlay;
    protected GameLevel level;
    // Shortcuts   
    protected StageManager stageManager;
    //Visual
    protected Node playerModel;
    // movement  ---------------------------------
    public float defaultSpeed = 0.05f;
    public float speed = 1;
    public float veclocity = 1;
    public float accel = 1.5f;
    public float airTime = 0;
    public float maxSpeed = 5;
    public float moveSpeed = 1;
    public float orientation;
    protected float changeViewSpeed = 0.5f;
    public float initSpeed = 1;
    public float initVec = 1;
    public boolean jumping;
    protected boolean prepareJump;
    protected boolean rolling;
    protected Vector3f targetPos;
    protected Vector3f viewDirection;
    protected Vector3f walkDirection = new Vector3f();
    //animation  ---------------------------------
    // AnimNameMap provide a safe way to change animation names!
    protected HashMap<String, String> animNameMap = new HashMap<String, String>();
    protected AnimChannel animationChannel;
    protected AnimControl animationControl;
    protected int currentAttackType = 1;
    protected AnimChannel shootingChannel;
    protected AnimChannel lowerBody, upperBody;
    protected SkeletonControl skeletonControl;
    // FIXME: Replace with AnimBehaviours
    // Controls  ---------------------------------
    protected boolean usePhysicControl = false;
    protected boolean inputControlled = false;
    // collision ---------------------------------
    protected float collisionTimePassed = 0;
    protected float collisionTimeInterval = 3;
    protected float awareItemDistance = 3;

    public FightCharacterControl(FightCharacter character) {
        this.gamePlay = FightGamePlay.getInstance();
        this.fightCharacter = character;

    }

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);

        setupAnimationController();
    }

    @Override
    public Control cloneForSpatial(Spatial newSpatial) {
        FightCharacterControl control = new FightCharacterControl(this.fightCharacter);
        control.setSpatial(newSpatial);
        control.setEnabled(true);
        return control;
    }

    protected void controlRender(RenderManager arg0, ViewPort arg1) {
    }

    protected void controlUpdate(float tpf) {

        if (this.fightCharacter.move) {
            this.fightCharacter.doMove(tpf);
        }

    }
//    public void updateEffect(float tpf) {
//        if (this.fightCharacter.effecting) {
//            this.fightCharacter.timeOfEffect += tpf;
//            if (this.fightCharacter.timeOfEffect > 0.5F) {
//                LastBloodMain.getInstance().getRootNode().detachChild(this.fightCharacter.effect);
//                this.fightCharacter.effecting = false;
//            }
//        }
//    }
    //Animation-----------------------------------------------------------------


    public void handleAnim(AnimControl control, AnimChannel channel, String animName) {
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (animName.equals(animNameMap.get("AttackKick"))
                || animName.equals(animNameMap.get("AttackKickJump"))
                || animName.equals(animNameMap.get("Bow"))
                || animName.equals(animNameMap.get("DefenseJump"))) {
            setAnim("Defense", 0.2f, 1, LoopMode.Loop);
        } else if (animName.equals(animNameMap.get("DefenseToDown"))){
            setAnim("DefenseDown", 0.2f, 1, LoopMode.Loop);
        }
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }

    public void setAnim(String animName, float transitionTime, float speed, LoopMode loop) {
        if (animNameMap.get(animName) == null) {
            //throw new RuntimeException("Can not find animName :" + animName);
        } else {
            animationChannel.setAnim(animNameMap.get(animName), transitionTime);
            animationChannel.setLoopMode(loop);
            animationChannel.setSpeed(speed);
        }
    }

    protected void setupAnimationController() {
        animNameMap.put("Stand", "Stand");
        animNameMap.put("DefenseJump", "DefenseJump");
        animNameMap.put("AttackKick", "AttackKick");
        animNameMap.put("AttackKickJump", "AttackKickJump");
        animNameMap.put("Defense", "Defense");
        animNameMap.put("Bow", "Bow");
        animNameMap.put("DefenseDown", "DefenseDown");
        animNameMap.put("DefenseToDown", "DefenseToDown");
        playerModel = (Node) SceneGraphUtils.findSpatialByPath("Armature/DragonFighter", (Node) spatial);
        animationControl = playerModel.getControl(AnimControl.class);
        setupBodyAnim();
        //setupPartsAnim();
        animationControl.addListener(this);
        setAnim("Bow", 0, 1, LoopMode.DontLoop);
    }

    protected void setupBodyAnim() {
        animationChannel = animationControl.createChannel();

    }

    protected void setupPartsAnim() {
//        lowerBody = animationControl.createChannel();
//        lowerBody.addFromRootBone("thigh_L");
//        lowerBody.addFromRootBone("thigh_R");
//        upperBody = animationControl.createChannel();
//        upperBody.addFromRootBone("spine");
        /*
         // will blend over 15 seconds to stand
         feet.setAnim("Walk", 15);
         feet.setSpeed(0.25f);
         feet.setLoopMode(LoopMode.Cycle);
         // left hand will pull
         upperBody.addFromRootBone("spine");
         upperBody.setAnim("Magic1");
         upperBody.setSpeed(0.5f);
         upperBody.setLoopMode(LoopMode.Cycle);
         */
    }
}