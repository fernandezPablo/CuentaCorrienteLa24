package View

import Model.Customer

interface IMainView {

    fun fillCustomersTable(customers: ArrayList<Customer>)

}