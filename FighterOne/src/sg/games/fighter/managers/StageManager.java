package sg.games.fighter.managers;

import sg.games.fighter.gameplay.FightGamePlay;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.stage.FightCam;
import sg.games.fighter.world.GameLevel;

/**
 *
 * @author hungcuong
 */
public class StageManager{
    // game shortcut
    public FightCam camsystem;
    // gui

    //stage
    private Object currentAction;
    /**
     * Singleton reference of Object.
     */
    private static StageManager selfRef;

    /**
     * Constructs singleton instance of Object.
     */
    private StageManager() {
        super();
//        selfRef = this;
    }

    private StageManager(FighterMain app) {

    }

    /**
     * Provides reference to singleton object of Object.
     *
     * @return Singleton instance of Object.
     */
    public static final StageManager getInstance() {
        if (selfRef == null) {
            selfRef = new StageManager(FighterMain.getInstance());

        }
        return selfRef;
    }

    public void initStage() {

    }

//    public void configStageCustom() {
//        super.configStageCustom();
//        gamePlayManager.initGamePlay(currentLevel);
//        app.getStateManager().attach(new FightCam());
//    }
//
//    public WorldManager getWorldManager() {
//        return (WorldManager) super.getWorldManager();
//    }
//
//    public void goInGame() {
//        doReadyToPlay();
//    }
//
//    public void doReadyToPlay() {
//        gamePlayManager.startLevel(currentLevel);
//        worldManager.attachWorld();
//    }
}
