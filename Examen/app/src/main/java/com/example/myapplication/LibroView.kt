package com.example.myapplication

import Biblioteca
import Libro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar

class LibroView : AppCompatActivity() {
    var arreglo = arrayListOf<Biblioteca>()
    var posicionBiblioteca= 0
    var posicionLibro= 0
    var adaptador: ArrayAdapter<Libro>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_mnager)
        if (intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>? != null)
            arreglo =
                ArrayList(intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>?)
        posicionBiblioteca = intent.getIntExtra("posicion",0)
        val botonListView = findViewById<Button>(R.id.btn_agregarLibros)
        botonListView
            .setOnClickListener {
                irActividadLibro(LibroCrud::class.java, "create")
            }
        val botonRegresar = findViewById<Button>(R.id.btn_regresar)
        botonRegresar
            .setOnClickListener {
                irActividadBiblioteca(MainActivity::class.java,arreglo)
            }

        //List View
        val listView = findViewById<ListView>(R.id.lv_libros)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo[posicionBiblioteca].libros
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()

        registerForContextMenu(listView)


    }

    //Ir actividad simple
    fun irActividadLibro(
        clase: Class<*>, actividad: String
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("listaDeObjetos", ArrayList(arreglo))
        intent.putExtra("actividad", actividad)
        intent.putExtra("posicion", posicionBiblioteca)
        startActivity(intent)
    }
    fun irActividadBiblioteca(
        clase: Class<*>, arreglo: ArrayList<Biblioteca>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("listaDeObjetos", ArrayList(arreglo))
        startActivity(intent)
    }


    //Ir actividad de update
    fun irActividad(
        clase: Class<*>, actividad: String, posicion: Int, posicionLibro:Int
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("listaDeObjetos", ArrayList(arreglo))
        intent.putExtra("actividad", actividad)
        intent.putExtra("posicion", posicion)
        intent.putExtra("posicionLibro", posicionLibro)
        startActivity(intent)

    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val infalter = menuInflater
        infalter.inflate(R.menu.menulibro, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionLibro = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editarLibro->{
                // Se nos redirigira a una ventana donde actualizaremos el item seeleccionaado
                irActividad(LibroCrud::class.java,"actualizar",posicionBiblioteca, posicionLibro)
                return true
            }
            R.id.mi_eliminarLibro->{
                //Eliminamos el item seleccionaado y actualizamos la vista
                arreglo[posicionBiblioteca].libros.removeAt((posicionLibro))
                adaptador!!.notifyDataSetChanged()
                return true
            }
            else ->super.onContextItemSelected(item)
        }


    }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lv_libros), texto, Snackbar.LENGTH_LONG)
        snack.show()
    }


}
