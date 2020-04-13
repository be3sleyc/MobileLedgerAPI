package info.chorimeb.mobileledgerapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()

        supportFragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}