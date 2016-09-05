package units;

import com.jme3.animation.AnimControl;
import com.jme3.animation.SkeletonControl;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import util.Fields;
import util.Methods;

/*
 * public class Sniper extends Unit
 * 
 * This Unit is a Sniper. The Sniper is a versatile unit, and the only
 * one able to climb to trees. From up there, he can hit any troop, and
 * the only way to defeat it is by blowing up the tree.
 */

public class Sniper extends Unit {
    
    // Constants
    public static final String NAME = "Sniper";
    
    public static final String ANIM_WALK = "Walk";
    public static final String ANIM_SHOOT = "Shoot";
    public static final String ANIM_DIE = "Die";
    
    public Sniper(int x, int z, Spatial model) {
        // Inits the variables
        this.attack = 3;
        // TODO: Sniper can only semiaerial attack when on a tree. The type of attack must be changed later
        this.typeAttack = Fields.SEMIAERIAL_ATTACK;
        this.life = 1;
        this.cost = 2;
        this.unitNode = new Node("Unit: " + NAME + x + "-" + z);
        this.unitNode.attachChild(model);
        unitNode.move(x, 0, z);
    }
    
    // Methods
    @Override
    public void move(int x, int z) {
        unitNode.move(x, 0, z);
    }
    
    @Override
    public void eliminate() {
        
    }
}
