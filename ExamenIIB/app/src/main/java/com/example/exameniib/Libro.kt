package com.example.exameniib

import java.io.Serializable

class Libro (
    val autor:String,
     val titulo:String,
     val disponibilidad:Boolean,
     val edicion:Int,
     val precio:Double
): Serializable {

    init {
        autor;
        titulo;
        disponibilidad;
        edicion;
        precio
    }

    override fun toString(): String {
        return "Autor: ${autor} \n " +
                "Titulo: ${titulo}\n" +
                "Edicion ${edicion}\n" +
                "Precio: ${precio}$"
    }



}