package tienda;

public class Zapato {

    // Definimos los atributos de todos los zapatos
    private static int id = 0;
    private int idZapato; // El id del zapato es único
    private String talla;
    private float precio;
    private Boolean disponible;

    // Definimos el constructor (aumenta el ID de zapato cuando se genera uno nuevo)
    public Zapato() {
        this.idZapato = ++ id;
    }

    // Definimos el getter para el idZapato
    public int getIdZapato() {
        return idZapato;
    }

    // Definimos el getter para la talla
    public String getTalla() {
        return talla;
    }

    // Definimos el getter para el precio
    public float getPrecio() {
        return precio;
    }

    // Definimos el getter para la disponibilidad
    public Boolean getDisponible() {
        return disponible;
    }

    // Definimos los setters
    public void setTalla(String talla_) {
        this.talla = talla_;
    }

    public void setPrecio(float precio_) {
        this.precio = precio_;
    }

    // Por defecto, el zapato está disponible, pero se puede cambiar con este setter
    public void setDisponible(Boolean disponible_) {
        this.disponible = disponible_;
    }
}

/** Al igual que en la clase de usuario, la clase Zapato se extiende para considerar tipos de zapatos: Oxford, Tenis y Sandalias
 * Estos artículos comparten atributos de la clase Zapato, pero tienen algunos atributos propios. */
class Oxford extends Zapato {
    // Definimos los atributos propios de la clase
    private Boolean dePiel;
    private Boolean conAgujetas;
    // Los colores se definen como un arreglo de Strings
    private String color;

    // Definimos setters para los atributos propios de la clase
    public void setDePiel(Boolean dePiel_) {
        this.dePiel = dePiel_;
    }
    public void setConAgujetas(Boolean conAgujetas_) {
        this.conAgujetas = conAgujetas_;
    }
    public void setColor(String color_) {
        this.color = color_;
    }

}

class Tenis extends Zapato {
    // Definimos los atributos propios de la clase
    // Los colores se definen como un arreglo de Strings
    private String color;

    // Definimos setters para los atributos propios de la clase
    public void setColor(String color_) {
        this.color = color_;
    }
}

class Sandalias extends Zapato {
    // Definimos los atributos propios de la clase
    private Boolean dePiel;
    // Los colores se definen como un arreglo de Strings
    private String color;

    // Definimos setters para los atributos propios de la clase
    public void setDePiel(Boolean dePiel_) {
        this.dePiel = dePiel_;
    }
    public void setColor(String color_) {
        this.color = color_;
    }
}
