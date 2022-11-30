package UI;

import ADT.Controller.MainController;
import ADT.Controller.fileSupervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseGame extends JDialog {
    private JComboBox comboBoxSavedGames;
    private JButton buttonLoadGame;
    private JButton buttonNewGame;
    private JPanel panelChooseGame;
    public Tablero tablero;
    String[] savedGames;
    fileSupervisor fileManager;

    public ChooseGame(JFrame parent) {
        super(parent);
        setTitle("Menu");
        setContentPane(panelChooseGame);
        setMinimumSize(new Dimension(230,250));
        setModal(true);
        setLocationRelativeTo(parent);
        fileManager = new fileSupervisor();
        savedGames = fileManager.getArchivos();
        for(String savedGame : savedGames){
            comboBoxSavedGames.addItem(savedGame);
        }

        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                tablero = new Tablero();
            }


        });

        buttonLoadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int [] resultado;
                String chosenGame = (String) comboBoxSavedGames.getSelectedItem();
                resultado = fileManager.loadGame(chosenGame);
                dispose();
                MainController.controlador.setNumeroPartida(resultado[1]);
                tablero = MainController.controlador.avanzarNivel(resultado[0]);


            }


        });

        setVisible(true);
    }
}
