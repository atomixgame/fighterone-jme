package sg.games.fighter.gameplay;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.animation.Skeleton;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import sg.games.fighter.gameplay.rig.CharacterRig;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.world.GameLevel;
import sg.games.fighter.managers.StageManager;

/**
 * FIXME: Replace with BlendTree Animation and ActionSelect!
 */
public class FightCharacter{
    // game short cut
    protected FighterMain app;
    protected StageManager stageManager;
    protected FightGamePlay gamePlayManager;
    protected AssetManager assetManager;
    protected Node rootNode;
    protected AudioRenderer audioR;
    public ActionListener actionListener;
    public GameLevel level;
    private Camera camera;
    
    //Information
    protected int score;
    protected int gameStatus;
    protected int iid;
    public String id;
    // Attributes
    private Vector3f towardVec;
    public float maxHealth;
    public float currentHealth;
    public float dmg;
    public float speed = 1f;
    public int energy = 100;
    //Visual
    public Spatial mesh;
//    public Node playerNode = new Node("playerNode");
//    public Node playerInfoNode = new Node("playerInfoNode");
    public CharacterRig rig;
    public FightCharacter opponent;
    public String actionHint;
    //protected Spatial model;
    // Animations    
    public AnimControl animControl;
    public AnimChannel animChannel;
    public HashMap<String, String> animMap;
    public Skeleton skeleton;
    public AnimEventListener animEvent;
    public float animSpeed;
    // Flags
    public boolean controlable = true;
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
    // Internal States Flags
    public boolean camRot;
    public boolean inputLeftPunch;
    public boolean inputRightPunch;
    public boolean inputLeftKick;
    public boolean inputRightKick;
    public boolean moveUp;
    public boolean moveLeft;
    public boolean moveRight;
    public boolean moveDown;
    public boolean pfb;
    public boolean pbb;
    public boolean psb;
    public boolean pslb;
    public boolean psrb;
    public boolean inputDef;
    // Time?
    protected long otppl = 0L;
    protected long otppr = 0L;
    protected long otpkl = 0L;
    protected long otpkr = 0L;
    protected long otpl = 0L;
    protected long otpr = 0L;
    protected long otpu = 0L;
    protected long otpd = 0L;
    protected long otps = 0L;
    protected long otpdef = 0L;
    protected long ctppl = 0L;
    protected long ctppr = 0L;
    protected long ctpkl = 0L;
    protected long ctpkr = 0L;
    protected long ctpl = 0L;
    protected long ctpr = 0L;
    protected long ctpu = 0L;
    protected long ctpd = 0L;
    protected long ctps = 0L;
    protected long ctpdef = 0L;
    // Effect
    public ParticleEmitter effect;
    public boolean effecting = false;
    public Vector3f effectPosition;
    public float timeOfEffect = 0.0F;
    // sounds
    protected ArrayList<AudioNode> sounds;
    protected AudioNode audioKO;
    protected AudioNode audioHh;
    protected AudioNode audioHa;
    protected AudioNode audioNa;
    protected AudioNode audioTD;
    protected AudioNode audioT;
    protected AudioNode audioWin;
    protected String audioName;
    protected boolean audioSend;
    // short cut
    protected Logger logger = Logger.getLogger("Fight Character ");
    private FightCharacterControl fightCharacterControl;

    public FightCharacter(FightGamePlayer player) {
        this.rootNode = new Node();
    }

    public void loadModel() {
        mesh = assetManager.loadModel("Models/Characters/dragon fighter/dragon fighter.j3o");
        this.mesh.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
    }

    public void rigModel() {
        rig = new CharacterRig(this);
    }

    public void initCharacter(String id) {
        this.id = id;
        this.app = FighterMain.getInstance();
        this.stageManager = StageManager.getInstance();
        this.assetManager = app.getAssetManager();
        this.rootNode = app.getWorldManager().getLevelNode();
        this.gamePlayManager = FightGamePlay.getInstance();
        this.camera = app.getCamera();
        this.audioR = app.getAudioRenderer();
        loadModel();
        rigModel();
        initController();
    }

    public void attachCharacter(Vector3f pos) {
        this.rootNode.attachChild(mesh);
        this.setLocation(pos.clone());
        this.move = true;
    }

    public void loadModels() {
    }

