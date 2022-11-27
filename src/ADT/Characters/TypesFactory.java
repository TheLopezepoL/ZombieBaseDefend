package ADT.Characters;

import ADT.CharacterTypes.Allies.Estructura;
import ADT.CharacterTypes.Allies.EstructuraAerea;
import ADT.CharacterTypes.Allies.EstructuraExplosiva;
import ADT.CharacterTypes.Allies.EstructuraOfensiva;
import ADT.Enums.EnumCharacters;

public class TypesFactory {
    //FACTORY METHOD
    public aTipo createType (EnumCharacters tipo){
        switch (tipo){
            case CONTACTO:
            case MEDIANO:
                return new EstructuraOfensiva();
            case AEREO:
                return new EstructuraAerea();
            case IMPACTO:
                return new EstructuraExplosiva();
            case BLOQUE:
                return new Estructura();
            case MULTIPLE:
                return new EstructuraOfensiva();
            default:
                throw new IllegalArgumentException("Tipo de personaje no existe");
        }
    }
}
