package info.chorimeb.mobileledgerapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.AccountListDataSource
import info.chorimeb.mobileledgerapp.data.AccountRecyclerAdapter
import info.chorimeb.mobileledgerapp.ui.TopSpacingItemDecoration
import info.chorimeb.mobileledgerapp.ui.login.LoggedInUserView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var accountAdapter: AccountRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val addAccount = findViewById<Button>(R.id.add_account)
        val addTransaction = findViewById<Button>(R.id.add_transaction)

        val loggedUser = intent.getSerializableExtra("USER_MODEL") as? LoggedInUserView

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        val data = AccountListDataSource.createDateSet()
        accountAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        account_balance_view.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingItemDecoration)
            accountAdapter = AccountRecyclerAdapter()
            adapter = accountAdapter
        }
    }
}
