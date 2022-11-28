package ADT.Controller;

import UI.Menu;

public class MainController {
    public static controllerSingleton controlador;
    public static volatile Boolean jugando;

    public static void main(String[] args) {

        controlador = controllerSingleton.getController();
        controlador.readCharacters();
        Menu menu = new Menu(null);
    }
}
