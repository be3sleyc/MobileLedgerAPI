package info.chorimeb.mobileledgerapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.ui.login.LoggedInUserView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val accountRView = findViewById<RecyclerView>(R.id.account_balance_view)
        val addAccount = findViewById<Button>(R.id.add_account)
        val addTransaction = findViewById<Button>(R.id.add_transaction)

        val loggedUser = intent.getSerializableExtra("USER_MODEL") as? LoggedInUserView

        accountRView



    }
}
