/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.atom.core.io;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class TextLineLoader implements AssetLoader {

    @Override
    public Object load(AssetInfo assetInfo) throws IOException {

        ArrayList<String> dictionary = null;
        InputStream in = assetInfo.openStream();
        if (in != null) {
            dictionary = new ArrayList<String>();


            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));
            String line = bufRead.readLine();
            while (line != null) {
                dictionary.add(line);
                line = bufRead.readLine();
            }

        }
        return dictionary;
    }
}
