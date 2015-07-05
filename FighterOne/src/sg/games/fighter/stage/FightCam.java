package sg.games.fighter.stage;

import sg.games.fighter.managers.StageManager;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import sg.games.fighter.gameplay.FightCharacter;
import sg.games.fighter.gameplay.FightGamePlay;
import sg.games.fighter.main.FighterMain;

/**
 * FightCam is the Control which handle the camera.
 *
 * @author cuong.nguyenmanh2
 */
public class FightCam extends AbstractAppState {
    // short cut

    protected FighterMain main;
    protected FightGamePlay gamePlay;
    protected StageManager stage;
    protected AppSettings setting;
    protected AssetManager asset;
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
        this.main = FighterMain.getInstance();
        this.stage = StageManager.getInstance();
        this.gamePlay = FightGamePlay.getInstance();
        this.camera = app.getCamera();
        this.p1 = gamePlay.character1;
        this.p2 = gamePlay.character2;
        createCamSystem(camera, this.centerPoint);
    }

    protected void createCamSystem(Camera cam, Vector3f mv) {
        camera.setLocation(new Vector3f(0, 10, 10));
        camera.lookAt(new Vector3f(), Vector3f.UNIT_Y);
//        AssetManager assetManager = LastBloodMain.getInstance().getAssetManager();
//        Box b = new Box(new Vector3f(1.0F, 1.0F, 1.0F), new Vector3f(-1.0F, -1.0F, -1.0F));
//        Material blue = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        blue.setColor("Color", ColorRGBA.Blue);
//        Material red = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        red.setColor("Color", ColorRGBA.Red);

        offset = new Vector3f(0f, 3.0F, 10F);
        camera.lookAt(this.camNode.getWorldTranslation().add(0.0F, 2.0F, 0.0F), Vector3f.UNIT_Y);
    }

    public void moveCam(float tpf) {
        Vector3f p1Pos = p1.mesh.getWorldTranslation();
        Vector3f p2Pos = p2.mesh.getWorldTranslation();
        distanceBetween = gamePlay.distance2d(p1Pos, p2Pos) / 2;
        centerPoint = p1Pos.clone().interpolate(p2Pos, 0.5f);
        float addZ = camMinDistance + distanceBetween / FastMath.tan(viewAngle) * 1.5f;
        float addY = camMinHeight + addZ * FastMath.tan(viewHorizontalAngle);
        Vector3f newPos = centerPoint.add(0, addY, addZ * camHeight);
        camera.setLocation(newPos);
        camera.lookAt(centerPoint.add(0, 1, 0), Vector3f.UNIT_Y);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        moveCam(tpf);
    }
}