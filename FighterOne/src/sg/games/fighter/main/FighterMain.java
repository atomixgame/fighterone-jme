package sg.games.fighter.main;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import java.util.logging.Logger;
import sg.games.fighter.gameplay.FightGamePlay;
import sg.games.fighter.managers.StageManager;
import sg.games.fighter.state.MainMenuState;
import sg.games.fighter.managers.GameGUIManager;
import sg.games.fighter.managers.WorldManager;
import sg.games.fighter.state.InGameState;

public class FighterMain extends SimpleApplication {
    // input

    public static final Logger logger = Logger.getLogger(FighterMain.class.getName());
    private static FighterMain selfRef;
    private StageManager stageManager;
    private GameGUIManager gameGUIManager;
    private WorldManager worldManager;
    // Network
    public String networkType;
    public boolean isNetworked = false;
    private FightGamePlay gamePlayManager;

    /**
     * Constructs singleton instance of Object.
     */
    private FighterMain() {
        //selfRef = this;
    }

    /**
     * Provides reference to singleton object of Object.
     *
     * @return Singleton instance of Object.
     */
    public static final FighterMain getInstance() {
        if (selfRef == null) {
            selfRef = new FighterMain();
        }
        return selfRef;
    }

    public static void main(String[] args) {
        AppSettings setting = new AppSettings(true);
        //setting.setFrameRate(60);
        setting.setTitle("Fighter One");
        setting.setWidth(840);
        setting.setHeight(480);
        FighterMain app = FighterMain.getInstance();
        //Logger.getLogger("").setLevel(Level.INFO);
        app.setShowSettings(false);
        app.setSettings(setting);

        app.start();
    }

    @Override
    public void simpleInitApp() {
        //setDisplayFps(false);
        //setDisplayStatView(false);
        lazyInit();
        initManagers();
        initKeys();
        startUp();
    }

    public void lazyInit() {
    }

    public void initManagers() {
        //gameGUIManager = new LBGameGUIManager(this);
        this.gameGUIManager = GameGUIManager.getInstance();
//      this.gameGUIManager.lazyInit(this);
//      this.gameGUIManager.init();
        this.stageManager = StageManager.getInstance();
        this.worldManager = new WorldManager(this);
        this.gamePlayManager = FightGamePlay.getInstance();
        //stageManager = new LBStageManager(this);
//        stageManager.init();
    }

    public void startUp() {
//        setDisplayStatView(false);
//        stateManager.attach(LoadingState.class);
//        stateManager.attach(new MainMenuState());
//        stateManager.attach(SlideState.class);
        this.stateManager.attach(new InGameState());
    }

    public GameGUIManager getGUIManager() {
        return (GameGUIManager) gameGUIManager;
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    public void initKeys() {
        //Config.getInstance().initKeys();
    }

    public GameGUIManager getGameGUIManager() {
        return gameGUIManager;
    }

    public StageManager getStageManager() {
        return stageManager;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public FightGamePlay getGamePlayManager() {
        return gamePlayManager;
    }

    public AppSettings getSettings() {
        return settings;
    }
}