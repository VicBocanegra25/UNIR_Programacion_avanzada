package tienda;

import java.util.ArrayList;

/* El carrito de compra es una representación del carrito en donde los usuarios colcoan los productos que están interesados
* en comprar y posteriormente pagan para procesar la orden. Está relacionado con el usuario final, las transacciones y los
* productos (Zapatos) */
public class CarritoDeCompra {

    // Los atributos del carrito son:
    private int idCarrito;
    // Una lista que contiene los zapatos
    private ArrayList<Zapato> productos;
    private float total;
    private UsuarioFinal usuario;

    // Definimos el constructor
    public CarritoDeCompra(UsuarioFinal usuario_){
        this.usuario = usuario_;
        // El carrito tiene el mismo id que el usuario
        this.idCarrito = usuario_.getIdUsuario();
        // La lista de productos inicia vacía
        this.productos = new ArrayList<Zapato>();
        // El total inicia en $0
        this.total = 0;
    }

    // Los métodos que definimos
    /* Este método remueve el artículo solicitado del carrito. El argumento requerido es el idZapato*/
    public void removerArticulo(int idZapato_){
        // Primero revisamos si el artículo está en el carrito y si está, lo eliminamos
        this.productos.removeIf(zapato -> zapato.getIdZapato() == idZapato_);
    }

    /* Este método muestra los artículos que actualmente están en el carrito. (Actualización): No basta con mostrarlos,
    * hay que devolverlos para utilizar este método durante el pago y poder actualizar las transacciones */
    public void mostrarProductos(){
        // Si el carrito está vacío, se muestra un mensaje
        if (this.productos.isEmpty()){
            System.out.println("El carrito está vacío");
        }
        // Si el carrito no está vacío, se muestran los artículos
        else {
            System.out.println("Los artículos en el carrito son:");
            for (Zapato zapato : this.productos) {
                // Se muestra el id del zapato
                System.out.println("Id: " + zapato.getIdZapato());
                // Se muestra la talla del zapato
                System.out.println("Talla: " + zapato.getTalla());
                // Se muestra el precio del zapato
                System.out.println("Precio: $" + zapato.getPrecio());
            }
        }
    }

    /* El método devolverProductos() devuelve los artículos que actualmente están en el carrito. (Actualización): No basta con mostrarlos,
     * hay que devolverlos para utilizar este método durante el pago y poder actualizar las transacciones */
    public ArrayList<Zapato> devolverProductos(){
        mostrarProductos();
        return this.productos;
    }

    // Es común que los usuarios quieran vaciar el carrito, por lo que definimos un método para hacerlo
    public void vaciarCarrito(){
        // Simplemente eliminamos todos los artículos del carrito
        this.productos.clear();
    }

    /* Para poder pagar, necesitamos conocer el total del carrito. Atravesamos la lista de productos consultando el precio
    * de cada artículo y actualizamos el total. */
    public float getTotal() {
        // El total inicia en $0
        this.total = 0;
        // Recorremos la lista de productos
        for (Zapato zapato : this.productos) {
            // Actualizamos el total
            this.total += zapato.getPrecio();
        }
        // Retornamos el total
        return this.total;
    }

    // Se decidió colocar el método para agregar artículos dentro del carrito, en lugar de en el mismo producto
    public void agregarArticulo(Zapato zapato_){
        // Verificamos si el zapato está disponible
        if (!zapato_.getDisponible()){
            // Si el zapato no está disponible, se lanza una excepción
            throw new IllegalArgumentException("El artículo no está disponible");
        }
        // Agregamos el artículo a la lista de productos
        this.productos.add(zapato_);
    }

    /* Conocer el importe del carrito y pagar son operaciones distintas. Para pagar, necesitamos verificar que el usuario realmente
    * ha iniciado sesión, por lo que pedimos su contraseña para confirmar la compra.
    * Además, la compra genera una transacción (que después se agrega al historial de compras del usuario). */
    public Transacciones pagar(String contraseña_){
        // Verificamos la validez de la contraseña
        if (usuario.getContraseña(contraseña_)){
            // Generamos la transacción
            Transacciones transaccion = new Transacciones();
            // Llamamos los setters de la transacción
            transaccion.actualizarIdUsuario(this.idCarrito);
            transaccion.actualizarFecha();
            transaccion.actualizarProductos(devolverProductos());
            transaccion.actualizarTotal(getTotal());
            usuario.actualizarTransacciones(transaccion);

            return transaccion;
        } else {
            // Si la contraseña no es válida, se lanza una excepción
            throw new IllegalArgumentException("La contraseña no es válida");
        }

    }
}
