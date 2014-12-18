package sg.games.fighter.ui.screens;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import sg.games.fighter.gameplay.FightCharacter;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.managers.GameGUIManager;

/**
 *
 * @author hungcuong
 */
public class UIInGameScreen {

    private GameGUIManager gameGUIManager;
//    private Screen screen;
//    private Element timeElement;
    // gui
    public Picture healthBar1;
    public Picture healthBar2;
    public Picture currentBar1;
    public Picture currentBar2;
    private final AssetManager assetManager;
    private final Node guiNode;
    public AppSettings setting;
    private Node levelNode;

    public UIInGameScreen(GameGUIManager guiManager) {
        this.gameGUIManager = guiManager;
        FighterMain app = FighterMain.getInstance();
        assetManager = app.getAssetManager();
        guiNode = app.getGuiNode();
//        setting = app.getSettings();
    }

    public void bind() {
//        this.screen = screen;
//        timeElement = screen.findElementByName("Time");

    }

    public void onStartScreen() {
//        timeElement = screen.findElementByName("Time");
        

    }


    public void onEndScreen() {
    }

    public void selectCharacter(String characterName) {
        //gameGUIManager.setInfoText("Hello! " + characterName);
        //gameGUIManager.getStageManager().playSound(characterName);
    }

    public void selectAction(String actionName) {
        //gameGUIManager.setInfoText("Right a way sir! " + actionName);
        //gameGUIManager.getStageManager().getGamePlayManager().getGamePlay().selectCharacterAction(actionName);
    }

    public void continueGame() {
        //gameGUIManager.getStageManager().getGamePlayManager().getGamePlay().endGame();
    }

//    public void setTime(float remainTime) {
//        if (timeElement == null) {
//            return;
//        }
//        int intTime = Math.round(remainTime);
//        int min = intTime / 60;
//        int second = intTime % 60;
//        timeElement.getRenderer(TextRenderer.class).setText("" + min + ":" + second);
//    }

    public void removeHealthBar() {
        guiNode.detachChild(healthBar1);
        guiNode.detachChild(healthBar2);
        guiNode.detachChild(currentBar1);
        guiNode.detachChild(currentBar2);
    }

    public void createHealthBar() {

        healthBar1 = new Picture("healthBar1");

        healthBar1.setImage(assetManager, "menu/healthbar1.png", false);
        healthBar1.setWidth(setting.getWidth() / 2.2F);
        healthBar1.setHeight(setting.getHeight() / 25);
        healthBar1.setPosition(setting.getWidth() * 0.02F, setting.getHeight() - setting.getHeight() / 10);
        guiNode.attachChild(healthBar1);

        healthBar2 = new Picture("healthBar2");
        healthBar2.setImage(assetManager, "menu/healthbar2.png", false);
        healthBar2.setWidth(setting.getWidth() / 2.2F);
        healthBar2.setHeight(setting.getHeight() / 25);
        healthBar2.setPosition(setting.getWidth() * 0.52F, setting.getHeight() - setting.getHeight() / 10);
        guiNode.attachChild(healthBar2);

        currentBar1 = new Picture("currentBar1");
        currentBar1.setImage(assetManager, "menu/currentbar1.png", true);
        currentBar1.setWidth(setting.getWidth() / 2.2F);
        currentBar1.setHeight(setting.getHeight() / 25);
        currentBar1.setPosition(setting.getWidth() * 0.02F, setting.getHeight() - setting.getHeight() / 10);
        guiNode.attachChild(currentBar1);

        currentBar2 = new Picture("currentBar2");
        currentBar2.setImage(assetManager, "menu/currentbar2.png", true);
        currentBar2.setWidth(setting.getWidth() / 2.2F);
        currentBar2.setHeight(setting.getHeight() / 25);
        currentBar2.setPosition(setting.getWidth() * 0.52F, setting.getHeight() - setting.getHeight() / 10);
        guiNode.attachChild(currentBar2);
    }

    public void toogleHealthBar() {
        if (guiNode.hasChild(healthBar1)) {
            removeHealthBar();
        } else {
            guiNode.attachChild(healthBar1);
            guiNode.attachChild(healthBar2);
            guiNode.attachChild(currentBar1);
            guiNode.attachChild(currentBar2);
        }
    }

    public void setHealthBar(FightCharacter p1, Picture currentBar1, Picture healthBar1) {
        currentBar1.setWidth(p1.currentHealth / p1.maxHealth * (setting.getWidth() / 2.2F));
        currentBar1.setPosition(
                healthBar1.getLocalTranslation().x + setting.getWidth() / 2.2F - p1.currentHealth / p1.maxHealth * (setting.getWidth() / 2.2F),
                currentBar1.getLocalTranslation().y);
    }
    
    public void setCharacterAvatar(){
        
    }
    public void talk(String line){
        
    }
}
