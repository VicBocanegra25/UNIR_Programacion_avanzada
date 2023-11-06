package tienda;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /* En esta primera parte del programa main, vamos a crear los zapatos de tres tipos: Oxford, Tenis y Sandalia*/
        Oxford zapato1 = new Oxford();
        Tenis zapato2 = new Tenis();
        Sandalias zapato3 = new Sandalias();

        Oxford zapato4 = new Oxford();
        Tenis zapato5 = new Tenis();
        Sandalias zapato6 = new Sandalias();

        // Ahora utilizamos los setters para darles atributos a los zapatos de diferentes tipos
        zapato1.setPrecio(1000);
        zapato1.setDisponible(true);
        zapato1.setDePiel(false);
        zapato1.setColor("Negro");

        // Agregamos un zapato sin disponibilidad
        zapato4.setPrecio(1500);
        zapato4.setDisponible(false);
        zapato4.setDePiel(true);
        zapato4.setColor("Café");

        zapato2.setPrecio(700);
        zapato2.setDisponible(true);
        zapato2.setColor("Blanco");
        zapato5.setPrecio(800);
        zapato5.setDisponible(true);
        zapato5.setColor("Azul");

        zapato3.setPrecio(500);
        zapato3.setDisponible(true);
        zapato3.setDePiel(true);

        zapato6.setPrecio(600);
        zapato6.setDisponible(true);
        zapato6.setColor("Café");

        /* En esta segunda parte del programa main, vamos a crear dos usuarios y dos transacciones independientes . */
        UsuarioFinal usuario1 = new UsuarioFinal();
        UsuarioFinal usuario2 = new UsuarioFinal();

        // Utilizamos los setters para colocar los atributos (nombre, apellido, email, contraseña, etc...)
        usuario1.actualizarNombre("Victor");
        usuario1.actualizarApellido("Bocanegra");
        usuario1.actualizarCorreoElectrónico("victor.bocanegra@unir.com");
        // La contraseña debe contener 8 caracteres, una mayúscula, una minúscula, un número y un caracter especial
        usuario1.actualizarContraseña("Contraseña1!");
        usuario1.actualizarDirección("Calle 1, Colonia 1, Ciudad 1");
        usuario1.actualizarCuentaBancaria("1234567890");

        usuario2.actualizarNombre("James");
        usuario2.actualizarApellido("Bond");
        usuario2.actualizarCorreoElectrónico("007@mi6.co.uk");
        usuario2.actualizarContraseña("Contraseña2!");
        usuario2.actualizarDirección("Calle 2, Colonia 2, Ciudad 2");
        usuario2.actualizarCuentaBancaria("0987654321");

        // Inicializamos los carritos de compra y agregamos los zapatos a cada carrito.
        // El primer usuario tendrá los productos más económicos
        usuario1.carrito.agregarArticulo(zapato1);
        usuario1.carrito.agregarArticulo(zapato2);
        usuario1.carrito.agregarArticulo(zapato3);
        // Colocamos la talla para los zapatos de este usuario.
        zapato1.setTalla("26"); zapato2.setTalla("26"); zapato3.setTalla("26");

        // El segundo usuario tendrá los productos más caros (sin embargo, uno de ellos no está disponible)
        // Confirmamos que se lanza la excepción, por lo que actualizamos y agregamos un zapato diferente
        Oxford zapato7 = new Oxford(); zapato7.setColor("Negro"); zapato7.setPrecio(1500); zapato7.setDisponible(true);

        usuario2.carrito.agregarArticulo(zapato7);
        usuario2.carrito.agregarArticulo(zapato5);
        usuario2.carrito.agregarArticulo(zapato6);
        zapato7.setTalla("27"); zapato5.setTalla("27"); zapato6.setTalla("27");


        // Mostramos los productos de cada carrito y el total
        usuario1.carrito.mostrarProductos();
        System.out.println("El total a pagar es: $" + usuario1.carrito.getTotal()+ "\n") ;

        usuario2.carrito.mostrarProductos();
        System.out.println("El total a pagar es: $" + usuario2.carrito.getTotal()+ "\n");

        // Llamemos los métodos para pagar y así generar transacciones
        usuario1.carrito.pagar("Contraseña1!");
        // Imprimimos el historial de transacciones
        usuario1.imprimirHistorial();

        





    }
}

