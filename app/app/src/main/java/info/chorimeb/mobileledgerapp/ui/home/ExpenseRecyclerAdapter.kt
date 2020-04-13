package info.chorimeb.mobileledgerapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.model.Account
import info.chorimeb.mobileledgerapp.data.model.Expense
import kotlinx.android.synthetic.main.layout_accountlistitem.view.*
import kotlinx.android.synthetic.main.layout_expenselistitem.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ExpenseRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Expense> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_expenselistitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExpenseViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(expenseList: List<Expense>) {
        items = expenseList
    }

    class ExpenseViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val expenseAccount: TextView = itemView.expenseAccount
        private val expenseAmount: TextView = itemView.expenseAmount
        private val expenseDate: TextView = itemView.expenseDate
        private val expenseCategory: TextView = itemView.expenseCategory

        fun bind(expense: Expense) {

            expenseAccount.text = expense.accountName
            expenseDate.text = expense.paidDate.toString()
            expenseAmount.text = NumberFormat.getCurrencyInstance(Locale.US).format(expense.amount)
            expenseCategory.text = expense.category
        }
    }


}