package com.example.citas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.citas.ui.theme.CitasTheme

data class ActionItem(
    val name: String,
    val icon: ImageVector,
    val action: () -> Unit
)
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
            title = {Text("Citas médicas", color = Color.White)},
            backgroundColor = Color(0xFF6C7FFD),
            actions = {
                IconButton(onClick = {taskMenuOpen = true}){
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Opciones", tint = Color.White)
                    TaskMenu(
                        expanded = taskMenuOpen,
                        onItemClick = { action = it },
                        onDismiss = {
                            taskMenuOpen = false
                        }
                    )
                }
            }
        )
        Box(Modifier.fillMaxSize()) {
            Text(
                text = "Contenido",
                modifier = Modifier.align(Alignment.Center)
            )
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
        "Cambiar nombre",
        "Enviar por email",
        "Copiar enlace",
        "Ocultar subtareas completas",
        "Eliminar"
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