/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.entities.rig;

import com.jme3.animation.Skeleton;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingVolume;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import sg.games.fighter.gameplay.FightCharacter;

/**
 *
 * @author hungcuong
 */
public class CharacterRig {

    private Node playerNode;
    private Skeleton skeleton;
    private AssetManager assetManager;
    // Positions
    public Node positionNode = new Node("positionNode");
    public Spatial positionCenter;
    public Spatial position;
    public Spatial newpos;
    // Debug Spatials & Bounding Volumes
    public Spatial playerCenter;
    public Spatial min;
    public Spatial h;
    public Spatial lh;
    public Spatial rh;
    public Spatial ub;
    public Spatial lb;
    public Spatial rs;
    public Spatial ls;
    public Spatial rf;
    public Spatial lf;
    public Spatial low;
    
    public BoundingVolume C;
    public BoundingVolume M;
    public BoundingVolume H;
    public BoundingVolume LH;
    public BoundingVolume RH;
    public BoundingVolume UB;
    public BoundingVolume LB;
    public BoundingVolume RS;
    public BoundingVolume LS;
    public BoundingVolume RF;
    public BoundingVolume LF;
    public BoundingVolume LOW;
    private final FightCharacter character;

    public CharacterRig(FightCharacter character) {
        this.character = character;
    }

    public void init(Node playerNode) {
        Material blue = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        blue.setColor("m_Color", ColorRGBA.Blue);
        
        Material red = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        red.setColor("m_Color", ColorRGBA.Red);
        
        Material lightgray = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        lightgray.setColor("m_Color", ColorRGBA.LightGray);
        
        Material orange = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        orange.setColor("m_Color", ColorRGBA.Orange);
        
        Material green = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        green.setColor("m_Color", ColorRGBA.Green);
        
        //Create a box for collision.
        Box b = new Box(Vector3f.ZERO, 1.0F, 1.0F, 1.0F);
        Sphere s = new Sphere(8, 8, 5.0F);

        this.playerNode = playerNode;
        this.h = new Geometry("head", b);
        this.h.setMaterial(blue);
        this.h.scale(2.0F);
        this.lh = new Geometry("head", b);
        this.lh.setMaterial(blue);
        this.rh = new Geometry("head", b);
        this.rh.setMaterial(blue);
        this.ub = new Geometry("head", b);
        this.ub.setMaterial(blue);
        this.ub.scale(2.0F);
        this.lb = new Geometry("head", b);
        this.lb.setMaterial(blue);
        this.lb.scale(2.0F);
        this.rs = new Geometry("head", b);
        this.rs.setMaterial(blue);
        this.ls = new Geometry("head", b);
        this.ls.setMaterial(blue);
        this.rf = new Geometry("head", b);
        this.rf.setMaterial(blue);
        this.lf = new Geometry("head", b);
        this.lf.setMaterial(blue);
        this.low = new Geometry("low", s);
        this.low.setMaterial(blue);


        this.playerNode.attachChild(this.h);
        this.playerNode.attachChild(this.lh);
        this.playerNode.attachChild(this.rh);
        this.playerNode.attachChild(this.ub);
        this.playerNode.attachChild(this.lb);
        this.playerNode.attachChild(this.rs);
        this.playerNode.attachChild(this.ls);
        this.playerNode.attachChild(this.rf);
        this.playerNode.attachChild(this.lf);
        this.playerNode.attachChild(this.low);


        this.playerCenter = new Geometry("playerCenter", b);
        this.playerCenter.setMaterial(orange);

        this.positionCenter = new Geometry("positionCenter", b);
        this.positionCenter.setMaterial(orange);

        this.position = new Geometry("position", b);
        this.position.setMaterial(green);

        this.newpos = new Geometry("newPos", b);
        this.newpos.setMaterial(lightgray);

        this.min = new Geometry("min", s);
        this.min.setMaterial(green);
        this.min.scale(1.4F);
        this.min.updateGeometricState();

        this.M = this.min.getWorldBound();
        this.C = this.playerCenter.getWorldBound();

        this.playerNode.attachChild(this.playerCenter);
        this.playerNode.attachChild(this.position);

        this.positionNode.attachChild(this.positionCenter);
        this.positionNode.attachChild(this.position);

        this.h.updateGeometricState();
        this.lh.updateGeometricState();
        this.rh.updateGeometricState();
        this.ub.updateGeometricState();
        this.lb.updateGeometricState();
        this.rs.updateGeometricState();
        this.ls.updateGeometricState();
        this.rf.updateGeometricState();
        this.lf.updateGeometricState();
        this.low.updateGeometricState();

        this.H = this.h.getWorldBound();
        this.LH = this.lh.getWorldBound();
        this.RH = this.rh.getWorldBound();
        this.UB = this.ub.getWorldBound();
        this.LB = this.lb.getWorldBound();
        this.RS = this.rs.getWorldBound();
        this.LS = this.ls.getWorldBound();
        this.RF = this.rf.getWorldBound();
        this.LF = this.lf.getWorldBound();
        this.LOW = this.low.getWorldBound();

        //FIXME: Reset all the positions
        /*
         //this.mesh.setLocalTranslation(Vector3f.ZERO);
         this.playerNode.setLocalTranslation(Vector3f.ZERO);
         this.positionNode.setLocalTranslation(Vector3f.ZERO);
        
         for (int i = 0; i < this.playerNode.getChildren().size(); i++) {
         this.playerNode.getChild(i).setLocalTranslation(Vector3f.ZERO);
         }
         for (int i = 0; i < this.positionNode.getChildren().size(); i++) {
         this.positionNode.getChild(i).setLocalTranslation(Vector3f.ZERO);
         }
         */
    }