    public void loadSounds() {
    }

//    public Vector3f playerCenterScreen() {
//        return camera.getScreenCoordinates(this.mesh.getWorldTranslation());
//    }
    protected void initController() {
        fightCharacterControl = new FightCharacterControl(this);
        this.mesh.addControl(fightCharacterControl);

        this.actionListener = new ActionListener() {
            public void onAction(String name, boolean keyPressed, float tpf) {
                handleInput(name, keyPressed, tpf);
            }
        };
    }

    public void handleInput(String name, boolean keyPressed, float tpf) {

        StringBuffer fullMessage = new StringBuffer(name);

        if (fullMessage.substring(0, 2).equals(FightCharacter.this.id)) {
            //System.out.println("Key!");

            //Remove the name.
            fullMessage = fullMessage.replace(0, 2, "");
            name = fullMessage.toString();

            if (name.equals("leftPunch")) {
                if (keyPressed) {
                    this.inputLeftPunch = true;

                } else {
                    this.inputLeftPunch = false;
                }
            }
            if (name.equals("rightPunch")) {
                if (keyPressed) {
                    this.inputRightPunch = true;
                } else {
                    this.inputRightPunch = false;
                }
            }
            if (name.equals("leftKick")) {
                if (keyPressed) {
                    this.inputLeftKick = true;
                    fightCharacterControl.setAnim("AttackKick", 0.4f, 1, LoopMode.DontLoop);
                } else {
                    this.inputLeftKick = false;
                }
            }
            if (name.equals("rightKick")) {
                if (keyPressed) {
                    this.inputRightKick = true;
                    fightCharacterControl.setAnim("AttackKickJump", 0.4f, 1, LoopMode.DontLoop);
                } else {
                    this.inputRightKick = false;
                }
            }

            if (name.equals("left")) {
                if (keyPressed) {
                    this.moveLeft = true;
                } else {
                    this.moveLeft = false;
                }
            }
            if (name.equals("right")) {
                if (keyPressed) {
                    this.moveRight = true;
                } else {
                    this.moveRight = false;
                }
            }
            if (name.equals("up")) {
                if (keyPressed) {
                    this.moveUp = true;
                    fightCharacterControl.setAnim("DefenseJump", 0.1f, 1, LoopMode.DontLoop);
                } else {
                    this.moveUp = false;
                }
            }
            if (name.equals("down")) {
                if (keyPressed) {
                    this.moveDown = true;
                    fightCharacterControl.setAnim("DefenseToDown", 0.1f, 1, LoopMode.DontLoop);
                } else {
                    fightCharacterControl.setAnim("Defense", 0.1f, 1, LoopMode.DontLoop);
                    this.moveDown = false;
                }
            }

            if (name.equals("side")) {
                if (keyPressed) {
                    this.psb = true;
                } else {
                    this.psb = false;
                }
            }
            if (name.equals("def")) {
                if (keyPressed) {
                    this.inputDef = true;
                } else {
                    this.inputDef = false;
                }
            }
        }
    }
    //Actions ------------------------------------------------------------------

    public void doAction(String name) {
    }

    public void doCombo() {
        if (this.controlable) {
            if (this.inputLeftPunch) {
                this.inputLeftPunch = false;
            }
            if (this.inputRightPunch) {
                this.inputRightPunch = false;
            }
            if (this.inputLeftKick) {
                this.inputLeftKick = false;
            }
            if (this.inputRightKick) {
                this.inputRightKick = false;
            }

            if (this.moveUp) {
                this.moveUp = false;
            }
        }
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
        this.controlable = false;
        this.move = true;

        this.currentHealth -= damage;

        if (this.currentHealth <= 0.0F) {
            gamePlayManager.winLose();
        } else {
            playSound(damage);
            playAnim(animName, blendtime, animSpeed);
        }
    }

    //Animations----------------------------------------------------------------
    public void playAnim(String animName, float blendTime, float animSpeed) {
        if (this.animControl.getAnimationNames().contains(animName)) {
            this.animChannel.setAnim(animName, blendTime);
            this.animChannel.setLoopMode(LoopMode.DontLoop);
            this.animChannel.setSpeed(animSpeed);
        }
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

        float distance = Math.abs(this.mesh.getLocalTranslation().length());
        if (this.forward) {
            this.lastCase = thiscase1;
            this.lastPressed = thispressed;
            if ((animName1.equals("B")) || (animName1.equals("Bend"))) {
                notOutOfRing(distance);
            } else {
                this.move = true;
            }
            playAnim(animName1, 0.01F, thisSpeed);
        } else {
            this.lastCase = thiscase2;
            this.lastPressed = thispressed;
            if ((animName2.equals("B")) || (animName2.equals("Bend"))) {
                notOutOfRing(distance);
            } else {
                this.move = true;
            }
            playAnim(animName2, 0.01F, thisSpeed);
        }
    }

