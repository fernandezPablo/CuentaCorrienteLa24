package Model

import Service.SqliteManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

object AccountDAO {

    fun getAccountForCustomer(customerDni : Long) : Account {
        var account : Account = Account()
        SqliteManager.instance.connect()
        val preparedStatement : PreparedStatement =
                SqliteManager.instance.connection!!.prepareStatement("SELECT * FROM `Account` WHERE `customerDni` = ? LIMIT 1")
        preparedStatement.setLong(1,customerDni)
        val resultSet : ResultSet = preparedStatement.executeQuery()
        while (resultSet.next()){
           account =  Account(resultSet.getInt("id"),resultSet.getDouble("balance"), ArrayList())
        }
        preparedStatement.close()
        resultSet.close()
        SqliteManager.instance.closeConnection()
        return  account
    }

    fun createAccountForCustomer(customerDni: Long){
        SqliteManager.instance.connect()
        val preparedStatementAccount = SqliteManager.instance.connection!!.
                prepareStatement("INSERT INTO Account(balance,customerDni) VALUES(?,?)")
        try {
            preparedStatementAccount.setDouble(1,0.0)
            preparedStatementAccount.setLong(2,customerDni)
            preparedStatementAccount.execute()
        }
        catch (ex : SQLException){
            println("Excepcion encontrada: ${ex.message}")
        }
        finally {
            preparedStatementAccount.close()
            SqliteManager.instance.closeConnection()
        }
    }

    fun modifyBalance(amount : Double, customerDni: Long){
        SqliteManager.instance.connect()
        val preparedStatementAccount = SqliteManager.instance.connection!!.
                prepareStatement("UPDATE Account SET balance = ? WHERE customerDni = ?")
        try {
            preparedStatementAccount.setDouble(1,amount)
            preparedStatementAccount.setLong(2,customerDni)
            preparedStatementAccount.execute()
        }
        catch (exception : SQLException){
            println("Excepcion encontrada: ${exception}")
        }
        finally {
            preparedStatementAccount.close()
            SqliteManager.instance.closeConnection()
        }

    }

}