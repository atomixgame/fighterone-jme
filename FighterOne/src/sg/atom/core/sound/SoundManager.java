package sg.atom.core.sound;

import sg.atom.core.sound.SoundClip;
import java.util.ArrayList;
import org.apache.commons.configuration.Configuration;
import sg.atom.core.lifecycle.IGameCycle;
import sg.games.fighter.main.FighterMain;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class SoundManager implements IGameCycle{
    protected FighterMain app;
    protected ArrayList<SoundClip> fxSounds;
    protected ArrayList<SoundClip> musicSounds;

    public SoundManager(FighterMain app) {
        
    }
    
    public void playSound(String soundName) {
    }
    
    //Cycle -------------------------------------------------------------------
    public void init(){
        
    }
    
    public void config(Configuration configuration){
        
    }
    
    public void load(){
        
    }
    
    public void update(float tpf){
        
    }
    
    public void finish(){
        
    }
    
    //GETTER & SETTER
}
