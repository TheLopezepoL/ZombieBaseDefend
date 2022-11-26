package ADT.Weapon;

import java.awt.*;

import ADT.Enums.EnumWeapons;

import javax.swing.*;

public class WeaponFactory {
    public  aWeapon FabricarWeapon(String nombre, double alcance, double danho
            , double radioExplosion, double velocidadDeAtaque, int nivel, EnumWeapons tipoArma, ImageIcon imagen, int vida){

        switch (tipoArma){
            case CONTACTO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, nivel, vida, imagen);
            case MEDIANO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, nivel, vida, imagen);
            case AEREO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, nivel, vida, imagen);
            case IMPACTO:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, nivel, vida, imagen);
            case MULTIPLE:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, nivel, vida, imagen);
            case BLOQUE:
                return new GunWeapon(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, nivel, vida, imagen);
            default:
                return null;
        }
    }
}

