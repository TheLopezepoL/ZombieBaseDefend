package ADT.Weapon;

import ADT.Enums.EnumCharacters;

import javax.swing.*;

public class WeaponFactory {
    public  aWeapon FabricarWeapon(String nombre, double alcance, double danho
            , int radioExplosion, double velocidadDeAtaque, EnumCharacters tipoArma, ImageIcon imagen, int cantidadAtaques){

        switch (tipoArma){
            case CONTACTO:
            case AEREO:
                return new GunWeapon(nombre, 1, danho, velocidadDeAtaque, imagen);
            case MEDIANO:
                return new GunWeapon(nombre, alcance, danho, velocidadDeAtaque, imagen);
            case IMPACTO:
                return new ExplosiveWeapon(nombre, 1, danho, radioExplosion, velocidadDeAtaque, imagen);
            case MULTIPLE:
                return new BurstWeapon(nombre, alcance, danho, velocidadDeAtaque, imagen,cantidadAtaques);
            case BLOQUE:
                return new GunWeapon("No tiene",0,0,0,imagen);
            default:
                return null;
        }
    }
}

