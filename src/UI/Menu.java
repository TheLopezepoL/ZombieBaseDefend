package UI;


import ADT.Controller.MainController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JDialog {
    private JButton playButton;
    private JPanel menuPanel;
    public Tablero tablero;

    public Menu(JFrame parent) {
        super(parent);
        setTitle("Menu");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(480, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        playButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (MainController.controlador.getBaseCharacters().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe crear personaje antes de jugar");
                }
                if (MainController.controlador.getEnemigos().size() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe crear zombies antes de jugar");
                } else {
                    dispose();
                    ChooseGame gameSelectionMenu = new ChooseGame(null);
                }

            }
        });

        setVisible(true);


    }

}
