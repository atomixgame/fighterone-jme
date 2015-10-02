/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.stage.fx;

import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import sg.atom.core.execution.BaseGameState;

/**
 *
 * @author cuongnguyen
 */
public class ParticleEffects extends BaseGameState {

    public Spatial createHitEffect(String name) {
        ParticleEmitter effect = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 10);
        Material particleMat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        particleMat.setTexture("Texture", assetManager.loadTexture(name + "/flame.png"));
        effect.setMaterial(particleMat);
        effect.setImagesX(2);
        effect.setImagesY(2);
        effect.setStartColor(new ColorRGBA(1.0F, 0.0F, 0.0F, 1.0F));
        effect.setEndColor(new ColorRGBA(1.0F, 0.0F, 0.0F, 1.0F));

        effect.setStartSize(5.0F);
        effect.setEndSize(1.1F);

        effect.setLowLife(0.5F);
        effect.setHighLife(1.0F);
        return effect;
    }
}
