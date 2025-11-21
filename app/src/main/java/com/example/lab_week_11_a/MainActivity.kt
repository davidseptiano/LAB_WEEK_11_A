package com.example.lab_week_11_a

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_11_a.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // ✅ SOLUSI: Ubah tipe variabel agar cocok dengan ViewModel yang Anda buat.
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil instance DataStore dari kelas Application kustom
        val settingsStore = (application as SettingsApplication).settingsStore

        // Menginisialisasi ViewModel menggunakan factory untuk meneruskan dependency
        settingsViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                    return SettingsViewModel(settingsStore) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        })[SettingsViewModel::class.java]

        // Mengamati LiveData dari SettingsViewModel
        settingsViewModel.textLiveData.observe(this, Observer { textValue ->
            binding.activityMainTextView.text = textValue
        })

        // Mengatur listener untuk tombol simpan
        binding.activityMainButton.setOnClickListener {
            val inputText = binding.activityMainEditText.text.toString()
            // Panggil metode save pada instance SettingsViewModel yang benar
            settingsViewModel.saveText(inputText)
        }
    }
}
