package Presenter

import Model.*
import View.IMainView

class MainPresenter (private val view: IMainView){

    fun initDataView(){
        val customers = CustomerDAO.readAllCustomers()
        var account : Account
        for (customer in customers){
            account = AccountDAO.getAccountForCustomer(customer.dni)
              customer.account = account
        }
        this.view.fillCustomersTable(customers)
    }

    fun newTurn(nTurn : Int) : Turn = TurnDAO.generateTurn(nTurn)

}