    public void alignBone(Spatial sp, String masterName, String childName) {
        Vector3f childModelSpacePosition = this.skeleton.getBone(childName).getModelSpacePosition();
        sp.setLocalTranslation(this.skeleton.getBone(masterName).getModelSpacePosition().add(childModelSpacePosition));
    }

    public void alignBone(Spatial sp, String masterName) {
        sp.setLocalTranslation(this.skeleton.getBone(masterName).getModelSpacePosition());
    }

    public void reAlignBones() {
        alignBone(playerCenter, "master");

        alignBone(h, "master", "h");

        alignBone(ub, "master", "ub");
        alignBone(lb, "master", "lb");

        alignBone(lh, "master", "h.L");
        alignBone(rh, "master", "h.R");

        alignBone(ls, "master", "s.L");
        alignBone(rs, "master", "s.R");

        alignBone(lf, "master", "toe.L");
        alignBone(rf, "master", "toe.R");
    }

    public void patch(boolean move) {
        if (move) {
            this.newpos.setLocalTranslation(this.position.getWorldTranslation());
            this.positionNode.detachChildNamed("position");
            //setLocation(this.newpos.getWorldTranslation());
            this.positionCenter.setLocalTranslation(Vector3f.ZERO);
            this.positionNode.attachChild(this.position);
            this.position.setLocalTranslation(Vector3f.ZERO);
        } else {
            this.newpos.setLocalTranslation(this.positionNode.getLocalTranslation());
            this.position.setLocalTranslation(Vector3f.ZERO);
        }
    }

    public void setupCollisions() {
    }
    /*
     protected void showBoxes() {
     LastBloodMain.getInstance().root.attachChild(this.playerNode);
     LastBloodMain.getInstance().root.attachChild(this.positionNode);
     LastBloodMain.getInstance().root.attachChild(this.newpos);
     }

     protected void hideBoxes() {
     LastBloodMain.getInstance().root.detachChild(this.playerNode);
     LastBloodMain.getInstance().root.detachChild(this.positionNode);
     LastBloodMain.getInstance().root.detachChild(this.min);
     }
     */
}
