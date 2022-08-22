package logica;


import persistencia.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto {
  //attributos
    private String nombre;
    private String id;
    private double temperatura;
    private double valorBase;
    private double costo;
    
    //constructores
    public Producto(String nombre, String id, double temperatura, double valorBase, double costo) {
        this.nombre = nombre;
        this.id = id;
        this.temperatura = temperatura;
        this.valorBase = valorBase;
        this.costo = costo;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
    
    public double calcularCostoDeAlmacenamiento(){
        return valorBase;
    }
    
    public void setcosto(double costo) {
        this.costo = costo;
        
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + " id=" + id + " temperatura=" + temperatura + " valorBase=" + valorBase + "costo="+costo+ '}';
    }
   
 public List<Producto> listarProducto()
    {
        //Me conecto a la base de datos
        ConexionBD miconexion = new ConexionBD();
        //Creo la lista que contiene los objetos cliente
        List<Producto> milistaproductos = new ArrayList<>();
        //Declaro la variable que contiene el objeto
        Producto miproducto;
        try
        {
            //Ejecuto la consulta y guardo el resultado en miconsulta
            ResultSet miconsulta = miconexion.consultarBD("SELECT id,nombre,temperatura,valorBase FROM productos");
            //Recorro todo el Resulset con el metodo next
            while(miconsulta.next())
            {
                miproducto = new Producto();
                miproducto.setId(miconsulta.getString("id"));
                miproducto.setNombre(miconsulta.getString("nombre"));
                miproducto.setTemperatura(miconsulta.getDouble("temperatura"));
                miproducto.setValorBase(miconsulta.getDouble("valorBase"));
            
                milistaproductos.add(miproducto);
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Error listando los productos. " + ex.getMessage());
        }
        finally
        {
            miconexion.cerrarConexion();
        }
        
        return(milistaproductos);
        }
        
         //METODO PARA GRABAR PRODUCTOS
    public boolean guardarProducto()
    {
        //Me conecto a la base de datos
        ConexionBD miconexion = new ConexionBD();
        //Creo el script SQL para la transacción
        String SQL = "INSERT INTO productos(id,nombre,temperatura,valorBase) VALUES("+this.id+",'"+this.nombre+"',"+this.temperatura+","+this.valorBase+");";
        if(miconexion.setAutoCommitBD(false)==true)
        {
            if(miconexion.insertarBD(SQL)==true)
            {
                miconexion.commitBD(); //Ejecuta el cambio preparado en el script
                miconexion.cerrarConexion();
                return(true);
            }
            else
            {
                miconexion.rollbackBD(); //Deshacer el cambio que se habia preparado
                miconexion.cerrarConexion();
                return(false);
            }
        }
        else
        {
            miconexion.cerrarConexion();
            return(false);  
        }
    }
    //METODO PARA ACTUALIZAR PRODUCTOS
    public boolean actualizarProducto()
    {
        //Me conecto a la base de datos
        ConexionBD miconexion = new ConexionBD();
        //Creo el script SQL para la transacción
        String SQL = "UPDATE productos SET id= '"+this.id+"' ,nombre= '"+this.nombre+"' ,temperatura= '"+this.temperatura+"' ,valorBase='"+this.valorBase+"' WHERE ID= "+this.id+"";
        if(miconexion.setAutoCommitBD(false)==true)
        {
            if(miconexion.actualizarBD(SQL)==true)
            {
                miconexion.commitBD(); //Ejecuta el cambio preparado en el script
                miconexion.cerrarConexion();
                return(true);
            }
            else
            {
                miconexion.rollbackBD(); //Deshacer el cambio que se habia preparado
                miconexion.cerrarConexion();
                return(false);
            }
        }
        else
        {
            miconexion.cerrarConexion();
            return(false);
        }
    }
    //METODO PARA BORRAR CLIENTES
    public boolean borrarProducto()
    {
        //Me conecto a la base de datos
        ConexionBD miconexion = new ConexionBD();
        //Creo el script SQL para la transacción
        String SQL = "DELETE FROM productos WHERE id="+this.id+";";
        if(miconexion.setAutoCommitBD(false)==true)
        {
            if(miconexion.borrarBD(SQL)==true)
            {
                miconexion.commitBD(); //Ejecuta el cambio preparado en el script
                miconexion.cerrarConexion();
                return(true);
            }
            else
            {
                miconexion.rollbackBD(); //Deshacer el cambio que se habia preparado
                miconexion.cerrarConexion();
                return(false);
            }
        }
        else
        {
            miconexion.cerrarConexion();
            return(false);  
        }
    }  
  
}
