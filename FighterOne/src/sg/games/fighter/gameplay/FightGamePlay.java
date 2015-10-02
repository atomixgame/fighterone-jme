package sg.games.fighter.gameplay;

import sg.games.fighter.stage.FightCam;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;
import sg.atom.core.lifecycle.AbstractManager;
import sg.atom.corex.player.Player;

import sg.games.fighter.main.FighterMain;
import sg.games.fighter.world.GameLevelStage;

public class FightGamePlay extends AbstractManager {

    protected FightCam fightCam;
    // players 
    protected Player player1;
    protected Player player2;
    protected FightCharacter character1;
    protected FightCharacter character2;
    protected String player1Name;
    protected String player2Name;
    protected ActionListener actionListener;
    protected FightMatch currentMatch;
    protected Node levelNode;
    protected GameLevelStage currentLevel;
    private List<FightCharacter> characters;

    public FightGamePlay(FighterMain app) {
        super(app);
    }

    public void init() {
        super.init();
        this.characters = new ArrayList<FightCharacter>();
        this.fightCam = new FightCam();
        getApp().getStateManager().attach(fightCam);
    }

    public void startLevel(GameLevelStage level) {
        this.currentLevel = level;
        startGame();
    }

    public void startGame() {
        levelNode = app.getWorldManager().getWorldNode();

        startMatch();
    }

    public void startMatch() {
        player1 = new Player(app, "player1");
        player2 = new Player(app, "player2");

        //FIXME: Use EntityFactory
        character1 = new FightCharacter();
        character2 = new FightCharacter();
        character1.init(getApp());
        character2.init(getApp());
        currentMatch = new FightMatch(character1, character2);
        characters.add(character1);
        characters.add(character2);
        character1.setOpponent(character2);
        character2.setOpponent(character1);

        character1.getModel().setLocalTranslation(new Vector3f(0, 0, 5));
        character2.getModel().setLocalTranslation(new Vector3f(0, 0, -5));

        
        getApp().getWorldManager().getWorldNode().attachChild(character1.getModel());
        getApp().getWorldManager().getWorldNode().attachChild(character2.getModel());

        character1.lookAt(character2);
        character2.lookAt(character1);

        setupKeys();
//        fightCam.setEnabled(true);
    }

