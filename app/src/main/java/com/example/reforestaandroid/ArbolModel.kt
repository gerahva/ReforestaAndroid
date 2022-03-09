package com.example.reforestaandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArbolModel : ViewModel() {

    private val retrofit= RetrofitAyuda.obtenerRetrofit()
    private val arboles:MutableLiveData<List<Arbol>> by lazy{

MutableLiveData<List<Arbol>>().also {

    cargarArboles()
}
    }

    fun obtenerArboles(): LiveData<List<Arbol>>{
return arboles
    }
    private fun cargarArboles(){
        //Aqui se hacen las operaciones asincronicaspor ejemplo con corutinas

GlobalScope.launch(Dispatchers.IO){
var x= retrofit.create(ServicioArbol::class.java)
    .obtenerTodos().execute().body()
    launch(Dispatchers.Main){
arboles.value=x

    }
}
    }
}