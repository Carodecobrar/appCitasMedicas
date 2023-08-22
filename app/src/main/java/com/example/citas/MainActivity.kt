package com.example.citas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.citas.ui.theme.CitasTheme

data class Cita(
    val id: Number,
    val doctor: String,
    val paciente: String,
    val estado: String
)
data class Usuario(
    val id: Number,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val estado: String
)
var index: Number = 0;
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CitasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    TopAppBarScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CitasTheme {
        Greeting("Android")
    }
}

@Composable
fun TopAppBarScreen() {
    var taskMenuOpen by remember { mutableStateOf(false) }
    var action by remember { mutableStateOf("Ninguna") }
    Column {
        TopAppBar(
            title = {Text("Citas médicas app", color = Color.White)},
            backgroundColor = Color(0xFF6C7FFD),
            actions = {
                IconButton(onClick = {taskMenuOpen = true}){
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Opciones", tint = Color.White)
                    TaskMenu(
                        expanded = taskMenuOpen,
                        onItemClick = {
                            if(it == "Ver citas médicas"){
                                index = 1;
                            }
                            if(it == "Ver pacientes"){
                                index = 2;
                            }
                            if(it == "Ver usuarios"){
                                index = 3;
                            }
                        },
                        onDismiss = {
                            taskMenuOpen = false
                        }
                    )
                }
            }
        )
        Box(Modifier.fillMaxSize()) {
            if (index == 1){
                TablaCitasMedicas()
            }
            if (index == 2){
                TablaPacientes()
            }
            if (index == 3){
                TablaMedicos()
            }
        }
    }
}

@Composable
fun TaskMenu(
    expanded: Boolean, // (1)
    onItemClick: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val options = listOf( // (2)
        "Ver citas médicas",
        "Ver pacientes",
        "Ver usuarios",
        "Salir"
    )

    DropdownMenu( // (3)
        expanded = expanded,
        onDismissRequest = onDismiss,
        Modifier.background(color = Color(0xFF6C7FFD))
    ) {
        options.forEach { option ->
            DropdownMenuItem( // (4)
                onClick = {
                    onItemClick(option)
                    onDismiss()
                }
            ) {
                Text(text = option)
            }
        }
    }
}

@Composable
fun TablaCitasMedicas(){
    val citas = listOf(
        Cita(1, "pepito", "pepita", "programada"),
        Cita(2, "pepito", "pepita", "programada"),
        Cita(3, "pepito", "pepita", "programada"),
        Cita(4, "pepito", "pepita", "programada"),
        Cita(5, "pepito", "pepita", "programada"),
        Cita(6, "pepito", "pepita", "programada")
    )
    Box(Modifier.fillMaxSize()) {
        ListWithColumnCita(items = citas)
    }
}
@Composable
fun TablaPacientes(){
    val pacientes = listOf(
        Usuario(1, "pepito", "perez", "21/08/2023", "activo"),
        Usuario(2, "jhon", "linares", "21/08/2023", "activo"),
        Usuario(3, "andres", "cortes", "21/08/2023", "activo"),
        Usuario(4, "sergio", "castro", "21/08/2023", "activo"),
        Usuario(5, "victor", "aguilera", "21/08/2023", "activo"),
        Usuario(6, "maria", "leon", "21/08/2023", "activo")
    )
    Box(Modifier.fillMaxSize()) {
        ListWithColumnUsuario(items = pacientes)
    }
}
@Composable
fun TablaMedicos(){
    val pacientes = listOf(
        Usuario(1, "juan", "perez", "21/08/2023", "activo"),
        Usuario(2, "gaspar", "linares", "21/08/2023", "activo"),
        Usuario(3, "baltazar", "cortes", "21/08/2023", "activo"),
        Usuario(4, "melchor", "castro", "21/08/2023", "activo"),
        Usuario(5, "jose", "aguilera", "21/08/2023", "activo"),
        Usuario(6, "maria", "de los angeles", "21/08/2023", "activo")
    )
    Box(Modifier.fillMaxSize()) {
        ListWithColumnUsuario(items = pacientes)
    }
}

@Composable
fun ListWithColumnCita(items: List<Cita>) { // (1)
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) { // (2)
        items.forEach { item -> // (4)
            ListItemRowCita(item) // (5)
        }
    }
}

@Composable // (3)
fun ListItemRowCita(item: Cita, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = "${item.id} ${item.doctor} ${item.paciente} ${item.estado}", style = MaterialTheme.typography.subtitle1)
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 210.dp, top =  0.dp, end = 0.dp, bottom = 0.dp)) {
            Text(text = "Cambiar estado")
        }
    }
}

@Composable
fun ListWithColumnUsuario(items: List<Usuario>) { // (1)
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) { // (2)
        items.forEach { item -> // (4)
            ListItemRowUsuario(item) // (5)
        }
    }
}

@Composable // (3)
fun ListItemRowUsuario(item: Usuario, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = "${item.id} ${item.nombre} ${item.apellido} ${item.fechaNacimiento} ${item.estado}", style = MaterialTheme.typography.subtitle1)
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 270.dp, top =  0.dp, end = 0.dp, bottom = 0.dp)) {
            Text(text = "Actualizar")
        }
    }
}