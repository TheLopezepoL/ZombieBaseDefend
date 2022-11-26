package ADT.Characters;

import ADT.Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class InfoCharacter extends JDialog {
    private JLabel LblName;
    private JLabel LblVida;
    private JPanel Panel;
    private JLabel LblNivel;
    private JButton[][] botonesTablero;

    public InfoCharacter(JFrame parent){
        super(parent);
        setTitle("Create Character");
        Panel.setLayout(new GridLayout(4, 4));
        setContentPane(Panel);
        setMinimumSize(new Dimension(480,474));
        setModal(true);
        setLocationRelativeTo(parent);

        Character [][] tablero = MainController.controlador.getTablero();
        botonesTablero = new JButton[tablero.length][tablero[0].length];

        for(int r = 0; r < tablero.length; r++)
        {
            for(int c = 0; c < tablero[0].length; c++)
            {
                botonesTablero[r][c] = new JButton("O");
                if (tablero[r][c] != null){
                    botonesTablero[r][c].setBackground(Color.BLACK);
                }
                else {
                    botonesTablero[r][c].setForeground(Color.WHITE);
                }
                //botonesTablero[r][c].addActionListener(new TileListener());
                Panel.add(botonesTablero[r][c]);
            }
        }
        setVisible(true);
    }
}
