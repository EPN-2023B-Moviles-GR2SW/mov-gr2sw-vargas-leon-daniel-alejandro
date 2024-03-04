package com.example.exameniib

import android.widget.ArrayAdapter
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class DBbiblioteca() {
    val db = FirebaseFirestore.getInstance()
    fun     crearBibliotecaDB(nuevaBiblioteca:Biblioteca){
        if (nuevaBiblioteca.id == ""){

            db.collection("libreria").add(
                hashMapOf(
                    "nombre" to nuevaBiblioteca.nombre,
                    "calle_principal" to nuevaBiblioteca.callePrincipal,
                    "calle_secundaria" to nuevaBiblioteca.calleSecundaria,
                    "estado" to nuevaBiblioteca.abierto,
                    "libros" to nuevaBiblioteca.libros
                )
            )

        }else{
            db.collection("libreria").document(nuevaBiblioteca.id.toString()).set(
                hashMapOf(
                    "nombre" to nuevaBiblioteca.nombre,
                    "calle_principal" to nuevaBiblioteca.callePrincipal,
                    "calle_secundaria" to nuevaBiblioteca.calleSecundaria,
                    "estado" to nuevaBiblioteca.abierto,
                    "libros" to nuevaBiblioteca.libros
                )
            )
        }

    }
    fun eliminaar(id:String){
        db.collection("libreria").document(id).delete()
    }
}