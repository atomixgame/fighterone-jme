package sg.games.fighter.gameplay;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import org.apache.commons.configuration.Configuration;
import sg.atom.core.lifecycle.IGameCycle;
import sg.atom.core.lifecycle.ManagableObject;

/**
 * FightMatch manage the info of the two opponent.
 *
 * @author hungcuong
 */
public class FightMatch implements ManagableObject {

    FightCharacter player1;
    FightCharacter player2;

    public FightMatch(FightCharacter player1, FightCharacter player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void onStart() {
    }

    public void onEnd() {
    }

    public void load(AssetManager assetManager) {
    }

    public void config(Configuration configuration) {
    }

    public void update(float tpf) {
    }

    public void finish() {
    }

    public void init(Application app) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initManagers(IGameCycle... managers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
