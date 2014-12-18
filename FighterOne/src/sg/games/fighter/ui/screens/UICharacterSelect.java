/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.ui.screens;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import sg.games.fighter.managers.GameGUIManager;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class UICharacterSelect  implements ScreenController {

    String player1, player2;
    GameGUIManager guiManager;

    public void setP1(String name) {
        player1 = name;
        //logger.log(Level.WARNING, "player1: " + player1);
    }

    public void setP2(String name) {
        player2 = name;
        //logger.log(Level.WARNING, "player2: " + player2);
    }

    // IMAGE
    public void displayAvatars() {
//        guiManager.setGUIImage("p1", player1 + "/avatar.png");
//        guiManager.setGUIImage("p2", player2 + "/avatar.png");
    }

    public void changeImage1(String name) {
//        guiManager.setGUIImage("avatarofp1", name + "/avatar.png");
    }

    public void changeImage2(String name) {
//        guiManager.setGUIImage("avatarofp2", name + "/avatar.png");
    }

    public void setp1choosep2(String name) {
        setP1(name);
        //guiManager.nextAction();
    }

    public void bind(Nifty nifty, Screen screen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onStartScreen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEndScreen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
