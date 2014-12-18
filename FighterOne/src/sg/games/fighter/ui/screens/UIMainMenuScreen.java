package sg.games.fighter.ui.screens;

import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.tools.SizeValue;
import sg.games.fighter.state.InGameState;
import sg.games.fighter.state.MainMenuState;
import sg.games.fighter.managers.GameGUIManager;

/**
 *
 * @author hungcuong
 */
public class UIMainMenuScreen extends AbstractAppState {

    GameGUIManager gameGUIManager;
//    Screen screen;
    String[] tabNames = {"KeyboardTabPanel", "GraphicsTabPanel", "SoundsTabPanel", "GeneralTabPanel"};
    String currentTab;
    private AppStateManager stateManager;

    public UIMainMenuScreen(GameGUIManager gameGUIManager) {
        this.gameGUIManager = gameGUIManager;
//        stateManager = gameGUIManager.getApp().getStateManager();
    }

    public void bind() {
//        this.screen = screen;
//        gameGUIManager.getInputManager().setCursorVisible(true);
    }

    public void onStartScreen() {
        stateManager.attach(this);
//        if (screen.getScreenId().equals("Options")) {
//            showTab("KeyboardTabPanel");
//        }

    }

    public void onEndScreen() {
        stateManager.detach(this);
    }
    /* Option screen */

    public void showOptions() {
        System.out.println(" Go to Options");
//        gameGUIManager.goToScreen("Options");
    }

    public void backToMainMenu() {
//        gameGUIManager.goToScreen("MainMenuScreen");
    }

//    public void showTab(String tabName) {
//        if (currentTab != tabName) {
//            hideAllTabs();
//            Element e = screen.findElementByName(tabName);
//            e.setVisibleToMouseEvents(true);
//            e.setConstraintHeight(new SizeValue("100%"));
//            e.setVisible(true);
//            e.setFocusable(true);
//
//            currentTab = tabName;
//            e.getParent().resetLayout();
//            screen.resetLayout();
//            screen.layoutLayers();
//
//            System.out.println(" " + e.getId() + " " + e.getConstraintHeight() + " " + e.getY() + " " + e.getConstraintY());
//            System.out.println("-------------------------------------------------");
//        }
//    }
//
//    public void hideAllTabs() {
//        for (String name : tabNames) {
//            //if (name != currentTab) {
//            Element e = screen.findElementByName(name);
//            System.out.println(" " + e.getId() + " " + e.getConstraintHeight() + " " + e.getY() + " " + e.getConstraintY());
//            e.setVisibleToMouseEvents(false);
//            e.setConstraintHeight(new SizeValue("0%"));
//            e.setFocusable(false);
//            e.setVisible(false);
//            e.getLayoutPart().getBox().setY(0);
//
//            System.out.println(" " + e.getId() + " " + e.getConstraintHeight() + " " + e.getY() + " " + e.getConstraintY());
//            //}
//        }
//
//    }
    /* Create game and level select */

    public void createHost() {
        //gameStateManager.changeState();
        //gameGUIManager.getApp().getGameStateManager().loadGame();
    }

    public void createLevelScreen() {
        /*
         Element levelIconsContainer = this.screen.findElementByName("levelIcons");
         Element levelIcon = this.screen.findElementByName("levelIcon");
         for (GBLevel level:gameGUIManager.getStageManager().getAvailableLevels()){
            
         }
         levelIconsContainer.add(levelIcon);
         */
    }

    public void selectLevel(int levelIndex) {
    }

    public void startLevel() {
    }

    public void singleGame() {
        MainMenuState menuState = stateManager.getState(MainMenuState.class);
        menuState.toState(InGameState.class);
    }

    public void goInGame() {
        MainMenuState menuState = stateManager.getState(MainMenuState.class);
        menuState.toState(InGameState.class);
    }

//    public void quitGame() {
//        gameGUIManager.getApp().quitGame();
//    }
}