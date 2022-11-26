package ADT.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.nio.file.*;

import ADT.Characters.Character;
import ADT.Characters.TypesFactory;
import ADT.Enums.EnumWeapons;
import ADT.State;
import ADT.Weapon.WeaponFactory;
import ADT.Weapon.aWeapon;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;

import javax.swing.*;

public class controllerSingleton {
    private static controllerSingleton myController; //static reference to the single object

    //FACTORY----------------------------------
    private WeaponFactory factoryWeapons;
    private TypesFactory factoryTypes;

    private ArrayList<Character> generated_characters;
    private Character mainCharacter;
    private ArrayList<Character> enemigos;
    private ArrayList<Character> base_characters;
    private ArrayList<aWeapon> base_weapons;
    private aWeapon armaDefault;
    private Boolean turno;
    //IMAGENES
    private static JSONParser parser;
    private static JSONParser parserArmas;
    Path currentRelativePath = Paths.get("");
    String stringRelativePathSerializables = currentRelativePath.toAbsolutePath().toString().concat("\\Serializables");

    private Character[][] tablero = {{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}};


    //constructor privado
    private controllerSingleton() {
        generated_characters = new ArrayList<Character>();
        base_characters = new ArrayList<Character>();
        enemigos = new ArrayList<Character>();
        base_weapons = new ArrayList<aWeapon>();
        //FACTORY
        factoryWeapons = new WeaponFactory();
        factoryTypes = new TypesFactory();
        //JSON
        parser = new JSONParser();
        parserArmas = new JSONParser();
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

    public Character getMainCharacter() {
        return mainCharacter;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean act) {
        this.turno = act;
    }

    public void setMainCharacter(Character main) {
        this.mainCharacter = main;
    }

    public boolean addToTablero(Character pj) {
        this.tablero[pj.getPosX()][pj.getPosY()] = pj;
        return true;
    }

    public aWeapon getArmaDefault() {
        return armaDefault;
    }

    public void setArmaDefault(aWeapon arma) {
        armaDefault = arma;
    }

    public void addEnemy(Character pj) {
        this.enemigos.add(pj);
    }

    public ArrayList<Character> getEnemigos() {
        return enemigos;
    }

    //get Weapons Factory
    public WeaponFactory getFactoryWeapons() {
        return factoryWeapons;
    }

    //get Types Factory
    public TypesFactory getFactoryTypes() {
        return factoryTypes;
    }

    public Character[][] getTablero() {
        return tablero;
    }

    public ArrayList<aWeapon> getBaseWeapons() {
        return base_weapons;
    }

    public java.util.ArrayList<Character> getBaseCharacters() {
        return base_characters;
    }

    public java.util.ArrayList<Character> getGeneratedCharacters() {
        return generated_characters;
    }

    public void refreshMatriz(Character pj, int oldX, int oldY) {
        tablero[oldX][oldY] = null;
        tablero[pj.getPosX()][pj.getPosY()] = pj;
    }

    //Personajes posibles
    public ArrayList<String> personajesJSON() {

        JSONParser jsonParser = parser;
        FileReader lector;
        try {
            lector = new FileReader(".\\src\\ImagenesPersonaje.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject lista;
        try {

            lista = (JSONObject) jsonParser.parse(lector);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> nombres = new ArrayList<>(lista.keySet());
        return nombres;
    }

    public ArrayList<String> nivelesJSON(String nombre) {

        JSONParser jsonParser = parser;
        FileReader lector;
        try {
            lector = new FileReader(".\\src\\ImagenesPersonaje.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject lista;
        try {

            lista = (JSONObject) jsonParser.parse(lector);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject personaje = (JSONObject) lista.get(nombre);
        ArrayList<String> niveles = new ArrayList<>(personaje.keySet());
        return niveles;
    }

    //Armas posibles
    public ArrayList<String> armasJSON() {

        JSONParser jsonParser = parser;
        FileReader lector;
        try {
            lector = new FileReader(".\\src\\ImagenesArma.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject lista;
        try {

            lista = (JSONObject) jsonParser.parse(lector);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> nombres = new ArrayList<>(lista.keySet());
        return nombres;
    }

    //print Armas


    public aWeapon getArmaByNombre(String nombreArma) {
        for (aWeapon arma : getBaseWeapons()) {
            if (arma.getNombre().equals(nombreArma)) {
                return arma.clone();
            }
        }
        return null;
    }

    public void setArmas(ArrayList<aWeapon> armas){
        this.base_weapons = armas;
    }

    public Character getCharacterByNombre(String nombrePersonaje) {
        for (Character personaje : getBaseCharacters()) {
            if (personaje.getNombre().equals(nombrePersonaje)) {
                return personaje.deepClone();
            }
        }
        return null;
    }

    //CREAR ARMAS---------------------------------------
    public aWeapon createBaseWeapon(String nombre, double alcance, double danho, double radioExplosion, double velocidadAtaque, int nivel, EnumWeapons tipoArma, int vida, ImageIcon imagen) {
        return factoryWeapons.FabricarWeapon(nombre, alcance, danho, velocidadAtaque, radioExplosion, nivel, tipoArma, imagen, vida);
    }

    //JSON
    public static String datos(String pNombre, int pNivel, State pEstado) {

        JSONParser jsonParser = parser;
        FileReader lector = null;
        try {
            lector = new FileReader(".\\src\\ImagenesPersonaje.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONObject lista = null;
        try {

            lista = (JSONObject) jsonParser.parse(lector);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject personaje = (JSONObject) lista.get(pNombre);
        JSONObject nivel = (JSONObject) personaje.get(Integer.toString(pNivel));
        String url = nivel.get(pEstado.name()).toString();
        return url;
    }

    public static String datosArma(String pNombre, int pNivel) {

        JSONParser jsonParser = parserArmas;
        JSONObject lista = null;
        try {

            lista = (JSONObject) jsonParser.parse(new FileReader(".\\src\\ImagenesArma.json"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject arma = (JSONObject) lista.get(pNombre);
        String url = arma.get(Integer.toString(pNivel)).toString();

        return url;
    }

    public static Image getImage(String url) {
        BufferedImage imagen = null;
        try {
            //Image es clase abstracta de java, podemos retornar bufferedImage
            //y ponerlo en atributo Image de personaje / arma
            imagen = ImageIO.read(new File(url));


        } catch (IOException e) {
            System.out.println("Imagen no encontrada, revisar formato");

        }
        return imagen;
    }

    public Character getGeneratedCharacterByIndex(int index) {
        if (getGeneratedCharacters().size() > index) return getGeneratedCharacters().get(index);
        return null;
    }

    public Character getBaseCharacterByIndex(int index) {
        if (getBaseCharacters().size() > index) return getBaseCharacters().get(index);
        return null;
    }


    public void serializarArmas() {

        Path pathSerializable = Paths.get(stringRelativePathSerializables.concat("\\armasSerializadas" + ".txt"));
        File fileSerializble = new File(pathSerializable.toString());
        try {
            FileOutputStream fos = new FileOutputStream(fileSerializble);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getBaseWeapons());
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void readArmas() {
        ArrayList<aWeapon> armas = new ArrayList<>();
        Path pathSerializable = Paths.get(stringRelativePathSerializables.concat("\\armasSerializadas" + ".txt"));
        File fileSerializble = new File(pathSerializable.toString());

        try {
            fileSerializble.createNewFile();
            if (fileSerializble.length() == 0) {
                return;
            }
            FileInputStream fis = new FileInputStream(fileSerializble);
            ObjectInputStream ois = new ObjectInputStream(fis);

            armas = (ArrayList) ois.readObject();

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
        setArmas(armas);

//Verify list data
        for (aWeapon arma : armas) {
            System.out.println(arma);
        }

    }
}
