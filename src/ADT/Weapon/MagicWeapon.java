package ADT.Weapon;

import javax.swing.*;
import java.awt.*;

public class MagicWeapon extends aWeapon {


    public MagicWeapon(String nombre, double alcance, double danho, double radioExplosion, double velocidadDeAtaque, ImageIcon Imagen) {
        super(nombre, alcance, danho, radioExplosion, velocidadDeAtaque,  "FUEGO", Imagen);
    }

    public MagicWeapon(MagicWeapon armaDeFuego){
        super(armaDeFuego.nombre, armaDeFuego.alcance, armaDeFuego.danho, armaDeFuego.radioExplosion,
                armaDeFuego.velocidadDeAtaque , "FUEGO", armaDeFuego.imagen);
    }

    public MagicWeapon() {
    }

    @Override
    public double utilizar() {
        return -danho;
    }


    public aWeapon clone(){
        return new MagicWeapon(this);
    };

    @Override
    public aWeapon deepClone(){
        return clone();
    };
}
