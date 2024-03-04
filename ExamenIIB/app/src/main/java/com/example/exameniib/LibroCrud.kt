package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class LibroCrud : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_crud)
        val botonAccion = findViewById<Button>(R.id.btn_input)

        if (intent.getStringExtra("actividad") == "crear")
            crearLibro(intent.getSerializableExtra("item") as Biblioteca,botonAccion )
        if (intent.getStringExtra("actividad")=="actualizar")
            actualizarLibro(intent.getSerializableExtra("item") as Biblioteca,botonAccion, intent.getIntExtra("posicion",0) )

    }



    private fun crearLibro(biblioteca:Biblioteca, botonCrear: Button?) {
        botonCrear
            ?.setOnClickListener {
                val autor = findViewById<EditText>(R.id.in_Autor)
                val titulo = findViewById<EditText>(R.id.in_titulo)
                val edicion = findViewById<EditText>(R.id.in_edicion)
                val precio = findViewById<EditText>(R.id.in_precio)
                val disponibilidad = findViewById<CheckBox>(R.id.cb_dispobibleLibro)
                val nuevoLibro = Libro(autor.text.toString(), titulo.text.toString(), disponibilidad.isChecked, edicion.text.toString().toInt(), precio.text.toString().toDouble() )
                biblioteca?.libros?.add(nuevoLibro)
                DBbiblioteca().crearBibliotecaDB(biblioteca)
                irActividad(LibroView::class.java,biblioteca)
            }

    }
    private fun actualizarLibro(biblioteca: Biblioteca, botonAccion: Button?, intExtra: Int) {
        if (botonAccion != null) {
            botonAccion.text = "Actualizar"
        }

        val text = findViewById<TextView>(R.id.text_tituloLibro)
        text.text = "Actualizar Libro"
        //Mostraremos la informacion actual del libro
        val autor = findViewById<EditText>(R.id.in_Autor)
        val titulo = findViewById<EditText>(R.id.in_titulo)
        val edicion = findViewById<EditText>(R.id.in_edicion)
        val precio = findViewById<EditText>(R.id.in_precio)
        val disponibilidad = findViewById<CheckBox>(R.id.cb_dispobibleLibro)

        autor.setText(biblioteca.libros[intExtra].autor)
        titulo.setText(biblioteca.libros[intExtra].titulo)
        edicion.setText(biblioteca.libros[intExtra].edicion.toString())
        precio.setText(biblioteca.libros[intExtra].precio.toString())
        disponibilidad.setChecked(biblioteca.libros[intExtra].disponibilidad)

        //Reemplazo de la informacion
        if(botonAccion != null){
            botonAccion
                .setOnClickListener {
                    val autor = findViewById<EditText>(R.id.in_Autor)
                    val titulo = findViewById<EditText>(R.id.in_titulo)
                    val edicion = findViewById<EditText>(R.id.in_edicion)
                    val precio = findViewById<EditText>(R.id.in_precio)
                    val disponibilidad = findViewById<CheckBox>(R.id.cb_dispobibleLibro)
                    val nuevoLibro = Libro(autor.text.toString(), titulo.text.toString(), disponibilidad.isChecked, edicion.text.toString().toInt(), precio.text.toString().toDouble() )
                    biblioteca.libros[intExtra] = nuevoLibro
                    DBbiblioteca().crearBibliotecaDB(biblioteca)
                    irActividad(LibroView::class.java,biblioteca)
                }
        }
    }
    fun irActividad(
        clase: Class<*>,
        biblioteca: Biblioteca
    ){

        val intent = Intent(this,clase)
        intent.putExtra("item",biblioteca)
        startActivity(intent)
    }

}