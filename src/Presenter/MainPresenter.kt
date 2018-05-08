package Presenter

import Model.Account
import Model.AccountDAO
import Model.Customer
import Model.CustomerDAO
import View.IMainView

class MainPresenter (private val view: IMainView){

    fun initDataView(){
        val customers = CustomerDAO().readAllCustomers()
        var account : Account
        for (customer in customers){
            account = AccountDAO().getAccountForCustomer(customer.dni)
              customer.account = account
        }
        this.view.fillCustomersTable(customers)
    }

}