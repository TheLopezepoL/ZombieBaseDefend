package ADT.Characters;

import ADT.Controller.MainController;

import java.util.ArrayList;

public abstract class aTipo extends Thread {
    public abstract int atacar(Character infoCharacter, ArrayList<Character> enemigos);

    public abstract boolean moverse(Character infoCharacter, int x, int y);

    public abstract ArrayList<Character> getDistanceRelic(Character atacker);

}