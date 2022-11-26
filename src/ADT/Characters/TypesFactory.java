package ADT.Characters;

import ADT.Enums.EnumCharacters;

public class TypesFactory {
    //FACTORY METHOD
    public aTipo createType (EnumCharacters tipo){
        switch (tipo){
            case TERRESTRE:
               return new tipoTerrestre();
            case AEREO:
                return new tipoAereo();
            default:
                throw new IllegalArgumentException("Tipo de personaje no existe");
        }
    }
}
