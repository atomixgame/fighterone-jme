package sg.games.fighter.stage;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import sg.atom.core.execution.BaseGameState;
import sg.games.fighter.gameplay.FightCharacter;
import sg.games.fighter.main.FighterMain;

/**
 * FightCam is the Control which handle the camera.
 *
 * @author cuong.nguyenmanh2
 */
public class FightCam extends BaseGameState {

    // Attributes
    protected Node camNode = new Node("camNode");
    protected Spatial camPosition;
    public float x1;
    public float x2;
    public float xmid;
    public float ddmc = 10.0F;
    //stage
    public Camera camera;
    public FightCharacter p1, p2;
    public float distanceBetween = 0;
    public Vector3f centerPoint = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f offset;
    public float viewAngle = FastMath.PI / 2;
    public float viewHorizontalAngle = FastMath.PI / 18;
    public float camHeight = 1f;
    public float camMinHeight = 1f;
    public float camMaxHeight = 5f;
    public float camMinDistance = 3f;
    
    public FightCam() {
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        this.camera = app.getCamera();
        setEnabled(false);
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        
        if (enabled) {
            getApp().getInputManager().setCursorVisible(false);
            getApp().getFlyByCamera().setEnabled(false);
            getApp().getFlyByCamera().setMoveSpeed(40);
            this.camera.setLocation(new Vector3f(0, 4, 20));
            this.p1 = getApp().getGamePlayManager().getCharacters().get(0);
            this.p2 = getApp().getGamePlayManager().getCharacters().get(1);
            createCamSystem(camera, this.centerPoint);
        }
    }
    
    protected void createCamSystem(Camera cam, Vector3f mv) {
        camera.setLocation(new Vector3f(0, 10, 10));
        camera.lookAt(new Vector3f(), Vector3f.UNIT_Y);
        offset = new Vector3f(0f, 3.0F, 10F);
        camera.lookAt(this.camNode.getWorldTranslation().add(0.0F, 2.0F, 0.0F), Vector3f.UNIT_Y);
    }
    
    public void moveCam(float tpf) {
        Vector3f p1Pos = p1.model.getWorldTranslation();
        Vector3f p2Pos = p2.model.getWorldTranslation();
        distanceBetween = p1Pos.distance(p2Pos) / 2;
        centerPoint = p1Pos.clone().interpolateLocal(p2Pos, 0.5f);
        float addZ = camMinDistance + distanceBetween / FastMath.tan(viewAngle) * 1.5f;
        float addY = camMinHeight + addZ * FastMath.tan(viewHorizontalAngle);
        Vector3f newPos = centerPoint.add(0, addY, addZ * camHeight);
        camera.setLocation(newPos);
        camera.lookAt(centerPoint.add(0, 1, 0), Vector3f.UNIT_Y);
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isEnabled()) {
            moveCam(tpf);
        }
    }
    
    @Override
    public FighterMain getApp() {
        return (FighterMain) super.getApp();
    }
}
