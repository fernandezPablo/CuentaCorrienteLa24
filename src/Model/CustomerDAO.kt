package Model

import Service.SqliteManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class CustomerDAO {

    fun readAllCustomers(): ArrayList<Customer>{

        //TEST THIS METHOD
        val customers : ArrayList<Customer> = ArrayList()
        val sqliteManager: SqliteManager = SqliteManager.instance
        sqliteManager.connect()
        var preparedStatement : PreparedStatement =
                sqliteManager.connection!!.prepareStatement("SELECT * FROM Customer")
        val resultSet: ResultSet = preparedStatement.executeQuery()

        while (resultSet.next()){
            customers.add(Customer(resultSet.getLong("dni"),resultSet.getString("name")))
        }

        sqliteManager.closeConnection()
        resultSet.close()

        return customers
    }

    fun writeCustomer(customer : Customer) : String{
        val sqliteManager = SqliteManager.instance
        sqliteManager.connect()

        var preparedStatement = sqliteManager.connection!!.prepareStatement("INSERT INTO Customer(dni,name) VALUES(?,?)")

        try {
            preparedStatement.setLong(1,customer.dni)
            preparedStatement.setString(2,customer.name)

            preparedStatement.execute()

            sqliteManager.closeConnection()
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