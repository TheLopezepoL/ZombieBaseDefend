package ADT.Weapon;

import javax.swing.*;

public class GunWeapon extends aWeapon {


    public GunWeapon(String nombre, double alcance, double danho, double velocidadDeAtaque, ImageIcon Imagen) {
        super(nombre, alcance, danho, velocidadDeAtaque, "FUEGO", Imagen);
    }

    public GunWeapon(GunWeapon armaDeFuego) {
        super(armaDeFuego.nombre, armaDeFuego.alcance, armaDeFuego.danho,
                armaDeFuego.velocidadDeAtaque, "FUEGO", armaDeFuego.imagen);
    }

    public GunWeapon() {
    }

    @Override
    public aWeapon clone() {
        return new GunWeapon(this);
    }

    @Override
    public aWeapon deepClone() {
        return clone();
    }

    @Override
    public double utilizar() {
        return danho;
    }


}
