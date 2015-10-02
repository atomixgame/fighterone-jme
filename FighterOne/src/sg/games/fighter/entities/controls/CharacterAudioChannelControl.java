/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.entities.controls;

import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import java.util.ArrayList;

/**
 *
 * @author cuongnguyen
 */
public class CharacterAudioChannelControl {

    protected AudioRenderer audioR;
    protected ArrayList<AudioNode> sounds;
    protected AudioNode audioKO;
    protected AudioNode audioHh;
    protected AudioNode audioHa;
    protected AudioNode audioNa;
    protected AudioNode audioTD;
    protected AudioNode audioT;
    protected AudioNode audioWin;
    protected String audioName;
    protected boolean audioSend;

    private void playSound(float damage) {
        if (damage >= 20.0F) {
            this.audioSend = true;
            this.audioName = "audioHh";
            audioR.playSource(this.audioHh);
        } else if (damage > 0.0F) {
            this.audioSend = true;
            this.audioName = "audioT";
            audioR.playSource(this.audioT);
        }
    }
}
