package UI;


import ADT.Controller.MainController;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JDialog{
    private JButton createWeaponButton;
    private JButton createCharacterButton;
    private JButton playButton;
    private JPanel menuPanel;
    public Tablero tablero;

    public Menu(JFrame parent){
        super(parent);
        setTitle("Menu");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(480,474));
        setModal(true);
        setLocationRelativeTo(parent);
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        createWeaponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateWeapon createWeapon = new CreateWeapon(null);
            }


        });

        createCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateZombie createZombie = new CreateZombie(null);
            }




        });




        playButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (MainController.controlador.getBaseCharacters().size() == 0){
                    JOptionPane.showMessageDialog(null, "Debe crear personaje antes de jugar");
                }
                else{
                    dispose();
                    tablero = new Tablero();
                }

                }
        });

        setVisible(true);



    }

}
