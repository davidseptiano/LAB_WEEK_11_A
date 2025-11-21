package com.example.lab_week_11_a

import android.app.Application // ✅ SOLUSI: Import kelas Application

/**
 * Kelas Application kustom untuk mengelola instance global, seperti SettingsStore.
 * Kelas ini akan diinisialisasi sekali saat aplikasi pertama kali dimulai.
 */
class SettingsApplication : Application() {
    // Properti ini akan diinisialisasi di onCreate, menyediakan akses global ke settingsStore.
    lateinit var settingsStore: SettingsStore

    override fun onCreate() {
        super.onCreate()

        // Inisialisasi SettingsStore dengan konteks aplikasi.
        // Ini memastikan hanya ada satu instance dari SettingsStore di seluruh aplikasi (singleton).
        settingsStore = SettingsStore(this)
    }
}
