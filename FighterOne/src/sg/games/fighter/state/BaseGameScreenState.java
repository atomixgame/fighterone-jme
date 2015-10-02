package sg.games.fighter.state;

import sg.atom.core.execution.BaseGameState;
import sg.games.fighter.main.FighterMain;

/**
 *
 * @author cuong.nguyen
 */
public class BaseGameScreenState extends BaseGameState {

    @Override
    public FighterMain getApp() {
        return (FighterMain) super.getApp();
    }

}
