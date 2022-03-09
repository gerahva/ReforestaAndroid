package com.example.reforestaandroid



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ArbolAdapter(private val onClick:(Arbol)->Unit)
    :ListAdapter<Arbol,ArbolAdapter.ArbolViewHolder>(ArbolCallback) {
    /*
     La siguiente es la clas UsuarioViewHolder  en ingles holder es contenedor, y debe su nombre
     a que va a contener la ADAPTACION DEL modelo Usuario y su layout usuario_item
     */
    class ArbolViewHolder(itemView: View, val onClick: (Arbol) -> Unit ):
        RecyclerView.ViewHolder(itemView) {
        //Aqui siguen lo atributos que SIEMPRE deben ser los mismos tipos que de layout usuario_item
        private val arbolNombre: TextView=itemView.findViewById(R.id.textoNombre)
        private  val arbolDescripcion:TextView=itemView.findViewById(R.id.textoDescripcion)
        private  val arbolUbicacion :TextView =itemView.findViewById(R.id.textoUbicacion)

        //declaramos el modelo donde se van a ADAPTAR esos items
        private  var arbolActual:Arbol?=null
        //A continucion vamos a inicializar el segundo argumento del constructor (el OnClick)
        //usndo un bloque inicialziado rpoque se trata de un evento
        init {
            itemView.setOnClickListener {
                arbolActual?.let{
                    onClick(it)
                }
            }
        }//Llave de cierre del bloque inicializador
        //Aqui enlazamos a el usaurio actual
        fun  enlazar(arbol: Arbol){
            arbolActual=arbol
            //Deespues iniciamos los valores
            arbolNombre.text=arbol.nombre
            arbolDescripcion.text=arbol.descripcion
            arbolUbicacion.text=arbol.ubicacion

        }
    }//Llave de cierre de la clase internal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArbolViewHolder {
        //Borre el codiguito de este metodo o funcion
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.arbol_item, parent, false)
        return ArbolViewHolder(view, onClick)

    }

    override fun onBindViewHolder(holder: ArbolViewHolder, position: Int) {
        val arbol=getItem(position)
        holder.enlazar(arbol)
    }


}//Llave de la clase externa

//Implementamos la classe UsuarioCallback
object  ArbolCallback:DiffUtil.ItemCallback<Arbol>(){
    override fun areItemsTheSame(oldItem: Arbol, newItem: Arbol): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Arbol, newItem: Arbol): Boolean {
        return oldItem.nombre==newItem.nombre
    }

}
