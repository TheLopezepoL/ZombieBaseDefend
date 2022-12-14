package ADT.CharacterTypes.Allies;

import ADT.CharacterTypes.Zombies.ZombieAereo;
import ADT.Characters.Character;
import ADT.Weapon.aWeapon;

import java.util.ArrayList;

public class EstructuraExplosiva extends Estructura {
    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigos) {
        boolean hasAttacked = false;
        for (Character enemigo : enemigos) {
            if (!(enemigo.getTipo() instanceof ZombieAereo)) {
                hasAttacked = true;
                double vidaAnterior = enemigo.getVida();
                infoCharacter.addToBitacora("Ataca a " + enemigo.getNombre() + " Vida Anterior: " + vidaAnterior);

                int danho = 0;
                double distancia = Math.sqrt((infoCharacter.getPosY() - enemigo.getPosY()) * (infoCharacter.getPosY() - enemigo.getPosY()) + (enemigo.getPosX() - infoCharacter.getPosX()) * (enemigo.getPosX() - infoCharacter.getPosX()));
                int aux = (int) distancia;

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

                enemigo.setVida(danho);
                enemigo.addToBitacora("Daño recibido: " + danho + " Nueva vida: " + enemigo.getVida());
                infoCharacter.addToBitacora("Daño total: " + danho + " Nueva vida: " + enemigo.getVida());
                if (enemigo.getVida() <= 0) {
                    enemigo.addToBitacora("He sido asesinado!");
                    enemigo.setEstado(ADT.State.DEAD);
                    //enemigo.updateImagen();
                }
            }
        }
        if (hasAttacked) {
            infoCharacter.addToBitacora("Mi proposito ha sido cumplido! Hasta nunca!");
            infoCharacter.setVida((int) infoCharacter.getVida());
            infoCharacter.setEstado(ADT.State.DEAD);
            //infoCharacter.updateImagen();
        }
        return 0;
    }
}
