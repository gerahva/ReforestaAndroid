package com.example.reforestaandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ListadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        //Empezamos con retrofit



        val modelo:ArbolModel by viewModels()

        modelo.obtenerArboles().observe(this, Observer<List<Arbol>>{ arboles->

            val arbolAdapter=ArbolAdapter{arbol ->adapterOnClick(arbol)  }

            //INvocamos el recuclerView
            val recyclerView: RecyclerView =findViewById(R.id.recycler_view)
            //Inyectamos el recycler
            recyclerView.adapter=arbolAdapter

            arbolAdapter.submitList(arboles)

        })












    }
    //Finalmente agregamos el metodo adapterOnClick, el cual aciva el onClick de cada
    fun adapterOnClick(arbol:Arbol) {
        //Aqui tendriamos que inyectarle ora vista para que nos lleve a esa vista
        var intent= Intent(this, DetalleActivity::class.java)
        intent.putExtra("arbol",arbol)
        startActivity(intent)
        Toast.makeText(this, arbol.nombre, Toast.LENGTH_LONG).show()
    }
    }
