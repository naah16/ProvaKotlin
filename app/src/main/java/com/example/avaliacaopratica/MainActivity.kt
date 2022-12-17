package com.example.avaliacaopratica

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.avaliacaopratica.ui.theme.AvaliacaoPraticaTheme
import java.lang.Double.parseDouble


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Body()
        }
    }
}

@Composable
fun Body() {
    var carDetails by remember { mutableStateOf(Carro()) }
    AvaliacaoPraticaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Text(text = "Concessionária de Seminovos", style = typography.h6, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                var modelo by remember { mutableStateOf(TextFieldValue("")) }
                // for preview add same text to all the fields

                //MODELO
                OutlinedTextField(
                    value = modelo,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Model") },
                    placeholder = { Text(text = "Fiat Uno ano 2003") },
                    onValueChange = {
                        modelo = it
                    },
                )

                //TIPO
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

                //PREÇO
                var preco by remember { mutableStateOf(TextFieldValue("")) }
                // Outlined Text input field with input type number
                // It will open the number keyboard
                OutlinedTextField(value = preco,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Price") },
                    placeholder = { Text(text = "R$40.000") },
                    onValueChange = {
                        preco = it
                    }
                )

                //BOTÃO
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
                    val interactionSource = remember { MutableInteractionSource() }
                    val isPressed by interactionSource.collectIsPressedAsState()
                    val context = LocalContext.current


                    // below line is use to create a button.
                    Button(
                        // below line is use to add onclick
                        // parameter for our button onclick
                        onClick = {
                            // when user is clicking the button we are displaying a toast message.
                            Toast.makeText(context, "Carro adicionado com sucesso!", Toast.LENGTH_LONG).show()

                        },
                        interactionSource = interactionSource,
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

                        /*CarProfile(carDetails)
                        val carros = mutableListOf(Carro(modelo.text,selectedText.toString(),
                            parseDouble(preco.toString())))
                        CarList(carros = carros) {
                            carDetails = it
                        }*/
                    }

                }

            }
        }
    }
}

@Composable
fun CarProfile(carro: Carro) {
    var expandDescription by remember { mutableStateOf(false)}
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text (
                    text = carro.model,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
                Text(
                    text = if(!expandDescription) "+" else "-",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    color = Color(0xFF8F8F8F),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            expandDescription = !expandDescription
                        }
                )
            }
            AnimatedVisibility(visible = expandDescription) {
                Text (
                    text = stringResource(
                        id = R.string.description_text,
                        carro.type,
                        carro.price,
                        carro.sold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun CarView(carro: Carro, onClick: () -> Unit) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        elevation = 4.dp
    ) {
        Column {
            Text(
                text = carro.model,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = carro.type,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = carro.price.toString(),
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = carro.sold.toString(),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun CarList(
    carros: MutableList<Carro>,
    onClick: (carro: Carro) -> Unit
) {
    if(carros.isNotEmpty()) {
        LazyColumn{
            items(carros) { carro ->
                CarView(carro = carro) {
                    onClick(carro)
                }
            }
        }
    } else {
        Column {
            Text(text = "There's no car registered yet :'(")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Body()
}