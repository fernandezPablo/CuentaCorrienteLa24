package Model

import Service.SqliteManager
import java.sql.PreparedStatement
import java.sql.ResultSet

class AccountDAO {

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
        return  account
    }
}