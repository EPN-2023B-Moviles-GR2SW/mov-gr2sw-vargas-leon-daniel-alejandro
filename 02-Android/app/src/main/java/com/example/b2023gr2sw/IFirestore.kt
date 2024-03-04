package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

import android.widget.ArrayAdapter

import android.widget.Button

import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.Query

import com.google.firebase.firestore.QueryDocumentSnapshot

import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase
import java.util.Date

import kotlin.collections.ArrayList

class IFirestore : AppCompatActivity() {
    var query: Query? = null
    val arreglo: ArrayList<ICities> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirestore)

        val listView = findViewById<ListView>(R.id.lv_firestore)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonDAtosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDAtosPrueba.setOnClickListener {
            crear()
        }
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener { consultarConOrden(adaptador) }
        val botonConsultarDocumento = findViewById<Button>(R.id.btn_fs_odoc)

        val botonCrearEjemplo = findViewById<Button>(R.id.btn_fs_crear)
        botonCrearEjemplo
            .setOnClickListener {
                crearEjemplo();
            }
        val botonFirebaseEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonFirebaseEliminar.setOnClickListener {
            eliminarRegistro()
        }
        val botonFirebaseEmpezarPaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonFirebaseEmpezarPaginar.setOnClickListener {
            eliminarRegistro()
        }
    }

    private fun crear() {
        val db = FirebaseFirestore.getInstance()
        db.collection("user").add(
            hashMapOf(
                "nombre" to "Daniel"
            )
        )
    }

    private fun eliminarRegistro() {
        TODO("Not yet implemented")
    }

    private fun crearEjemplo() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")

        val datosEstudiante = hashMapOf(
            "nombre" to "Daniel",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "La magdalena",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )
        referenciaEjemploEstudiante
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener {
            }
            .addOnFailureListener {  }

        val identificador = Date().time
        referenciaEjemploEstudiante
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }

        referenciaEjemploEstudiante
            .add(datosEstudiante)
            .addOnCompleteListener {  }
            .addOnFailureListener {  }
    }

    private fun consultarConOrden(adaptador: ArrayAdapter<ICities>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("citiies")
        limpiarAreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            .orderBy("population",Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    ciudad.id
                    anadirAArreglo(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{

            }
    }

    private fun anadirAArreglo(ciudad: QueryDocumentSnapshot?) {
        val nuevaciudad = ICities(
            ciudad?.data?.get("name") as String?,
            ciudad?.data?.get("state") as String?,
            ciudad?.data?.get("country") as String?,
            ciudad?.data?.get("capital") as String?,
            ciudad?.data?.get("population") as String?,
            ciudad?.data?.get("regions") as ArrayList<String>?
        )
    }

    private fun limpiarAreglo() {
        arreglo.clear()
    }

    private fun crearDatosPrueba() {
        val db = Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal"),
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal"),
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast"),
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu"),
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei"),
        )
        cities.document("BJ").set(data5)
    }
}