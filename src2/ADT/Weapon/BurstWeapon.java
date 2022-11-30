package ADT.Weapon;

import javax.swing.*;

public class BurstWeapon extends aWeapon {
    int cantidadAtaques;


    public BurstWeapon(String nombre, double alcance, double danho, double velocidadDeAtaque, ImageIcon Imagen, int cantidadAtaques) {
        super(nombre, alcance, danho, velocidadDeAtaque, "RAFAGA", Imagen);
        this.cantidadAtaques = cantidadAtaques;
    }

    public BurstWeapon(BurstWeapon armaDeFuego) {
        super(armaDeFuego.nombre, armaDeFuego.alcance, armaDeFuego.danho,
                armaDeFuego.velocidadDeAtaque, "RAFAGA", armaDeFuego.imagen);
        this.cantidadAtaques = armaDeFuego.cantidadAtaques;
    }

    public BurstWeapon() {
    }

    @Override
    public aWeapon clone() {
        return new BurstWeapon(this);
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

