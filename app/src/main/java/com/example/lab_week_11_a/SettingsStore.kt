package com.example.lab_week_11_a

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Membuat extension property pada Context untuk menyediakan singleton instance dari DataStore.
 * Ini adalah cara yang direkomendasikan untuk memastikan hanya ada satu instance DataStore per file.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsStore")

/**
 * Kelas ini berfungsi sebagai perantara untuk membaca dan menulis data ke Jetpack DataStore.
 * @param context Konteks aplikasi yang digunakan untuk mengakses DataStore.
 */
class SettingsStore(private val context: Context) {

    // Objek pendamping untuk menyimpan konstanta, dalam hal ini adalah kunci untuk DataStore.
    companion object {
        // Membuat kunci (key) untuk mengakses data String di DataStore.
        val KEY_TEXT = stringPreferencesKey("key_text")
    }

    /**
     * Sebuah Flow yang akan mengeluarkan (emit) nilai teks setiap kali data di DataStore berubah.
     * ViewModel akan mengamati Flow ini.
     */
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            // Membaca nilai dari DataStore menggunakan KEY_TEXT.
            // Jika tidak ada nilai (null), kembalikan string kosong.
            preferences[KEY_TEXT] ?: ""
        }

    /**
     * Fungsi suspend untuk menyimpan teks ke DataStore secara asinkron.
     * @param text Teks yang akan disimpan.
     */
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }
}
