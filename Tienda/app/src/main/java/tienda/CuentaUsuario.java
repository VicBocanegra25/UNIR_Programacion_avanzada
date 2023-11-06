package tienda;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;

/* La clase CuentaUsuario es una representación genérica de las cuentas que se utilizarán para interactuar con la
aplicación. Esta cuenta es una superclase*/

public class CuentaUsuario {

     // Definición de los atributos de la clase CuentaUsuario.
    private static int id = 0;
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String correoElectrónico;
    private String contraseña;

    // El constructor inicia únicamente con el idUsuario (se va incrementando)
    public CuentaUsuario() {
        this.idUsuario = ++ id;
    }
    // Al crear un nuevo usuario hacemos uso de los setters para asignarle valores a los atributos
    void actualizarNombre(String nombre) {
        // Verificamos si el nombre es válido (es un String (utilizamos regex) y no está vacío (llamamos el método auxuliar)
        if (VerificadorContraseña.cadenaNoVacia(nombre) && nombre.matches("[a-zA-Z]+")) {
            this.nombre = nombre;
        } else {
            // Si el nombre no es válido, se lanza una excepción
            throw new IllegalArgumentException("El nombre no es válido");
        }
    }
    void actualizarApellido(String apellido) {
        // Verificamos si el apellido es válido (es un String (utilizamos regex) y no está vacío (llamamos el método auxuliar)
        if (VerificadorContraseña.cadenaNoVacia(apellido) && apellido.matches("[a-zA-Z]+")) {
            this.apellido = apellido;
        } else {
            // Si el nombre no es válido, se lanza una excepción
            throw new IllegalArgumentException("El apellido no es válido");
        }
    }
    void actualizarCorreoElectrónico(String correoElectrónico) {
        // Es importante validar si el correo electrónico es válido. Utilizaremos la librería Apache Commons Validator
        EmailValidator validator = EmailValidator.getInstance();
        // Verificamos si el correo electrónico es válido
        if (validator.isValid(correoElectrónico)) {
            this.correoElectrónico = correoElectrónico;
        } else {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }
    }
    void actualizarContraseña(String contraseña_) {
        // La seguridad en el desarrollo es importante. Se implementó una clase auxiliar (VerificadorContraseña) para validar la contraseña y también
        // se implementó una función Hash (bcrypt) para encriptarla.
        boolean contraseñaValida = VerificadorContraseña.validarContraseña(contraseña_);
        if (contraseñaValida) {
            // Si el usuario ingresó una contraseña válida, utilizamos el método en la clase auxiliar para encriptarla
            this.contraseña = VerificadorContraseña.hashPassword(contraseña_);
        }
        // Si el usuario ingresó una contraseña no válida, se lanza una excepción
        else {
            throw new IllegalArgumentException("La contraseña no es válida");
        }
    }

    // En este punto ya hemos definido los setters, ahora definimos los getters
    public int getIdUsuario() {
        return idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getCorreoElectrónico() {
        return correoElectrónico;
    }
    /** El getter de la contraseña es diferente, no devolvemos la contraseña, sino que tomamos el Hash,
     * el cual se utilizará para verificar si el usuario colocó la contraseña correcta durante un inicio de sesión.*/

    public Boolean getContraseña(String contraseña_) {
        return VerificadorContraseña.verifyPassword(contraseña_, this.contraseña);
    }

    // Definimos el método para eliminar la cuenta (se solicita contraseña)
    public void eliminarCuenta(String contraseña_) {
        // Primero validamos que la contraseña sea correcta
        boolean contraseñaCorrecta = getContraseña(contraseña_);
        // Si la contraseña es correcta, eliminamos la cuenta
        if (contraseñaCorrecta) {
            this.idUsuario = 0;
            this.nombre = null;
            this.apellido = null;
            this.correoElectrónico = null;
            this.contraseña = null;
        }
        // Si la contraseña no es correcta, se lanza una excepción
        else {
            throw new IllegalArgumentException("La contraseña no es correcta");
        }
    }




}

class Administrador extends CuentaUsuario{
    // Definimos el booleano isAdministrator
    private boolean isAdministrator;
}


class UsuarioFinal extends CuentaUsuario{
    // El usuario final tiene los atributos adicionales de dirección y cuenta bancaria
    private String dirección;
    private String cuentaBancaria;
    // Además, tiene un carrito de compras
    protected CarritoDeCompra carrito;
    // Y tiene un historial de transacciones
    private ArrayList<Transacciones> transacciones;

    // Constructor
    public UsuarioFinal() {
        this.carrito = new CarritoDeCompra(this);
        this.transacciones = new ArrayList<Transacciones>();

    }

    // Agregamos los setters para los atributos adicionales
    void actualizarDirección(String dirección_) {
        if (VerificadorContraseña.cadenaNoVacia(dirección_)) {
            this.dirección = dirección_;
        } else {
            throw new IllegalArgumentException("La dirección no es válida");
        }
    }
    protected void actualizarCuentaBancaria(String cuentaBancaria_) {
        if (VerificadorContraseña.cadenaNoVacia(cuentaBancaria_)) {
            this.cuentaBancaria = cuentaBancaria_;
        } else {
            throw new IllegalArgumentException("La cuenta bancaria no es válida");
        }
    }

    // Setter para las transacciones
    protected void actualizarTransacciones(Transacciones transacciones_) {
        this.transacciones.add(transacciones_);
    }

    // Getter para el historial de transacciones
    public ArrayList<Transacciones> getTransacciones() {
        return transacciones;
    }
    // Método auxiliar para imprimir el historial (se llama el getter)
    public void imprimirHistorial(){
        System.out.println("Imprimiendo el historial de transacciones:");
        for (Transacciones transaccion : this.transacciones) {
            System.out.println("No. Transacción: " + transaccion.getIdTransacción());
            System.out.println("Fecha: " + transaccion.getFecha());
            System.out.println("Productos: " + transaccion.getProductos());
            System.out.println("Total: $" + transaccion.getTotal());
        }
    }
}
