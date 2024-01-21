package com.example.b2023gr2sw

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?,
    ):    SQLiteOpenHelper(contexto,"moviles",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador=
            """
                CREATE TABLE ENTRENADOR(
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun crearEntrenador(
        nombre:String,
        descripcion:String
    ):Boolean{
      val basedatosEscritura = writableDatabase
      val valoresAGuardar = ContentValues()
      valoresAGuardar.put("nombre",nombre)
      valoresAGuardar.put("descripcion", descripcion)
      val resultadoGuardar = basedatosEscritura.insert(
              "ENTRENADOR",
              null,
              valoresAGuardar
          )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }
    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura =writableDatabase

        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorformulario(
        nombre:String,
        descripcion: String,
        id: Int
    ):Boolean{
        val conexionEscritura =writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR",
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }
    fun consultarEntrenadorPorID(id:Int):BEntrenador{
        val baseDatosLectura = readableDatabase
        val scriptConsltaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsltaLectura, parametrosConsultaLectura)


        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()
        if (existeUsuario){
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if (id != null){
                    usuarioEncontrado.id   = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            }while (resultadoConsultaLectura.moveToLast())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }


}