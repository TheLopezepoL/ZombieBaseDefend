package ADT.Controller;

import ADT.Characters.Character;
import ADT.State;
import UI.Tablero;

import javax.swing.*;
import java.util.ArrayList;

public class Jugando extends Thread {
    Tablero tablero;
    volatile  Boolean jugando = true;
    ArrayList<Thread> threads;
    public Jugando(Tablero tablero){
        this.tablero = tablero;
        threads = new ArrayList<>();
    }
    @Override
    public void run(){
        for (Character runnableCharacter : MainController.controlador.getGeneratedCharacters()){
            Thread t = new Thread(runnableCharacter);
            t.start();
            threads.add(t);
        }

        while (jugando){
            cleanMuertos();
            tablero.cargarTablero(tablero.parent);
            if(verificarGane()){
                for(Thread threadPersonaje:threads){
                    threadPersonaje.interrupt();
                }
                int result = JOptionPane.showConfirmDialog(null,"Usted ha ganado! Desea avanzar al nivel "+(MainController.controlador.getNivel()+1)+ "?", "Felicidades!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    MainController.controlador.avanzarNivel(MainController.controlador.getNivel()+1);
                    tablero.dispose();
                }else if (result == JOptionPane.NO_OPTION){
                    MainController.controlador.avanzarNivel(MainController.controlador.getNivel());
                    tablero.dispose();
                }else {
                    tablero.dispose();
                }

            }else if (verificarPerdida()){
                for(Thread threadPersonaje:threads){
                    threadPersonaje.interrupt();
                }
                jugando = false;
                int result = JOptionPane.showConfirmDialog(null,"La reliquia a caído! Desea avanzar al nivel "+
                                (MainController.controlador.getNivel()+1) +" de todas maneras?", "Ouch!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    MainController.controlador.avanzarNivel(MainController.controlador.getNivel()+1);
                    tablero.dispose();
                }else if (result == JOptionPane.NO_OPTION){
                    MainController.controlador.avanzarNivel(MainController.controlador.getNivel());
                    tablero.dispose();
                }else {
                    tablero.dispose();
                }
                for(Thread threadPersonaje:threads){
                    threadPersonaje.interrupt();
                }
            }
            try {
                Thread.sleep(650);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean verificarGane(){
        boolean muertos = true;
        for(Character character: MainController.controlador.getGeneratedCharacters()){
            if (character.getEstado() == ADT.State.DEFAULT && character.getIsEnemigo() ){
                return muertos = false;
            }
        }
        return muertos;
    }

    private boolean verificarPerdida(){
        if (MainController.controlador.getMainCharacter().getEstado() == ADT.State.DEAD){
            return true;
        }
        return false;
    }

    private void cleanMuertos(){
        for(Character character: MainController.controlador.getGeneratedCharacters()){
            if (character.getVida() <= 0 || character.getEstado() == ADT.State.DEAD){
                MainController.controlador.getTablero()[character.getPosX()][character.getPosY()] = null;
            }
        }
    }


}
