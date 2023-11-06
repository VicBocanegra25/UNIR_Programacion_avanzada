package tienda;

import java.util.ArrayList;

/* Las transacciones representan las compras realizadas por el usuario.
* Como tal, incluyen el id de transacción, el id del usuario que hizo la compra, la fecha, el id de los productos (y el precio),
* el total de la transacción */
public class Transacciones {
    // Definimos los atributos
    private static int id = 0;
    private int idTransacción;

    private int idUsuario;
    private String fecha;
    private ArrayList<Zapato> productos;
    private float total;

    // El constructor inicia vacío, pero se actualiza con los métodos setters
    public Transacciones(){
        this.idTransacción = ++ id;
        this.idUsuario = 0;
        this.fecha = null;
        this.productos = new ArrayList<Zapato>();
        this.total = 0;
    }

    // Definimos los setters

    // Las transacciones se autoincrementan, por lo que no es necesario pasar un idTransacción
    // El idUsuario se pasa como argumento
    protected void actualizarIdUsuario(int idUsuario_){
        this.idUsuario = idUsuario_;
    }
    // La fecha se obtiene de forma automática con una librería
    protected void actualizarFecha(){
        this.fecha = java.time.LocalDate.now().toString();
    }
    // La lista de productos se pasa como argumento
    protected void actualizarProductos(ArrayList<Zapato> productos_){
        this.productos = productos_;
    }
    // El total se pasa como argumento
    protected void actualizarTotal(float total_){
        this.total = total_;
    }

    // Definimos los getters para los atributos
    public int getIdTransacción() {
        return idTransacción;
    }
    public String getFecha() {
        return fecha;
    }
    public ArrayList<Zapato> getProductos() {
        return productos;
    }
    public float getTotal() {
        return total;
    }


}
