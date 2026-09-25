/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 *
 * @author trosky
 */
public class CRUDPedidos {
    
    private bd_pedidos pedido;
    private ConexionBaseDatos baseDatos;
    
    public CRUDPedidos() throws Exception{
        this.baseDatos = new ConexionBaseDatos();
    }
    
    
    public void agregarPedido() throws Exception{
        
        if(pedido == null){
            throw new Exception("El objeto pedido no esta inicializado");
        }
        
        if(pedido.getId() <= 0){
            throw new Exception("El Id del pedido es necesario");
        }
        
         String sqlInsert = "INSERT INTO Pedidos ("
            + "Id_pedidos, Fecha, Fecha_envio, Fecha_entrega, Cliente, Proveedor, "
            + "Valor, Estado, Pais, Departamento, Ciudad, NomenclaturaVivienda, Propina) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
        try {
            PreparedStatement sentenciaSQL = (PreparedStatement) baseDatos.crearSentencia(sqlInsert);

            // 1. Clave primaria (int)
            sentenciaSQL.setInt(1, pedido.getId());

            // 2. Fechas y nombres
            sentenciaSQL.setString(2, pedido.getFecha());
            sentenciaSQL.setString(3, pedido.getFechaEnvio());
            sentenciaSQL.setString(4, pedido.getFechaEntrega());
            sentenciaSQL.setString(5, pedido.getCliente());
            sentenciaSQL.setString(6, pedido.getProveedor());

            // 3. Montos y estado
            sentenciaSQL.setDouble(7, pedido.getValor());
            sentenciaSQL.setString(8, pedido.getEstado());

            // 4. Ubicación
            sentenciaSQL.setString(9, pedido.getPais());
            sentenciaSQL.setString(10, pedido.getDepartamento());
            sentenciaSQL.setString(11, pedido.getCiudad());
            sentenciaSQL.setString(12, pedido.getNomenclaturaVivienda());

            // 5. Propina
            sentenciaSQL.setDouble(13, pedido.getPropina());

            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception e) {
            throw new Exception("Error al agregar pedido"
                    +pedido.getId()+"<br/>Explicacion: "+e.getMessage());        
        }finally{
            baseDatos.desconectar();
        }
        
    }
    
    
    public void modificarPedido() throws Exception{
        if (pedido == null){
            throw new Exception("El objeto pedido no esta inicializado"); 
        }
        
        if(pedido.getId() <=0 ){
            throw new Exception("El ID del pedido es necesario");
        }
        
        String sqlUpdate = "UPDATE Pedidos SET "
                + "Fecha = ?, Fecha_envio = ?, Fecha_entrega = ?, Cliente = ?, Proveedor = ?, "
                + "Valor = ?, Estado = ?, Pais = ?, Departamento = ?, Ciudad = ?, "
                + "NomenclaturaVivienda = ?, Propina = ? "
                + "WHERE Id_pedidos = ?";

        try {
            PreparedStatement sentenciaSQL = (PreparedStatement) baseDatos.crearSentencia(sqlUpdate);

            // Campos a actualizar (del 1 al 12)
            sentenciaSQL.setString(1, pedido.getFecha());
            sentenciaSQL.setString(2, pedido.getFechaEnvio());
            sentenciaSQL.setString(3, pedido.getFechaEntrega());
            sentenciaSQL.setString(4, pedido.getCliente());
            sentenciaSQL.setString(5, pedido.getProveedor());
            sentenciaSQL.setDouble(6, pedido.getValor());
            sentenciaSQL.setString(7, pedido.getEstado());
            sentenciaSQL.setString(8, pedido.getPais());
            sentenciaSQL.setString(9, pedido.getDepartamento());
            sentenciaSQL.setString(10, pedido.getCiudad());
            sentenciaSQL.setString(11, pedido.getNomenclaturaVivienda());
            sentenciaSQL.setDouble(12, pedido.getPropina());

            // Parámetro del WHERE (Parámetro 13)
            sentenciaSQL.setInt(13, pedido.getId());

            baseDatos.actualizar(sentenciaSQL);

        } catch (Exception error) {
            throw new Exception("Error al actualizar el pedido " + pedido.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
            
    }
    
    public void eliminarPedido() throws Exception {

        
        if (pedido == null) {
            throw new Exception("El objeto pedido no está inicializado.");
        }
        if (pedido.getId()<= 0) {
            throw new Exception("El ID del Pedido es necesario.");
        }

        
        String sqlDelete = "DELETE FROM Pedidos WHERE Id_pedidos = ?";

        try {
            
            PreparedStatement sentenciaSQL = (PreparedStatement) baseDatos.crearSentencia(sqlDelete);

            
            sentenciaSQL.setInt(1, pedido.getId());

            
            baseDatos.actualizar(sentenciaSQL);

        } catch (Exception error) {
            throw new Exception("Error al Eliminar el Pedido " + pedido.getId()
                    + "<br/>Explicación: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }
    
    

  
    
    
    
    
    
   
    
    
}
