package com.example.avaliacaopratica

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avaliacaopratica.ui.theme.AvaliacaoPraticaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvaliacaoPraticaTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Body()
                    }
                }
            }
        }
    }
}

@Composable
fun Body() {
        Column {

            Text(text = "Text Inputs", style = typography.h6, modifier = Modifier.padding(8.dp))
            var text by remember { mutableStateOf(TextFieldValue("")) }
            // for preview add same text to all the fields



            // Outlined Text Input Field
            OutlinedTextField(
                value = text,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = { Text(text = "Model") },
                placeholder = { Text(text = "Fiat Uno ano 2003") },
                onValueChange = {
                    text = it
                },

            )

            // Outlined Input text with icon on the left
            // inside leadingIcon property add the icon
            OutlinedTextField(
                value = text,

                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Choose a type...") },
                placeholder = { Text(text = "Hatch") },
                onValueChange = {
                    text = it
                }
            )

            var numberText by remember { mutableStateOf(TextFieldValue("")) }
            // Outlined Text input field with input type number
            // It will open the number keyboard
            OutlinedTextField(value = numberText,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Price") },
                placeholder = { Text(text = "40000") },
                onValueChange = {
                    numberText = it
                }
            )
        }
}

/*@Composable
fun CarroList(carros: MutableList<Carro>,
                onClick: (carro: Carro) -> Unit) {
    LazyColumn {
        items(carros) { carro ->
            ContactView(carro = carro) {
                onClick(carro)
            }
        }
    }
}*/



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Body()
}