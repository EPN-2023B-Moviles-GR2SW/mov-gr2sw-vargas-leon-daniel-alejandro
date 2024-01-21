package com.example.myapplication

import Biblioteca

class BDBiblioteca {
    companion object{
        var arregloBibliotecas = arrayListOf<Biblioteca>()
        init {
            arregloBibliotecas.add(Biblioteca(ArrayList(), "LA bibliotoca", "Zaruma", "Puruha", true))
        }
    }
}