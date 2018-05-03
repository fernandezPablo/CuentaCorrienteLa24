package Presenter

import Model.Customer
import Model.CustomerDAO
import Model.VehicleDAO
import View.INewCustomerView

class NewCustomerPresenter(private val view : INewCustomerView) {

    fun registerCustomer(){
        val customer = Customer(this.view.getCustomerDni(),this.view.getCustomerName())
        val accessToCustomerObject = CustomerDAO()
        val messageCustomerTransaction = accessToCustomerObject.writeCustomer(customer)
        if(!messageCustomerTransaction.equals("OK")){
            this.view.showMessage(messageCustomerTransaction,"ERROR AL REGISTRAR CLIENTE","ERROR")
            return
        }

        val vehicles = this.view.getVehiclesData()
        val accesToVehiceObject = VehicleDAO()
        var messageVehicleTransaction = ""
        for (vehicle in vehicles){
            vehicle.customer = customer
            messageVehicleTransaction = accesToVehiceObject.writeVehicle(vehicle)
            if(!messageVehicleTransaction.equals("OK")){
                this.view.showMessage("Error al registrar vehiculo ${vehicle.domain}\n ${messageVehicleTransaction}",
                        "ERROR AL REGISTRAR VEHICULO","ERROR")
            }
        }
    }

}