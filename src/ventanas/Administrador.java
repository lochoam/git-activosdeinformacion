/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.sql.*;
import clases.Conexiones;

/**
 *
 * @author lochoa
 */
public class Administrador extends JFrame implements ActionListener {

    private JButton buttonUsuarios, buttonFuncionarios, buttonGUsuarios;
    private JLabel labelRegistroUsuarios, labelGestionUsuarios, labelUsuario;
    public static int sesion_usuario;
    String user, nombre_usuario;

    public Administrador() {
        user = Login.user;
        sesion_usuario = 1;
        
        setSize(800,500);
        setResizable(false);
        setTitle("Administrador - Sesión de " + user);
        setLayout(null);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        labelUsuario = new JLabel();
        labelUsuario.setBounds(20, 20, 200, 20);
        add(labelUsuario);

        ImageIcon imagen = new ImageIcon("images/registrar_usuarios.png");
        buttonUsuarios = new JButton(imagen);
        buttonUsuarios.setBounds(50, 100, 120, 120);
        buttonUsuarios.addActionListener(this);
        add(buttonUsuarios);
        
        labelRegistroUsuarios = new JLabel("Registro de Usuarios");
        labelRegistroUsuarios.setBounds(50, 220, 200, 20);
        add(labelRegistroUsuarios);

        ImageIcon imagen1 = new ImageIcon("images/gestionar_usuarios.png");
        buttonGUsuarios = new JButton(imagen1);
        buttonGUsuarios.setBounds(300, 100, 120, 120);
        add(buttonGUsuarios);
        
        labelRegistroUsuarios = new JLabel("Registro de Usuarios");
        labelRegistroUsuarios.setBounds(50, 220, 200, 20);
        add(labelRegistroUsuarios);
        
        try {
            Connection cn = Conexiones.conetarActivos();
            PreparedStatement pst = cn.prepareStatement("select nombre_usuario from usuarios where username = '" + user +"'");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                nombre_usuario = rs.getString("nombre_usuario");
                labelUsuario.setText(nombre_usuario);
            }
        } catch (Exception e) {
            System.err.println("Error en la conexión desde la interfaz Administrador");
        } 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonUsuarios){
            RegistrarUsuarios VUsuarios = new RegistrarUsuarios();
            VUsuarios.setVisible(true);
            //dispose();
           //new RegistrarUsuarios().setVisible(true);
        }
        
    }

    public static void main(String[] args) {
        Administrador ventana = new Administrador();
        ventana.setBounds(0, 0, 800, 500);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
}
