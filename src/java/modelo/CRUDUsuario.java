/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.tomcat.jakartaee.commons.lang3.ArrayUtils;

/**
 *
 * @author trosky
 */
public class CRUDUsuario {
    
    private ConexionBaseDatos baseDatos;
    
    public CRUDUsuario() throws Exception{
        this.baseDatos = new ConexionBaseDatos();
    }
    
    public Usuarios autenticar(String usuarioInput, String contraseñaInput) throws Exception{
        
        if(usuarioInput == null || usuarioInput.trim().isEmpty() ||
               contraseñaInput == null || contraseñaInput.trim().isEmpty() ){
            throw new Exception("Usuario y contraseña requeridos");
        }
        
        Usuarios usuarioEncontrado = null;
        String sqlSelect = "SELECT * FROM Usuarios WHERE usuario = ? AND contraseña = ?";
        
        try {
            PreparedStatement sentenciaSQL = (PreparedStatement) 
                    baseDatos.crearSentencia(sqlSelect);
            sentenciaSQL.setString(1, usuarioInput);
            sentenciaSQL.setString(2, contraseñaInput);
       ResultSet resultado = baseDatos.consultar(sentenciaSQL);

            if (resultado.next()) {
                usuarioEncontrado = new Usuarios();
                usuarioEncontrado.setIdUsuario(resultado.getInt("id_usuario"));
                usuarioEncontrado.setUsuario(resultado.getString("usuario"));
                usuarioEncontrado.setNombre(resultado.getString("nombre"));
                usuarioEncontrado.setTipo(resultado.getString("tipo"));
            }

        } catch (Exception e) {
            throw new Exception("Error durante el inicio de sesión: " + e.getMessage());
        } finally {
            baseDatos.desconectar();
        }

        return usuarioEncontrado; 
    }
}

   
    

