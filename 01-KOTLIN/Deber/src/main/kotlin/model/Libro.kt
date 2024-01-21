package model

class Libro (
    private val autor:String,
    private val titulo:String,
    private val disponibilidad:Boolean,
    private val edicion:Int,
    private val precio:Double
){

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

    override fun toString(): String {
        return "Libro(autor='$autor', titulo='$titulo', disponibilidad=$disponibilidad, edicion=$edicion, precio=$precio)"
    }



}