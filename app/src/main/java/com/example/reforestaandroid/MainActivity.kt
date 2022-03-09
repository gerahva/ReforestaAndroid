package com.example.reforestaandroid

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        var miubicselecc=String()
       val ubicaciones=ArrayList<String>()

        ubicaciones.add("America del Norte")
        ubicaciones.add("México")
        ubicaciones.add("América Central")
        ubicaciones.add("Europa")
        ubicaciones.add("Asia Central")
        ubicaciones.add("Asia Septentrional")
        ubicaciones.add("Europa del Norte")
        ubicaciones.add("Africa")
        ubicaciones.add("Australia")

var spinner=spinnera
var adapter=
    ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item,ubicaciones)

spinner.adapter=adapter

        //DEclaramos el item aqui para que pueda ser vistos por todos
        var item:String?=null

        //Ïnicia spinner
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 item=        parent?.getItemAtPosition(position).toString()


                (parent?.getChildAt(0) as TextView).setTextColor(Color.WHITE)



                miubicselecc=item.toString()
                 Toast.makeText(applicationContext,"Seleccionaste la región "+item,Toast.LENGTH_SHORT).show()



            }

        }





        //Sin que oprimas ningun boton invocamos el servicio REST
        //Objeto global deretrofit
        //Creamos el objeto retrofit
        var retrofit = Retrofit.Builder()
            .baseUrl("https://reforesta.herokuapp.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build()


        //Encerramos en una corutina

        GlobalScope.launch(Dispatchers.IO) {

            //Con elñ objeto retrofit invocamos el servicio
            var servicio = retrofit.create(ServicioArbol::class.java)

            //Apoyandonos de este servicio invocamos ya uno concreto
            var arboles = servicio.obtenerTodos()
                .execute()
                .body()
              //Norlamnet se encierra en el bloque main de la corutina donde se muestra informacion garfica
            launch(Dispatchers.Main){
                Log.i("XX", "" + arboles?.size!!)
              //  Toast.makeText(applicationContext, "El cuarto es  ${arboles.get(4).descripcion}", Toast.LENGTH_LONG).show()
            }


        }//Aqui termina la corutina global


//Guardar al oprimir el boton
        guardar.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                //Creamos el objeto retrofit

                //Con elñ objeto retrofit invocamos el servicio
                var servicio = retrofit.create(ServicioArbol::class.java)

                //Creamos un arbol fake
              //  var arbol = Arbol()
              //  arbol.nombre = "Palo dulce";
              //  arbol.descripcion = "Es un arbol nativo de la sierra";
               // var estatus = servicio.guardarArbol(arbol)
               //     .execute()
                 //   .body()
                var arbol=Arbol()
                arbol.nombre=campoNombre.text.toString()
                arbol.descripcion=campoDescripcion.text.toString()
               arbol.ubicacion=item
                var estatus=servicio.guardarArbol(arbol)
                   .execute()
                   .body()

                //Norlamnet se encierra en el bloque main de la corutina donde se muestra informacion garfica
                launch(Dispatchers.Main) {
                    Log.i("XX", "" + estatus?.mensaje!!)
                    Toast.makeText(
                        applicationContext,
                        "Mensaje del servidor  ${estatus.mensaje}}",
                        Toast.LENGTH_LONG
                    ).show()

                    //Limpiamos los campos
                    campoNombre.text=null
                    campoDescripcion.text=null

                }//Termina Main de la UI

            }
        }



    }//Termina onCreate()


}