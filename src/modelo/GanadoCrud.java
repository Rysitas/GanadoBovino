/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import com.sun.istack.internal.logging.Logger;
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
public class GanadoCrud extends conexion{
    
    public boolean create(Ganado c){
        try {
            String sql = "INSERT INTO GANADO (CODIGO, TIPO, PESO, EDAD) VALUES (?,?,?,?)";
            Connection con = getConexion();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, String.valueOf(c.getCcodigo()));
            ps.setString(2, c.getTipo());
            ps.setString(3, Float.toString(c.getPeso()));
            ps.setString(4, String.valueOf(c.getEdad()));
            ps.execute();
            con.close();
            ps = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registro Erroneo");
            return false;
        }
        return true;
    }
    
    public ArrayList<Ganado> listGanados(){
        ArrayList listaGanado = new ArrayList();
        Ganado ganado;
        try {
            Connection con = getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM GANADO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {   
                ganado = new Ganado();
                ganado.setCcodigo(rs.getInt(1));
                ganado.setTipo(rs.getString(2));
                ganado.setPeso(rs.getFloat(3));
                ganado.setEdad(rs.getInt(4));
                listaGanado.add(ganado);
            }
        } catch (Exception e) {
        }
        return listaGanado;
    }
    
    public boolean eliminar(Ganado gan) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "DELETE FROM GANADO WHERE CODIGO=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, gan.getCcodigo());
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
    
    
    
}