    public void setupKeys() {
        InputManager inputManager = app.getInputManager();
        inputManager.addMapping("p1left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("p1right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("p1up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("p1down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("p1leftPunch", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("p1rightPunch", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("p1leftKick", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("p1rightKick", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("p1side", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addMapping("p1def", new KeyTrigger(KeyInput.KEY_X));

        inputManager.addMapping("p2left", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("p2right", new KeyTrigger(KeyInput.KEY_L));
        inputManager.addMapping("p2up", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("p2down", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("p2leftPunch", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("p2rightPunch", new KeyTrigger(KeyInput.KEY_O));
        inputManager.addMapping("p2leftKick", new KeyTrigger(KeyInput.KEY_Y));
        inputManager.addMapping("p2rightKick", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("p2side", new KeyTrigger(KeyInput.KEY_N));
        inputManager.addMapping("p2def", new KeyTrigger(KeyInput.KEY_M));

        inputManager.addListener(character1.getActionListener(), new String[]{"p1left", "p1right", "p1up", "p1down",
            "p1leftPunch", "p1rightPunch", "p1leftKick", "p1rightKick", "p1side", "p1def"});
        inputManager.addListener(character2.getActionListener(), new String[]{"p2left", "p2right", "p2up", "p2down",
            "p2leftPunch", "p2rightPunch", "p2leftKick", "p2rightKick", "p2side", "p2def"});
    }

    public FightCharacter otherPlayer(FightCharacter p) {
        if (p == character1) {
            return character2;
        } else {
            return character1;
        }
    }

    /*
     public void lookAt(FightCharacter fc1, FightCharacter fc2) {
     Vector3f fc2Pos = fc2.mesh.getWorldTranslation().clone().setY(0.0F);
     fc1.mesh.lookAt(fc2Pos, Vector3f.UNIT_Y);
     //fc1.playerNode.lookAt(fc2Pos, Vector3f.UNIT_Y);
     //fc1.positionNode.lookAt(fc2Pos, Vector3f.UNIT_Y);
     }
     */
    public boolean separate() {
        return false;
    }

    public void lookAtOther(String id) {
        if (id.equals(character1.id)) {
            //lookAt(character1, character2);
        } else if (id.equals(character2.id)) {
            //lookAt(character2, character1);
        }
    }

    /**
     * Utils
     */
    public float distance2d(Vector3f a, Vector3f b) {
        float dx = a.x - b.x;
        float dz = a.z - b.z;
        float distance = (float) Math.sqrt(dx * dx + dz * dz);
        return distance;
    }

    /**
     * Direction of the character toward the center.
     */
    public void forwardOrBackward() {

    }

    /**
     * FIXME : Replace with Skill Effect & Event Manager
     */
    public void onHit(FightCharacter p1, FightCharacter p2, Spatial colSpatial) {
        String animName = p2.current + p1.attackWhere + p1.attackEffect;
        p1.attack = false;

//        p1.effectPosition = colSpatial.getWorldTranslation();
//        p1.placeHitEffect(p1.effectPosition);
//        p2.animSpeed = 2.0F;
//        p2.current = "stand";
//        p2.onAttacked(animName, 0.01F, p2.animSpeed, p1.dmg);
    }

    public void updateAllCollisionShapes() {
//        character1.updateCollisionShapes();
//        character2.updateCollisionShapes();
    }
    /* FIXME : Replace with collision triggers */

    public void collision() {
        // update the rigs 
        // update the collision groups
//        collision(character1, character2);
//        collision(character2, character1);

    }

//    // Replace with Collsion trigger. and collision group
//    public void collision(FightCharacter fcc1, FightCharacter fcc2) {
//        CharacterRig fc1Rig = fcc1.rig;
//        CharacterRig fc2Rig = fcc2.rig;
//        if (fcc1.attack) {
//            if ((fcc2.current.equals("highdef")) && ((fcc1.attackWhere.equals("high")) || (fcc1.attackWhere.equals("mid")))) {
//                if (fcc1.attackByWhat.equals("hand")) {
//                    if ((fc1Rig.LH.intersects(fc2Rig.H)) || (fc1Rig.LH.intersects(fc2Rig.UB)) || (fc1Rig.LH.intersects(fc2Rig.LB))) {
//                        fcc1.attack = false;
//                        fcc2.animSpeed = 1.0F;
//                        fcc2.onAttacked("highdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                    } else if ((fc1Rig.RH.intersects(fc2Rig.H)) || (fc1Rig.RH.intersects(fc2Rig.UB)) || (fc1Rig.RH.intersects(fc2Rig.LB))) {
//                        fcc1.attack = false;
//                        fcc2.animSpeed = 1.0F;
//                        fcc2.onAttacked("highdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                    }
//
//                } else if (fcc1.attackByWhat.equals("foot")) {
//                    if ((fc1Rig.LF.intersects(fc2Rig.H)) || (fc1Rig.LF.intersects(fc2Rig.UB)) || (fc1Rig.LF.intersects(fc2Rig.LB))) {
//                        fcc1.attack = false;
//                        fcc2.animSpeed = 1.0F;
//                        fcc2.onAttacked("highdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                    } else if ((fc1Rig.RF.intersects(fc2Rig.H)) || (fc1Rig.RF.intersects(fc2Rig.UB)) || (fc1Rig.RF.intersects(fc2Rig.LB))) {
//                        fcc1.attack = false;
//                        fcc2.animSpeed = 1.0F;
//                        fcc2.onAttacked("highdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                    }
//                }
//
//            } else if ((fcc2.current.equals("lowdef")) && (fcc1.attackWhere.equals("low"))) {
//                if (fcc1.attackWhere.equals("low")) {
//                    if (fcc1.attackByWhat.equals("hand")) {
//                        if ((fc1Rig.LH.intersects(fc2Rig.LB)) || (fc1Rig.LH.intersects(fc2Rig.LOW))) {
//                            fcc1.attack = false;
//                            fcc2.animSpeed = 1.0F;
//                            fcc2.onAttacked("lowdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                        } else if ((fc1Rig.RH.intersects(fc2Rig.LB)) || (fc1Rig.RH.intersects(fc2Rig.LOW))) {
//                            fcc1.attack = false;
//                            fcc2.animSpeed = 1.0F;
//                            fcc2.onAttacked("lowdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                        }
//                    } else if (fcc1.attackByWhat.equals("foot")) {
//                        if ((fc1Rig.LF.intersects(fc2Rig.LB)) || (fc1Rig.LF.intersects(fc2Rig.LOW))) {
//                            fcc1.attack = false;
//                            fcc2.animSpeed = 1.0F;
//                            fcc2.onAttacked("lowdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                        } else if ((fc1Rig.RF.intersects(fc2Rig.LB)) || (fc1Rig.RF.intersects(fc2Rig.LOW))) {
//                            fcc1.attack = false;
//                            fcc2.animSpeed = 1.0F;
//                            fcc2.onAttacked("lowdefmove", 0.01F, fcc2.animSpeed, 0.0F);
//                        }
//                    }
//
//                }
//
//            } else if (fcc2.current.contains("def")) {
//                if ((fcc2.current.equals("highdef")) && (fcc1.attackWhere.equals("low"))) {
//                    if (fcc1.attackByWhat.equals("hand")) {
//                        if ((fc1Rig.LH.intersects(fc2Rig.LB)) || (fc1Rig.LH.intersects(fc2Rig.LOW))) {
//                            onHit(fcc1, fcc2, fc2Rig.low);
//                        } else if ((fc1Rig.RH.intersects(fc2Rig.LB)) || (fc1Rig.RH.intersects(fc2Rig.LOW))) {
//                            onHit(fcc1, fcc2, fc2Rig.low);
//                        }
//
//                    } else if (fcc1.attackByWhat.equals("foot")) {
//                        if ((fc1Rig.LF.intersects(fc2Rig.LB)) || (fc1Rig.LF.intersects(fc2Rig.LOW))) {
//                            onHit(fcc1, fcc2, fc2Rig.low);
//                        } else if ((fc1Rig.RF.intersects(fc2Rig.LB)) || (fc1Rig.RF.intersects(fc2Rig.LOW))) {
//                            onHit(fcc1, fcc2, fc2Rig.low);
//                        }
//                    }
//                } else if ((fcc2.current.equals("lowdef")) && (fcc1.attackWhere.equals("mid"))) {
//                    if (fcc1.attackByWhat.equals("hand")) {
//                        if ((fc1Rig.LH.intersects(fc2Rig.H)) || (fc1Rig.RH.intersects(fc2Rig.H))) {
//                            onHit(fcc1, fcc2, fc2Rig.h);
//                        }
//                    } else if (fcc1.attackByWhat.equals("foot")) {
//                        if ((fc1Rig.LF.intersects(fc2Rig.H)) || (fc1Rig.RF.intersects(fc2Rig.H))) {
//                            onHit(fcc1, fcc2, fc2Rig.h);
//                        }
//                    }
//                }
//            } else {
//                if (fcc1.attackByWhat.equals("hand")) {
//                    if (fcc1.attackWhere.equals("high")) {
//                        if (fcc2.current.equals("stand")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.H)) || (fc1Rig.RH.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.UB)) || (fc1Rig.RH.intersects(fc2Rig.UB))) {
//                                onHit(fcc1, fcc2, fc2Rig.ub);
//                            }
//
//                        } else if (fcc2.current.equals("down")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.H)) || (fc1Rig.RH.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            }
//
//                        } else if (!fcc2.current.equals("onfloor")) {
//                            fcc2.current.equals("inair");
//                        }
//
//                    } else if (fcc1.attackWhere.equals("mid")) {
//                        if (fcc2.current.equals("stand")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.UB)) || (fc1Rig.RH.intersects(fc2Rig.UB))) {
//                                onHit(fcc1, fcc2, fc2Rig.ub);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.LB)) || (fc1Rig.RH.intersects(fc2Rig.LB))) {
//                                onHit(fcc1, fcc2, fc2Rig.lb);
//                            }
//
//                        } else if (fcc2.current.equals("down")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.H)) || (fc1Rig.RH.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            }
//                        } else if (!fcc2.current.equals("onfloor")) {
//                            fcc2.current.equals("inair");
//                        }
//
//                    } else if (fcc1.attackWhere.equals("low")) {
//                        if (fcc2.current.equals("stand")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.RF)) || (fc1Rig.RH.intersects(fc2Rig.RF))) {
//                                onHit(fcc1, fcc2, fc2Rig.rf);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.LF)) || (fc1Rig.RH.intersects(fc2Rig.LF))) {
//                                onHit(fcc1, fcc2, fc2Rig.lf);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.LOW)) || (fc2Rig.RH.intersects(fc2Rig.LOW))) {
//                                onHit(fcc1, fcc2, fc2Rig.low);
//                            }
//
//                        } else if (fcc2.current.equals("down")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.LB)) || (fc1Rig.RH.intersects(fc2Rig.LB))) {
//                                onHit(fcc1, fcc2, fc2Rig.lb);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.UB)) || (fc1Rig.RH.intersects(fc2Rig.UB))) {
//                                onHit(fcc1, fcc2, fc2Rig.ub);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.LOW)) || (fc2Rig.RH.intersects(fc2Rig.LOW))) {
//                                onHit(fcc1, fcc2, fc2Rig.low);
//                            }
//
//                        } else if (fcc2.current.equals("onfloor")) {
//                            if ((fc1Rig.LH.intersects(fc2Rig.H)) || (fc1Rig.RH.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            } else if ((fc1Rig.LH.intersects(fc2Rig.LB)) || (fc1Rig.RH.intersects(fc2Rig.LB))) {
//                                onHit(fcc1, fcc2, fc2Rig.lb);
//                            }
//                        } else {
//                            fcc2.current.equals("inair");
//                        }
//
//                    }
//
//                }
//
//                if (fcc1.attackByWhat.equals("foot")) {
//                    if (fcc1.attackWhere.equals("high")) {
//                        if (fcc2.current.equals("stand")) {
//                            if ((fc1Rig.LF.intersects(fc2Rig.H)) || (fc1Rig.RF.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.UB)) || (fc1Rig.RF.intersects(fc2Rig.UB))) {
//                                onHit(fcc1, fcc2, fc2Rig.ub);
//                            }
//
//                        } else if (fcc2.current.equals("down")) {
//                            if ((fc1Rig.LF.intersects(fc2Rig.H)) || (fc1Rig.RF.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            }
//
//                        } else if (!fcc2.current.equals("onfloor")) {
//                            fcc2.current.equals("inair");
//                        }
//
//                    } else if (fcc1.attackWhere.equals("mid")) {
//                        if (fcc2.current.equals("stand")) {
//                            if ((fc1Rig.LF.intersects(fc2Rig.UB)) || (fc1Rig.RF.intersects(fc2Rig.UB))) {
//                                onHit(fcc1, fcc2, fc2Rig.ub);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.LB)) || (fc1Rig.RF.intersects(fc2Rig.LB))) {
//                                onHit(fcc1, fcc2, fc2Rig.lb);
//                            }
//
//                        } else if (fcc2.current.equals("down")) {
//
//                            if ((fc1Rig.LF.intersects(fc2Rig.H)) || (fc1Rig.RF.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            }
//                        } else if (!fcc2.current.equals("onfloor")) {
//                            fcc2.current.equals("inair");
//                        }
//
//                    } else if (fcc1.attackWhere.equals("low")) {
//                        if (fcc2.current.equals("stand")) {
//                            if ((fc1Rig.LF.intersects(fc2Rig.RF)) || (fc1Rig.RF.intersects(fc2Rig.RF))) {
//                                onHit(fcc1, fcc2, fc2Rig.rf);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.LF)) || (fc1Rig.RF.intersects(fc2Rig.LF))) {
//                                onHit(fcc1, fcc2, fc2Rig.lf);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.LOW)) || (fc1Rig.RF.intersects(fc2Rig.LOW))) {
//                                onHit(fcc1, fcc2, fc2Rig.low);
//                            }
//
//                        } else if (fcc2.current.equals("down")) {
//                            if ((fc1Rig.LF.intersects(fc2Rig.LB)) || (fc1Rig.RF.intersects(fc2Rig.LB))) {
//                                onHit(fcc1, fcc2, fc2Rig.lb);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.UB)) || (fc1Rig.RF.intersects(fc2Rig.UB))) {
//                                onHit(fcc1, fcc2, fc2Rig.ub);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.LOW)) || (fc1Rig.RF.intersects(fc2Rig.LOW))) {
//                                onHit(fcc1, fcc2, fc2Rig.low);
//                            }
//
//                        } else if (fcc2.current.equals("onfloor")) {
//                            if ((fc1Rig.LF.intersects(fc2Rig.H)) || (fc1Rig.RF.intersects(fc2Rig.H))) {
//                                onHit(fcc1, fcc2, fc2Rig.h);
//                            } else if ((fc1Rig.LF.intersects(fc2Rig.LB)) || (fc1Rig.RF.intersects(fc2Rig.LB))) {
//                                onHit(fcc1, fcc2, fc2Rig.lb);
//                            }
//                        } else {
//                            fcc2.current.equals("inair");
//                        }
//
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
    public boolean isOutOfRing(String id) {
        //FIXME: use the level.
        /*
         if (id.equals("character1")) {
         return character1.position.getWorldTranslation().length() > floorsize;
         }

         return character2.position.getWorldTranslation().length() > floorsize;
         */
        return false;
    }

    public void doOutOfRing(FightCharacter p1) {
        //p1.patch();
//        p1.controlable = false;
//        p1.currentHealth = 0.0F;
//        p1.animChannel.setAnim("OUT", 0.01F);
//        p1.animChannel.setLoopMode(LoopMode.DontLoop);
        //setHealthBar(character1, currentBar1, healthBar1);
    }

    public void onOutOfRing(String id) {
        if (id.equals("p1")) {
            doOutOfRing(character1);
            winLose();
        } else {
            doOutOfRing(character2);
            winLose();
        }
    }

    public void doWinLose(FightCharacter fc, String animName) {
//        fc.audioSend = true;
//        fc.audioName = "audioWin";
        //audioR.playSource(fc.audioWin);
//        fc.animChannel.setAnim(animName);
//        fc.animChannel.setLoopMode(LoopMode.DontLoop);
    }

    public void doKO(FightCharacter p1, String animName) {
        //audioR.playSource(character1.audioKO);
//        p1.animSpeed = 1.0F;
//        p1.animChannel.setAnim(animName);
//        p1.animChannel.setLoopMode(LoopMode.DontLoop);
//        p1.animChannel.setSpeed(p1.animSpeed);
    }

    /**
     * Check the winning condition of the game.
     */
    public void winLose() {
        //main.ingame = false;

//        if ((character1.animChannel.getAnimationName().equals("OUT")) && (!character2.animChannel.getAnimationName().equals("OUT"))) {
//            if (character2.currentHealth <= 10.0F) {
//                doWinLose(character2, "greatwin");
//            } else if (character2.currentHealth == 100.0F) {
//                doWinLose(character2, "perfectwin");
//            } else if ((character2.currentHealth > 10.0F) && (character2.currentHealth < 100.0F)) {
//                doWinLose(character2, "normalwin");
//            }
//        } else if ((!character1.animChannel.getAnimationName().equals("OUT")) && (character2.animChannel.getAnimationName().equals("OUT"))) {
//            if (character1.currentHealth <= 10.0F) {
//                doWinLose(character1, "greatwin");
//            } else if (character1.currentHealth == 100.0F) {
//                doWinLose(character1, "perfectwin");
//            } else if ((character1.currentHealth > 10.0F) && (character1.currentHealth < 100.0F)) {
//                doWinLose(character1, "normalwin");
//            }
//        } else if ((!character1.animChannel.getAnimationName().equals("OUT")) || (!character2.animChannel.getAnimationName().equals("OUT"))) {
//            if ((character1.currentHealth <= 0.0F) && (character2.currentHealth > 0.0F)) {
//                doKO(character1, "KO");
//                if (character2.currentHealth <= 10.0F) {
//                    doWinLose(character2, "greatwin");
//                } else if (character2.currentHealth == 100.0F) {
//                    doWinLose(character2, "perfectwin");
//                } else if ((character2.currentHealth > 10.0F) && (character2.currentHealth < 100.0F)) {
//                    doWinLose(character2, "normalwin");
//                }
//            } else if ((character2.currentHealth <= 0.0F) && (character1.currentHealth > 0.0F)) {
//                doKO(character2, "KO");
//                if (character1.currentHealth <= 10.0F) {
//                    doWinLose(character1, "greatwin");
//                } else if (character1.currentHealth == 100.0F) {
//                    doWinLose(character1, "perfectwin");
//                } else if ((character1.currentHealth > 10.0F) && (character1.currentHealth < 100.0F)) {
//                    doWinLose(character1, "normalwin");
//                }
//            } else {
//                doKO(character1, "KO");
//                doKO(character2, "KO");
//            }
//        }
    }

    public void update(float tpf) {
        /*
         collision();
         */
    }

    public void createPlayerCharacter(FightCharacter character, String modelPath, Vector3f pos) {
//        character.init();
    }

    public List<FightCharacter> getCharacters() {
        return characters;
    }

    @Override
    public FighterMain getApp() {
        return (FighterMain) super.getApp();
    }
}
