package UI;

import ADT.CharacterTypes.Allies.Estructura;
import ADT.Characters.Character;
import ADT.Controller.MainController;
import ADT.State;
import ADT.Weapon.aWeapon;
import com.sun.tools.javac.Main;

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

    private JButton[][] botonesTablero;
    JFrame parent = new JFrame();

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

        buttonAddEstructura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameCharAdded = comboBoxPersonajes.getSelectedItem().toString();
                Character charAdded = MainController.controlador.getCharacterByNombre(nameCharAdded);
                int posX = (int) spinnerPosX.getValue();
                int posY = (int) spinnerPosY.getValue();
                if (placeCharacter(charAdded, posX, posY)) {
                    MainController.controlador.setCapacidadPersonajes(MainController.controlador.getCapacidadPersonajes() - charAdded.getCampos());
                }
            }
        });

    }


    public void cargarTablero(JFrame window) {
        panelTablero.removeAll();

        Character[][] tablero = MainController.controlador.getTablero();
        botonesTablero = new JButton[tablero.length][tablero[0].length];

        for (int botonX = 0; botonX < tablero.length; botonX++) {
            for (int botonY = 0; botonY < tablero[0].length; botonY++) {
                Character personaje = tablero[botonX][botonY];
                botonesTablero[botonX][botonY] = new JButton("O");
                if (tablero[botonX][botonY] != null) {
                    ImageIcon newimg = personaje.getImagen();
                    Image image = newimg.getImage(); // transform it
                    Image newimg2 = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    ImageIcon imageIcon = new ImageIcon(newimg2);  // transform it back
                    botonesTablero[botonX][botonY].setIcon(imageIcon);
                } else {
                    botonesTablero[botonX][botonY].setForeground(Color.WHITE);
                }

                botonesTablero[botonX][botonY].putClientProperty("x", botonX);
                botonesTablero[botonX][botonY].putClientProperty("y", botonY);
                botonesTablero[botonX][botonY].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Character mainPj = MainController.controlador.getMainCharacter();
                        int oldX = mainPj.getPosX();
                        int oldY = mainPj.getPosY();
                        int newX = (Integer) (((JButton) e.getSource()).getClientProperty("x"));
                        int newY = (Integer) (((JButton) e.getSource()).getClientProperty("y"));
                        if (MainController.controlador.getMainCharacter().getTipo().moverse(mainPj, newX, newY)) {
                            MainController.controlador.refreshMatriz(MainController.controlador.getMainCharacter(), oldX, oldY);
                        }

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
        MainController.controlador.setEnemigos(MainController.controlador.getBaseCharacters());
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
            charAdded.setIsEnemigo(true);
            if (placeCharacter(charAdded,randX,randY)){
                capacidadEnemigos = capacidadEnemigos - charAdded.getCampos();
            }
            else return;
        }
    }

    private static int getMinValue(int[] array) {
        int minValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return minValue;
    }

    public boolean placeCharacter(Character charAdded, int posX, int posY) {
        if (-1 > posX || posX > 24 || -1 > posY || posY > 24) {
            if (!charAdded.getIsEnemigo()) JOptionPane.showMessageDialog(null, "La posición suministrada no es válida");
            return false;
        }


        if ((MainController.controlador.getCapacidadPersonajes() - charAdded.getCampos() < 0)) {
            if (!charAdded.getIsEnemigo())
                JOptionPane.showMessageDialog(null, "Recursos insuficientes para colocar este personaje");
            return false;
        }

        if (MainController.controlador.getTablero()[posX][posY] == null) {
            if (charAdded.getNombre().equals("Reliquia (Necesaria)") && !charAdded.getIsEnemigo()) {
                if (MainController.controlador.getMainCharacter() == null) {
                    MainController.controlador.setMainCharacter(charAdded);
                } else {
                    if (!charAdded.getIsEnemigo())
                        JOptionPane.showMessageDialog(null, "Solo se puede colocar una reliquia");
                    return false;
                }
            }
            charAdded.setPos(posX, posY);
            MainController.controlador.addToTablero(charAdded);
            MainController.controlador.getGeneratedCharacters().add(charAdded);
            cargarTablero(parent);
        } else {
            if (!charAdded.getIsEnemigo()) JOptionPane.showMessageDialog(null, "Este campo ya está ocupado");
            return false;
        }
        return true;
    }
}
