package ADT.Weapon;

import ADT.Enums.EnumCharacters;

import javax.swing.*;

public class WeaponFactory {
    public aWeapon FabricarWeapon(String nombre, double alcance, double danho
            , int radioExplosion, double velocidadDeAtaque, EnumCharacters tipoArma, ImageIcon imagen, int cantidadAtaques) {

        return switch (tipoArma) {
            case ESTRUCTURA_CONTACTO, ESTRUCTURA_AEREO, ZOMBIE_AEREO, ZOMBIE_CONTACTO ->
                    new GunWeapon(nombre, 1, danho, velocidadDeAtaque, imagen);
            case ZOMBIE_MEDIO_ALCANCE, ESTRUCTURA_MEDIO_ALCANCE ->
                    new GunWeapon(nombre, alcance, danho, velocidadDeAtaque, imagen);
            case ZOMBIE_IMPACTO, ESTRUCTURA_IMPACTO ->
                    new ExplosiveWeapon(nombre, 1, danho, radioExplosion, velocidadDeAtaque, imagen);
            case ESTRUCTURA_ATAQUE_MULTIPLE ->
                    new BurstWeapon(nombre, alcance, danho, velocidadDeAtaque, imagen, cantidadAtaques);
            case ESTRUCTURA_BLOQUE -> new GunWeapon("No tiene", 0, 0, 0, imagen);

        };
    }
}

