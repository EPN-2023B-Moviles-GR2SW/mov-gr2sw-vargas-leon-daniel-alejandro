package com.example.b2023gr2sw

class BBaseDatosMemoria {
    companion object{
        var arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(BEntrenador(1,"Daniel","a@a.com"))
            arregloBEntrenador
                .add(BEntrenador(2,"Alejandro","a@adad.com"))
            arregloBEntrenador
                .add(BEntrenador(3,"Carolina","c@c.com"))

        }
    }
}