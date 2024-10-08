package com.example.simondice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.ui.theme.Colors
import com.example.simondice.ui.theme.SimonDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimonDiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(name = "Android", modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

fun comprobacion (){
    if (secuencia.size == secuenciaMaquina.size){
        comprobarSecuencia()
    }
}
fun comprobarSecuencia(){
    if (secuenciaMaquina == secuencia){
        secuencia.clear()
        Log.d("TAG", "CORRECTO")

    }else{
        secuencia.clear()
        secuenciaMaquina.clear()
        Log.d("TAG", "INCORRECTO")
    }
}
val secuenciaMaquina = mutableListOf<Int>()
fun generarSecuenciaMaquina(){
    val random = (0..3).random()
    secuenciaMaquina.add(random)
    Log.d("TAG", Colors.entries.get(random).nombre + " " + Colors.entries.get(random).id)
    Log.d("TAG", secuenciaMaquina.toString())
}
val secuencia = mutableListOf<Int>()
fun click(id : Int){
    secuencia.add(id)
    Log.d("TAG", Colors.entries.get(id).nombre + " " + Colors.entries.get(id).id)
    Log.d("TAG", secuencia.toString())
    comprobacion()
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ){
        Text(
            text = "SIMON DICE ",
            fontSize = 38.sp,
            modifier = Modifier.padding(vertical = 100.dp),
        )
        Row {
            Column {
                Button(onClick = { click(Colors.RED.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red, // Color de fondo del n botón
                        contentColor = Color.White,   // Color del texto del botón
                    )
                ) {
                    Text(text = Colors.RED.nombre)
                }
                Button(onClick = { click(Colors.BLUE.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue, // Color de fondo del n botón
                        contentColor = Color.White,   // Color del texto del botón
                    )
                ) {
                    Text(text = Colors.BLUE.nombre)
                }
            }
            Column {
                Button(onClick = { click(Colors.GREEN.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green, // Color de fondo del n botón
                        contentColor = Color.White,   // Color del texto del botón
                    )
                ) {
                    Text(text = Colors.GREEN.nombre)
                }
                Button(onClick = { click(Colors.YELLOW.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow, // Color de fondo del n botón
                        contentColor = Color.White,   // Color del texto del botón
                    )
                ) {
                    Text(text = Colors.YELLOW.nombre)
                }
            }
        }
        TextButton(onClick = { generarSecuenciaMaquina() },
            modifier = Modifier
                .padding(10.dp)
                .size(300.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray, // Color de fondo del n botón
                contentColor = Color.White,   // Color del texto del botón
            )
        ) {
            Text(text = "START ronda: " + secuenciaMaquina.size)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceTheme {
        Greeting("Android")
    }
}