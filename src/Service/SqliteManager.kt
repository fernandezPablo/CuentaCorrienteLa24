package Service

import sun.rmi.runtime.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.logging.Logger

class SqliteManager private constructor
(
        var connection: Connection? = null,
        var dbName: String = "/Data/La24Accounts.sqlite",
        var stringConnection: String = "jdbc:sqlite ${dbName}",
        var controller: String = "org.sqlite.JDBC"
)
{
    private object holder { val INSTANCE = SqliteManager()}

    companion object sqliteManagerComapnion{
        val instance: SqliteManager by lazy { holder.INSTANCE }
    }

    private fun isDriver(): Boolean{
        try {
            Class.forName(controller)
            return true
        }
        catch (ex: ClassNotFoundException){
            System.out.println(ex.message)
            return false
        }
    }

    fun connect(): Unit{
        if (isDriver()){
            try {
                this.connection = DriverManager.getConnection(stringConnection)
            }
            catch (ex: SQLException){
                System.out.println(ex.message)
            }
        }
    }

}