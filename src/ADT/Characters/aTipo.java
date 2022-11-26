package ADT.Characters;

import java.util.ArrayList;

public abstract class aTipo {
    public abstract int atacar(Character infoCharacter, ArrayList<Character> enemigo);

    public abstract int atacar(Character character, Character character1);

    public abstract boolean moverse(Character infoCharacter, int x, int y);
}