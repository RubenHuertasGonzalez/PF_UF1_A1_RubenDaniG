package com.ruben.pf_uf1_a1_rubendanig

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.socket.client.Socket
import io.socket.emitter.Emitter

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCodi: EditText
    private lateinit var enviarButton: Button
    private lateinit var mSocket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCodi = findViewById(R.id.editTextTextCodi)
        enviarButton = findViewById(R.id.buttonEnviarDades)

        SocketHandler.setSocket()
        mSocket = SocketHandler.getSocket()

        mSocket.connect()

        enviarButton.setOnClickListener {
            try {
                val codigo = editTextCodi.text.toString()
                val mensaje = "Código $codigo enviado"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

                mSocket.emit("enviarPIN", codigo)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al enviar el código", Toast.LENGTH_SHORT).show()
            }
        }

        mSocket.on("sendPIN", onCounter)
    }

    private val onCounter = Emitter.Listener { args ->
        runOnUiThread {
            if (args.isNotEmpty() && args[0] is Int) {
                val counter = args[0] as Int
                // Aquí puedes realizar la lógica para actualizar la UI con el contador
                // Por ejemplo, puedes mostrar el contador en un TextView
                // textViewCounter.text = counter.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Desconectar el socket cuando la actividad se destruye
        mSocket.disconnect()
    }
}