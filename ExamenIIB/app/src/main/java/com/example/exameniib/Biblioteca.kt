package com.example.exameniib

import java.io.Serializable

class Biblioteca(
    //TODO cambiar libro por un arreglo de libros
    val libros: ArrayList<Libro> = ArrayList(),
    val nombre: String?,
    val callePrincipal: String?,
    val calleSecundaria: String?,
    val abierto: Boolean?,
    val id:String?
): Serializable {
    override fun toString(): String {
        return "Nombre: ${nombre}  \n" +
                "Calle principal: ${callePrincipal} \n" +
                "Calle Secundaria: ${calleSecundaria}"
    }
}