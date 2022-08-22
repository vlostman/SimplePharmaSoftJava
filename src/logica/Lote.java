
package logica;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    private List<Producto> productos;

    public Lote() {
        this.productos=new ArrayList<>();
    }
    
    public void agregarProducto(Producto p){
        productos.add(p);
    }
    
    public void rrecorrerLote(){
        for(Producto producto:productos){
            System.out.println(producto);
        }
    }
    
    @Override
    public String toString() {
        return "Lote{" + "productos=" +"\n"+ productos + '}';
    }
    
    
    
}