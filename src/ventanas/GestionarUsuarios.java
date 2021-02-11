package ventanas;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import clases.Conexiones;
       
/**
 *
 * @author lochoa
 */
public class GestionarUsuarios extends JFrame implements ActionListener{
    private JTable tablaGUsuarios;
    private JLabel labelTituloFormulario;
    
    public GestionarUsuarios(){
        user = Login.user;
        setTitle("Gestionar usuarios - Sesión de " + user);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
                
        setLayout(null);
        
        labelTituloFormulario = new JLabel("GESTIÓN DE USUARIOS");
        labelTituloFormulario.setBounds(230, 30, 200, 20);
        add(labelTituloFormulario);
        
        JTable tabla = new JTable();
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
    public static void main(String[] args) {
        GestionarUsuarios ventana1 = new GestionarUsuarios();
        ventana1.setBounds(0, 0, 600, 400);
        ventana1.setVisible(true);
        ventana1.setResizable(false);
        ventana1.setLocationRelativeTo(null);
    }
    
}
