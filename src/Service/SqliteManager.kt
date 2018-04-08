package Service

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class SqliteManager private constructor
(
        var connection: Connection? = null,
        //revisar la rutapath to './Data/La24Accounts.sqlite': 'C:\Users\PABLO\IdeaProjects\CuentaCorrienteLa24\.\Data' does not exist
        var dbName: String = "La24Accounts.sqlite",
        var stringConnection: String = "jdbc:sqlite:${dbName}",
        var controller: String = "org.sqlite.JDBC"
)
{
    init {
        print("Creating DB...")
    }

    fun createDb(query : String){
        instance.connect()
        /**
         * split the string extracted from sql file on individual statements in order to execute one by one
         * */
        val statements: List<String> = query.split(";")
        try {
            for (st in statements){
                instance.connection!!.createStatement().execute(st)
            }
        }
        catch (ex: SQLException){
            print(ex.message)
        }
        finally {
            instance.closeConnection()
        }
    }

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
            println("Hay problemas con el driver")
            println(ex.message)
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

    fun closeConnection(){
        try {
            instance.connection!!.close()
        }
        catch (ex: SQLException){
            print(ex.message)
        }
    }

}