package ADT.Controller;

import ADT.Characters.Character;
import ADT.Weapon.aWeapon;

import java.util.ArrayList;

public class MementoController {

    private ArrayList<Character> generated_characters;    // GUARDAR
    private Boolean turno;  // GUARDAR
    private Character[][] tablero;    // GUARDAR
    private int nivel;  // GUARDAR
    private ArrayList<Character> enemigos;  // GUADAR
    private ArrayList<Character> base_characters;   // GUARDAR
    private ArrayList<aWeapon> base_weapons;  // GUARDAR

    public MementoController(ArrayList<Character> generated_characters, Boolean turno, Character[][] tablero, int nivel, ArrayList<Character> enemigos, ArrayList<Character> base_characters, ArrayList<aWeapon> base_weapons) {
        this.generated_characters = generated_characters;
        this.turno = turno;
        this.tablero = tablero;
        this.nivel = nivel;
        this.enemigos = enemigos;
        this.base_characters = base_characters;
        this.base_weapons = base_weapons;
    }

    public ArrayList<Character> getGenerated_characters() {
        return generated_characters;
    }

    public Boolean getTurno() {
        return turno;
    }

    public Character[][] getTablero() {
        return tablero;
    }

    public int getNivel() {
        return nivel;
    }

    public ArrayList<Character> getEnemigos() {
        return enemigos;
    }

    public ArrayList<Character> getBase_characters() {
        return base_characters;
    }

    public ArrayList<aWeapon> getBase_weapons() {
        return base_weapons;
    }
}
