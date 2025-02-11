package com.example.avaliacaopratica

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
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
    var carros = mutableListOf<Carro>()
    AvaliacaoPraticaTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Text(text = "Concessionária de Seminovos", style = typography.h6, modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
                var modelo by remember { mutableStateOf(TextFieldValue("")) }

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

                var mostrarLista by remember { mutableStateOf(false) }
                //BOTÃO
                Column(
                    // we are using column to align our imageview to center of the screen.
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            Toast.makeText(context, "Car added successfully!", Toast.LENGTH_LONG).show()
                            mostrarLista = true
                        },                        
                        modifier = Modifier.padding(all = Dp(10F)),
                        enabled = true,
                        shape = MaterialTheme.shapes.medium
                    )

                    {
                        Text(text = "Submit")
                    }
                    if(mostrarLista) {
                        val strPreco = preco.text
                        CarProfile(carDetails)

                        val carro = Carro(modelo.text,selectedText,parseDouble(strPreco))
                        carros.add(carro)
                        CarList(carros = carros) {
                            carDetails = it
                        }
                        mostrarLista = false
                    }else{
                        CarList(carros = carros) {
                            carDetails = it
                        }
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
        elevation = 4.dp,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text (
                    text = carro.model,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    color = Color.Black,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarView(carro: Carro, onClick: () -> Unit) {
    var statusMudado by remember { mutableStateOf(carro.sold)}
    var expandDescription by remember { mutableStateOf(false)}
    var colorVariable = Color.White

    if(!statusMudado) {
        colorVariable = Color.Green
    } else {
         colorVariable = Color.Red
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .height(70.dp)
    ) {
        Box (
            modifier = Modifier
                .width(10.dp)
        )
        Text(
            text = carro.model,
            fontWeight = FontWeight.Black,
            fontSize = 24.sp,
            style = if(statusMudado) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(textDecoration = TextDecoration.None),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, colorVariable)
                .padding(8.dp)
                .clickable {
                    expandDescription = !expandDescription
                }
        )
        Text (text = if(statusMudado)"sold" else "")
    }

    AnimatedVisibility(visible = expandDescription) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .combinedClickable(
                    onClick = {
                        onClick()
                    },
                    onLongClick = {
                        statusMudado = true
                    }
                ),
            elevation = 4.dp
        ) {
            Column {
                Text(
                    text = "Type: " + carro.type,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Price: R$" + carro.price.toString(),
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = if (!statusMudado) "Status: This vehicle is available." else "Status: This vehicle is sold.",
                    modifier = Modifier.padding(8.dp)
                )
            }
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
        Column( modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

        ){
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(text = "There's no car registered yet :'(")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Body()
}