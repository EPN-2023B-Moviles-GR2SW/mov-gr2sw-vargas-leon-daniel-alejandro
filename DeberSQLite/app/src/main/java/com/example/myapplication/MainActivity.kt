package com.example.myapplication

import Biblioteca
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

class MainActivity : AppCompatActivity() {
    var arreglo = BDBiblioteca.arregloBibliotecas
    var posicionItemSeleccionado = 0
    var adaptador: ArrayAdapter<Biblioteca>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Se obtiene las bibliotecas por intent
        if (intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>? != null)
            arreglo = ArrayList(intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>?)

        val botonListView = findViewById<Button>(R.id.btn_new_biblio)
        botonListView
            .setOnClickListener{
                irActividad(BibliotecaManager::class.java, "create")
            }

        //List View
        val listView = findViewById<ListView>(R.id.lv_bibliotecas)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()

        registerForContextMenu(listView)


    }
    //Ir actividad simple
    fun irActividad(
        clase:Class<*>, actividad:String
    ){
        val intent = Intent(this,clase)
        intent.putExtra("listaDeObjetos", ArrayList(arreglo))
        intent.putExtra("actividad", actividad)
        startActivity(intent)
    }
    //Ir actividad con posicion
    fun irActividad(
        clase:Class<*>, actividad:String,posicion:Int
    ){
        val intent = Intent(this,clase)
        intent.putExtra("listaDeObjetos", ArrayList(arreglo))
        intent.putExtra("actividad", actividad)
        intent.putExtra("posicion", posicion)
        startActivity(intent)
    }


    //Context Menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val infalter = menuInflater
        infalter.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                // Se nos redirigira a una ventana donde actualizaremos el item seeleccionaado
                irActividad(BibliotecaManager::class.java,"update",posicionItemSeleccionado)
                return true
            }
            R.id.mi_eliminar->{
                //Eliminamos el item seleccionaado y actualizamos la vista
                arreglo.removeAt((posicionItemSeleccionado))
                adaptador!!.notifyDataSetChanged()
                return true
            }
            R.id.mi_ver_libros->{
                irActividad(LibroView::class.java, "verLibros",posicionItemSeleccionado)
                return true
            }
            else ->super.onContextItemSelected(item)
        }


    }




}