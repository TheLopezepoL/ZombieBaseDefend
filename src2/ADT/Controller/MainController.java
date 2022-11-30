package ADT.Controller;

import ADT.Characters.Character;
import UI.Menu;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainController {
    public static controllerSingleton controlador;
    public static void main(String[] args) {

        controlador = controllerSingleton.getController();
        controlador.readCharacters();
        Menu menu = new Menu(null);
    }

    /*public static void addMemento(MementoController memento) {
        // GUARDAR EN MEMORIA
        if (savedGames.size() > 10)
            savedGames.remove(0);
        savedGames.add(memento);

        // GUARDAR EN TXT
        Path pathSerializableSavedGames = Paths.get(Paths.get("").toAbsolutePath().toString().concat("\\Serializables").concat("\\partidasGuardadas.txt"));
        File fileSerializbleSavedGames = new File(pathSerializableSavedGames.toString());
        try {
            FileOutputStream fosEstrucutras = new FileOutputStream(fileSerializbleSavedGames);
            ObjectOutputStream oosSavedGames = new ObjectOutputStream(fosEstrucutras);
            oosSavedGames.writeObject(savedGames);
            oosSavedGames.close();
            fosEstrucutras.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void cargarJuegos() {
        ArrayList<MementoController> savedGamesTxt = new ArrayList<MementoController>();
        Path pathSerializable = Paths.get(Paths.get("").toAbsolutePath().toString().concat("\\Serializables").concat("\\partidasGuardadas.txt"));
        File fileSerializble = new File(pathSerializable.toString());

        try {
            fileSerializble.createNewFile();
            if (fileSerializble.length() == 0) {
                return;
            }
            FileInputStream fis = new FileInputStream(fileSerializble);
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedGamesTxt = (ArrayList) ois.readObject();
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
        savedGames.addAll(savedGamesTxt);
    }*/
}
