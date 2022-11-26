package ADT.Weapon;

import ADT.Characters.Character;
import ADT.Controller.controllerSingleton;
import ADT.IPrototype;
import ADT.State;

import javax.swing.*;
import java.awt.Image;
import java.io.Serializable;

public abstract class aWeapon implements IPrototype<aWeapon>, Serializable {
        public String nombre;
        public double alcance;
        public double danho;
        public double radioExplosion;
        public double velocidadDeAtaque;
        public String tipo;
        public boolean activo;
        public ImageIcon imagen;

        public aWeapon(String nombre, double alcance, double danho, double radioExplosion,
                       double velocidadDeAtaque,String tipo,int vida, ImageIcon imagen) {
            this.nombre = nombre;
            this.alcance = alcance;
            this.danho = danho;
            this.radioExplosion = radioExplosion;
            this.velocidadDeAtaque = velocidadDeAtaque;
            this.activo = true;
            this.tipo = tipo;
            this.imagen = imagen;
        }

        //Para cuando hagamos clone, pasarle la imagen directamente
        public aWeapon(String nombre, double alcance, double danho, double radioExplosion,
                       double velocidadDeAtaque,String tipo,ImageIcon imagen) {
        this.nombre = nombre;
        this.alcance = alcance;
        this.danho = danho;
        this.radioExplosion = radioExplosion;
        this.velocidadDeAtaque = velocidadDeAtaque;
        this.activo = true;
        this.tipo = tipo;
        this.imagen = imagen;
        }
//
//        public Image getImagen(){
//            String url = controllerSingleton.getController().datosArma(this.nombre,this.nivel);
//            return controllerSingleton.getController().getImage(url);
//
//        }
//
//        public void updateImagen(){
//            String url = controllerSingleton.getController().datosArma(this.nombre,this.nivel);
//            this.imagen = controllerSingleton.getController().getImage(url);
//        }

        public aWeapon() {
        }

    public String getNombre() {
        return nombre;
    }

    @Override
        public abstract aWeapon clone();
        @Override
        public abstract aWeapon deepClone();
        public abstract double utilizar();

    }