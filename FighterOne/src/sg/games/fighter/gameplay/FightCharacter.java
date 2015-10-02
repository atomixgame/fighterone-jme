package sg.games.fighter.gameplay;

import sg.games.fighter.entities.controls.FightCharacterControl;
import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Spatial;
import java.util.logging.Logger;
import org.apache.commons.configuration.Configuration;
import sg.atom.core.lifecycle.IGameCycle;
import sg.atom.core.lifecycle.ManagableObject;
import sg.games.fighter.entities.rig.CharacterRig;
import sg.games.fighter.main.FighterMain;

/**
 * FIXME: Replace with BlendTree Animation and ActionSelect!
 */
public class FightCharacter implements ManagableObject {

    protected Logger logger = Logger.getLogger("Fight Character ");
    protected FighterMain app;
    protected FightGamePlay gamePlayManager;
    public ActionListener actionListener;

    //Information
    protected int score;
    protected int gameStatus;
    protected int iid;
    public String id;
    // Attributes
    private Vector3f towardVec;
    public float maxHealth;
    public float health;
    public float attackPower;
    public float speed = 1f;
    public int energy = 100;
    public int maxEnergy = 100;
    //Visual
    public Spatial model;
//    public Node playerNode = new Node("playerNode");
//    public Node playerInfoNode = new Node("playerInfoNode");
    public CharacterRig rig;
    public FightCharacter opponent;
    public String actionHint;
    //protected Spatial model;

    // Flags
    public boolean cancellookat = false;
    // Indication
    public String attackByWhat;
    public String attackWhere;
    public String attackEffect;
    public String current = "stand";
    public String lastCase = "";
    public String lastPressed = "";
    //Input by users
    public boolean forward;
    public boolean attack;
    public boolean highdef;
    public boolean lowdef;
    public boolean move;

//    // Time?
//    protected long otppl = 0L;
//    protected long otppr = 0L;
//    protected long otpkl = 0L;
//    protected long otpkr = 0L;
//    protected long otpl = 0L;
//    protected long otpr = 0L;
//    protected long otpu = 0L;
//    protected long otpd = 0L;
//    protected long otps = 0L;
//    protected long otpdef = 0L;
//    protected long ctppl = 0L;
//    protected long ctppr = 0L;
//    protected long ctpkl = 0L;
//    protected long ctpkr = 0L;
//    protected long ctpl = 0L;
//    protected long ctpr = 0L;
//    protected long ctpu = 0L;
//    protected long ctpd = 0L;
//    protected long ctps = 0L;
//    protected long ctpdef = 0L;
    // Effect
    public boolean effecting = false;
    public float timeOfEffect = 0.0F;
    private FightCharacterControl fightCharacterControl;

    public FightCharacter() {
    }

    public void init(Application app) {
        this.app = (FighterMain) app;
        load(app.getAssetManager());
        rigModel();
        initController();
    }

    public void initManagers(IGameCycle... managers) {
    }

    public void load(AssetManager assetManager) {
        model = getApp().getAssetManager().loadModel("Models/Characters/dragon fighter/dragon fighter.j3o");
        model.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
    }

    public void config(Configuration configuration) {
    }

    public void update(float tpf) {
    }

    public void finish() {
    }

    public void rigModel() {
        rig = new CharacterRig(this);
    }

    protected void initController() {
        fightCharacterControl = new FightCharacterControl(this);
        this.model.addControl(fightCharacterControl);

//        this.actionListener = new ActionListener() {
//            public void onAction(String name, boolean keyPressed, float tpf) {
//                handleInput(name, keyPressed, tpf);
//            }
//        };
    }

    // SKILLS ----------------------------------------------------------------------
//    public void fire() {
//        this.energy -= 10;
//
//        Spatial fire = createSkillModel("Fire");
//        BulletControl fireBullet = fire.getControl(BulletControl.class);
//        fireBullet.setStart(this);
//        fireBullet.setTarget(getOpponent());
//        fireBullet.start();
//    }
    private Spatial createSkillModel(String fire) {
        //stageManager.
        return null;
    }

