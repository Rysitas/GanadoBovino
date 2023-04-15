package controlador;

import modelo.conexion;
import vista.FLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login {
    
    conexion cn = new conexion();

    public int validarIngreso(){
        String usuario = FLogin.txtUsuario.getText();
        String clave = String.valueOf(FLogin.txtPass.getPassword());
        int resultado = 0;
        String SSQL = "SELECT * FROM USUARIOS WHERE USUARIO = '"+usuario+"' and CLAVE = '"+clave+"';";
        Connection conn = null;
        try {
            conn = cn.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SSQL);
            if (rs.next()) {
                resultado = 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex, "Error de desconexion", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }
    
}

