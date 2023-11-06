package tienda;
/* Esta clase se utilizará para realizar pruebas de la clase UsuarioTest.java, se probarán algunos métodos de la clase,
* en particular, queremos probar que el usuario es capaz de generar una contraseña segura.
* Realizaremos las pruebas utilizando JUnit*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    // Inicializamos la clase Usuario
    private UsuarioFinal usuario;

    // Este método se realiza antes de cada prueba
    @BeforeEach
    void setUp() {
        // Inicializaremos un usuario final con datos de prueba, que después serán modificados
        usuario = new UsuarioFinal();
        usuario.actualizarNombre("Jon");
        usuario.actualizarApellido("Snow");
        usuario.actualizarCorreoElectrónico("jsnow@westeros.com");
        usuario.actualizarContraseña("NightWatch1!");
        usuario.actualizarDirección("Winterfell");
        usuario.actualizarCuentaBancaria("123456789");

    }
    // Ahora probamos actualizar los datos con los setters
    @Test
    void actualizarNombrePrueba(){
        String nuevoNombre = "Aegon";
        assertDoesNotThrow(() -> usuario.actualizarNombre(nuevoNombre));
        assertEquals(nuevoNombre, usuario.getNombre());
    }
    @Test
    void actualizarApellidoPrueba(){
        String nuevoApellido = "Targaryen";
        assertDoesNotThrow(() -> usuario.actualizarApellido(nuevoApellido));
        assertEquals(nuevoApellido, usuario.getApellido());
    }
    // Ahora probamos actualizando la contraseña colocando una contraseña válida (8 caracteres, 1 mayúscula, 1 minúscula, 1 número y 1 caracter especial))
    @Test
    void actualizarContraseñaPruebaValida(){
        String nuevaContraseña = "Targaryen1!";
        assertDoesNotThrow(() -> usuario.actualizarContraseña(nuevaContraseña));
        assertEquals(true, usuario.getContraseña("Targaryen1!"));
    }
    // Probamos con una contraseña que no cumple los requisitos (lanza excepción)
    @Test
    void actualizarContraseñaPruebaInvalida(){
        String nuevaContraseña = "Targaryen";
        assertThrows(IllegalArgumentException.class, () -> usuario.actualizarContraseña(nuevaContraseña));
    }

    // Probamos eliminando la cuenta
    @Test
    void eliminarCuentaPrueba(){
        // El método requiere la contraseña para eliminar la cuenta
        assertDoesNotThrow(() -> usuario.eliminarCuenta("NightWatch1!"));
        // Verificamos que los datos se hayan eliminado
        assertEquals(0, usuario.getIdUsuario());
        assertEquals(null, usuario.getNombre());
        assertEquals(null, usuario.getApellido());
        assertEquals(null, usuario.getCorreoElectrónico());

    }
}
