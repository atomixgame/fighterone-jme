/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.ui.screens;

import com.jme3.app.state.AppStateManager;
import sg.games.fighter.state.GameLoadingState;
import sg.games.fighter.state.MainMenuState;
import sg.games.fighter.managers.GameGUIManager;

/**
 *
 * @author hungcuong
 */
public class UILevelSelectScreen {

    GameGUIManager gameGUIManager;
    private String sky;
    private String earth;
    private String screenId;
    private AppStateManager stateManager;
    private String character;

    public UILevelSelectScreen(GameGUIManager gameGUIManager) {
        this.gameGUIManager = (GameGUIManager) gameGUIManager;
//        stateManager = gameGUIManager.getApp().getStateManager();
    }

    public void setp2choosescene(String name) {
        //setP2(name);
    }

    public void changeSkyImage(String name) {
//        gameGUIManager.setGUIImage("sky", "Textures/Levels/sky/" + name + ".png");
    }

    public void changeEarthImage(String name) {
//        gameGUIManager.setGUIImage("earth", "Textures/Levels/sky/earth/" + name + ".png");
    }

    public void setChooseCharacter(String name) {
        character= name;
        nextAction();
    }

    public void setSky(String name) {
        sky = name;
        System.out.println("Set sky");
        nextAction();
    }

    public void setEarth(String name) {
        earth = name;
        System.out.println("Set Earth");
        nextAction();
    }

    public void bind() {

    }

    public void onStartScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEndScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nextAction() {
        if (screenId.endsWith("ChooseCharacter")) {
//            gameGUIManager.goToScreen("ChooseSkyScreen");
        } else if (screenId.endsWith("ChooseSkyScreen")) {
//            gameGUIManager.goToScreen("ChooseEarthScreen");
        } else if (screenId.endsWith("ChooseEarthScreen")) {
            //gameGUIManager.goToScreen("ChooseEarthScreen");
            goInGame();
        }

    }

    public void goInGame() {
        MainMenuState menuState = stateManager.getState(MainMenuState.class);
        GameLoadingState loadingState = new GameLoadingState();
        stateManager.detach(menuState);
//        stateManager.attach(loadingState);
    }
}
