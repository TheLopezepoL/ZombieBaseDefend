package ADT.Characters;

import ADT.State;
import ADT.Weapon.aWeapon;

import java.util.ArrayList;

public class tipoTerrestre extends aTipo{

    public tipoTerrestre (){

    }

    @Override
    public int atacar(Character infoCharacter, ArrayList<Character> enemigo) {
        return 0;
    }

    @Override
    public  int atacar(Character infoCharacter, Character enemigo){
        double vidaAnterior = enemigo.vida;
        System.out.println("Ataca a "+enemigo.nombre +" Vida Anterior: " +vidaAnterior);
        System.out.println("Personaje Terrestre: Habilidad ---");

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
        enemigo.setVida(danho);
        if (enemigo.vida <= 0){
            enemigo.setEstado(State.DEAD);
            enemigo.updateImagen();
        }
        System.out.println("Daño total: "+danho+" Nueva vida: "+enemigo.vida);
        return 0;
    }

    @Override
    public boolean moverse(Character infoCharacter, int x, int y){
        infoCharacter.posX =  x;
        infoCharacter.posY =  y;
        return true;
    }
}
