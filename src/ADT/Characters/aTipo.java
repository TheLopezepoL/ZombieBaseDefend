package ADT.Characters;

import java.util.ArrayList;

public abstract class aTipo extends Thread {
    public abstract int atacar(Character infoCharacter, ArrayList<Character> enemigos);

    public abstract boolean moverse(Character infoCharacter, int x, int y);
}