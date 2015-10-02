package sg.games.fighter.managers;

import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.input.controls.ActionListener;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.shadow.PssmShadowRenderer;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import org.apache.commons.configuration.Configuration;
import sg.games.fighter.main.FighterMain;
import sg.games.fighter.world.GameLevelStage;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class WorldManager {

    protected Geometry floor;
    protected PssmShadowRenderer pssmRenderer;
    private ActionListener actionListener;
    private Node levelNode;
    private GameLevelStage currentLevel;
    private FighterMain app;
    private final Node worldNode;

    public WorldManager(FighterMain app) {
        this.app = app;
        this.worldNode = new Node("WorldNode");
    }

    public void config(Configuration props) {
//        initActionListener();
    }

    public void createScreenFire() {
        Quad fireQuad = new Quad(800, 400, false);
        Geometry fire = new Geometry("A", fireQuad);
        Material fireMat = new Material(app.getAssetManager(), "ShaderBlow/MatDefs/SimpleSprite/SimpleSprite.j3md");
        Texture aniTex = app.getAssetManager().loadTexture("Textures/Fx/More/fx_spell_lightning_03.tga");
        aniTex.setWrap(Texture.WrapMode.Repeat);
        fireMat.setTexture("AniTexMap", aniTex);
        fireMat.setInt("numTilesU", 4);
        fireMat.setInt("numTilesV", 4);
        fireMat.setInt("Speed", 40);
        fireMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Additive);
        //fire.setQueueBucket(RenderQueue.Bucket.Transparent);
        fire.setMaterial(fireMat);
        fire.setLocalTranslation(0, 200, 0);
        //fire.lookAt(Vector3f.ZERO, Vector3f.UNIT_X);
//        guiNode.attachChild(fire);

    }

    public void createLevelFire() {
        /**
         * Uses Texture from jme3-test-data library!
         */
        float SCALE = 1;
        ParticleEmitter fire = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
        Material fireMat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Particle.j3md");
        fireMat.setTexture("Texture", app.getAssetManager().loadTexture("Effects/Explosion/flame.png"));
        fire.setMaterial(fireMat);
        fire.setImagesX(2);
        fire.setImagesY(2); // 2x2 texture animation
        fire.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f));   // red
        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        fire.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0).mult(SCALE));

        fire.setStartSize(0.6f * SCALE);
        fire.setEndSize(0.1f * SCALE);
        fire.setGravity(0f, 0f, 0f);
        fire.setLowLife(0.5f);
        fire.setHighLife(3f);
        fire.getParticleInfluencer().setVelocityVariation(0.3f);
        //fire.setLocalTranslation(0, 100, 0);
        fire.scale(SCALE);
        //fire.setEnabled(true);
        //fire.emitAllParticles();
        levelNode.attachChild(fire);
        //System.out.println("Attach fire!");
    }

    public void createRain() {
    }

    public void createWaterFall() {
    }

    public void createLightning() {
    }

    public void createBloodSpread() {
    }

    public void createLeaves() {
    }

    public void createLight() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.0F, -10.0F, -10.0F).normalizeLocal());
        if (levelNode == null) {
            System.out.println("levelNodenote is null");
        } else {
            levelNode.addLight(sun);
        }
    }

    public void createSky(String name) {
        if (name == null || name.isEmpty()) {
            name = "spheresky";
        }
        Spatial skyModel = SkyFactory.createSky(app.getAssetManager(), "Textures/Levels/sky/" + name + ".png", true);
        levelNode.attachChild(skyModel);
        skyModel.setName("sky");
    }

    public void createFloor(String type, String name, int size) {
        if (name == null || name.isEmpty()) {
            name = "bluefire";
            type = "square";
            getCurrentLevel().setFloorSize(10);
        }
        size = getCurrentLevel().getFloorsize();
        Material lightMat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        Texture earthTex = app.getAssetManager().loadTexture("Textures/Levels/earth/" + name + ".png");
        lightMat.setTexture("ColorMap", earthTex);

        if (type.equals("round")) {
            floor = new Geometry("round", new Sphere(8, 8, 1.0F));
        } else if (type.equals("square")) {
            floor = new Geometry("square", new Box(new Vector3f(-1.0F, -1.0F, -1.0F), new Vector3f(1.0F, 1.0F, 1.0F)));
        }

        floor.setMaterial(lightMat);

        floor.scale(size, 0.1F, size);
        floor.setLocalTranslation(0.0F, -0.1F, 0.0F);
        floor.setShadowMode(RenderQueue.ShadowMode.Receive);

        levelNode.attachChild(floor);
    }

    public void toogleFloor() {
        if (levelNode.hasChild(levelNode.getChild("round"))) {
            removeFloor();
        } else {
            createFloor("round", getCurrentLevel().getEarth(), getCurrentLevel().getFloorsize());
        }
    }

    public void toogleSky() {
        if (levelNode.hasChild(levelNode.getChild("sky"))) {
            removeSky();
        } else {
//            createSky(getCurrentLevel().sky);
        }
    }

    public void removeSky() {
        levelNode.detachChildNamed("sky");
    }

    public void removeFloor() {
        levelNode.detachChild(floor);
    }

    public void createShadow() {
        if (pssmRenderer == null) {
            pssmRenderer = new PssmShadowRenderer(app.getAssetManager(), 80, 1);
            pssmRenderer.setDirection(new Vector3f(-1.0F, -1.0F, -1.0F).normalizeLocal());
            pssmRenderer.setShadowIntensity(0.6F);
        }
        app.getViewPort().addProcessor(pssmRenderer);
    }

    public void removeShadow() {
        app.getViewPort().removeProcessor(pssmRenderer);
    }

    public void toogleShadow() {
        if (app.getViewPort().getProcessors().contains(pssmRenderer)) {
            removeShadow();
        } else {
            createShadow();
        }
    }

//    public void initActionListener() {
//        getStageManager().getApp().getInputManager().addMapping("skyKey", new Trigger[]{new KeyTrigger(60)});
//        getStageManager().getApp().getInputManager().addMapping("earthKey", new Trigger[]{new KeyTrigger(61)});
//        getStageManager().getApp().getInputManager().addMapping("shadowKey", new Trigger[]{new KeyTrigger(62)});
//
//        actionListener = new ActionListener() {
//            public void onAction(String name, boolean keyPressed, float tpf) {
//
//                if ((name.equals("skyKey")) && (keyPressed)) {
//                    getCurrentLevel().toogleSky();
//                }
//
//                if ((name.equals("earthKey")) && (keyPressed)) {
//                    getCurrentLevel().toogleFloor();
//                }
//
//                if ((name.equals("shadowKey")) && (keyPressed)) {
//                    toogleShadow();
//                }
//            }
//        };
//        getStageManager().getApp().getInputManager().addListener(actionListener, new String[]{"skyKey", "earthKey", "shadowKey"});
//    }


    public void attachWorld() {
        createLight();
        createSky(getCurrentLevel().getSky());
//        createFloor("round", getCurrentLevel().getEarth(), getCurrentLevel().getFloorsize());
//        createShadow();
        worldNode.attachChild(levelNode);
        app.getRootNode().attachChild(worldNode);
    }
    
    public GameLevelStage getCurrentLevel() {
        return (GameLevelStage) currentLevel;
    }

    public Node getLevelNode() {
        return levelNode;
    }

    public void loadLevel(GameLevelStage level) {
        levelNode = new Node();
        levelNode.attachChild(app.getAssetManager().loadModel(level.getPath()));
        this.currentLevel = level;
    }
}
