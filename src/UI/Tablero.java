package UI;

import ADT.Characters.Character;
import ADT.Controller.Jugando;
import ADT.Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Tablero extends JDialog {
    private JPanel Panel;
    private JPanel panelTablero;
    private JPanel panelControles;
    private JComboBox comboBoxPersonajes;
    private JSpinner spinnerPosX;
    private JSpinner spinnerPosY;
    private JButton buttonAddEstructura;
    private JButton buttonJugar;
    private JButton guardarButton;

    private JButton[][] botonesTablero;
    public JFrame parent = new JFrame();

    public Tablero() {
        //super(parent);
        setTitle("Tablero");
        Panel.setLayout(new GridLayout(1, 2));
        panelTablero.setLayout(new GridLayout(25, 25));
        setContentPane(Panel);
        for (Character item : MainController.controlador.getBaseCharacters()) {
            comboBoxPersonajes.addItem(item.getNombre());
        }
        setMinimumSize(new Dimension(1920, 1030));
        setLocationRelativeTo(parent);
        cargarTablero(parent);
        colocarEnemigos();

        setVisible(true);
        Jugando jugando = new Jugando(this);

        buttonAddEstructura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameCharAdded = comboBoxPersonajes.getSelectedItem().toString();
                Character charAdded = MainController.controlador.getCharacterByNombre(nameCharAdded);
                int posX = (int) spinnerPosX.getValue();
                int posY = (int) spinnerPosY.getValue();
                String resultado = MainController.controlador.placeCharacter(charAdded, posX, posY);
                if (resultado.equals("correcto")) {
                    MainController.controlador.setCapacidadPersonajes(MainController.controlador.getCapacidadPersonajes() - charAdded.getCampos());
                    JOptionPane.showMessageDialog(null,"Colocado, usted tiene todavía "+String.valueOf(MainController.controlador.getCapacidad()+
                            " campos disponibles"));
                    cargarTablero(parent);
                } else {
                    JOptionPane.showMessageDialog(null,resultado);
                }
            }
        });

        buttonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainController.controlador.getMainCharacter() == null) {
                    JOptionPane.showMessageDialog(null,"Se debe colocar la reliquia antes jugar");
                }else
                new Thread(jugando).start();



            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainController.controlador.getNumeroPartida() == 0){
                    int numeroPartida = MainController.controlador.getFileSupervisor().saveGame(MainController.controlador.getNivel(),0);
                    MainController.controlador.setNumeroPartida(numeroPartida);
                }

                JOptionPane.showMessageDialog(null,"Juego guardado");
            }
        });

    }


    public void cargarTablero(JFrame window) {
        panelTablero.removeAll();

        Character[][] tablero = MainController.controlador.getTablero();
        botonesTablero = new JButton[tablero.length][tablero[0].length];

        for (int botonY = 0; botonY < tablero.length; botonY++) {
            for (int botonX = 0; botonX < tablero[0].length; botonX++) {
                Character personaje = tablero[botonX][botonY];
                botonesTablero[botonX][botonY] = new JButton();
                if (tablero[botonX][botonY] != null) {
                    ImageIcon newimg = personaje.getImagen();
                    Image image = newimg.getImage(); // transform it
                    Image newimg2 = image.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    ImageIcon imageIcon = new ImageIcon(newimg2);  // transform it back
                    botonesTablero[botonX][botonY].setIcon(imageIcon);
                    if (personaje.getIsEnemigo()) botonesTablero[botonX][botonY].setBackground(Color.red);
                    else botonesTablero[botonX][botonY].setBackground(Color.blue);
                }
                botonesTablero[botonX][botonY].putClientProperty("x", botonX);
                botonesTablero[botonX][botonY].putClientProperty("y", botonY);
                botonesTablero[botonX][botonY].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int newX = (Integer) (((JButton) e.getSource()).getClientProperty("x"));
                        int newY = (Integer) (((JButton) e.getSource()).getClientProperty("y"));

                        cargarTablero(window);
                        window.invalidate();
                        window.validate();
                        window.repaint();

                    }
                });


                //botonesTablero[r][c].addActionListener(new TileListener());
                panelTablero.add(botonesTablero[botonX][botonY]);
            }
        }
        panelTablero.revalidate();
        panelTablero.repaint();
    }

    public void colocarEnemigos() {
        int capacidadEnemigos = MainController.controlador.getCapacidadPersonajes();
        int[] costos = MainController.controlador.getCostos();
        Random rand = new Random();
        int random;
        int randX;
        int randY;
        while (getMinValue(costos) <= capacidadEnemigos) {
            random = rand.nextInt(costos.length);
            randX = rand.nextInt(MainController.controlador.getTablero().length);
            randY = rand.nextInt(MainController.controlador.getTablero()[0].length);
            Character charAdded = MainController.controlador.getEnemigoByIndex(random).deepClone();
            if (MainController.controlador.placeCharacter(charAdded, randX, randY).equals("correcto")) {
                capacidadEnemigos = capacidadEnemigos - charAdded.getCampos();
            }
        }
        cargarTablero(parent);
    }

    private int getMinValue(int[] array) {
        int minValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }




}
