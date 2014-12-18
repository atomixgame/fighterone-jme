package sg.games.fighter.state;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import java.util.logging.Logger;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.stage.FightCam;
import sg.games.fighter.world.GameLevel;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class InGameState extends AbstractAppState {
    private static final Logger logger = Logger.getLogger(InGameState.class.getName());
    private FighterMain app;
//    private Node rootNode;
//    private AssetManager assetManager;
//    private AppStateManager stateManager;    
//    private GameGUIManager gameGUIManager;
//    private StageManager stageManager;
//    private boolean gamePause;
//    private WorldManager worldManager;
//    private FightGamePlay gamePlayManager;
//    private GameLevel currentLevel;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (FighterMain) app; // can cast Application to something more specific
//
//        this.rootNode = this.app.getRootNode();
//        this.assetManager = this.app.getAssetManager();
//        this.stateManager = this.app.getStateManager();
//        this.gameGUIManager = this.app.getGameGUIManager();
//        this.stageManager = (StageManager) this.app.getStageManager();
        setEnabled(true);

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (enabled) {
            goInGame();
        } else {
            goOutGame();
        }
    }

    public void goInGame() {
        GameLevel currentLevel = new GameLevel(app, "Level_Default", "Scenes/Levels/Draculas Castle/Dracula's Castle.j3o");
        app.getWorldManager().loadLevel(currentLevel);
        app.getWorldManager().attachWorld();
//        app.getStageManager().configStage();
//        app.getStageManager().goInGame();
//        getGameGUIManager().goToScreen("InGameScreen");
        app.getGamePlayManager().startLevel(currentLevel);
        app.getStateManager().attach(new FightCam());
    }

    public void pauseGame() {
        //getStageManager().pauseGame();
    }

    public void goOutGame() {
        //getStageManager().goOutGame();
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
    }

    public void resumeGame() {
        //getGameGUIManager().resumeGame();
        //getStageManager().resumeGame();
    }

    public FighterMain getApp() {
        return app;
    }
}
