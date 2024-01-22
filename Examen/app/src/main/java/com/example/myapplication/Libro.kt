import java.io.Serializable

class Libro (
    private val autor:String,
    private val titulo:String,
    private val disponibilidad:Boolean,
    private val edicion:Int,
    private val precio:Double
): Serializable{

    init {
        autor;
        titulo;
        disponibilidad;
        edicion;
        precio
    }
    fun getAutor(): String {
        return autor
    }
    fun getTitulo(): String {
        return titulo
    }
    fun getEdicion(): Int {
        return edicion
    }
    fun getDisponibilidad(): Boolean {
        return disponibilidad
    }
    fun getPrecio():Double{
        return precio
    }

    override fun toString(): String {
       return "Autor: ${autor} \n " +
               "Titulo: ${titulo}\n" +
               "Edicion ${edicion}\n" +
               "Precio: ${precio}$"
        }



}