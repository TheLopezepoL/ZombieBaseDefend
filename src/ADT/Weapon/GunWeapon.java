package ADT.Weapon;

import javax.swing.*;
import java.awt.*;

public class GunWeapon extends aWeapon{


    public GunWeapon(String nombre, double alcance, double danho, double radioExplosion, double velocidadDeAtaque, int nivel, int vida, ImageIcon Imagen) {
        super(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, "FUEGO", Imagen);
    }

    public GunWeapon(GunWeapon armaDeFuego){
        super(armaDeFuego.nombre, armaDeFuego.alcance, armaDeFuego.danho, armaDeFuego.radioExplosion,
                armaDeFuego.velocidadDeAtaque, "FUEGO", armaDeFuego.imagen);
    }

    public GunWeapon() {
    }

    @Override
    public aWeapon clone(){
        return new GunWeapon(this);
    };

    @Override
    public aWeapon deepClone(){
        return clone();
    };

    @Override
    public double utilizar() {
        return danho;
    }


}
