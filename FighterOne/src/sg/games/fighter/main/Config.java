/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.main;

import sg.games.fighter.utils.ConfigPair;
import com.jme3.input.InputManager;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;
import sg.games.fighter.utils.XMLHelper;

/**
 *
 * @author hungcuong
 */
public class Config {
    // configs
    // const

    public ConfigPair[] data1;
    public ConfigPair[] data2;
    /**
     * Singleton reference of Object.
     */
    private static Config selfRef;
    private InputManager input;
    private XMLHelper xmlinput;

    /**
     * Constructs singleton instance of Object.
     */
    private Config() {
        selfRef = this;
    }

    /**
     * Provides reference to singleton object of Object.
     *
     * @return Singleton instance of Object.
     */
    public static final Config getInstance() {
        if (selfRef == null) {
            selfRef = new Config();
        }
        return selfRef;
    }

    // lazy init
    public void init(InputManager input) {
        this.input = input;
        xmlinput = new XMLHelper();
    }

    public void writeDefaultConfig() {
        data1 = xmlinput.writedefault1();
        data2 = xmlinput.writedefault2();
    }

    public void save() {
        XMLHelper xmlinput = new XMLHelper();
        try {
            xmlinput.write(data1, data2);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {

        data1 = xmlinput.writedefault1();
        data2 = xmlinput.writedefault2();
        try {
            xmlinput.write(data1, data2);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initKeys() {
        try {
            xmlinput.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        input.deleteMapping("p1left");
        input.deleteMapping("p1right");
        input.deleteMapping("p1up");
        input.deleteMapping("p1down");
        input.deleteMapping("p1lp");
        input.deleteMapping("p1rp");
        input.deleteMapping("p1lk");
        input.deleteMapping("p1rk");
        input.deleteMapping("p1side");
        input.deleteMapping("p1def");

        input.deleteMapping("p2left");
        input.deleteMapping("p2right");
        input.deleteMapping("p2up");
        input.deleteMapping("p2down");
        input.deleteMapping("p2lp");
        input.deleteMapping("p2rp");
        input.deleteMapping("p2lk");
        input.deleteMapping("p2rk");
        input.deleteMapping("p2side");
        input.deleteMapping("p2def");

        setInputKey("p1left", XMLHelper.readp1, 0);
        setInputKey("p1right", XMLHelper.readp1, 1);
        setInputKey("p1up", XMLHelper.readp1, 2);
        setInputKey("p1down", XMLHelper.readp1, 3);
        setInputKey("p1lp", XMLHelper.readp1, 4);
        setInputKey("p1rp", XMLHelper.readp1, 5);
        setInputKey("p1lk", XMLHelper.readp1, 6);
        setInputKey("p1rk", XMLHelper.readp1, 7);
        setInputKey("p1side", XMLHelper.readp1, 8);
        setInputKey("p1def", XMLHelper.readp1, 9);


        setInputKey("p2left", XMLHelper.readp2, 0);
        setInputKey("p2right", XMLHelper.readp2, 1);
        setInputKey("p2up", XMLHelper.readp2, 2);
        setInputKey("p2down", XMLHelper.readp2, 3);
        setInputKey("p2lp", XMLHelper.readp2, 4);
        setInputKey("p2rp", XMLHelper.readp2, 5);
        setInputKey("p2lk", XMLHelper.readp2, 6);
        setInputKey("p2rk", XMLHelper.readp2, 7);
        setInputKey("p2side", XMLHelper.readp2, 8);
        setInputKey("p2def", XMLHelper.readp2, 9);
    }

    public void setInputKey(String name, ArrayList<ConfigPair> source, int index) {
        input.addMapping(name, new Trigger[]{new KeyTrigger(source.get(index).getSecond())});
    }
}
