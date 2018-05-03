package View

import Model.Vehicle
import javax.print.attribute.standard.PrinterMoreInfoManufacturer

interface INewCustomerView {

    fun getCustomerName() : String
    fun setCustomerName(name : String) : Unit
    fun getCustomerDni() : Long
    fun setCustomerDni(dni : Long) : Unit
    fun getVehicleDomain() : String
    fun setVehicleDomain(domain : String) : Unit
    fun getVehicleManufacturer() : String
    fun setVehicleManufacturer(manufacturer: String) : Unit
    fun getVehicleVersion() : String
    fun setVehicleVersion(version : String) : Unit
    fun getVehicleYear() : Int
    fun setVehicleYear(year : Int) : Unit
    fun getVehiclesData() : ArrayList<Vehicle>
    fun showMessage(message : String,title : String, messageType: String) : Unit

}