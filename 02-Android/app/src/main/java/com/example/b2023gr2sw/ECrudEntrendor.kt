package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudEntrendor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrendor)
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)

            val entrenador = EBaseDeDatos.tablaEntrenador!!
                .consultarEntrenadorPorID(
                    id.text.toString().toInt()
                )
            if(entrenador.id == 0){
                mostrarSnackbar("Usu no encontrado")
            }
            id.setText(entrenador.id.toString())
            nombre.setText(entrenador.nombre)
            descripcion.setText(entrenador.descripcion)

            val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
            botonCrearBDD
                .setOnClickListener {
                    val nombre = findViewById<EditText>(R.id.input_nombre)
                    val descripcion = findViewById<EditText>(R.id.input_descripcion)
                    val respuesta = EBaseDeDatos
                        .tablaEntrenador!!.crearEntrenador(
                            nombre.text.toString(),
                            descripcion.text.toString()
                        )
                    if (respuesta) mostrarSnackbar("Ent. Creado")
                }
            val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
            botonActualizarBDD
                .setOnClickListener {
                    val id = findViewById<EditText>(R.id.input_id)
                    val nombre = findViewById<EditText>(R.id.input_nombre)
                    val descripcion = findViewById<EditText>(R.id.input_descripcion)
                    val respuesta = EBaseDeDatos.tablaEntrenador!!.actualizarEntrenadorformulario(
                            nombre.text.toString(),
                            descripcion.text.toString(),
                                id.text.toString().toInt()
                        )
                    if (respuesta) mostrarSnackbar("Ent. Actualizado")
                }
            val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
            botonEliminarBDD.setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val respuesta = EBaseDeDatos.tablaEntrenador!!
                    .eliminarEntrenadorFormulario(
                        id.text.toString().toInt()
                    )
                if (respuesta)mostrarSnackbar("Usu. Eliminado")

            }
        }

    }
    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.cl_sqlite),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }
}