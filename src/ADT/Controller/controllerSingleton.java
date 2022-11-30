package ADT.Controller;

import ADT.Characters.Character;
import ADT.Characters.TypesFactory;
import ADT.Characters.aTipo;
import ADT.Enums.EnumCharacters;
import ADT.State;
import ADT.Weapon.WeaponFactory;
import ADT.Weapon.aWeapon;
import UI.Tablero;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class controllerSingleton {
    private static controllerSingleton myController; //static reference to the single object
    //IMAGENES
    Path currentRelativePath = Paths.get("");
    String stringRelativePathSerializables = currentRelativePath.toAbsolutePath().toString().concat("\\Serializables");
    int capacidadPersonajes;
    //FACTORY----------------------------------
    private final WeaponFactory factoryWeapons;
    private final TypesFactory factoryTypes;
    private ArrayList<Character> generated_characters;    // GUARDAR
    private Character mainCharacter;
    private ArrayList<Character> enemigos;  // GUADAR
    private ArrayList<Character> base_characters;   // GUARDAR
    private ArrayList<aWeapon> base_weapons;  // GUARDAR
    private Boolean turno;  // GUARDAR
    private Character[][] tablero;    // GUARDAR
    private int nivel;  // GUARDAR
    private int numeroPartida;
    private fileSupervisor gameSaver;

    //constructor privado
    private controllerSingleton() {
        generated_characters = new ArrayList<Character>();
        base_characters = new ArrayList<Character>();
        enemigos = new ArrayList<Character>();
        base_weapons = new ArrayList<aWeapon>();
        //FACTORY
        factoryWeapons = new WeaponFactory();
        factoryTypes = new TypesFactory();
        tablero = new Character[25][25];
        capacidadPersonajes = 20;
        nivel = 1;
        gameSaver = new fileSupervisor();
        numeroPartida = 0;
        //CREACION ARBOL RELIQUIA
        aTipo tipo = factoryTypes.createType(EnumCharacters.ESTRUCTURA_BLOQUE);
        ArrayList<aWeapon> arrayVacio = new ArrayList<>();
        ImageIcon arbol = new ImageIcon(currentRelativePath.toAbsolutePath().toString().concat("\\src\\icons\\arbol.jpg"));
        Character reliquia = new Character("Reliquia (Necesaria)", 100.0, 0, 0, 0, 0, arrayVacio, tipo, State.DEFAULT, arbol, 0, 0, false);
        base_characters.add(reliquia);

        try {
            Files.createDirectories(Paths.get(stringRelativePathSerializables));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //getInstance
    public static controllerSingleton getController() {
        if (myController == null) {
            myController = new controllerSingleton();
        }
        return myController;
    }

    //GETTERS
    public Character[][] getTablero() {
        return tablero;
    }
    public Character getMainCharacter() {
        return mainCharacter;
    }
    public int getNivel(){return nivel;}
    public int getCapacidadPersonajes() {
        return this.capacidadPersonajes;
    }
    public ArrayList<Character> getEnemigos() {
        return enemigos;
    }
    public ArrayList<Character> getEnemigosNivel() {
        ArrayList<Character> resultado = new ArrayList<>();
        for (Character enemigo:enemigos){
            if (enemigo.getNivelAparicion()<=getNivel()){
                resultado.add(enemigo);
            }
        }
        return resultado;
    }
    public TypesFactory getFactoryTypes() {
        return factoryTypes;
    }
    public void setMainCharacter(Character main) {
        this.mainCharacter = main;
    }
    public java.util.ArrayList<Character> getBaseCharacters() {
        return base_characters;
    }
    public java.util.ArrayList<Character> getGeneratedCharacters() {
        return generated_characters;
    }
    public int getCapacidad() {
        return capacidadPersonajes;
    }
    public Character getCharacterByNombre(String nombrePersonaje) {
        for (Character personaje : getBaseCharacters()) {
            if (personaje.getNombre().equals(nombrePersonaje)) {
                return personaje.deepClone();
            }
        }
        return null;
    }
    public int[] getCostos() {
        return getEnemigosNivel().stream()
                .mapToInt(Character::getCampos)
                .toArray();
    }
    public Character getGeneratedCharacterByIndex(int index) {
        if (getGeneratedCharacters().size() > index) return getGeneratedCharacters().get(index);
        return null;
    }
    public Character getBaseCharacterByIndex(int index) {
        if (getBaseCharacters().size() > index) return getBaseCharacters().get(index);
        return null;
    }
    public Character getEnemigoByIndex(int index) {
        if (getEnemigos().size() > index) return getEnemigos().get(index);
        return null;
    }
    public fileSupervisor getFileSupervisor(){
        return gameSaver;
    }
    public int getNumeroPartida(){
        return numeroPartida;
    }
    //SETTERS
    public void setCapacidadPersonajes(int cantidad) {
        this.capacidadPersonajes = cantidad;
    }
    public void setNumeroPartida(int numeroPartida) {this.numeroPartida = numeroPartida;}
    public void setNivel(int nivel) {this.nivel = nivel;}

    public void addEnemy(Character pj) {
        this.enemigos.add(pj);
    }

    //CREAR ARMAS---------------------------------------
    public aWeapon createBaseWeapon(String nombre, double alcance, double danho, int radioExplosion, double velocidadAtaque, EnumCharacters tipoArma, ImageIcon imagen
            , int cantidadAtaques) {
        return factoryWeapons.FabricarWeapon(nombre, alcance, danho, radioExplosion, velocidadAtaque, tipoArma, imagen, cantidadAtaques);
    }
    //SERIALIZACION PERSONAJES
    public void serializarPersonajes() {

        Path pathSerializablePersonajes = Paths.get(stringRelativePathSerializables.concat("\\personajesSerializados.txt"));
        File fileSerializblePersonajes = new File(pathSerializablePersonajes.toString());
        ArrayList<Character> personajes = new ArrayList<>();
        personajes.addAll(getEnemigos());
        personajes.addAll(getBaseCharacters());
        try {
            FileOutputStream fosEstrucutras = new FileOutputStream(fileSerializblePersonajes);
            ObjectOutputStream oosPersonajes = new ObjectOutputStream(fosEstrucutras);
            oosPersonajes.writeObject(personajes);
            oosPersonajes.close();
            fosEstrucutras.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void readCharacters() {
        ArrayList<Character> personajes = new ArrayList<>();
        Path pathSerializable = Paths.get(stringRelativePathSerializables.concat("\\personajesSerializados.txt"));
        File fileSerializble = new File(pathSerializable.toString());

        try {
            fileSerializble.createNewFile();
            if (fileSerializble.length() == 0) {
                return;
            }
            FileInputStream fis = new FileInputStream(fileSerializble);
            ObjectInputStream ois = new ObjectInputStream(fis);
            personajes = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        for (Character personaje : personajes) {
            if (personaje.getIsEnemigo()) {
                getEnemigos().add(personaje);
            } else {
                if (!personaje.getNombre().equals("Reliquia (Necesaria)")) getBaseCharacters().add(personaje);
            }
        }
    }

    public MementoController saveGame() {
        return new MementoController(this.generated_characters, this.turno, this.tablero, this.nivel, this.enemigos, this.base_characters, this.base_weapons);
    }

    public void restoreGame(MementoController memento) {
        this.generated_characters = memento.getGenerated_characters();
        this.turno = memento.getTurno();
        this.tablero = memento.getTablero();
        this.nivel = memento.getNivel();
        //this.enemigos = memento.getEnemigos();
        //this.base_characters = memento.getBase_characters();
        //this.base_weapons = memento.getBase_weapons();
    }

    public void subirNivel() {
    }

    //FUNCIONES TABLERO

    public boolean addToTablero(Character pj) {
        if (this.tablero[pj.getPosX()][pj.getPosY()] != null) {
            return false;
        }
        this.tablero[pj.getPosX()][pj.getPosY()] = pj;
        return true;
    }

    public String placeCharacter(Character charAdded, int posX, int posY) {
        if (-1 > posX || posX > 24 || -1 > posY || posY > 24) {
            return "Posicionamiento fuera de la cuadrícula, por favor inténtelo de  nuevo";
        }
        if ((MainController.controlador.getCapacidadPersonajes() - charAdded.getCampos() < 0 && !charAdded.getIsEnemigo())) {
            return "Usted no cuenta con los campos suficientes para colocar este personaje";
        }
        if(MainController.controlador.getNivel() < charAdded.getNivelAparicion()){
            return "Esta estructura requiere jugar en el nivel "+String.valueOf(charAdded.getNivelAparicion())+", actualmente usted se encuentra en el nivel " +String.valueOf(MainController.controlador.getNivel());
        }
        if (MainController.controlador.getTablero()[posX][posY] == null) {
            if (charAdded.getNombre().equals("Reliquia (Necesaria)")) {
                if (MainController.controlador.getMainCharacter() == null) {
                    MainController.controlador.setMainCharacter(charAdded);
                } else {
                    return "Solo se puede colocar una reliquia";
                }
            }
            if (!MainController.controlador.getGeneratedCharacters().contains(charAdded))
                MainController.controlador.getGeneratedCharacters().add(charAdded);
            getTablero()[charAdded.getPosX()][charAdded.getPosY()] = null;
            charAdded.setPos(posX, posY);
            MainController.controlador.addToTablero(charAdded);
            return "correcto";
        } else {
            return "Esta celda ya se encuentra ocupada";
        }
    }

    public void clearTablero(){
        for( int i = 0; i < tablero.length; i++ )
            Arrays.fill( tablero[i], null );
    }

//FLUJO DEL JUEGO
    public Tablero avanzarNivel(int nivel){
        int nivelAntiguo = getNivel();
        int diferenciaNiveles = nivel - nivelAntiguo;
        setNivel(nivel);
        clearTablero();
        getGeneratedCharacters().clear();
        setMainCharacter(null);
        setCapacidadPersonajes(20);
        if (diferenciaNiveles != 0 || (nivel == nivelAntiguo && nivel != 1)){
            setCapacidadPersonajes(getCapacidad()+(5*(nivel-1)));
            mejorarPersonajes(diferenciaNiveles);
        }
        Tablero tablero = new Tablero();
        return tablero;
    }

    public void mejorarPersonajes(int diferenciaNivel){
        for (int i = 0; i<=diferenciaNivel; i++){
            for (Character character : getBaseCharacters()) {
                int porcentaje = ThreadLocalRandom.current().nextInt(5, 21) / 100;
                character.subirEstadisticas(porcentaje);
            }
        }
    }
}
