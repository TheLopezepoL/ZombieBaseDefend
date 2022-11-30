package ADT.Controller;

import ADT.Characters.Character;
import UI.Tablero;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Jugando extends Thread {
    Tablero tablero;
    CountDownLatch latch = new CountDownLatch(1);
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

        while (true){
            tablero.cargarTablero(tablero.parent);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
