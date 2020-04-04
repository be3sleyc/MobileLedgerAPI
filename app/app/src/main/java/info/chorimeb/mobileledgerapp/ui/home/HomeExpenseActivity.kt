package info.chorimeb.mobileledgerapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.ExpenseListDataSource
import info.chorimeb.mobileledgerapp.data.ExpenseRecyclerAdapter
import info.chorimeb.mobileledgerapp.ui.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_expense_home.*

class HomeExpenseActivity : AppCompatActivity() {

    private lateinit var expenseAdapter: ExpenseRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_home)

        initRecyclerView()
        addDataSet()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun addDataSet() {
        val data = ExpenseListDataSource.createDataSet()
        expenseAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        expense_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@HomeExpenseActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingItemDecoration)
            expenseAdapter = ExpenseRecyclerAdapter()
            adapter = expenseAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.accounts -> {
                val intent = Intent(applicationContext, HomeAccountActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.expenses -> {
//                val intent = Intent(applicationContext, HomeExpenseActivity::class.java)
//                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}