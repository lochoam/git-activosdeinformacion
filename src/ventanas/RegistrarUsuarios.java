package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import clases.Conexiones;

public class RegistrarUsuarios extends JFrame implements ActionListener {

    private JLabel labelTitulo, labelNombre, labelUserName, labelPass, labelPermisos;
    private JTextField textNombre, textUserName, textPass;
    private JComboBox comboPermisos;
    private JButton buttonRegistrar;
    String user;

    public RegistrarUsuarios() {

        user = Login.user;
        setTitle("Registrar nuevo usuario - Sesión de " + user);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setLayout(null);
        
        labelTitulo = new JLabel("REGISTRO DE USUARIOS");
        labelTitulo.setBounds(230, 20, 200, 20);
        add(labelTitulo);

        labelNombre = new JLabel("Nombre de Usuario:");
        labelNombre.setBounds(20, 60, 200, 20);
        add(labelNombre);

        textNombre = new JTextField();
        textNombre.setBounds(20, 80, 255, 25);
        textNombre.setBackground(new Color(255, 255, 255));
        textNombre.setFont(new Font("Andale Mono", 1, 12));
        //textUsuario.setForeground(new Color(255,0,0));
        add(textNombre);

        labelUserName = new JLabel("Username:");
        labelUserName.setBounds(350, 60, 200, 20);
        add(labelUserName);

        textUserName = new JTextField();
        textUserName.setBounds(350, 80, 200, 25);
        textUserName.setBackground(new Color(255, 255, 255));
        textUserName.setFont(new Font("Andale Mono", 1, 12));
        //textUsuario.setForeground(new Color(255,0,0));
        add(textUserName);

        labelPass = new JLabel("Password");
        labelPass.setBounds(350, 120, 200, 20);
        add(labelPass);

        textPass = new JTextField();
        textPass.setBounds(350, 140, 200, 25);
        textPass.setBackground(new Color(255, 255, 255));
        textPass.setFont(new Font("Andale Mono", 1, 12));
        //textUsuario.setForeground(new Color(255,0,0));
        add(textPass);

        labelPermisos = new JLabel("Permisos de: ");
        labelPermisos.setBounds(20, 120, 200, 20);
        add(labelPermisos);

        comboPermisos = new JComboBox();
        comboPermisos.setBounds(20, 140, 150, 20);
        comboPermisos.addItem("");
        comboPermisos.addItem("Administrador");
        comboPermisos.addItem("Control");
        comboPermisos.addItem("Operativo");
        comboPermisos.addItem("Consulta");
        add(comboPermisos);

        ImageIcon imagen = new ImageIcon("images/registrar.png");
        buttonRegistrar = new JButton(imagen);
        buttonRegistrar.setBounds(350, 190, 120, 120);
        buttonRegistrar.addActionListener(this);
        add(buttonRegistrar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonRegistrar) {

            String nombre = textNombre.getText().trim();
            String username = textUserName.getText().trim();
            String password = textPass.getText().trim();
            String permiso_string = "";
            int permiso = comboPermisos.getSelectedIndex();

            if (permiso == 1) {
                permiso_string = "Administrador";
            } else if (permiso == 2) {
                permiso_string = "Control";
            } else if (permiso == 3) {
                permiso_string = "Operativo";
            } else if (permiso == 4) {
                permiso_string = "Consulta";
            }
            if (!nombre.equals("") || !username.equals("") || !password.equals("")) {
                try {
                    Connection cn = Conexiones.conetarActivos();
                    PreparedStatement pst = cn.prepareStatement("select username from usuarios where username = '" + username + "'");

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        textUserName.setBackground(Color.red);
                        JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible");
                        cn.close();
                    } else {
                        cn.close();

                        try {
                            Connection cn2 = Conexiones.conetarActivos();
                            PreparedStatement pst2 = cn2.prepareStatement("insert into usuarios values (?,?,?,?,?,?,?)");

                            pst2.setInt(1, 0);
                            pst2.setString(2, nombre);
                            pst2.setString(3, username);
                            pst2.setString(4, password);
                            pst2.setString(5, permiso_string);
                            pst2.setString(6, user);
                            pst2.setString(7, "Activo");

                            pst2.executeUpdate();
                            cn2.close();

                            Limpiar();

                            JOptionPane.showMessageDialog(null, "Registro exitoso");
                            this.dispose();

                        } catch (SQLException e2) {
                            System.err.println("Error en registrar usuario " + e2);
                            JOptionPane.showMessageDialog(null, "Error al registrar, contacte al administrador");
                        }

                    }
                } catch (SQLException e1) {
                    System.err.println("Error en validar nombre de usuario" + e);
                    JOptionPane.showMessageDialog(null, "ERROR al comprar usuario, contacte al administrador");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos vacíos");
            }

        }
    }

    public void Limpiar() {
        textNombre.setText("");
        textPass.setText("");
        textUserName.setText("");
        comboPermisos.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        RegistrarUsuarios ventana = new RegistrarUsuarios();
        ventana.setBounds(0, 0, 600, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}
