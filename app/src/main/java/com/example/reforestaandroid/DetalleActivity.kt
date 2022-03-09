package com.example.reforestaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        var arbol=    intent.extras?.getSerializable("arbol") as Arbol

        //en ese id actualizamos el valor del objeto que se serializo con el extras
        detalleNombre.text=arbol.nombre
        //SE puede hacer lo mismo con el resto de campos
        detalleDescripcion.text=arbol.descripcion
        detalleUbicacion.text=arbol.ubicacion


    }
}