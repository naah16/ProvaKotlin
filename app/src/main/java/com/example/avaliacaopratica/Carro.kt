package com.example.avaliacaopratica

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Carro (
    val model: String = "",
    val type: String = "",
    var price: Double = 0.0,
    val sold: Boolean = false
)

/*class SettingsViewModel: ViewModel() {
    val items = MutableStateFlow<MutableList<ClipData.Item>>(mutableStateListOf())
    val registro = MutableStateFlow("")

    fun setItem(items: List<Carro>) {
        viewModelScope.launch {
            this@SettingsViewModel.items.emit(items as MutableList<ClipData.Item>)
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val items by viewModel.items.collectAsState()
    val register by viewModel.registro.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(top = 32.dp)

    ) {
        Text(
            text = "Carros",
            modifier = Modifier.padding(start = 32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (items.isNotEmpty()) {
            LazyColumn {
                stickyHeader {
                    Text(
                        text = "Ativações",
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 16.dp, bottom = 16.dp)
                    )
                }
                items(items = items) { item ->
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        } else {
            Column() {
                Text(text = "There's no car registered yet :'(")
            }

        }
    }
}

@Composable
fun SettingsItem(
    item: Carro,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp)
    ) {

    }

    Spacer(modifier = Modifier.width(4.dp))

    Column() {
        Text(text = item.model)
        Text(text = item.type)
        Text(text = item.price.toString())
        Text(text = item.sold.toString())
    }
}*/