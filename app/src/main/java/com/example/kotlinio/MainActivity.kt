package com.example.kotlinio

import CobaViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinio.Data.DataForm
import com.example.kotlinio.Data.DataSource.jenis
import com.example.kotlinio.ui.theme.KotlinioTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            TampilForm()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()){
    var  textNama by remember {
        mutableStateOf("")
    }

    var texttlp by remember {
        mutableStateOf("")
    }
    var textAlmt by remember {
        mutableStateOf("")
    }
    var textemail by remember {
        mutableStateOf("")
    }


    val context = LocalContext.current
    val dataForm : DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState

    OutlinedTextField(
        value = textNama,
        onValueChange = {textNama = it},
        label = { Text(text = "Username")},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(text = "Masukkan nama anda")}
    )
    OutlinedTextField(
        value = texttlp,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {texttlp = it},
        label = { Text(text = "Masukkan nomor telpon anda")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(text = "")}
    )
    OutlinedTextField(
        value = textAlmt,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        onValueChange = {textAlmt = it},
        label = { Text(text = "Masukkan Alamat Anda")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
    OutlinedTextField(
        value = textemail,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        onValueChange = {textemail = it},
        label = { Text(text = "Masukkan Email Anda")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
    SelectJK(
        options = jenis.map{ id -> context.resources.getString(id)},
        onSelectedChanged = {cobaViewModel.setJK(it)})
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = {cobaViewModel.BacaData(textNama, texttlp, dataForm.sex, textemail)
        }
    ) {
        Text(text = stringResource(R.string.submit),
            fontSize = 16.sp)

    }
    Spacer(modifier = Modifier.height(100.dp))
    TextHasil(
        namanya = cobaViewModel.namaUsr,
        telponnya =cobaViewModel.noTlp ,
        jenisnya = cobaViewModel.jenisKl,
        alamatnya =  textAlmt,
        emailnya = textemail
        )
}

@Composable
fun TextHasil(namanya:String,telponnya:String,jenisnya:String,alamatnya:String,emailnya:String){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp))
        Text(text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
    }

}
@Composable
fun SelectJK(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}) {

    var selectedValue by rememberSaveable { mutableStateOf("")
    }
    Column (modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }

}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinioTheme {
        TampilLayout()
    }
}