package info.chorimeb.mobileledgerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import info.chorimeb.mobileledgerapp.ui.login.LoggedInUserView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val loggedUser = intent.getSerializableExtra("USER_MODEL") as? LoggedInUserView

    }
}
