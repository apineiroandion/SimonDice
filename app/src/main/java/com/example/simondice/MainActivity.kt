package com.example.simondice

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.SystemClock.sleep
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RestrictTo
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.ui.theme.Colors
import com.example.simondice.ui.theme.SimonDiceTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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

var toastText = mutableStateOf("")
fun setToastText(text: String){
    toastText.value = text
}
fun getToastText(): String{
    return toastText.value
}
var ronda = mutableStateOf(0)
fun aumentarRonda(){
    ronda.value++
}

fun comprobacion (){
    if (secuencia.size <= secuenciaMaquina.size){
        comprobarSecuencia()
    }
}

fun comprobarSecuencia(){
    if (secuenciaMaquina == secuencia){
        secuencia.clear()
        Log.d("TAG", "CORRECTO")
        setToastText("Ronda " + ronda.value + " superada")
    }
    else if (secuenciaMaquina.subList(0, secuencia.size) == secuencia){
        Log.d("TAG", "CORRECTO")
        setToastText("Vas por buen camino!!")
    }
    else{
        secuencia.clear()
        secuenciaMaquina.clear()
        ronda.value = 0
        Log.d("TAG", "INCORRECTO")
        setToastText("Ronda perdida :(")
    }
}
val secuenciaMaquina = mutableListOf<Int>()
fun generarSecuenciaMaquina(){
    aumentarRonda()
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
    mostrarToast(getToastText(), this)
}
fun mostrarToast(toastText: String, context: Context){
    val duration = Toast.LENGTH_SHORT
    val toast = Toast.makeText(context, toastText, duration)
    toast.show()
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val redButtonColor = remember { mutableStateOf(Color.Red) }
    val blueButtonColor = remember { mutableStateOf(Color.Blue) }
    val greenButtonColor = remember { mutableStateOf(Color.Green) }
    val yellowButtonColor = remember { mutableStateOf(Color.Yellow) }

    suspend fun colorearSecuencia (){
        for (i in secuenciaMaquina){
            when(i){
                Colors.RED.id -> {
                    redButtonColor.value = Colors.RED.colorPressed
                    delay(1000)
                    redButtonColor.value = Color.Red
                }
                Colors.BLUE.id -> {
                    blueButtonColor.value = Colors.BLUE.colorPressed
                    delay(1000)
                    blueButtonColor.value = Color.Blue
                }
                Colors.GREEN.id -> {
                    greenButtonColor.value = Colors.GREEN.colorPressed
                    delay(1000)
                    greenButtonColor.value = Color.Green
                }
                Colors.YELLOW.id -> {
                    yellowButtonColor.value = Colors.YELLOW.colorPressed
                    delay(1000)
                    yellowButtonColor.value = Color.Yellow
                }
            }
        }
    }

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
                        containerColor = redButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.RED.nombre)
                }
                Button(onClick = { click(Colors.BLUE.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueButtonColor.value,
                        contentColor = Color.White,
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
                        containerColor = greenButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.GREEN.nombre)
                }
                Button(onClick = { click(Colors.YELLOW.id) },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(150.dp, 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = yellowButtonColor.value,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = Colors.YELLOW.nombre)
                }
            }
        }
        val coroutineScope = rememberCoroutineScope()
        TextButton(onClick = {
            coroutineScope.launch {
                generarSecuenciaMaquina()
                colorearSecuencia()
            }
                             },
            modifier = Modifier
                .padding(10.dp)
                .size(300.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White,
            )
        ) {
            Text(text = "START ronda: " + ronda.value)
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