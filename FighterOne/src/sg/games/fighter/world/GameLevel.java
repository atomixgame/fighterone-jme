package sg.games.fighter.world;

import sg.games.fighter.main.FighterMain;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class GameLevel {

    int uid = 0;
    String path;
    String name;
    String description;
    int status;
    int floorsize = 100;
    String sky;
    String earth;
    private final FighterMain app;

    public GameLevel(FighterMain app, String name, String path) {
        this.app = app;
        this.name = name;
        this.path = path;
    }

    public void load() {
        // create the stage
        setFloorSize(100);
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
