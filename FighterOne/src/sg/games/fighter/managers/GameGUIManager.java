package sg.games.fighter.managers;

import com.jme3.asset.AssetManager;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.renderer.ViewPort;
import com.jme3.ui.Picture;
import java.util.HashMap;
import java.util.logging.Logger;
import sg.games.fighter.stage.FightCam;
import sg.games.fighter.main.FighterMain;

public class GameGUIManager {
    static final Logger logger = Logger.getLogger(GameGUIManager.class.getName());
    //stage
    public FightCam camsystem;
    /**
     * Singleton reference of Object.
     */
    private static GameGUIManager selfRef;
    private Picture backgroundPicture;
    private Material bgMat;
    private HashMap<String, JmeCursor> cursors = new HashMap<String, JmeCursor>();
    private BitmapText infoText;
    private String currentCursor;
    private FighterMain app;

    /**
     * Constructs singleton instance of Object.
     */
    private GameGUIManager() {
        selfRef = this;
    }

    private GameGUIManager(FighterMain app) {
    }

    /**
     * Provides reference to singleton object of Object.
     *
     * @return Singleton instance of Object.
     */
    public static final GameGUIManager getInstance() {
        if (selfRef == null) {
            selfRef = new GameGUIManager(FighterMain.getInstance());
        }
        return selfRef;
    }

    //lazy init
    public void initGUI() {
        app.getFlyByCamera().setEnabled(false);
        app.getInputManager().setCursorVisible(true);
    }

    public void initCustom() {

//        setupCommonScreens();
    }

    public void load(){
        loadCursors();
    }
//    public void setGUIImage(String name, String path) {
//        setGUIImage(nifty.getCurrentScreen().findElementByName(name), path);
//    }
//
//    public void setGUIImage(Element e, String path) {
//        Screen screen = nifty.getCurrentScreen();
//        NiftyImage img = nifty.getRenderEngine().createImage(screen, path, false);
//        e.getRenderer(ImageRenderer.class).setImage(img);
//    }

    public void syncButtonText() {
        /*
         int id = 0;
         try {
         id = Integer.parseInt(nifty.getCurrentScreen().getFocusHandler().getKeyboardFocusElement().getId());
         } catch (Exception localException) {
         }
         if (id < 10) {
         config.data1[id].setSecond(Keyboard.getEventKey());
         try {
         setKeyButtonText(id, id, config.data1);
         } catch (Exception localException1) {
         }
         } else {
         config.data2[(id - 10)].setSecond(Keyboard.getEventKey());
         try {
         setKeyButtonText(id, id - 10, config.data2);
         } catch (Exception localException2) {
         }
         }
         */
    }

//    public void setKeyButtonText(int buttonId, int dataId, ConfigPair[] data2) {
//        String buttonIdStr = Integer.toString(buttonId);
//        Element element = nifty.getCurrentScreen().findElementByName(buttonIdStr);
//        ButtonControl control = element.findNiftyControl(buttonIdStr, ButtonControl.class);
//        //control.setText(config.data2[dataId].getFirst() + ": " + hash(Keyboard.getEventKey()));
//
//    }

    // BACKGROUND
    void initBackground() {
        bgMat = getApp().getAssetManager().loadMaterial("Materials/Background/CircleFx.j3m");
        backgroundPicture = new Picture("background");
        backgroundPicture.setMaterial(bgMat);
        backgroundPicture.setWidth(getApp().getSettings().getWidth());
        backgroundPicture.setHeight(getApp().getSettings().getHeight());
        backgroundPicture.setPosition(0, 0);

        backgroundPicture.updateGeometricState();

        ViewPort pv = getApp().getRenderManager().createPreView("background", getApp().getCamera());
        pv.setClearFlags(true, true, true);
        pv.attachScene(backgroundPicture);

        app.getViewPort().setClearFlags(false, true, true);
        backgroundPicture.updateGeometricState();
    }

    public void loadCursors() {
        AssetManager assetManager = app.getAssetManager();
        cursors.put("move", (JmeCursor) assetManager.loadAsset("Textures/Cursors/TronCursors/TRONmove.ani"));
        cursors.put("busy", (JmeCursor) assetManager.loadAsset("Textures/Cursors/TronCursors/TRONbusy.ani"));
        cursors.put("normal", (JmeCursor) assetManager.loadAsset("Textures/Cursors/TronCursors/TRONnormal.ani"));
        cursors.put("precision", (JmeCursor) assetManager.loadAsset("Textures/Cursors/TronCursors/TRONprecision.ani"));
        currentCursor = "";
        setCursor("normal");

    }
    /* END GUI */

//    public void setupCommonScreens() {
//        getGUI(Nifty.class).registerScreenController(new UIInGameScreen(this),
//                new UIMainMenuScreen(this),
//                new UILoadingScreen(this),
//                new UILevelSelectScreen(this));
//
//        registerScreen(NiftyGUIService.class, "InGameScreen", "Interface/Screens/InGame/InGame.xml");
//        registerScreen(NiftyGUIService.class, "MainMenuScreen", "Interface/Screens/MainMenu/MainMenu.xml");
//        registerScreen(NiftyGUIService.class, "LoadingScreen", "Interface/Screens/InGame/Loading.xml");
//        registerScreen(NiftyGUIService.class, "SelectLevelScreen", "Interface/Screens/MainMenu/SelectLevel.xml");
//
////        bindUI("InGameScreen", new UIMainMenuScreen(this));
//    }

//    public void initInfoText() {
//        //guiNode.detachAllChildren();
//        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
//        infoText = new BitmapText(guiFont, false);
//        infoText.setSize(guiFont.getCharSet().getRenderedSize());
//        infoText.setText("Hello World");
//        infoText.setLocalTranslation(300, infoText.getLineHeight(), 0);
//        guiNode.attachChild(infoText);
//    }

//    public void goInGame() {
//        nifty.gotoScreen("InGameScreen");
//        initInfoText();
//    }
//
//    public void pauseGame() {
//    }
//
//    public void goOutGame() {
//    }
//
//    public void resumeGame() {
//    }

    public void simpleUpdate(float tpf) {
    }

    public BitmapText getInfoText() {
        return infoText;
    }

    public void setInfoText(String text) {
        infoText.setText(text);
    }

    public void setCursor(String cursorName) {
        if (!currentCursor.equalsIgnoreCase(cursorName)) {
            app.getInputManager().setMouseCursor(cursors.get(cursorName));
            currentCursor = cursorName;
        }
    }

    public FighterMain getApp() {
        return app;
    }
    
    
}