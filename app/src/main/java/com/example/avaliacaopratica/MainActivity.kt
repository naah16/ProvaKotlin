package com.example.avaliacaopratica

import android.graphics.Color
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius.Companion.Zero
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange.Companion.Zero
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize.Companion.Zero
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
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

            Text(text = "Concessionária de Seminovos", style = typography.h6, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
            var text by remember { mutableStateOf(TextFieldValue("")) }
            // for preview add same text to all the fields

            //MODELO
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

            //TIPO
            Column() {
                dropDownMenu()
            }

            //PREÇO
            var numberText by remember { mutableStateOf(TextFieldValue("")) }
            // Outlined Text input field with input type number
            // It will open the number keyboard
            OutlinedTextField(value = numberText,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Price") },
                placeholder = { Text(text = "R$40.000") },
                onValueChange = {
                    numberText = it
                }
            )

            Column {
                Button()
            }
        }

}
@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Hatch", "Truck", "Motorbike", "Sedan", "Pickup Truck", "Van", "SUV")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    //textfieldSize = coordinates.size.toSize()
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Choose a type...")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun Button() {

    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Top,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // below line is use to get
        // the context for our app.
        val context = LocalContext.current

        // below line is use to create a button.
        Button(
            // below line is use to add onclick
            // parameter for our button onclick
            onClick = {
                // when user is clicking the button
                // we are displaying a toast message.
                Toast.makeText(context, "Adicionado com Sucesso!", Toast.LENGTH_LONG).show()
            },
            // in below line we are using modifier
            // which is use to add padding to our button
            modifier = Modifier.padding(all = Dp(10F)),

            // below line is use to set or
            // button as enable or disable.
            enabled = true,

            // below line is use to
            // add border to our button.
            //border = BorderStroke(width = 1.dp, brush = SolidColor(Color.BLUE)),

            // below line is use to add shape for our button.
            shape = MaterialTheme.shapes.medium,
        )
        // below line is use to
        // add text on our button
        {
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Body()
}