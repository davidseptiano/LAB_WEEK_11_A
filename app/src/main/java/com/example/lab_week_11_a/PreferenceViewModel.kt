package com.example.lab_week_11_a

import androidx.lifecycle.LiveData // ✅ SOLUSI: Import kelas LiveData
import androidx.lifecycle.ViewModel   // ✅ SOLUSI: Import kelas ViewModel

/**
 * ViewModel ini bertindak sebagai perantara antara UI dan PreferenceWrapper.
 *
 * @param preferenceWrapper Instance dari wrapper yang menangani logika SharedPreferences.
 */
class PreferenceViewModel(private val preferenceWrapper: PreferenceWrapper) : ViewModel() {

    /**
     * Menyimpan teks ke SharedPreferences melalui wrapper.
     * @param text Teks yang akan disimpan.
     */
    fun saveText(text: String) {
        preferenceWrapper.saveText(text)
    }

    /**
     * Mendapatkan teks dari SharedPreferences sebagai LiveData yang bisa diamati.
     * @return LiveData<String> yang akan diperbarui saat data berubah.
     */
    fun getText(): LiveData<String> {
        return preferenceWrapper.getText()
    }
}
