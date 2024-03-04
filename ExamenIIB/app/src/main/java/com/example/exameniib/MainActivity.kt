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
import com.google.android.gms.tasks.Task
import com.google.common.reflect.TypeToken
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson

class MainActivity() : AppCompatActivity() {
    var adaptador: ArrayAdapter<Biblioteca>? = null
    var posicionItemSeleccionado:Int = 0
    //* BASE DE DATO
    val db = FirebaseFirestore.getInstance()
    val arreglo = ArrayList<Biblioteca>()
    var query: Query? = null

    fun consultarBiblioteca(
        adaptador: ArrayAdapter<Biblioteca>
    ): ArrayAdapter<Biblioteca> {
        val bibliotecaRef = db.collection("libreria")
        var tarea: Task<QuerySnapshot>? = null
        if (query == null) {
            tarea = bibliotecaRef.get() // 1era vez
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
        } else {
            // consulta de la consulta anterior empezando en el nuevo documento
            tarea = query!!.get()
        }
        if (tarea != null) {
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, bibliotecaRef)
                    for (biblioteca in documentSnapshots) {
                        anadirAArregloBiblioteca(biblioteca)
                    }
                    adaptador.notifyDataSetChanged()
                }
                .addOnFailureListener {
                    // si hay fallos
                }
        }

        return adaptador
    }
    fun limpiarArreglo() {
        arreglo.clear()
    }
    fun guardarQuery(
        documentSnapshots: QuerySnapshot,
        refCities: Query
    ){
        if (documentSnapshots.size() > 0) {
            val ultimoDocumento = documentSnapshots
                .documents[documentSnapshots.size() - 1]
            query = refCities
                // Start After nos ayuda a paginar
                .startAfter(ultimoDocumento)
        }
    }

    //Aqui creamos los objetos y los guardamos en nuestro arreglo
    fun anadirAArregloBiblioteca(
        ciudad: QueryDocumentSnapshot
    ){
        val nuevaBiblio = Biblioteca(
            ciudad.data.get("libros") as ArrayList<Libro>,
            ciudad.data.get("nombre") as String?,
            ciudad.data.get("calle_principal") as String?,
            ciudad.data.get("calle_secundaria") as String?,
            ciudad.data.get("estado") as Boolean?,
            ciudad.id
        )

        this.arreglo.add(nuevaBiblio)
    }


    // Fin de Base de Datos


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Se obtiene las bibliotecas por intent
       // if (intent.getSerializableExtra("listaDeObjetos") as ArrayList<com.example.exameniib.Biblioteca>? != null)
         //   arreglo = ArrayList(intent.getSerializableExtra("listaDeObjetos") as ArrayList<com.example.exameniib.Biblioteca>?)
        val listView = findViewById<ListView>(R.id.lv_bibliotecas)
        val botonListView = findViewById<Button>(R.id.btn_new_biblio)
        botonListView
            .setOnClickListener{
                irActividad(BibliotecaManager::class.java, "create")
            }
        //List View
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        consultarBiblioteca(adaptador!!)
        listView.adapter  = adaptador
        adaptador!!.notifyDataSetChanged()
        registerForContextMenu(listView)


    }
    //Ir actividad simple
    fun irActividad(
        clase:Class<*>, actividad:String
    ){
        val intent = Intent(this,clase)
        intent.putExtra("actividad", actividad)
        startActivity(intent)
    }
    fun irActividad(
        clase:Class<*>, actividad:String, biblioteca_seleccionada: Biblioteca
    ){
        val intent = Intent(this,clase)
        intent.putExtra("actividad", actividad)
        intent.putExtra("item", biblioteca_seleccionada)
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
                irActividad(BibliotecaManager::class.java,"update",arreglo.get(posicionItemSeleccionado))
                return true
            }
            R.id.mi_eliminar->{
                //Eliminamos el item seleccionaado y actualizamos la vista
                DBbiblioteca().eliminaar(arreglo.get(posicionItemSeleccionado).id.toString())
                arreglo.removeAt(posicionItemSeleccionado)//Para refrescar la lista sin volver a hacer consulta
                adaptador!!.notifyDataSetChanged()


                return true
            }
            R.id.mi_ver_libros->{
                irActividad(LibroView::class.java,"verLibros", arreglo.get(posicionItemSeleccionado))
                return true
            }
            else ->super.onContextItemSelected(item)
        }


    }
    //Fn de context menu





}