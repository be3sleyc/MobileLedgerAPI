package info.chorimeb.mobileledgerapp.data

import info.chorimeb.mobileledgerapp.data.model.Expense
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ExpenseListDataSource {
    companion object {
        fun createDataSet(): ArrayList<Expense> {
            val list = ArrayList<Expense>()
            list.add(
                Expense(
                    203, "Wells Credit", 2203, LocalDateTime.parse(
                        "2020-03-22 03:31:01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ), "", -89.50, "", "Misc"
                )
            )
            list.add(
                Expense(
                    202, "Wells Checking", 2203, LocalDateTime.parse(
                        "2020-03-22 03:31:01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ), "", 1205.43, "", "Misc"
                )
            )
            list.add(
                Expense(
                    201, "Cash", 2203, LocalDateTime.parse(
                        "2020-03-22 03:31:01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ), "", 0.00, "", "Misc"
                )
            )
            list.add(
                Expense(
                    204, "Wells Savings", 2203, LocalDateTime.parse(
                        "2020-03-22 03:31:01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ), "", 400.01, "", "Misc"
                )
            )
            list.add(
                Expense(
                    205, "Chase Amazon Credit", 2203, LocalDateTime.parse(
                        "2020-03-22 03:31:01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ), "", -180.23, "", "Misc"
                )
            )
            list.add(
                Expense(
                    206, "UCCU Savings", 2203, LocalDateTime.parse(
                        "2020-03-22 03:31:01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    ), "", 201.45, "", "Misc"
                )
            )
            return list
        }
    }
}