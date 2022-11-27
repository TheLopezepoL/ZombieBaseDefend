package UI;

import ADT.Characters.Character;
import ADT.Controller.MainController;
import ADT.Enums.EnumCharacters;
import ADT.Enums.EnumWeapons;
import ADT.State;
import ADT.Weapon.aWeapon;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;


public class CreateWeapon extends JDialog {
    //private JTextField txtName;
    private JTextField textField_nombre;
    private JSpinner spinner_vida;
    private JButton btn_imagen;
    private JSpinner spinner_nivel;
    private JSpinner spAlcance;
    private JSpinner spDanho;
    private JSpinner spRadio;
    private JSpinner spVelocidad;
    private JButton createButton;
    private JButton cancelButton;
    private JComboBox cbTipo;
    private JPanel createWeaponPanel;
    private JSpinner campos_spinner;
    String selectedPathFile;
    protected Character.BuilderCharacter builderCharacter;

    public CreateWeapon(JFrame parent) {
        super(parent);
        setTitle("Create Weapon");
        setContentPane(createWeaponPanel);
        setMinimumSize(new Dimension(480, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        builderCharacter = new Character.BuilderCharacter();


        cbTipo.setModel(new DefaultComboBoxModel<>(EnumWeapons.values()));

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField_nombre.getText();
                int alcance = (Integer) spAlcance.getValue();
                int danho = (Integer) spDanho.getValue();
                int velocidad = (Integer) spVelocidad.getValue();
                int radio = (Integer) spRadio.getValue();
                EnumWeapons tipo = (EnumWeapons) cbTipo.getSelectedItem();
                int nivel = (Integer) spinner_nivel.getValue();
                int campos = (Integer) campos_spinner.getValue();
                int vida = (Integer) spinner_vida.getValue();
                ImageIcon imageIcon = new ImageIcon(selectedPathFile);

                aWeapon arma = MainController.controlador.createBaseWeapon(name,alcance,danho,radio,velocidad,tipo,imageIcon);

                Character nuevoCharacter = builderCharacter.setName(name)
                        .setVida(vida)
                        .setNivel(nivel)
                        .setCosto(0)
                        .setEsEnemigo(false)
                        .setCampos(campos)
                        .setNivelAparicion(nivel)
                        .setEstado(State.DEFAULT)
                        .setTipo(EnumCharacters.TERRESTRE)
                        .setImagen(imageIcon)
                        .addWeapon(arma)
                        .build();

                if (arma != null) {
                    MainController.controlador.getBaseCharacters().add(nuevoCharacter);
                    JOptionPane.showMessageDialog(null, "Success");
                } else {
                    JOptionPane.showMessageDialog(null, "Error");
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.controlador.serializarPersonajes();
                dispose();
            }
        });

        btn_imagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser browseImageFile = new JFileChooser();
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
                browseImageFile.addChoosableFileFilter(fnef);
                int showOpenDialogue = browseImageFile.showOpenDialog(null);
                if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
                    File selectedImageFile = browseImageFile.getSelectedFile();
                    selectedPathFile = selectedImageFile.getAbsolutePath();
                    JOptionPane.showMessageDialog(null, selectedPathFile);
                }
            }
        });

        setVisible(true);

    }
}
