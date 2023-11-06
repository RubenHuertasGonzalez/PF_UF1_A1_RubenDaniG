package com.ruben.pf_uf1_a1_rubendanig

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextCodi = findViewById<EditText>(R.id.editTextTextCodi)

        val enviarButton = findViewById<Button>(R.id.buttonEnviarDades)
        enviarButton.setOnClickListener {
            val codigo = editTextCodi.text.toString()
            val mensaje = "Codigo $codigo enviado"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}


