package ADT.Weapon;

import javax.swing.*;
import java.awt.*;

public class MeleeWeapon extends aWeapon{

    public MeleeWeapon(String nombre, double alcance, double danho, double radioExplosion, double velocidadDeAtaque, int nivel, ImageIcon Imagen) {
        super(nombre, alcance, danho, radioExplosion, velocidadDeAtaque, "FUEGO", Imagen);
    }

    public MeleeWeapon(MeleeWeapon armaDeFuego){
        super(armaDeFuego.nombre, armaDeFuego.alcance, armaDeFuego.danho, armaDeFuego.radioExplosion,
                armaDeFuego.velocidadDeAtaque , "FUEGO", armaDeFuego.imagen);
    }
    public MeleeWeapon() {
    }


    @Override
    public double utilizar() {
        return danho;
    }

    public aWeapon clone(){
        return new MeleeWeapon(this);
    };

    @Override
    public aWeapon deepClone(){
        return clone();
    };

}
