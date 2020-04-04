package info.chorimeb.mobileledgerapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.AccountListDataSource
import info.chorimeb.mobileledgerapp.data.AccountRecyclerAdapter
import info.chorimeb.mobileledgerapp.ui.TopSpacingItemDecoration
import info.chorimeb.mobileledgerapp.ui.login.LoggedInUserView
import kotlinx.android.synthetic.main.activity_account_home.*

class HomeAccountActivity : AppCompatActivity() {

    private lateinit var accountAdapter: AccountRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_home)

        val addAccount = findViewById<Button>(R.id.add_account)
        val addTransaction = findViewById<Button>(R.id.add_transaction)

        val loggedUser = intent.getSerializableExtra("USER_MODEL") as? LoggedInUserView

        initRecyclerView()
        addDataSet()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun addDataSet() {
        val data = AccountListDataSource.createDateSet()
        accountAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        account_balance_view.apply {
            layoutManager = LinearLayoutManager(this@HomeAccountActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingItemDecoration)
            accountAdapter = AccountRecyclerAdapter()
            adapter = accountAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.accounts -> {
//                val intent = Intent(applicationContext, HomeAccountActivity::class.java)
//                startActivity(intent)
                true
            }
            R.id.expenses -> {
                println("starting expenses recycler view home screen")
                val intent = Intent(applicationContext, HomeExpenseActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
