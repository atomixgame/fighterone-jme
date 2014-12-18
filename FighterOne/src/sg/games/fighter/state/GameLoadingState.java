/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.state;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import java.util.logging.Logger;
import sg.games.fighter.main.FighterMain;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class GameLoadingState extends AbstractAppState {

    private FighterMain app;
    private Node rootNode;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private float oldPercent = -1f;
    Logger logger = Logger.getLogger(GameLoadingState.class.getName());

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (FighterMain) app; // can cast Application to something more specific

        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
//        this.gameStateManager = (GameStateManager) this.app.getGameStateManager();
//        this.gameGUIManager = this.app.getGameGUIManager();
//        this.stageManager = this.app.getStageManager();
        setEnabled(true);

    }
//
//    @Override
//    public void setEnabled(boolean enabled) {
//        // Pause and unpause
//        super.setEnabled(enabled);
//        if (enabled) {
//            initPhase();
//        } else {
//            if (loadComplete) {
//                //nextState();
//            }
//
//        }
//    }
//
//    @Override
//    protected void initPhase() {
//        loadingScreen = gameGUIManager.getGUI(Nifty.class).getScreen("loadingScreen");
//        if (loadingScreen == null) {
//            throw new RuntimeException("May be: You didn't add the LoadingScreen in XML yet!");
//        } else {
//            guiController = (UILoadingScreen) loadingScreen.getScreenController();
//        }
//    }
//
//    @Override
//    protected void loadPhase() {
//        stageManager.loadStage();
//        System.out.println("Finish Loading !");
//        stageManager.configStage();
//        System.out.println("Finish Config !");
//    }
//
//    @Override
//    protected void finishPhase() {
//        stageManager.attachStage();
//    }
//
//    @Override
//    protected void nextState() {
//        //gameStateManager.goInGame();
//
//        InGameState inGameState = new InGameState();
//        GameLoadingState loadingState = this;
//        stateManager.detach(loadingState);
//        stateManager.attach(inGameState);
//    }
//
//    @Override
//    protected void watchTask() {
//        // Wait for the GUI controller to finish screen changing
//        if (guiController != null && loadingScreen.isRunning()) {
//            if (stageManager.getProgressInfo().getCurrentProgressName() != null) {
//                float currentProcess = stageManager.getProgressInfo().getCurrentProgressPercent();
//                if (oldPercent != currentProcess) {
//                    guiController.setProgress(currentProcess, stageManager.getProgressInfo().getCurrentProgressName());
//                    oldPercent = currentProcess;
//                }
//            }
//        }
//    }
//
//    public void updateProgressBar(boolean hasError, String errorMsg) {
//        // Wait for the GUI controller to finish screen changing
//        if (guiController != null && gameGUIManager.getGUI(Nifty.class).getCurrentScreen().getScreenId().equals("loadingScreen")) {
//            if (hasError) {
//                //
//                guiController.setProgress(0, "Error ! Press Esc to back to main menu :" + errorMsg);
//            } else {
//                if (stageManager.getProgressInfo().getCurrentProgressName() != null) {
//                    float currentProcess = stageManager.getProgressInfo().getCurrentProgressPercent();
//                    if (oldPercent != currentProcess) {
//                        guiController.setProgress(currentProcess, stageManager.getProgressInfo().getCurrentProgressName());
//                        oldPercent = currentProcess;
//
//                        System.out.println("Load :" + oldPercent);
//                    }
//                }
//            }
//        }
//    }
}
