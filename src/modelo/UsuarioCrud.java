/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Bayro
 */
public class UsuarioCrud extends conexion{
    public boolean create(Usuario s){
        try {
            String sql = "INSERT INTO USUARIOS (IDUSUARIO, USUARIO, CLAVE) VALUES (?,?,?)";
            Connection con = getConexion();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, String.valueOf(s.getId()));
            ps.setString(2, s.getUsuario());
            ps.setString(3, s.getClave());
            ps.execute();
            con.close();
            ps = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registro Erroneo");
            return false;
        }
        return true;
    }
    
    public boolean eliminar(Usuario user) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM USUARIOS WHERE IDUSUARIO=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public ArrayList<Usuario> listUsuarios(){
        ArrayList listaUsuario = new ArrayList();
        Usuario usuario;
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM USUARIOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {   
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setUsuario(rs.getString(2));
                usuario.setClave(rs.getString(3));
                listaUsuario.add(usuario);
            }
        } catch (Exception e) {
        }
        return listaUsuario;
    }
    
}
