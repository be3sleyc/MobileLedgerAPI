package info.chorimeb.mobileledgerapp.ui.home

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.Expense
import info.chorimeb.mobileledgerapp.ui.TopSpacingItemDecoration
import info.chorimeb.mobileledgerapp.ui.account.AccountActivity
import info.chorimeb.mobileledgerapp.ui.expense.ExpenseActivity
import info.chorimeb.mobileledgerapp.ui.login.LoggedInUserView
import info.chorimeb.mobileledgerapp.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.recycler_accounts.*
import kotlinx.android.synthetic.main.recycler_transactions.*

class HomeActivity : AppCompatActivity() {

    private lateinit var accountAdapter: AccountRecyclerAdapter
    private lateinit var expenseAdapter: ExpenseRecyclerAdapter
    private lateinit var homeViewModel: HomeViewModel
    private var isFabOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val loggedUser = intent.getSerializableExtra("USER_MODEL") as? LoggedInUserView

        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_add_menu)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close_menu)
        val fabRotCW = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRotCCW = AnimationUtils.loadAnimation(this, R.anim.rotate_counterclockwise)

        homeViewModel = ViewModelProvider(this, HomeViewModelFactory())
                .get(HomeViewModel::class.java)

        homeViewModel.getAccounts(1)
        homeViewModel.getExpenses(1)

        fabadd.setOnClickListener {
            isFabOpen = if (isFabOpen) {
                fabaddaccount.startAnimation(fabClose)
                fabaddtransaction.startAnimation(fabClose)
                fabadd.startAnimation(fabRotCCW)
                false
            } else {
                fabaddaccount.startAnimation(fabOpen)
                fabaddtransaction.startAnimation(fabOpen)
                fabadd.startAnimation(fabRotCW)
                fabaddaccount.isClickable
                fabaddtransaction.isClickable
                true
            }

            fabaddtransaction.setOnClickListener {
                val intent = Intent(applicationContext, ExpenseActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Add Transaction Activity", Toast.LENGTH_SHORT).show()
            }

            fabaddaccount.setOnClickListener {
                val intent = Intent(applicationContext, AccountActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Add Account Activity", Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.accountResult.observe(this@HomeActivity, Observer {
            val accountResult = it ?: return@Observer

            if (accountResult.error != null) {
                // do something
            } else if (accountResult.success != null) {
                initAccountRecyclerView(accountResult.success.accountlist)
            }
        })

        homeViewModel.expenseResult.observe(this@HomeActivity, Observer {
            val expenseResult = it ?: return@Observer

            if (expenseResult.error != null) {
                // do something
            } else if (expenseResult.success != null) {
                initExpenseRecyclerView(expenseResult.success.expenselist)
            }
        })

        account_title.setOnClickListener {
            expense_title.setTypeface(null, Typeface.NORMAL)
            account_title.setTypeface(null, Typeface.BOLD)
            transactionRecyclerInclude.visibility = View.GONE
            accountRecyclerInclude.visibility = View.VISIBLE
        }

        expense_title.setOnClickListener {
            expense_title.setTypeface(null, Typeface.BOLD)
            account_title.setTypeface(null, Typeface.NORMAL)
            transactionRecyclerInclude.visibility = View.VISIBLE
            accountRecyclerInclude.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun initAccountRecyclerView(accountList: ArrayList<Account>) {
        account_balance_view.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingItemDecoration)
            accountAdapter =
                AccountRecyclerAdapter(accountList)
            adapter = accountAdapter
        }
    }

    private fun initExpenseRecyclerView(expenseList: ArrayList<Expense>) {
        expense_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            val topSpacingItemDecoration = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingItemDecoration)
            expenseAdapter =
                ExpenseRecyclerAdapter(expenseList)
            adapter = expenseAdapter
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this@HomeActivity, SettingsActivity::class.java));
                return true
            }
            R.id.logout -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
