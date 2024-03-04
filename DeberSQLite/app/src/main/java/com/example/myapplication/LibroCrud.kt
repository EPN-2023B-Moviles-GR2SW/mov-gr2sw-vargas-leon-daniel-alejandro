package com.example.myapplication

import Biblioteca
import Libro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class LibroCrud : AppCompatActivity() {
    var posicionBiblioteca = 0
    var posicionLibro = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_crud)
        var libros = intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>?
        val botonCrear = findViewById<Button>(R.id.btn_input)
        posicionBiblioteca = intent.getIntExtra("posicion",0)
        posicionLibro = intent.getIntExtra("posicionLibro", 0)
        if (intent.getStringExtra("actividad")=="actualizar")
            actualizarLibro(ArrayList(libros), botonCrear, intent.getIntExtra("posicion",0),intent.getIntExtra("posicionLibro",0))
        else
            crearLibro(ArrayList(libros),botonCrear)

    }

    fun irActividad(
        clase:Class<*>, arreglo:ArrayList<Biblioteca>
    ){
        val intent = Intent(this,clase)
        intent.putExtra("listaDeObjetos", (arreglo))
        intent.putExtra("posicion", posicionBiblioteca)
        startActivity(intent)
    }
    //CRUD Libros
    fun actualizarLibro(arrayList: java.util.ArrayList<Biblioteca>, botonCrear: Button, posicion: Int, posicionLibro:Int) {

        botonCrear.text = "Actualizar"

        val text = findViewById<TextView>(R.id.text_tituloLibro)
        text.text = "Actualizar Libro"
        //Mostraremos la informacion actual del libro
        val autor = findViewById<EditText>(R.id.in_Autor)
        val titulo = findViewById<EditText>(R.id.in_titulo)
        val edicion = findViewById<EditText>(R.id.in_edicion)
        val precio = findViewById<EditText>(R.id.in_precio)
        val disponibilidad = findViewById<CheckBox>(R.id.cb_dispobibleLibro)

        autor.setText(arrayList[posicion].libros[posicionLibro].getAutor())
        titulo.setText(arrayList[posicion].libros[posicionLibro].getTitulo())
        edicion.setText(arrayList[posicion].libros[posicionLibro].getEdicion().toString())
        precio.setText(arrayList[posicion].libros[posicionLibro].getPrecio().toString())
        disponibilidad.setChecked(arrayList[posicion].libros[posicionLibro].getDisponibilidad())

        //Reemplazo de la informacion
        if(botonCrear != null){
            botonCrear
                .setOnClickListener {
                    val autor = findViewById<EditText>(R.id.in_Autor)
                    val titulo = findViewById<EditText>(R.id.in_titulo)
                    val edicion = findViewById<EditText>(R.id.in_edicion)
                    val precio = findViewById<EditText>(R.id.in_precio)
                    val disponibilidad = findViewById<CheckBox>(R.id.cb_dispobibleLibro)
                    val nuevoLibro = Libro(autor.text.toString(), titulo.text.toString(), disponibilidad.isChecked, edicion.text.toString().toInt(), precio.text.toString().toDouble() )
                    arrayList[posicion].libros[posicionLibro] = nuevoLibro
                    irActividad(LibroView::class.java, arrayList)
                }
        }
    }

    private fun crearLibro(arrayList: java.util.ArrayList<Biblioteca>, botonCrear: Button?) {
        botonCrear
            ?.setOnClickListener {
                val autor = findViewById<EditText>(R.id.in_Autor)
                val titulo = findViewById<EditText>(R.id.in_titulo)
                val edicion = findViewById<EditText>(R.id.in_edicion)
                val precio = findViewById<EditText>(R.id.in_precio)
                val disponibilidad = findViewById<CheckBox>(R.id.cb_dispobibleLibro)
                val nuevoLibro = Libro(autor.text.toString(), titulo.text.toString(), disponibilidad.isChecked, edicion.text.toString().toInt(), precio.text.toString().toDouble() )
                arrayList[posicionBiblioteca]?.libros?.add(nuevoLibro)
                irActividad(LibroView::class.java, arrayList)
            }
    }
}