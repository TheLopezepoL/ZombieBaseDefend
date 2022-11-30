package UI;

import ADT.Characters.Character;
import ADT.Controller.MainController;
import ADT.Weapon.aWeapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharInfoVisualizer extends JDialog {
    private JLabel lblNombre;
    private JLabel lblVida;
    private JLabel lblZombie;
    private JLabel lblCampos;
    private JLabel lblNivelAparicion;
    private JLabel lblTipo;
    private JLabel lblAlcance;
    private JLabel lblDano;
    private JLabel lblImagen;
    private JScrollPane scrollBitacora;
    private JPanel panelVisualizer;
    private JTextArea textAreaBitacora;
    private JButton buttonUpdate;
    Character subject;
    aWeapon arma;

    public CharInfoVisualizer(int posX, int posY, JFrame parent){
        super(parent);
        setTitle("Info");
        setContentPane(panelVisualizer);
        setMinimumSize(new Dimension(600,450));
        setModal(true);
        setLocationRelativeTo(parent);
        subject = MainController.controlador.getTablero()[posX][posY];
        if (subject.getArmas().size() != 0){
            arma = subject.getArmas().get(0);
            lblAlcance.setText(String.valueOf(arma.alcance));
            lblDano.setText(String.valueOf(arma.danho));
        }else {
            lblAlcance.setText("No ataca");
            lblDano.setText("No ataca");
        }
        lblNombre.setText(subject.getNombre());
        lblVida.setText(String.valueOf(subject.getVida()));
        lblZombie.setText(String.valueOf(subject.getIsEnemigo()));
        lblCampos.setText(String.valueOf(subject.getCampos()));
        lblNivelAparicion.setText(String.valueOf(subject.getNivelAparicion()));
        lblTipo.setText(String.valueOf(subject.getNivel()));
        ImageIcon newimg = subject.getImagen();
        Image image = newimg.getImage(); // transform it
        Image newimg2 = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        ImageIcon imageIcon = new ImageIcon(newimg2);
        lblImagen.setIcon(imageIcon);
        textAreaBitacora.setText(subject.getBitacora());

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subject.getArmas().size() != 0){
                    arma = subject.getArmas().get(0);
                    lblAlcance.setText(String.valueOf(arma.alcance));
                    lblDano.setText(String.valueOf(arma.danho));
                }else {
                    lblAlcance.setText("No ataca");
                    lblDano.setText("No ataca");
                }
                lblNombre.setText(subject.getNombre());
                lblVida.setText(String.valueOf(subject.getVida()));
                lblZombie.setText(String.valueOf(subject.getIsEnemigo()));
                lblCampos.setText(String.valueOf(subject.getCampos()));
                lblNivelAparicion.setText(String.valueOf(subject.getNivelAparicion()));
                lblTipo.setText(String.valueOf(subject.getNivel()));
                ImageIcon newimg = subject.getImagen();
                Image image = newimg.getImage(); // transform it
                Image newimg2 = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                ImageIcon imageIcon = new ImageIcon(newimg2);
                lblImagen.setIcon(imageIcon);
                textAreaBitacora.setText(subject.getBitacora());
                panelVisualizer.revalidate();
                panelVisualizer.repaint();


            }


        });


        setVisible(true);
    }


}
