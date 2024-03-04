package com.example.exameniib

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
import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class LibroView : AppCompatActivity() {


    var adaptador: ArrayAdapter<Libro>? = null
    var posicionLibro = 0
    var arreglo = ArrayList<Libro>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_mnager)
       // if (intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>? != null)
          //  arreglo =
             //   ArrayList(intent.getSerializableExtra("listaDeObjetos") as ArrayList<Biblioteca>?)
        arreglo = (intent.getSerializableExtra("item") as Biblioteca).libros
        //Botones
        val botonRegresar = findViewById<Button>(R.id.btn_regresar)

        val botonAgregarLibro = findViewById<Button>(R.id.btn_agregarLibros)
        //Configuraion de botones
        botonRegresar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        botonAgregarLibro.setOnClickListener {
            irActividad(LibroCrud::class.java,"crear",intent.getSerializableExtra("item") as Biblioteca, posicionLibro)
        }
        //List View
        val listView = findViewById<ListView>(R.id.lv_libros)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo

        )
        listView.adapter = adaptador

        adaptador!!.notifyDataSetChanged()
        registerForContextMenu(listView)


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
                irActividad(LibroCrud::class.java,"actualizar",intent.getSerializableExtra("item") as Biblioteca, posicionLibro)
                return true
            }
            R.id.mi_eliminarLibro->{
                //Eliminamos el item seleccionaado y actualizamos la vista
                val eliminado = (intent.getSerializableExtra("item") as Biblioteca)
                    eliminado.libros.removeAt((posicionLibro))
                DBbiblioteca().crearBibliotecaDB(eliminado)

                adaptador!!.notifyDataSetChanged()
                return true
            }
            else ->super.onContextItemSelected(item)
        }


    }




    //Ir actividad simple

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }
    fun irActividad(
        clase:Class<*>, actividad:String, biblioteca_seleccionada: Biblioteca,int:Int
    ){
        val intent = Intent(this,clase)
        intent.putExtra("actividad", actividad)
        intent.putExtra("item", biblioteca_seleccionada)
        intent.putExtra("posicion", int)
        startActivity(intent)
    }




    }





