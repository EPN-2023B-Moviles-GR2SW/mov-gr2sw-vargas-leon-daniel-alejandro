package com.example.myapplication

import Biblioteca
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class BibliotecaManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_biblioteca)
        //intent que permite obtener la lista para agregar elementoa a esta.
        var bibliotecas = intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>?
        val botonCrear = findViewById<Button>(R.id.btn_crear_biblioteca)
        if (intent.getStringExtra("actividad")!="update")
            crearBiblioteca(ArrayList(bibliotecas),botonCrear)
        else
            actualizarBiblioteca(ArrayList(bibliotecas), botonCrear, intent.getIntExtra("posicion",0))

    }



    fun irActividad(
        clase:Class<*>, arreglo:ArrayList<Biblioteca>
    ){
        val intent = Intent(this,clase)
        intent.putExtra("listaDeObjetos", (arreglo))
        startActivity(intent)
    }
    fun mostrarSnackbar(texto:String){
        Snackbar
            .make(
                findViewById(R.id.constrain_crear),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }
    //CRUD para biblioteca
    private fun crearBiblioteca(bibliotecas: ArrayList<Biblioteca>, botonCrear: Button){
        botonCrear
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.in_Nombre_Biblioteca)
                val callePrincipal = findViewById<EditText>(R.id.in_callaPrincipal)
                val calleSecundaria = findViewById<EditText>(R.id.in_calleSecundaria)
                val estado = findViewById<CheckBox>(R.id.cb_disponible)
                val nuevaBiblioteca = Biblioteca(ArrayList(), nombre.text.toString(), callePrincipal.text.toString(), calleSecundaria.text.toString(), estado.isChecked)
                bibliotecas?.add(nuevaBiblioteca)
                irActividad(MainActivity::class.java, ArrayList(bibliotecas))
            }
    }
    private fun actualizarBiblioteca(arrayList: ArrayList<Biblioteca>, botonCrear: Button?, posicion:Int) {
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

        nombre.setText(arrayList[posicion].nombre)
        callePrincipal.setText(arrayList[posicion].callePrincipal)
        calleSecundaria.setText(arrayList[posicion].calleSecundaria)
        estado.setChecked(arrayList[posicion].abierto)

        //reemplazamos la informacion
        if (botonCrear != null) {
            botonCrear
                .setOnClickListener {
                    val nombre = findViewById<EditText>(R.id.in_Nombre_Biblioteca)
                    val callePrincipal = findViewById<EditText>(R.id.in_callaPrincipal)
                    val calleSecundaria = findViewById<EditText>(R.id.in_calleSecundaria)
                    val estado = findViewById<CheckBox>(R.id.cb_disponible)
                    val nuevaBiblioteca = Biblioteca(ArrayList(), nombre.text.toString(), callePrincipal.text.toString(), calleSecundaria.text.toString(), estado.isChecked)
                    arrayList[posicion] = nuevaBiblioteca
                    irActividad(MainActivity::class.java, ArrayList(arrayList))
                }
        }


    }

}