package com.example.b2023gr2sw

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.b2023gr2sw.ui.theme.B2023gr2swTheme
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result->
            if(result.resultCode == Activity.RESULT_OK){
                if (result.data != null){
                    val data = result.data
                    "${data?.getStringExtra("nombreModificado")}"
                }
            }
        }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.id_layout_main), texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    val callbackIntentPickUri =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result->
            if (result.resultCode == RESULT_OK){
                if(result.data != null){
                    if (result.data!!.data != null){
                        val uri: Uri = result.data!!.data!!
                        val cursor = contentResolver.query(
                            uri, null, null, null, null, null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        mostrarSnackbar("Telefono ${telefono}")
                    }
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)
        }


        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener{
                irActividad(BListView::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                callbackIntentPickUri.launch(intentConRespuesta)
            }

        val botonIntentExplicito = findViewById<Button>(
            R.id.btn_ir_intent_explicito
        )
        botonIntentExplicito
            .setOnClickListener {
                abrirActividadConParametros(
                    CintentExplicitoParametros::class.java
                )
            }
        val botonSqlite = findViewById<Button>(
            R.id.btn_sqlite
        )
        botonSqlite
            .setOnClickListener {
                irActividad(ECrudEntrendor::class.java)
            }
        val botonRView = findViewById<Button>(R.id.btn_recycle_view)
        botonRView.setOnClickListener {
            irActividad(FRecycleView::class.java)
        }
        val botonFirebaseUI = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFirebaseUI
            .setOnClickListener {
                irActividad(HFirebaseUIAuth::class.java)
            }


    }

    private fun abrirActividadConParametros(clase:Class<*>) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombre", "Daniel")
        intentExplicito.putExtra("apellido", "Vargas")
        intentExplicito.putExtra("edad", 22)
        callbackContenidoIntentExplicito.launch(intentExplicito)

    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }

}

