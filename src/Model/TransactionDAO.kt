package Model

import Service.SqliteManager
import java.sql.Date
import java.sql.SQLException
import java.text.SimpleDateFormat

object TransactionDAO {
    /*
    * TODO (generate again the database)
    * */

    fun generateTransaction(transaction : Transaction){
        SqliteManager.instance.connect()
        val preparedStatementTransaction = SqliteManager.instance.connection!!.
                prepareStatement("INSERT INTO \"Transaction\"(amount,time,type,accountId,vehicleDomain,turnDate,turnNumber) VALUES(?,?,?,?,?,?,?) ")
        try {
            preparedStatementTransaction.setDouble(1,transaction.amount)
            preparedStatementTransaction.setString(2,SimpleDateFormat("HH:mm").format(transaction.time))
            preparedStatementTransaction.setString(3,transaction.type.name)
            preparedStatementTransaction.setInt(4,transaction.account.id)
            preparedStatementTransaction.setString(6,SimpleDateFormat("dd/MM/yyyy").format(transaction.turn.date))
            preparedStatementTransaction.setInt(7,transaction.turn.number.number)
            if(transaction.type.equals(TransactionType.CARGA)){
                preparedStatementTransaction.setString(5,transaction.vehicle?.domain)
            }
            else{
                preparedStatementTransaction.setString(5,"N/N")
            }
            preparedStatementTransaction.execute()
        }
        catch (exception : SQLException){
            println(exception.message)
        }
        finally {
            preparedStatementTransaction.close()
            SqliteManager.instance.closeConnection()
        }
    }
    /*error parsing time stamp*/
    fun getTransactions(accountId: Int, turnDate: Date, turnNumber: TurnNumber): ArrayList<Transaction> {
        val transactions : ArrayList<Transaction> = ArrayList()
        SqliteManager.instance.connect()
        val preparedStatementTransaction = SqliteManager.instance.connection!!
                .prepareStatement("SELECT * FROM \"Transaction\" WHERE accountId = ? AND turnDate = ? AND turnNumber = ?")
        try {
            preparedStatementTransaction.setInt(1,accountId)
            preparedStatementTransaction.setString(2,SimpleDateFormat("dd/MM/yyyy").format(turnDate))
            preparedStatementTransaction.setInt(3,turnNumber.number)
            val transactionsResultSet = preparedStatementTransaction.executeQuery()
            while (transactionsResultSet.next()){
                transactions.add(
                        Transaction(
                                transactionsResultSet.getInt("number"),
                                transactionsResultSet.getDouble("amount")
                        ))
                var utilDate : java.util.Date = SimpleDateFormat("HH:mm").parse(transactionsResultSet.getString("time"))
                transactions.last().time = Date(utilDate.time)
                if(transactionsResultSet.getString("type").equals("CARGA")){
                    transactions.last().type = TransactionType.CARGA
                }
                else{
                    transactions.last().type = TransactionType.DEPOSITO
                }
                transactions.last().account = Account(transactionsResultSet.getInt("accountId"))
            }
        }
        catch (exception: SQLException){
            println(exception.message)
        }
        finally {
            preparedStatementTransaction.close()
            SqliteManager.instance.closeConnection()
        }
        return transactions
    }

}