/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.games.fighter.utils;

/**
 *
 * @author cuong.nguyenmanh2
 */
public class ConfigPair {

    String first;
    int second;

    public ConfigPair(String first, int second) {
        setFirst(first);
        setSecond(second);
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}