    //Events--------------------------------------------------------------------
    protected void onAttacked(String animName, float blendtime, float animSpeed, float damage) {
//        this.controlable = false;
        this.move = true;
        this.health -= damage;

//        if (this.currentHealth <= 0.0F) {
//            gamePlayManager.winLose();
//        } else {
//            playSound(damage);
//            playAnim(animName, blendtime, animSpeed);
//        }
    }

//Movements ------------------------------------------------------------------
    protected void notOutOfRing(float distance) {
//        if (level.floor.getName().equals("round")) {
//            if (distance > level.floorsize - 20) {
//                this.move = false;
//            } else {
//                this.move = true;
//            }
//        } else if (!level.floor.getName().equals("square")) {
//            if (level.floor.getName().equals("infinitive")) {
//                if (level.cd12 >= level.md12) {
//                    this.move = false;
//                } else {
//                    this.move = true;
//                }
//            }
//        }
    }

    protected void forward(String animName1, String animName2, String thiscase1, String thiscase2, String thispressed, float thisSpeed) {
        gamePlayManager.lookAtOther(this.id);
        gamePlayManager.forwardOrBackward();

        float distance = Math.abs(this.model.getLocalTranslation().length());
        if (this.forward) {
            this.lastCase = thiscase1;
            this.lastPressed = thispressed;
            if ((animName1.equals("B")) || (animName1.equals("Bend"))) {
                notOutOfRing(distance);
            } else {
                this.move = true;
            }
//            playAnim(animName1, 0.01F, thisSpeed);
        } else {
            this.lastCase = thiscase2;
            this.lastPressed = thispressed;
            if ((animName2.equals("B")) || (animName2.equals("Bend"))) {
                notOutOfRing(distance);
            } else {
                this.move = true;
            }
//            playAnim(animName2, 0.01F, thisSpeed);
        }
    }

    protected void backward(String animName1, String animName2, String thiscase1, String thiscase2, String thispressed, float thisSpeed) {
        gamePlayManager.lookAtOther(this.id);
        gamePlayManager.forwardOrBackward();

        float distance = Math.abs(this.model.getLocalTranslation().length());
//        if (this.forward) {
//            this.lastCase = thiscase1;
//            this.lastPressed = thispressed;
//            if ((animName1.equals("B")) || (animName1.equals("Bend"))) {
//                notOutOfRing(distance);
//            } else {
//                this.move = true;
//            }
//            playAnim(animName1, 0.01F, thisSpeed);
//        } else {
//            this.lastCase = thiscase2;
//            this.lastPressed = thispressed;
//            if ((animName2.equals("B")) || (animName2.equals("Bend"))) {
//                notOutOfRing(distance);
//            } else {
//                this.move = true;
//            }
//            playAnim(animName2, 0.01F, thisSpeed);
//        }
    }

    public void lookAt(FightCharacter fc2) {
        Vector3f fc2Pos = fc2.model.getWorldTranslation().clone().setY(0.0F);
        model.lookAt(fc2Pos, Vector3f.UNIT_Y);
    }

    public void doMove(float tpf) {
        Vector3f newPos = this.model.getLocalTranslation().clone();
        Vector3f opponentPos = opponent.model.getLocalTranslation();
        towardVec = newPos.subtract(opponentPos);

//        if (this.moveLeft) {
//            newPos.addLocal(towardVec.normalize().mult(speed * tpf));
//            setLocation(newPos);
//        } else if (this.moveRight) {
//            newPos.addLocal(towardVec.normalize().mult(-speed * 2 * tpf));
//            setLocation(newPos);
//        }
    }

    public void setLocation(Vector3f location) {
        this.model.setLocalTranslation(location);
    }

//    public void placeHitEffect(Vector3f location) {
//        this.effect.setLocalTranslation(location);
//        this.model.attachChild(this.effect);
//        this.effect.emitAllParticles();
//        this.timeOfEffect = 0.0F;
//        this.effecting = true;
//    }
//    public void updateCollisionShapes() {
//        ArrayList<Spatial> shapes = new ArrayList<Spatial>();
//        for (Spatial s : shapes) {
//            s.updateGeometricState();
//        }
//    }
//    public void updateCollisionGroup(String groupName) {
//    }
    //SETTER & GETTER ----------------------------------------------------------
    public InputListener getActionListener() {
        return actionListener;
    }

    public void setOpponent(FightCharacter oponent) {
        this.opponent = oponent;
    }

    public FightCharacter getOpponent() {
        return opponent;
    }

    public FighterMain getApp() {
        return app;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public Spatial getModel() {
        return model;
    }

    public void setModel(Spatial model) {
        this.model = model;
    }

    public CharacterRig getRig() {
        return rig;
    }

    public void setRig(CharacterRig rig) {
        this.rig = rig;
    }

}
