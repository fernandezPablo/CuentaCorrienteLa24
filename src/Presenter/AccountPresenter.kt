package Presenter

import Model.*
import View.IAcconuntView
import java.sql.Date
import java.time.temporal.TemporalAmount

class AccountPresenter(private val view : IAcconuntView){

    fun findCustomer(dni : Long) = CustomerDAO.getCustomer(dni)
    fun findCustomerAccount(dni: Long) = AccountDAO.getAccountForCustomer(dni)
    fun modifyCustomerBalance(amount: Double, customerDni: Long) = AccountDAO.modifyBalance(amount,customerDni)
    fun generateTransaction(transaction : Transaction) = TransactionDAO.generateTransaction(transaction)
    fun getTransactions(accountId : Int, turnDate : Date, turnNumber: TurnNumber ){
        view.loadTransactionsTable(TransactionDAO.getTransactions(accountId,turnDate,turnNumber))
    }

}