package com.example.gestortiempo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var campoActividad: EditText
    lateinit var botonAgregar: Button
    lateinit var botonBorrar: Button
    lateinit var listaTareas: ListView
    lateinit var contadorTareas: TextView

    var tareas = ArrayList<String>()
    lateinit var adaptador: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campoActividad = findViewById(R.id.campoActividad)
        botonAgregar = findViewById(R.id.botonAgregar)
        listaTareas = findViewById(R.id.listaTareas)
        contadorTareas = findViewById(R.id.contadorTareas)
        botonBorrar = findViewById(R.id.botonBorrar)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tareas
        )

        listaTareas.adapter = adaptador

        botonAgregar.setOnClickListener {
            agregarTarea()
        }

        listaTareas.setOnItemClickListener { parent, view, position, id ->
            tareas.removeAt(position)
            adaptador.notifyDataSetChanged()
            actualizarContador()
        }

        botonBorrar.setOnClickListener {
            tareas.clear()
            adaptador.notifyDataSetChanged()
            actualizarContador() }
    }

    fun agregarTarea(){
        val tarea = campoActividad.text.toString()
        if (tarea != ""){
            if (!tareas.contains(tarea)){
                tareas.add(tarea)
                adaptador.notifyDataSetChanged()
                campoActividad.setText("")
                actualizarContador()
                Toast.makeText(this, "Tarea añadida", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "La tarea ya existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun actualizarContador(){
        contadorTareas.text = "Tareas: ${tareas.size}"
    }
}