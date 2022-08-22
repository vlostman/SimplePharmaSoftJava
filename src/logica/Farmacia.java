
package logica;

import java.util.ArrayList;
import java.util.List;

public class Farmacia{
    private String nit;
    private String nombre;
    private String direccion;
    private List<Almacen> almacenes;

    public Farmacia(String nit, String nombre, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.almacenes=new ArrayList<>();
    }
    
    public Farmacia(){
        this.almacenes=new ArrayList<>();       
    }
    
    public void agregarAlmacen(Almacen almacen){
        almacenes.add(almacen);
    }
    
    
    public void agregarProducto(String codigoAlmacen,Producto p){
        for(Almacen almacen:almacenes){
            if(almacen.getCodigo().equals(codigoAlmacen)){
                almacen.agregarProducto(p);
            }
        }
    }
    
    public void rrecorrerAlmacenes(){
        for(Almacen almacen:almacenes){
            System.out.println(almacen);
        }
    }

    @Override
    public String toString() {
        return "Farmacia{" + "nit=" + nit + " nombre=" + nombre + " direccion=" + direccion + " almacenes=" + almacenes + '}';
    }
    
    
    
}
