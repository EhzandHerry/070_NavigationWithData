package com.example.activity6

import androidx.lifecycle.ViewModel
import com.example.activity6.data.ContactUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 3000

class ContactViewModel : ViewModel(){
    private val _stateUI = MutableStateFlow(ContactUIState())
    val stateUI: StateFlow<ContactUIState> = _stateUI.asStateFlow()

    fun setContact(listData: MutableList<String>){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = listData[0],
                alamat = listData[1],
                notelp = listData[2]
            )
        }
    }

    fun setJumlah(jmlEsJumbo:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jumlah = jmlEsJumbo)
            )
        }
    }

    fun setRasa(rasaPilihan: String){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                rasa = rasaPilihan
            )
        }
    }

    fun resetOrder(){
        _stateUI.value = ContactUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ) : String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }
}
