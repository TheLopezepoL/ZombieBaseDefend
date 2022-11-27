package ADT.CharacterTypes.Allies;

import ADT.Characters.Character;
import ADT.Characters.aTipo;

import java.io.Serializable;
import java.util.ArrayList;

public class Estructura extends aTipo implements Serializable {


    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigos) {
        return 0;
    }

    @Override
    public boolean moverse(Character character, int x, int y) {
        return false;
    }
}
