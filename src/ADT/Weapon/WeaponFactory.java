package ADT.Weapon;

import java.awt.*;

import ADT.Enums.EnumWeapons;

import javax.swing.*;

public class WeaponFactory {
    public  aWeapon FabricarWeapon(String nombre, double alcance, double danho
            , double radioExplosion, double velocidadDeAtaque, EnumWeapons tipoArma, ImageIcon imagen){

        switch (tipoArma){
            case CONTACTO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, imagen);
            case MEDIANO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, imagen);
            case AEREO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, imagen);
            case IMPACTO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, imagen);
            case MULTIPLE:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, imagen);
            case BLOQUE:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, imagen);
            default:
                return null;
        }
    }
}

