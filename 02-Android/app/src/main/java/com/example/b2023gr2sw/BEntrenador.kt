package com.example.b2023gr2sw

import java.io.Serializable

class BEntrenador(
    var id: Int,
    var nombre: String?,
    var descripcion:String?
): Serializable {
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}