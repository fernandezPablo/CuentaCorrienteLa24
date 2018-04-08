package Presenter

import Model.CustomerDAO
import View.IMainView

class MainPresenter (private val view: IMainView){

    fun initDataView(){
        val customers = CustomerDAO().readAllCustomers()
        this.view.fillCustomersTable(customers)
    }
}