package Model

import Service.SqliteManager
import java.sql.SQLException

class VehicleDAO {

    /*@param [vehicle] Vehicle to register in the storage
    * @return returns a message about state of transaction
    * */
    fun writeVehicle(vehicle : Vehicle) : String{
        val sqliteManager : SqliteManager = SqliteManager.instance
        sqliteManager.connect()
        val preparedStatement = sqliteManager.connection!!.prepareStatement(
                "INSERT INTO Vehicle(domain,manufacturer,version,year,customerDni) VALUES(?,?,?,?,?)")
        try {
            preparedStatement.setString(1,vehicle.domain)
            preparedStatement.setString(2,vehicle.manufacturer)
            preparedStatement.setString(3,vehicle.version)
            preparedStatement.setInt(4,vehicle.year)
            preparedStatement.setLong(5,vehicle.customer.dni)
            preparedStatement.execute()
        }
        catch (exception : SQLException){
            return exception.message.toString()
        }
        finally {
            sqliteManager.closeConnection()
        }
        return "OK"
    }

}