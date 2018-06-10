package View

import Model.Transaction

interface IAcconuntView {

    fun loadCustomerInView(dni : Long) : Unit
    fun loadTransactionsTable(transactions : ArrayList<Transaction>)

}