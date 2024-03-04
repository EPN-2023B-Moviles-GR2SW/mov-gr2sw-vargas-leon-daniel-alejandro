package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class BibliotecaManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_biblioteca)
        //intent que permite obtener la lista para agregar elementoa a esta.

        val botonCrear = findViewById<Button>(R.id.btn_crear_biblioteca)
        if (intent.getStringExtra("actividad")!="update")
            crearBiblioteca(botonCrear)
        else{
            actualizarBiblioteca( botonCrear, intent.getSerializableExtra("item") as Biblioteca)
        }
    }
    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }
    //CRUD para biblioteca
    private fun crearBiblioteca( botonCrear: Button){
        botonCrear
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.in_Nombre_Biblioteca)
                val callePrincipal = findViewById<EditText>(R.id.in_callaPrincipal)
                val calleSecundaria = findViewById<EditText>(R.id.in_calleSecundaria)
                val estado = findViewById<CheckBox>(R.id.cb_disponible)
                val nuevaBiblioteca = Biblioteca(ArrayList(), nombre.text.toString(), callePrincipal.text.toString(), calleSecundaria.text.toString(), estado.isChecked,"")
                //Guardado con FireStore
                    DBbiblioteca().crearBibliotecaDB(nuevaBiblioteca)
                //bibliotecas?.add(nuevaBiblioteca)
                irActividad(MainActivity::class.java)
            }
    }
    private fun actualizarBiblioteca(botonCrear: Button?, biblioteca: Biblioteca) {
        if (botonCrear != null) {
            botonCrear.text = "Actualizar"
        }
        val text = findViewById<TextView>(R.id.tex_titulo)
        text.text = "Actualizar Biblioteca"
        //Mostraremos l informacion actual
        //NOMBRE DE LA LIBRERIA
        val nombre = findViewById<EditText>(R.id.in_Nombre_Biblioteca)
        val callePrincipal = findViewById<EditText>(R.id.in_callaPrincipal)
        val calleSecundaria = findViewById<EditText>(R.id.in_calleSecundaria)
        val estado = findViewById<CheckBox>(R.id.cb_disponible)

        nombre.setText(biblioteca.nombre)
        callePrincipal.setText(biblioteca.callePrincipal)
        calleSecundaria.setText(biblioteca.calleSecundaria)
        biblioteca.abierto?.let { estado.isChecked = it }

        //reemplazamos la informacion
        if (botonCrear != null) {
            botonCrear
                .setOnClickListener {
                    val nombre = findViewById<EditText>(R.id.in_Nombre_Biblioteca)
                    val callePrincipal = findViewById<EditText>(R.id.in_callaPrincipal)
                    val calleSecundaria = findViewById<EditText>(R.id.in_calleSecundaria)
                    val estado = findViewById<CheckBox>(R.id.cb_disponible)
                    val nuevaBiblioteca = Biblioteca(biblioteca.libros, nombre.text.toString(), callePrincipal.text.toString(), calleSecundaria.text.toString(), estado.isChecked,biblioteca.id)
                    DBbiblioteca().crearBibliotecaDB(nuevaBiblioteca)
                    irActividad(MainActivity::class.java)
                }

                }
        }





}