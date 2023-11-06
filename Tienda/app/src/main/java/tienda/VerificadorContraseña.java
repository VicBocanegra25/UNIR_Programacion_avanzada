package tienda;

import org.passay.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;
import java.util.List;

/**
 * Esta es una clase auxiliar, su propósito es validar la contraseña enviada por el usuario.
 * La contraseña es válida si contiene 8 a 30 caracteres, una mayúscula, una minúscula, un número,
 * sin espacios y un carácter especial.
 */
public class VerificadorContraseña {
    public static boolean validarContraseña(String contraseña_) {
        // Creando una lista de reglas para la validación de la contraseña
        List<Rule> reglas = Arrays.asList(
                // longitud entre 8 y 30 caracteres
                new LengthRule(8, 30),
                // al menos 1 carácter en mayúscula
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // al menos 1 carácter en minúscula
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // al menos 1 número
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // al menos 1 carácter especial
                new CharacterRule(EnglishCharacterData.Special, 1),
                // no espacios en blanco
                new WhitespaceRule()
        );

        // Creando el verificador de contraseñas con las reglas definidas
        PasswordValidator verificador = new PasswordValidator(reglas);

        // Validando la contraseña
        RuleResult resultado = verificador.validate(new PasswordData(contraseña_));

        // Si la contraseña es válida, retornamos true
        if (resultado.isValid()) {
            return true;
        } else {
            // Si la contraseña no es válida, mostramos los mensajes de error y retornamos false
            for (String msg : verificador.getMessages(resultado)) {
                System.out.println(msg);
            }
            return false;
        }
    }
    // Un método para encriptar la contraseña
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }
    // Un método para verificar la contraseña
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    /** El siguiente es un método auxiliar para verificar que una cadena no esté vacía
     * */
    public static boolean cadenaNoVacia(String cadena) {
        return cadena != null && !cadena.isEmpty();
    }

}
