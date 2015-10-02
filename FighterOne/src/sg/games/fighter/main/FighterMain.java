package sg.games.fighter.main;

import com.jme3.system.AppSettings;
import java.util.logging.Logger;
import sg.atom.core.AtomMain;
import sg.games.fighter.gameplay.FightGamePlay;
import sg.games.fighter.state.InGameState;

public class FighterMain extends AtomMain {

    public static final Logger logger = Logger.getLogger(FighterMain.class.getName());
    private static FighterMain selfRef;
    // Network
    protected String networkType;
    protected boolean isNetworked = false;

    /**
     * Constructs singleton instance of Object.
     */
    private FighterMain() {
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
        app.setShowSettings(false);
        app.setSettings(setting);

        app.start();
    }

    @Override
    public void initManagers() {
        super.initManagers();
        this.gamePlayManager = new FightGamePlay(this);
    }

    @Override
    public void initStates() {
//        setDisplayStatView(false);
        this.stateManager.attach(new InGameState());
    }

    @Override
    public FightGamePlay getGamePlayManager() {
        return (FightGamePlay) super.getGamePlayManager();
    }

    @Override
    public boolean isDebugMode() {
        return true;
    }
}
