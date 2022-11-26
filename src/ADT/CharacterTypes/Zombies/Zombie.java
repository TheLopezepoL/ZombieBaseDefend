package ADT.CharacterTypes.Zombies;

import ADT.Characters.Character;
import ADT.Characters.aTipo;

import java.util.ArrayList;

public class Zombie extends aTipo {
    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigo) {
        return 0;
    }

    @Override
    public int atacar(Character character, Character character1) {
        return 0;
    }


    @Override
    public boolean moverse(Character character, int i, int i1) {
        return false;
    }
}