package sg.games.fighter.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.logging.Logger;
import sg.atom.core.execution.BaseGameState;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.world.GameLevelStage;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class InGameState extends BaseGameState {

    private static final Logger logger = Logger.getLogger(InGameState.class.getName());

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
//
        setEnabled(true);

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (enabled) {
            getApp().getWorldManager().load();
            goInGame();
        } else {
            goOutGame();
        }
    }

    public void goInGame() {
        GameLevelStage currentLevel = new GameLevelStage(getApp(), "Level_Default", "Scenes/Levels/Draculas Castle/Dracula's Castle.j3o");
        loadLevel(currentLevel);
    }

    protected void loadLevel(GameLevelStage currentLevel) {
        Spatial levelNode = getApp().getAssetManager().loadModel(currentLevel.getPath());
        getApp().getWorldManager().getWorldNode().attachChild(levelNode);
        getApp().getGamePlayManager().startLevel(currentLevel);
    }

    public void pauseGame() {
    }

    public void goOutGame() {
    }

    public void resumeGame() {
    }

    @Override
    public FighterMain getApp() {
        return (FighterMain) app;
    }
}
