package logica;

public class Almacen {
    private String codigo;
    private String nombre;
    private Lote lote;

    public Almacen(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.lote= new Lote();
    }
    
    public void agregarProducto(Producto p){
        lote.agregarProducto(p);
    }

    @Override
    public String toString() {
        return "\nAlmacen{" + "codigo=" + codigo + " nombre=" + nombre + " lote=" + lote + '}';
    }

    public String getCodigo() {
        return codigo;
    }

}
