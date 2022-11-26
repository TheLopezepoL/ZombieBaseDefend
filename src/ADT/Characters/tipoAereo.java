package ADT.Characters;

import ADT.State;
import ADT.Weapon.aWeapon;

import java.util.ArrayList;

public class tipoAereo extends aTipo{

    public tipoAereo (){ }

    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigo) {
        return 0;
    }

    @Override
    public  int atacar(Character infoCharacter, Character enemigo){
        double vidaAnterior = enemigo.vida;
        System.out.println("Ataca a "+enemigo.nombre +" Vida Anterior: " +vidaAnterior);
        System.out.println("Es tipo aereo: Habilidad de daño/efecto doble ");

        int danho = 0;
        double distancia =  Math.sqrt((infoCharacter.posY - enemigo.posY) * (infoCharacter.posY - enemigo.posY) + (enemigo.posX - infoCharacter.posX) * (enemigo.posX - infoCharacter.posX));
        int aux = (int)distancia;

        for (aWeapon arma : infoCharacter.getArmas()){
            if (arma.activo){
                if (arma.alcance >= aux){
                    danho+=arma.utilizar();
                    System.out.println("\tArma: "+arma.nombre +
                            "\tTipo: "+arma.tipo+
                            "Alcance: "+arma.alcance+"\tDaño causado: "+ arma.utilizar());
                }
                else{
                    System.out.println("\tArma: "+arma.nombre +"\tAlcance: "+arma.alcance+"\tFuera de Alcance");
                }

            }

        }
        System.out.println("Daño de armas: "+danho+"\t Daño total (habilidad doble): "+danho*2);
        enemigo.setVida(danho*2);
        if (enemigo.vida <= 0){
            enemigo.setEstado(State.DEAD);
            enemigo.updateImagen();
        }
        System.out.println("Daño total: "+danho*2+" Nueva vida: "+enemigo.vida);
        return 0;
    }

    @Override
    public  boolean moverse(Character infoCharacter, int x, int y){
        infoCharacter.posX =  x;
        infoCharacter.posY =  y;
        System.out.println("Moverse");
        return true;
    }
}
