package sg.games.fighter.world;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import org.apache.commons.configuration.Configuration;
import sg.atom.core.lifecycle.IGameCycle;
import sg.atom.core.lifecycle.ManagableObject;
import sg.games.fighter.main.FighterMain;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class GameLevelStage implements ManagableObject {

    private final FighterMain app;
    int uid = 0;
    String path;
    String name;
    String description;
    int status;
    int floorsize = 100;
    String sky;
    String earth;

    public GameLevelStage(FighterMain app, String name, String path) {
        this.app = app;
        this.name = name;
        this.path = path;
    }

    public void init(Application app) {
    }

    public void initManagers(IGameCycle... managers) {
    }

    public void load(AssetManager assetManager) {
    }

    public void config(Configuration configuration) {
    }

    public void update(float tpf) {
    }

    public void finish() {
    }

    public void load() {
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    public void setFloorSize(int size) {
        floorsize = size;
    }

    public void setSky(String name) {
        sky = name;
    }

    public void setEarth(String name) {
        earth = name;
    }

    public String getEarth() {
        return earth;
    }

    public int getFloorsize() {
        return floorsize;
    }

    public String getSky() {
        return sky;
    }

}
