package com.example.lista

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etnovastarefa = findViewById<EditText>(R.id.etnovatarefa)
        val btadd = findViewById<Button>(R.id.btadd)
        val tvtitulo = findViewById<TextView>(R.id.tvtitulo)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            tvtitulo.isVisible = false
            etnovastarefa.isVisible = true
            etnovastarefa.isEnabled = true
            btadd.isVisible = true
        }

        val lvtarefas = findViewById<ListView>(R.id.lvtarefas)
        val ListaTarefas: ArrayList<String> = ArrayList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ListaTarefas)
        lvtarefas.adapter = adapter

        btadd.setOnClickListener {
            if (etnovastarefa.text.isNullOrEmpty()) {
                Toast.makeText(this, "Digite uma tarefa...", Toast.LENGTH_SHORT).show()

            } else {
                ListaTarefas.add(etnovastarefa.text.toString())
                adapter.notifyDataSetChanged()
                etnovastarefa.setText("")
                etnovastarefa.isVisible = false
                etnovastarefa.isEnabled = false
                btadd.isVisible = false
                tvtitulo.isVisible = true
            }
        }
        lvtarefas.setOnItemClickListener{ _, _, position, _ ->
            val alerta = AlertDialog.Builder( this)
            alerta.setTitle("Atenção")
            alerta.setMessage("Quer mesmo excluir esse item?")
            alerta.setPositiveButton("Confirmar"){ dialog, _ ->
                ListaTarefas.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()}
            alerta.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()}
            alerta.create().show()
            true
        }

    }
}
