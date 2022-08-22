package logica;

    public class ProductoRefrigerado extends Producto{

    public ProductoRefrigerado(String nombre, String id, double temperatura, double valorBase, double costo) {
        super(nombre, id, temperatura, valorBase, costo);
    }  
   
    public double calcularCostoDeAlmacenamiento(){
        return getValorBase()*1.1;
    }

    @Override
    public String toString() {
        return "ProductoRefrigerado:"+"Nombre:"+getNombre()+" id:"+getId()+" Temperatura:"+getTemperatura()+"Costo:"+calcularCostoDeAlmacenamiento();
    }

    
}

