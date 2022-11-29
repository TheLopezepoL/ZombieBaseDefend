package ADT.Characters;

import ADT.Controller.MainController;
import ADT.Enums.EnumCharacters;
import ADT.IBuilder;
import ADT.IPrototype;
import ADT.State;
import ADT.Weapon.aWeapon;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Character implements IPrototype<Character>, Runnable, Serializable {
    protected String nombre;
    protected double vida;
    protected int nivel;
    protected int campos;
    protected int nivelAparicion;
    protected int costo;
    protected String bitacora;
    protected boolean esEnemigo;
    protected ArrayList<aWeapon> armas;
    protected ImageIcon imagen;
    protected int posX;
    protected int posY;
    protected aTipo tipo; //REFERENCIA A COMPORTAMIENTO X TIPO
    protected State estado;

    public Character() {
    }

    public Character(String nombre, double vida, int nivel, int campos, int nivelAparicion,
                     int costo, ArrayList<aWeapon> weapons, aTipo tipo, State estado, ImageIcon imagen,
                     int posX, int posY, boolean esEnemigo) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.campos = campos;
        this.nivelAparicion = nivelAparicion;
        this.costo = costo;
        this.armas = weapons;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
        this.estado = estado;
        this.imagen = imagen;
        this.bitacora = "";
        this.esEnemigo = esEnemigo;
    }

    //Thread

    @Override
    public void run() {
        while (true){
            ArrayList<Character> enemies = this.getTipo().getDistanceRelic(this);
            if (enemies.isEmpty()){
                moveToRelic(this);
            }
            else this.getTipo().atacar(this,enemies);
            try {
                Thread.sleep((int) (getArmas().get(0).velocidadDeAtaque*1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //PROTOTYPE-------------------------------------------------------------------------------------------------------
    @Override
    public Character clone() {
        return new Character(this.nombre, this.vida, this.nivel, this.campos,
                this.nivelAparicion, this.costo, this.armas, this.tipo, this.estado, this.imagen, this.posX, this.posX, this.esEnemigo);
    }

    @Override
    public Character deepClone() {

        ArrayList<aWeapon> armasClone = new ArrayList<aWeapon>();
        for (int i = 0; i < this.armas.size(); i++) {
            armasClone.add(this.armas.get(i).clone());
        }
        return new Character(this.nombre, this.vida, this.nivel, this.campos,
                this.nivelAparicion, this.costo, armasClone, this.tipo, this.estado, this.imagen, this.posX, this.posX, this.esEnemigo);

    }

    public void updateImagen(ImageIcon imagen) {

        this.imagen = imagen;
    }

    public void setIsEnemigo(boolean isEnemigo){
        this.esEnemigo = isEnemigo;
    }

    public String getNombre() {
        return nombre;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(int puntos) {
        this.vida = this.vida - puntos;
    }

    public boolean getIsEnemigo() {
        return esEnemigo;
    }

    public int getNivel() {
        return nivel;
    }

    public int getCampos() {
        return campos;
    }

    public int getNivelAparicion() {
        return nivelAparicion;
    }

    public int getCosto() {
        return costo;
    }

    public ArrayList<aWeapon> getArmas() {
        return armas;
    }

    public aTipo getTipo() {
        return tipo;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public aWeapon getWeaponIndex(int index) {
        return this.getArmas().get(index);
    }

    public void setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void addToBitacora(String evento) {
        this.bitacora = this.bitacora + "\n" + evento;
    }

    public void moveToRelic(Character character){
        Character reliquia = MainController.controlador.getMainCharacter();
        int diferenciaX = reliquia.getPosX() - character.getPosX();
        int diferenciaY = reliquia.getPosY() - character.getPosY();
        String resultado;

        if(diferenciaX > 0 && diferenciaY == 0){
            getTipo().moverse(character, character.getPosX() + 1, character.getPosY());
        }
        else if(diferenciaX < 0 && diferenciaY == 0){
            getTipo().moverse(character,character.getPosX()-1,character.getPosY());
        }
        else if(diferenciaX == 0 && diferenciaY > 0){
            getTipo().moverse(character,character.getPosX(),character.getPosY()+1);
            //return Direction.DOWN;
        }
        else if(diferenciaX == 0 && diferenciaY < 0){
            getTipo().moverse(character,character.getPosX(),character.getPosY()-1);
        }
        else if(diferenciaX > 0 && diferenciaY > 0){
            //return Direction.DOWN_RIGHT;
            getTipo().moverse(character,character.getPosX()+1,character.getPosY()+1);
        }
        else if(diferenciaX < 0 && diferenciaY > 0){
            getTipo().moverse(character,character.getPosX()-1,character.getPosY()+1);
            //return Direction.DOWN_LEFT;
        }
        else if(diferenciaX > 0 && diferenciaY < 0){
            //return Direction.UP_RIGHT;
            getTipo().moverse(character,character.getPosX()+1,character.getPosY()-1);
        }
        else if(diferenciaX < 0 && diferenciaY < 0){
            getTipo().moverse(character,character.getPosX()-1,character.getPosY()-1);
            //return Direction.UP_LEFT;
        }
        //return Direction.DEFAULT;
    }




    //CHARACTER BUILDER------------------------------------------------------------------------
    public static class BuilderCharacter implements IBuilder<Character> {
        protected ArrayList<aWeapon> armas = new ArrayList<>();
        private String nombre;
        private double vida;
        private int nivel;
        private int campos;
        private int nivelAparicion;
        private int costo;
        private ImageIcon imagen;
        private int posX;
        private int posY;
        private boolean esEnemigo;
        private aTipo tipo;
        private State estado;

        public BuilderCharacter() {
        }

        public BuilderCharacter setPos(int x, int y) {
            this.posX = x;
            this.posY = y;
            return this;
        }

        //SETS
        public BuilderCharacter setName(String name) {
            this.nombre = name;
            return this;
        }

        public BuilderCharacter setVida(int vida) {
            this.vida = vida;
            return this;
        }

        public BuilderCharacter setNivel(int nivel) {
            this.nivel = nivel;
            return this;
        }

        public BuilderCharacter setCampos(int campos) {
            this.campos = campos;
            return this;
        }

        public BuilderCharacter setNivelAparicion(int nivelAparicion) {
            this.nivelAparicion = nivelAparicion;
            return this;
        }

        public BuilderCharacter setCosto(int costo) {
            this.costo = costo;
            return this;
        }

        public BuilderCharacter addWeapon(aWeapon arma) {
            //clone arma
            this.armas.clear();
            this.armas.add(arma);
            return this;
        }

        public BuilderCharacter setTipo(EnumCharacters type) {
            //clone arma
            this.tipo = MainController.controlador.getFactoryTypes().createType(type);
            return this;
        }

        public BuilderCharacter setEstado(State estado) {
            this.estado = estado;
            return this;
        }

        public BuilderCharacter setEsEnemigo(boolean esEnemigo) {
            this.esEnemigo = esEnemigo;
            return this;
        }

        public BuilderCharacter setImagen(ImageIcon imagen) {
            this.imagen = imagen;
            return this;
        }

        @Override
        public Character build() {
            return new Character(nombre, vida, nivel, campos, nivelAparicion, costo, armas, tipo, estado, imagen, posX, posY, esEnemigo);
        }
    }

}
