/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.stage.story;

import com.google.common.collect.TreeTraverser;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.graph.DirectedGraph;
import sg.atom.gameplay.GameCharacter;
import sg.atom.stage.action.GameAction;

/**
 *
 * @author CuongNguyen
 */
public class Dialogue {
    String title;
    int status;
    int line;
    HashMap<String, GameAction> actionMap = new HashMap<String, GameAction>();
    List<GameCharacter> characters;
    
    // Dialogue as Graph
    DirectedGraph<DialogueNode, DialogueChoice> dialogueGraph;
    TreeTraverser<String> dialogueTraverser;
    
    public void showOptions() {
    }


    public void saveXML(String path) {
    }
}
