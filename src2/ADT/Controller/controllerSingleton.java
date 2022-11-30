package ADT.Controller;

import ADT.Characters.Character;
import ADT.Characters.TypesFactory;
import ADT.Characters.aTipo;
import ADT.Enums.EnumCharacters;
import ADT.State;
import ADT.Weapon.WeaponFactory;
import ADT.Weapon.aWeapon;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class controllerSingleton {
    private static controllerSingleton myController; //static reference to the single object
    //IMAGENES
    Path currentRelativePath = Paths.get("");
    String stringRelativePathSerializables = currentRelativePath.toAbsolutePath().toString().concat("\\Serializables");
    //FACTORY----------------------------------
    private final WeaponFactory factoryWeapons;
    private final TypesFactory factoryTypes;
    private ArrayList<Character> generated_characters;    // GUARDAR
    private Character mainCharacter;
    private ArrayList<Character> enemigos;  // GUADAR
    private ArrayList<Character> base_characters;   // GUARDAR

    //constructor privado
    private controllerSingleton() {
        generated_characters = new ArrayList<Character>();
        base_characters = new ArrayList<Character>();
        enemigos = new ArrayList<Character>();
        //FACTORY
        factoryWeapons = new WeaponFactory();
        factoryTypes = new TypesFactory();
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
    public Character getMainCharacter() {
        return mainCharacter;
    }
    public ArrayList<Character> getEnemigos() {
        return enemigos;
    }
    public TypesFactory getFactoryTypes() {
        return factoryTypes;
    }
    public java.util.ArrayList<Character> getBaseCharacters() {
        return base_characters;
    }
    public java.util.ArrayList<Character> getGeneratedCharacters() {
        return generated_characters;
    }
    public Character getCharacterByNombre(String nombrePersonaje) {
        for (Character personaje : getBaseCharacters()) {
            if (personaje.getNombre().equals(nombrePersonaje)) {
                return personaje.deepClone();
            }
        }
        return null;
    }

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
}
