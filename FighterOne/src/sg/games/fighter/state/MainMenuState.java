/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.state;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.scene.Node;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.managers.GameGUIManager;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class MainMenuState extends AbstractAppState {

    private FighterMain app;
    private Node rootNode;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    //private ViewPort viewPort;
    private GameGUIManager gameGUIManager;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (FighterMain) app; // can cast Application to something more specific
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.inputManager = this.app.getInputManager();
        this.gameGUIManager = (GameGUIManager) this.app.getGameGUIManager();
        setEnabled(true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        // Pause and unpause
        super.setEnabled(enabled);
        if (enabled) {
//            gameGUIManager.goToScreen("MainMenuScreen");
        } else {
        }
    }

    public void toState(Class<? extends AppState> newStateClass) {
        if (InGameState.class.isAssignableFrom(newStateClass)) {
            stateManager.detach(this);
            stateManager.attach(new InGameState());
        }
    }

    public void fromState(Class<? extends AppState> newStateClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
