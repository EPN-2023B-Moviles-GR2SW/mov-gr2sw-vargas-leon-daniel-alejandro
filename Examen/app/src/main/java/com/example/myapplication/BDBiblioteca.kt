package com.example.myapplication

import Biblioteca
import Libro

class BDBiblioteca {
    companion object{
        var arregloBibliotecas = arrayListOf<Biblioteca>()
        init {
            arregloBibliotecas.add(Biblioteca(ArrayList(), "LA bibliotoca", "Zaruma", "Puruha", true))
            arregloBibliotecas[0].libros.add(Libro("a","b",true, 1,10.0))
        }
    }
}