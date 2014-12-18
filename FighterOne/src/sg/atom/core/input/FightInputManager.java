/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.atom.core.input;

import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.button.ButtonControl;
import de.lessvoid.nifty.screen.Screen;
import java.util.logging.Logger;
import sg.games.fighter.gameplay.FightGamePlay;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.managers.GameGUIManager;

/**
 *
 * @author hungcuong
 */
public class FightInputManager {
    // short cut

    protected Logger logger;
    protected FightGamePlay functions;
    protected Nifty nifty;
    protected FighterMain main;
    private InputManager input;
    // game shortcut
    private GameGUIManager guiManager;
    private ActionListener allKeyListener;

    public void inputKey(String name, int k) {
        input.addMapping(name, new Trigger[]{new KeyTrigger(k)});
    }

    public void initAllInputListener() {
        inputKey("Q", 16);
        inputKey("W", 17);
        inputKey("E", 18);
        inputKey("R", 19);
        inputKey("T", 20);
        inputKey("Z", 44);
        inputKey("U", 22);
        inputKey("I", 23);
        inputKey("O", 24);
        inputKey("P", 25);
        inputKey("A", 30);
        inputKey("S", 31);
        inputKey("D", 32);
        inputKey("F", 33);
        inputKey("G", 34);
        inputKey("H", 35);
        inputKey("J", 36);
        inputKey("K", 37);
        inputKey("L", 38);
        inputKey("Y", 21);
        inputKey("X", 45);
        inputKey("C", 46);
        inputKey("V", 47);
        inputKey("B", 48);
        inputKey("N", 49);
        inputKey("M", 50);
        inputKey(",", 51);
        inputKey(".", 52);
        inputKey("-", 12);
        inputKey("1", 2);
        inputKey("2", 3);
        inputKey("3", 4);
        inputKey("4", 5);
        inputKey("5", 6);
        inputKey("6", 7);
        inputKey("7", 8);
        inputKey("8", 9);
        inputKey("9", 10);
        inputKey("0", 11);
        inputKey("SPACE", 57);
        inputKey("RETURN", 28);
        inputKey("PGUP", 201);
        inputKey("PGDN", 209);
        inputKey("DELETE", 211);
        inputKey("INSERT", 210);
        inputKey("HOME", 199);
        inputKey("END", 207);
        inputKey("NUMPAD0", 82);
        inputKey("NUMPAD1", 79);
        inputKey("NUMPAD2", 80);
        inputKey("NUMPAD3", 81);
        inputKey("NUMPAD4", 75);
        inputKey("NUMPAD5", 76);
        inputKey("NUMPAD6", 77);
        inputKey("NUMPAD7", 71);
        inputKey("NUMPAD8", 72);
        inputKey("NUMPAD9", 73);
        inputKey("UP", 200);
        inputKey("DOWN", 208);
        inputKey("LEFT", 203);
        inputKey("RIGHT", 205);

        allKeyListener = new ActionListener() {
            int counter = 0;

            public void onAction(String name, boolean keyPressed, float fpf) {
            }
        };
        input.addListener(allKeyListener, new String[]{"Q", "W", "E", "R", "T", "Z", "U", "I", "O", "P",
                    "A", "S", "D", "F", "G", "H", "J", "K", "L",
                    "Y", "X", "C", "V", "B", "N", "M", ",", ".", "-",
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                    "SPACE", "RETURN", "PGUP", "PGDN", "DELETE", "INSERT", "HOME", "END",
                    "NUMPAD0", "NUMPAD1", "NUMPAD2", "NUMPAD3", "NUMPAD4", "NUMPAD5", "NUMPAD6", "NUMPAD7", "NUMPAD8", "NUMPAD9",
                    "UP", "DOWN", "LEFT", "RIGHT"});
    }

