package Model

import Service.SqliteManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

object CustomerDAO {

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

        preparedStatement.close()
        resultSet.close()
        sqliteManager.closeConnection()

        return customers
    }

    fun writeCustomer(customer : Customer) : String{
        val sqliteManager = SqliteManager.instance
        sqliteManager.connect()

        var preparedStatementCustomer = sqliteManager.connection!!.prepareStatement("INSERT INTO Customer(dni,name) VALUES(?,?)")

        try {
            preparedStatementCustomer.setLong(1,customer.dni)
            preparedStatementCustomer.setString(2,customer.name)

            preparedStatementCustomer.execute()
            AccountDAO.createAccountForCustomer(customerDni = customer.dni)
        }
        catch (exception : SQLException){
            sqliteManager.closeConnection()
            return exception.message.toString()
        }
        finally {
            preparedStatementCustomer.close()
            sqliteManager.closeConnection()
        }

        return "OK"
    }

    fun getCustomer(dni : Long) : Customer{
        var customer = Customer()
        val sqliteManager = SqliteManager.instance

        sqliteManager.connect()

        val preparedStatement = sqliteManager.connection!!.
                prepareStatement("SELECT * FROM Customer WHERE Customer.dni = ? LIMIT 1")
        try {
            preparedStatement.setLong(1,dni)
            val resultSet : ResultSet = preparedStatement.executeQuery()
            while (resultSet.next()){
                customer = Customer(resultSet.getLong("dni"),resultSet.getString("name"))
            }
        }
        catch (ex : SQLException){
            print(ex.message)
        }
        finally {
            preparedStatement.close()
            sqliteManager.closeConnection()
        }
        return customer
    }
    
}