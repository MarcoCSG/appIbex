package com.example.ibex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ibex.network.ApiService
import com.example.ibex.network.response.LoginResponse
import com.example.ibex.util.PreferenceHelper
import com.example.ibex.util.PreferenceHelper.get
import com.example.ibex.util.PreferenceHelper.set
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy { //accede al archivo ApiService
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["data", ""].contains("")) { //sirve para guardar la sesion
            periodo()
        }

        val btnSelecPeriodo = findViewById<Button>(R.id.btnIngresar)
        btnSelecPeriodo.setOnClickListener {
            performLogin() //accede a todo el codigo private performLogin
        }

    }

    private fun periodo() {
        val i = Intent(this, SelecEmpresa:: class.java)//metodo intent para entrar a la siguiente vista
        startActivity(i)
        finish()
    }

    private fun createSessionPreference(data: String) {
        val preferences = PreferenceHelper.defaultPrefs(this) //sirve para guardar la sesion
        preferences["data"] = data
    }


    private fun performLogin() { //inicia sesion con la api
        val etEmail = findViewById<EditText>(R.id.edtCorreo).text.toString()  //recupera correo
        val etPasword = findViewById<EditText>(R.id.edtPassword).text.toString() //recupera contraseña

        val call = apiService.postLogin(etEmail,etPasword) //los pasa como parametros de consulta del archivo ApiService "login"
        call.enqueue(object : Callback<LoginResponse>{ //enqueue()método, que permite que la solicitud se ejecute de forma asíncrona.
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) //y define los metodos de loginResponce
            {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse == null) { //si el mensaje es null, se muestra un mensaje de error en la aplicación y se sale de la función.
                        Toast.makeText(applicationContext, "se produjo un erorr en la conexión", Toast.LENGTH_SHORT).show()
                        return
                    }
                    if (loginResponse.result){
                        createSessionPreference(loginResponse.data.toString())
                        periodo()
                        /*Si no es null, se comprueba si el resultado de la respuesta es positivo. Si lo es, se llama a la función
                        createSessionPreference con el valor del campo data de loginResponse
                        como parámetro y luego se llama a la función periodo. */

                    } else {
                        Toast.makeText(applicationContext, "Correo/Contraseña incorrectos ", Toast.LENGTH_SHORT).show()
                        //si no es positivo manda ese mensaje
                    }
                } else {
                    Toast.makeText(applicationContext, "se produjo un error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Algo salio mal :(", Toast.LENGTH_SHORT).show() //manda mensaje de error y evita que se cierre la app
            }

        })

    }

}