    public void setFocus(String name, boolean keyPressed, float fpf, int counter) {
        Screen currentScreen = nifty.getCurrentScreen();
        if ((name.equals("UP")) && (!keyPressed)) {
            try {
                int id = Integer.parseInt(currentScreen.getFocusHandler().getKeyboardFocusElement().getId());

                if (((id == 0) || (id == 10)) && (counter == 0)) {
                    helperOfInitAllInput();
                    currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id + 1)));
                    counter += 1;
                    return;
                }
                if (((id == 0) || (id == 10)) && (counter > 0)) {
                    currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id + 1)));
                    helperOfInitAllInput();
                    currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id + 2)));
                    counter = 0;
                    return;
                }

                currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id + 1)));
                helperOfInitAllInput();
                currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id + 2)));
            } catch (Exception localException) {
            }
        } else if ((name.equals("DOWN")) && (!keyPressed)) {
            try {
                int id = Integer.parseInt(currentScreen.getFocusHandler().getKeyboardFocusElement().getId());
                if (((id == 9) || (id == 19)) && (counter == 0)) {
                    currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id - 1)));
                    helperOfInitAllInput();
                    currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id)));
                    counter += 1;
                    return;
                }
                if (((id == 9) || (id == 19)) && (counter > 0)) {
                    String old8 = ((ButtonControl) currentScreen.findNiftyControl("8", ButtonControl.class)).getText();
                    String old18 = ((ButtonControl) currentScreen.findNiftyControl("18", ButtonControl.class)).getText();
                    helperOfInitAllInput();
                    ((ButtonControl) currentScreen.findNiftyControl("8", ButtonControl.class)).setText(old8);
                    ((ButtonControl) currentScreen.findNiftyControl("18", ButtonControl.class)).setText(old18);
                    currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(Integer.toString(id + 1)));
                    counter = 0;
                    return;
                }

                passFocus(currentScreen, -1);
                helperOfInitAllInput();
            } catch (Exception localException1) {
            }
        } else if ((name.equals("SPACE")) && (!keyPressed)) {
            try {
                helperOfInitAllInput();
                passFocus(currentScreen, 1);
            } catch (Exception localException2) {
            }
        } else if ((name.equals("RETURN")) && (!keyPressed)) {
            helperOfInitAllInput();
            passFocus(currentScreen, 1);
        } else if ((name.endsWith("")) && (!keyPressed)) {
            try {
                helperOfInitAllInput();
                passFocus(currentScreen, 1);
            } catch (Exception localException3) {
            }
        }
    }

    public void passFocus(Screen currentScreen, int idi) {
        int id = Integer.parseInt(currentScreen.getFocusHandler().getKeyboardFocusElement().getId());
        String nextid = Integer.toString(id + idi);
        currentScreen.getFocusHandler().setKeyFocus(currentScreen.findElementByName(nextid));
    }

    public void helperOfInitAllInput() {
        //guiManager.syncButtonText();
    }
    
    
    public void populateKeysConfig() {
        /*
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                ((ButtonControl) nifty.getCurrentScreen().findElementByName(Integer.toString(i)).findNiftyControl(Integer.toString(i), ButtonControl.class))
                        .setText(config.data1[i].getFirst() + ": " + hash(config.data1[i].getSecond()));
            } else {
                ((ButtonControl) nifty.getCurrentScreen().findElementByName(Integer.toString(i)).findNiftyControl(Integer.toString(i), ButtonControl.class))
                        .setText(config.data2[(i - 10)].getFirst() + ": " + hash(config.data2[(i - 10)].getSecond()));
            }
        }
        */
    }

    public String hash(int keyCode) {
        String keyChar = null;
        switch (keyCode) {
            case 2:
                keyChar = "1";
                break;
            case 3:
                keyChar = "2";
                break;
            case 4:
                keyChar = "3";
                break;
            case 5:
                keyChar = "4";
                break;
            case 6:
                keyChar = "5";
                break;
            case 7:
                keyChar = "6";
                break;
            case 8:
                keyChar = "7";
                break;
            case 9:
                keyChar = "8";
                break;
            case 10:
                keyChar = "9";
                break;
            case 11:
                keyChar = "0";
                break;
            case 16:
                keyChar = "Q";
                break;
            case 17:
                keyChar = "W";
                break;
            case 18:
                keyChar = "E";
                break;
            case 19:
                keyChar = "R";
                break;
            case 20:
                keyChar = "T";
                break;
            case 44:
                keyChar = "Z";
                break;
            case 22:
                keyChar = "U";
                break;
            case 23:
                keyChar = "I";
                break;
            case 24:
                keyChar = "O";
                break;
            case 25:
                keyChar = "P";
                break;
            case 30:
                keyChar = "A";
                break;
            case 31:
                keyChar = "S";
                break;
            case 32:
                keyChar = "D";
                break;
            case 33:
                keyChar = "F";
                break;
            case 34:
                keyChar = "G";
                break;
            case 35:
                keyChar = "H";
                break;
            case 36:
                keyChar = "J";
                break;
            case 37:
                keyChar = "K";
                break;
            case 38:
                keyChar = "L";
                break;
            case 21:
                keyChar = "Y";
                break;
            case 45:
                keyChar = "X";
                break;
            case 46:
                keyChar = "C";
                break;
            case 47:
                keyChar = "V";
                break;
            case 48:
                keyChar = "B";
                break;
            case 49:
                keyChar = "N";
                break;
            case 50:
                keyChar = "M";
                break;
            case 210:
                keyChar = "Insert";
                break;
            case 211:
                keyChar = "Delete";
                break;
            case 199:
                keyChar = "Home";
                break;
            case 207:
                keyChar = "End";
                break;
            case 201:
                keyChar = "PgUp";
                break;
            case 209:
                keyChar = "PgDn";
                break;
            case 57:
                keyChar = "Space";
                break;
            case 28:
                keyChar = "Enter";
                break;
            case 203:
                keyChar = "Left";
                break;
            case 205:
                keyChar = "Right";
                break;
            case 200:
                keyChar = "Up";
                break;
            case 208:
                keyChar = "Down";
                break;
            case 51:
                keyChar = ",";
                break;
            case 52:
                keyChar = ".";
                break;
            case 12:
                keyChar = "-";
                break;
        }

        return keyChar;
    }
}
