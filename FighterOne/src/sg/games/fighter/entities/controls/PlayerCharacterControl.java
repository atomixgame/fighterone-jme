package sg.games.fighter.entities.controls;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author cuongnguyen
 */
public class PlayerCharacterControl extends AbstractControl {

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

    public void handleInput(String name, boolean keyPressed, float tpf) {

//        StringBuffer fullMessage = new StringBuffer(name);
//
//        if (fullMessage.substring(0, 2).equals(FightCharacter.this.id)) {
//            //System.out.println("Key!");
//
//            //Remove the name.
//            fullMessage = fullMessage.replace(0, 2, "");
//            name = fullMessage.toString();
//
//            if (name.equals("leftPunch")) {
//                if (keyPressed) {
//                    this.inputLeftPunch = true;
//
//                } else {
//                    this.inputLeftPunch = false;
//                }
//            }
//            if (name.equals("rightPunch")) {
//                if (keyPressed) {
//                    this.inputRightPunch = true;
//                } else {
//                    this.inputRightPunch = false;
//                }
//            }
//            if (name.equals("leftKick")) {
//                if (keyPressed) {
//                    this.inputLeftKick = true;
//                    fightCharacterControl.setAnim("AttackKick", 0.4f, 1, LoopMode.DontLoop);
//                } else {
//                    this.inputLeftKick = false;
//                }
//            }
//            if (name.equals("rightKick")) {
//                if (keyPressed) {
//                    this.inputRightKick = true;
//                    fightCharacterControl.setAnim("AttackKickJump", 0.4f, 1, LoopMode.DontLoop);
//                } else {
//                    this.inputRightKick = false;
//                }
//            }
//
//            if (name.equals("left")) {
//                if (keyPressed) {
//                    this.moveLeft = true;
//                } else {
//                    this.moveLeft = false;
//                }
//            }
//            if (name.equals("right")) {
//                if (keyPressed) {
//                    this.moveRight = true;
//                } else {
//                    this.moveRight = false;
//                }
//            }
//            if (name.equals("up")) {
//                if (keyPressed) {
//                    this.moveUp = true;
//                    fightCharacterControl.setAnim("DefenseJump", 0.1f, 1, LoopMode.DontLoop);
//                } else {
//                    this.moveUp = false;
//                }
//            }
//            if (name.equals("down")) {
//                if (keyPressed) {
//                    this.moveDown = true;
//                    fightCharacterControl.setAnim("DefenseToDown", 0.1f, 1, LoopMode.DontLoop);
//                } else {
//                    fightCharacterControl.setAnim("Defense", 0.1f, 1, LoopMode.DontLoop);
//                    this.moveDown = false;
//                }
//            }
//
//            if (name.equals("side")) {
//                if (keyPressed) {
//                    this.psb = true;
//                } else {
//                    this.psb = false;
//                }
//            }
//            if (name.equals("def")) {
//                if (keyPressed) {
//                    this.inputDef = true;
//                } else {
//                    this.inputDef = false;
//                }
//            }
//        }
    }
    //Actions ------------------------------------------------------------------

    public void doAction(String name) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    @Override
    protected void controlUpdate(float tpf) {
    }

    public void doCombo() {
        if (this.isEnabled()) {
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
}
