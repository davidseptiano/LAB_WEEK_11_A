package com.example.lab_week_11_a

// ✅ SOLUSI: Tambahkan semua import yang diperlukan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel untuk mengelola data dari SettingsStore (DataStore) dan menyediakannya ke UI.
 */
class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    // LiveData privat yang dapat diubah untuk menyimpan nilai teks dari DataStore.
    // Ini adalah 'backing property' untuk textLiveData.
    private val _textLiveData = MutableLiveData<String>()

    // LiveData publik yang hanya bisa dibaca (read-only) yang akan diamati oleh UI.
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Meluncurkan coroutine untuk mendapatkan teks dari data store secara asinkron.
        // ✅ SOLUSI: Hapus teks 'asynchronously' yang tidak valid
        viewModelScope.launch {
            // Mengoleksi (listen) data dari Flow 'text' yang ada di SettingsStore.
            // Setiap kali ada data baru, nilai LiveData akan diperbarui.
            settingsStore.text.collect {
                _textLiveData.value = it
            }
        }
    }

    /**
     * Menyimpan teks ke DataStore.
     * @param text Teks yang akan disimpan.
     */
    fun saveText(text: String) {
        // Meluncurkan coroutine untuk menyimpan teks ke data store secara asinkron.
        // ✅ SOLUSI: Hapus teks 'asynchronously' yang tidak valid
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }
}
