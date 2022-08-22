
package logica;


public class ProductoNoRefrigerado extends Producto {
  
    public ProductoNoRefrigerado(String nombre, String id, double temperatura, double valorBase, double costo) {
        super(nombre, id, temperatura, valorBase, costo);
    }
    
    public ProductoNoRefrigerado(){
    }
   @Override
    public double calcularCostoDeAlmacenamiento(){
        return getValorBase()*1.1f;
    }
}
