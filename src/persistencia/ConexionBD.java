package persistencia;

import java.sql.*;
import java.util.logging.*;

public class ConexionBD
{
    //CONFIGURACION DE LA CONEXION A LA BASE DE DATOS
    private String ruta = "";
    private Connection miconexion = null;
    private Statement mitransaccion = null;
    private ResultSet miconsulta = null;
    final String NombreBD = "reto5.db"; //CAMBIE AQUI EL NOMBRE DE LA BASE DE DATOS, DEBE ESTAR EN LA CARPETA RAIZ
    //ESTABLECER LOS PARAMETROS DE CONEXION A LA BASE DE DATOS
    public ConexionBD()
    {
        ruta = "jdbc:sqlite:"+NombreBD;
        try
        {
            miconexion = DriverManager.getConnection(ruta);
            if(miconexion != null)
            {
                DatabaseMetaData metadata = miconexion.getMetaData();
                System.out.println("¡ La Base de datos " + NombreBD + " se conectó exitosamente! Driver utilizado: " + metadata.getDriverName());
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    //ABIR LA CONEXION
    public Connection getConnection()
    {
        return(miconexion);
    }
    //CIERRA LA CONEXION
    public void closeConnection(Connection conexion)
    {
        if(conexion != null)
        {
            try
            {
                conexion.close();
            }
            catch(SQLException ex)
            {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);    
            }
        }
    }
    /*CRUD*/    
    //METODO PARA HACER UNA CONSULTA TIPO SELECT (CRUD -> R) READ
    public ResultSet consultarBD(String sentencia)
    {
        try
        {
            mitransaccion = miconexion.createStatement();
            miconsulta = mitransaccion.executeQuery(sentencia);
        }
        catch(SQLException sqlex)
        {
            System.out.println(sqlex.getMessage());
        }
        catch(RuntimeException rex)
        {
            System.out.println(rex.getMessage());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return miconsulta;
    }
    //METODO PARA HACER UNA CONSULTA TIPO INSERT (CRUD -> C) CREATE
    public boolean insertarBD(String sentencia)
    {
        try
        {
            mitransaccion = miconexion.createStatement();
            mitransaccion.execute(sentencia);
        }
        catch(SQLException | RuntimeException sqlex)
        {
            System.out.println("Error rutina: " + sqlex);
            return false;
        }
        return true;
    }
    //METODO PARA HACER UNA CONSULTA TIPO DELETE (CRUD -> D) DELETE
    public boolean borrarBD(String sentencia)
    {
        try
        {
            mitransaccion = miconexion.createStatement();
            mitransaccion.execute(sentencia);
        }
        catch(SQLException | RuntimeException sqlex)
        {
            System.out.println("Error rutina: " + sqlex);
            return false;
        }
        return true;
    }
    //METODO PARA HACER UNA CONSULTA TIPO UPDATE (CRUD -> U) UPDATE
    public boolean actualizarBD(String sentencia)
    {
        try
        {
            mitransaccion = miconexion.createStatement();
            mitransaccion.executeUpdate(sentencia);
        }
        catch(SQLException | RuntimeException sqlex)
        {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
    //Por defecto, una conexión funciona en modo autocommit, es decir, cada vez que se ejecuta una sentencia SQL se abre y se cierra automáticamente una transacción, que sólo afecta a dicha sentencia
    public boolean setAutoCommitBD(boolean parametro)
    {
        try
        {
            miconexion.setAutoCommit(parametro);
        }
        catch (SQLException sqlex)
        {
            System.out.println("Error al configurar el autoCommit " + sqlex.getMessage());
            return false;
        }
        return true;
    }
    //Metodo para cerrar la conexion
    public void cerrarConexion()
    {
        closeConnection(miconexion);
    }
    //Hacer un commit se refiere a la idea de confirmar un conjunto de cambios provisionales de forma permanente
    public boolean commitBD()
    {
        try
        {
            miconexion.commit();
            return true;
        } catch(SQLException sqlex)
        {
            System.out.println("Error al hacer commit " + sqlex.getMessage());
            return false;
        }
    }
    //Un rollback o reversión es una operación que devuelve a la base de datos a algún estado previo
    public boolean rollbackBD()
    {
        try
        {
            miconexion.rollback();
            return true;
        }
        catch(SQLException sqlex)
        {
            System.out.println("Error al hacer rollback " + sqlex.getMessage());
            return false;
        }
    }

}
