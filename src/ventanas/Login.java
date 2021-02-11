
package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import clases.Conexiones;
import ventanas.Administrador;

/**
 *
 * @author lochoa
 */
public class Login extends JFrame implements ActionListener {

    public static String user = "";
    String pass = "";

    private JLabel labelImagen, labelNombreSistema, labelUsuario, labelPass;
    private JTextField textUsuario, textPass;
    private JButton buttonIngresar;

    public Login() {
        setLayout(null);
        setTitle("LISO - Cooperativa de Ahorro y Crédito Cristo Rey");
        //getContentPane().setBackground(new Color(255,0,0));
        //setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());

        ImageIcon imagen = new ImageIcon("images/ccrey_logo.png");
        labelImagen = new JLabel(imagen);
        labelImagen.setBounds(25, 25, 300, 180);
        add(labelImagen);

        labelNombreSistema = new JLabel("Gestión de Seguridad de la Información");
        labelNombreSistema.setBounds(65, 135, 300, 170);
        labelNombreSistema.setFont(new Font("Andale Mono", 3, 12));
        labelNombreSistema.setForeground(new Color(0, 0, 50));
        add(labelNombreSistema);

        labelUsuario = new JLabel("Usuario");
        labelUsuario.setBounds(45, 240, 200, 30);
        labelUsuario.setFont(new Font("Andale Mono", 1, 12));
        labelUsuario.setForeground(new Color(0, 0, 0));
        add(labelUsuario);

        textUsuario = new JTextField();
        textUsuario.setBounds(45, 265, 255, 25);
        textUsuario.setBackground(new Color(255, 255, 255));
        textUsuario.setFont(new Font("Andale Mono", 1, 12));
        //textUsuario.setForeground(new Color(255,0,0));
        add(textUsuario);

        labelPass = new JLabel("Login");
        labelPass.setBounds(45, 295, 200, 30);
        labelPass.setFont(new Font("Andale Mono", 1, 12));
        labelPass.setForeground(new Color(0, 0, 0));
        add(labelPass);

        textPass = new JTextField();
        textPass.setBounds(45, 320, 255, 25);
        textPass.setBackground(new Color(255, 255, 255));
        textPass.setFont(new Font("Andale Mono", 1, 12));
        //textUsuario.setForeground(new Color(255,0,0));
        add(textPass);

        buttonIngresar = new JButton("INGRESAR");
        buttonIngresar.setBounds(100, 400, 150, 30);
        buttonIngresar.setBackground(new Color(255, 255, 255));
        buttonIngresar.setFont(new Font("Andale Mono", 1, 14));
        buttonIngresar.setForeground(new Color(255, 0, 0));
        buttonIngresar.addActionListener(this);
        add(buttonIngresar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonIngresar) {
            user = textUsuario.getText().trim();
            pass = textPass.getText().trim();

            if (!user.equals("") || !pass.equals("")) {
                try {
                    Connection cn = Conexiones.conetarActivos();
                    PreparedStatement pst = cn.prepareStatement("select tipo_nivel, estatus from usuarios where username = '" + user
                            + "' and password = '" + pass + "'");
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        String tipo_nivel = rs.getString("tipo_nivel");
                        String estatus = rs.getString("estatus");

                        if (tipo_nivel.equalsIgnoreCase("administrador") && estatus.equalsIgnoreCase("activo")) {
                            dispose();
                            new Administrador().setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes llenar los campos");
                        textPass.setText("");
                        textUsuario.setText("");
                    }

                } catch (SQLException e1) {
                    System.err.println("Error en el boton INGRESAR" + e1);
                    JOptionPane.showMessageDialog(null, "Error al iniciar sessión!!, contacte al Administrador");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes llenar los campos");
            }
        }
    }
    public static void main(String[] args) {
        Login ventana = new Login();
        ventana.setBounds(0, 0, 370, 600);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}