    protected void backward(String animName1, String animName2, String thiscase1, String thiscase2, String thispressed, float thisSpeed) {
        gamePlayManager.lookAtOther(this.id);
        gamePlayManager.forwardOrBackward();

        float distance = Math.abs(this.mesh.getLocalTranslation().length());
        if (this.forward) {
            this.lastCase = thiscase1;
            this.lastPressed = thispressed;
            if ((animName1.equals("B")) || (animName1.equals("Bend"))) {
                notOutOfRing(distance);
            } else {
                this.move = true;
            }
            playAnim(animName1, 0.01F, thisSpeed);
        } else {
            this.lastCase = thiscase2;
            this.lastPressed = thispressed;
            if ((animName2.equals("B")) || (animName2.equals("Bend"))) {
                notOutOfRing(distance);
            } else {
                this.move = true;
            }
            playAnim(animName2, 0.01F, thisSpeed);
        }
    }

    public void lookAt(FightCharacter fc2) {
        Vector3f fc2Pos = fc2.mesh.getWorldTranslation().clone().setY(0.0F);
        mesh.lookAt(fc2Pos, Vector3f.UNIT_Y);
    }

    public void doMove(float tpf) {
        Vector3f newPos = this.mesh.getLocalTranslation().clone();
        Vector3f opponentPos = opponent.mesh.getLocalTranslation();
        towardVec = newPos.subtract(opponentPos);

        if (this.moveLeft) {
            newPos.addLocal(towardVec.normalize().mult(speed * tpf));
            setLocation(newPos);
        } else if (this.moveRight) {
            newPos.addLocal(towardVec.normalize().mult(-speed * 2 * tpf));
            setLocation(newPos);
        }

    }

    public void setLocation(Vector3f location) {
        this.mesh.setLocalTranslation(location);
    }

    // FX Particles ------------------------------------------------------------
    protected void createHitEffect(String name) {
        this.effect = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 10);
        Material particleMat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        particleMat.setTexture("Texture", assetManager.loadTexture(name + "/flame.png"));
        this.effect.setMaterial(particleMat);
        this.effect.setImagesX(2);
        this.effect.setImagesY(2);
        this.effect.setStartColor(new ColorRGBA(1.0F, 0.0F, 0.0F, 1.0F));
        this.effect.setEndColor(new ColorRGBA(1.0F, 0.0F, 0.0F, 1.0F));

        this.effect.setStartSize(5.0F);
        this.effect.setEndSize(1.1F);

        this.effect.setLowLife(0.5F);
        this.effect.setHighLife(1.0F);
    }

    public void placeHitEffect(Vector3f location) {
        this.effect.setLocalTranslation(location);
        rootNode.attachChild(this.effect);
        this.effect.emitAllParticles();
        this.timeOfEffect = 0.0F;
        this.effecting = true;
    }
    //Sounds--------------------------------------------------------------------

    private void playSound(float damage) {
        if (damage >= 20.0F) {
            this.audioSend = true;
            this.audioName = "audioHh";
            audioR.playSource(this.audioHh);
        } else if (damage > 0.0F) {
            this.audioSend = true;
            this.audioName = "audioT";
            audioR.playSource(this.audioT);
        }
    }
    //Collisions----------------------------------------------------------------

    public void updateCollisionShapes() {
        ArrayList<Spatial> shapes = new ArrayList<Spatial>();
        for (Spatial s : shapes) {
            s.updateGeometricState();
        }
    }

    public void updateCollisionGroup(String groupName) {
    }

    //SETTER & GETTER ----------------------------------------------------------
    public InputListener getActionListener() {
        return actionListener;
    }

    public void setOponent(FightCharacter oponent) {
        this.opponent = oponent;
    }

    public FightCharacter getOpponent() {
        return opponent;
    }
}
