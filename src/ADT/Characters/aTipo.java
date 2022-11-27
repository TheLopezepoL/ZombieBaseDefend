package ADT.Characters;

import java.util.ArrayList;

public abstract class aTipo {
    public abstract int atacar(Character infoCharacter, ArrayList<Character> enemigos);

    public abstract boolean moverse(Character infoCharacter, int x, int y);
}