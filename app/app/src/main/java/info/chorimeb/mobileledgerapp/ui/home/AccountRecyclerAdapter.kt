package info.chorimeb.mobileledgerapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.data.model.Account
import kotlinx.android.synthetic.main.layout_accountlistitem.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AccountRecyclerAdapter(private val items: ArrayList<Account>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AccountViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_accountlistitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AccountViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class AccountViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val accountIcon: ImageView = itemView.accountTypeIcon
        private val accountName: TextView = itemView.accountNameStr
        private val accountBalance: TextView = itemView.accountBalanceStr

        fun bind(account: Account) {
            accountIcon.setImageResource(
                when (account.type) {
                    "credit" -> R.drawable.ic_credit
                    "debit" -> R.drawable.ic_credit
                    "cash" -> R.drawable.ic_cash
                    "savings" -> R.drawable.ic_lend
                    else -> R.drawable.ic_launcher_background
                }
            )
            accountName.text = account.name
            accountBalance.text =
                NumberFormat.getCurrencyInstance(Locale.US).format(account.balance)
        }
    }


}