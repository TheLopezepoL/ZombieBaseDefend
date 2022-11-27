package UI;

import ADT.Characters.Character;
import ADT.Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero extends JDialog {
    private JPanel Panel;

    private JButton[][] botonesTablero;
    JFrame parent = new JFrame();

    public Tablero(){
        //super(parent);
        setTitle("Tablero");

        Panel.setLayout(new GridLayout(4, 4));
        Panel.setPreferredSize(new Dimension(400, 400));

        setContentPane(Panel);
        setMinimumSize(new Dimension(480,474));
        setModal(true);
        setLocationRelativeTo(parent);

        cargarTablero(parent);
        setVisible(true);

    }

    public void cargarTablero(JFrame window){
        Panel.removeAll();

        Character[][] tablero = MainController.controlador.getTablero();
        botonesTablero = new JButton[tablero.length][tablero[0].length];

        for( int r = 0; r < tablero.length; r++)
        {
            for(int c = 0; c < tablero[0].length; c++)
            {
                Character personaje = tablero[r][c];
                botonesTablero[r][c] = new JButton("O");
                if (tablero[r][c] != null) {
                    ImageIcon newimg = personaje.getImagen();
                    botonesTablero[r][c].setIcon(newimg);
                }
                else {
                    botonesTablero[r][c].setForeground(Color.WHITE);
                }
                //Es Main Character?
                if(personaje == MainController.controlador.getMainCharacter()){
                    botonesTablero[r][c].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //MainController.jugar();
                            //Jugando jugando = new Jugando();
                            //jugando.run();
                            window.invalidate();
                            window.validate();
                            window.repaint();
                        }
                    });
                }

                else if(MainController.controlador.getEnemigos().contains(personaje)){

                    botonesTablero[r][c].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            //MainController.controlador.getMainCharacter().getTipo().atacar(MainController.controlador.getMainCharacter(),personaje);
                            window.invalidate();
                            window.validate();
                            window.repaint();
                        }
                    });
                }
                else{
                    botonesTablero[r][c].putClientProperty( "x", r);
                    botonesTablero[r][c].putClientProperty( "y", c);
                    botonesTablero[r][c].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Character mainPj = MainController.controlador.getMainCharacter();
                            int oldX = mainPj.getPosX();
                            int oldY = mainPj.getPosY();
                            int newX = (Integer)(((JButton)e.getSource()).getClientProperty( "x" ));
                            int newY = (Integer)(((JButton)e.getSource()).getClientProperty( "y" ));
                            if (MainController.controlador.getMainCharacter().getTipo().moverse(mainPj,newX,newY)){
                                MainController.controlador.refreshMatriz(MainController.controlador.getMainCharacter(),oldX,oldY);
                            }

                            cargarTablero(window);
                            window.invalidate();
                            window.validate();
                            window.repaint();

                        }
                    });
                }


                //botonesTablero[r][c].addActionListener(new TileListener());
                Panel.add(botonesTablero[r][c]);
            }
        }
        Panel.revalidate();
        Panel.repaint();
    }
}
