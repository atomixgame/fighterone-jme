/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import sg.games.fighter.main.FighterMain;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class MainMenuState extends BaseGameScreenState {

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        setEnabled(true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
        } else {
        }
    }

    public void toState(Class<? extends AppState> newStateClass) {
        if (InGameState.class.isAssignableFrom(newStateClass)) {
            stateManager.detach(this);
            stateManager.attach(new InGameState());
        }
    }

    public void fromState(Class<? extends AppState> newStateClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
