package ADT.Controller;

import ADT.Characters.Character;
import ADT.Characters.InfoCharacter;
import ADT.Enums.EnumCharacters;
import ADT.Enums.EnumWeapons;
import ADT.State;
import ADT.Weapon.WeaponFactory;
import ADT.Weapon.aWeapon;
import UI.CreateWeapon;
import UI.Menu;

import javax.swing.*;
import java.util.ArrayList;

public class MainController {
    public static controllerSingleton controlador;
    public static volatile Boolean jugando;

    public static void main(String[] args) {

        controlador = controllerSingleton.getController();
        controlador.readCharacters();
        Menu menu = new Menu(null);
    }
}
