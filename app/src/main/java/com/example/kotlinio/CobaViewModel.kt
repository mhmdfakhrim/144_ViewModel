import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kotlinio.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel: ViewModel() {
    var namaUsr : String by mutableStateOf("")
        private set
    var noTlp : String by mutableStateOf("")
        private set
    var jenisKl : String by mutableStateOf("")
        private set
    var alamat : String by mutableStateOf("")
        private set
    var almtemail : String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun BacaData(nm: String, tlp: String, jk: String, address: String, email: String){
        namaUsr=nm;
        noTlp=tlp;
        jenisKl=jk;
        alamat=address;
        almtemail=email;
    }
    fun setJK(selectJK:String){
        _uiState.update { currentState -> currentState.copy(sex= selectJK)}

    }
    fun setStatus(selectStatus:String){
        _uiState.update { currentState -> currentState.copy(status= selectStatus)}

    }

    fun BacaData(nm: String, tlp: String, jk: String, email: String) {

    }

    fun BacaData(textNama: String, texttlp: String, sex: String) {

    }
}