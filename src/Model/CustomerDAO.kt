package Model

import Service.SqliteManager
import java.sql.PreparedStatement
import java.sql.ResultSet

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
    
}