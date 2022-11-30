package ADT.CharacterTypes.Allies;

import ADT.Characters.Character;
import ADT.Controller.MainController;
import ADT.Weapon.aWeapon;
import UI.Tablero;

import java.util.ArrayList;

public class EstructuraAerea extends Estructura {
    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigos) {
        for (Character enemigo : enemigos) {
            double vidaAnterior = enemigo.getVida();
            infoCharacter.addToBitacora("Ataca a " + enemigo.getNombre() + " Vida Anterior: " + vidaAnterior);
            infoCharacter.addToBitacora("Personaje Terrestre: Habilidad ---");

            int danho = 0;
            double distancia = Math.sqrt((infoCharacter.getPosY() - enemigo.getPosY()) * (infoCharacter.getPosY() - enemigo.getPosY()) + (enemigo.getPosX() - infoCharacter.getPosX()) * (enemigo.getPosX() - infoCharacter.getPosX()));
            int aux = (int) distancia;
            if (!infoCharacter.getArmas().isEmpty()) {
                for (aWeapon arma : infoCharacter.getArmas()) {
                    if (arma.activo) {
                        if (arma.alcance >= aux) {
                            danho += arma.utilizar();
                            infoCharacter.addToBitacora("\tArma: " + arma.nombre +
                                    "\tTipo: " + arma.tipo +
                                    "Alcance: " + arma.alcance + "\tDaño causado: " + arma.utilizar());
                        } else {
                            infoCharacter.addToBitacora("\tArma: " + arma.nombre + "\tAlcance: " + arma.alcance + "\tFuera de Alcance");
                        }

                    }
                }

            }
            enemigo.setVida(danho);
            enemigo.addToBitacora("Daño recibido: " + danho + " Nueva vida: " + enemigo.getVida());
            infoCharacter.addToBitacora("Daño total: " + danho + " Nueva vida: " + enemigo.getVida());
            if (enemigo.getVida() <= 0) {
                enemigo.addToBitacora("He sido asesinado!");
                enemigo.setEstado(ADT.State.DEAD);

                //enemigo.updateImagen();
            }
        }
        return 0;
    }

    @Override
    public boolean moverse(Character infoCharacter, int x, int y) {
        String resultado = MainController.controlador.placeCharacter(infoCharacter, x, y);
        if (resultado.equals("correcto"))
            infoCharacter.addToBitacora("Me muevo a las coordenadas -> (" + x + ", " + y + ").");
        return true;

    }

}
