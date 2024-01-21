package model

class Biblioteca(
    //TODO cambiar libro por un arreglo de libros
    val libros: ArrayList<Libro> = ArrayList(),
    val nombre: String?,
    val callePrincipal: String?,
    val calleSecundaria: String?,
    val abierto: Boolean
){

    override fun toString(): String {
        return "\nBiblioteca(nombre=$nombre, callePrincipal=$callePrincipal, calleSecundaria=$calleSecundaria, abierto=$abierto, \nlibros=$libros\n)"
    }
}