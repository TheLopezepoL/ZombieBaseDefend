package ADT.CharacterTypes.Allies;

import ADT.Characters.Character;
import ADT.Characters.aTipo;
import ADT.Controller.MainController;
import UI.Menu;
import UI.Tablero;

import java.io.Serializable;
import java.util.ArrayList;

public class Estructura extends aTipo implements Serializable {


    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigos) {
        return 0;
    }

    @Override
    public boolean moverse(Character character, int x, int y) {
        return false;
    }
    @Override
    public ArrayList<Character> getDistanceEnemies(Character atacker){
        ArrayList<Character> result = new ArrayList<>();
        for (Character enemy : MainController.controlador.getGeneratedCharacters()){
            if (enemy.getIsEnemigo() && enemy.getVida() > 0){
                double distancia = Math.sqrt((atacker.getPosY() - enemy.getPosY()) * (atacker.getPosY() - enemy.getPosY()) + (enemy.getPosX() - atacker.getPosX()) * (enemy.getPosX() - atacker.getPosX()));
                if (!atacker.getArmas().isEmpty()) {
                    if (Math.round(distancia) <= atacker.getArmas().get(0).alcance) {
                        result.add(enemy);
                    }
                }
            }
        }
        return result;
    }



}
