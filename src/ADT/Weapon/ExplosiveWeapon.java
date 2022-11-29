package ADT.Weapon;

import javax.swing.*;

public class ExplosiveWeapon extends aWeapon {
    double radioExplosion;


    public ExplosiveWeapon(String nombre, double alcance, double danho, int radioExplosion, double velocidadDeAtaque, ImageIcon Imagen) {
        super(nombre, alcance, danho, velocidadDeAtaque, "FUEGO", Imagen);
        this.radioExplosion = radioExplosion;
    }

    public ExplosiveWeapon(ExplosiveWeapon armaDeFuego) {
        super(armaDeFuego.nombre, armaDeFuego.alcance, armaDeFuego.danho,
                armaDeFuego.velocidadDeAtaque, "FUEGO", armaDeFuego.imagen);
        this.radioExplosion = armaDeFuego.radioExplosion;
    }

    public ExplosiveWeapon() {
    }

    @Override
    public double utilizar() {
        return -danho;
    }


    public aWeapon clone() {
        return new ExplosiveWeapon(this);
    }

    @Override
    public aWeapon deepClone() {
        return clone();
    }

}
