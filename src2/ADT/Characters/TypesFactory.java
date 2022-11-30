package ADT.Characters;

import ADT.CharacterTypes.Allies.Estructura;
import ADT.CharacterTypes.Allies.EstructuraAerea;
import ADT.CharacterTypes.Allies.EstructuraExplosiva;
import ADT.CharacterTypes.Allies.EstructuraOfensiva;
import ADT.CharacterTypes.Zombies.Zombie;
import ADT.CharacterTypes.Zombies.ZombieAereo;
import ADT.CharacterTypes.Zombies.ZombieImpacto;
import ADT.Enums.EnumCharacters;

public class TypesFactory {
    //FACTORY METHOD
    public aTipo createType(EnumCharacters tipo) {
        return switch (tipo) {
            case ESTRUCTURA_CONTACTO, ESTRUCTURA_MEDIO_ALCANCE, ESTRUCTURA_ATAQUE_MULTIPLE -> new EstructuraOfensiva();
            case ESTRUCTURA_AEREO -> new EstructuraAerea();
            case ESTRUCTURA_IMPACTO -> new EstructuraExplosiva();
            case ESTRUCTURA_BLOQUE -> new Estructura();
            case ZOMBIE_CONTACTO, ZOMBIE_MEDIO_ALCANCE -> new Zombie();
            case ZOMBIE_AEREO -> new ZombieAereo();
            case ZOMBIE_IMPACTO -> new ZombieImpacto();
        };
    }
}
