package Model

import Service.SqliteManager
import java.sql.Date
import java.sql.SQLException
import java.util.*

object TurnDAO {

    fun generateTurn(nTurn : Int) : Turn{
        SqliteManager.instance.connect()
        val turn = Turn()
        when(nTurn){
            1 -> turn.number = TurnNumber.ONE
            2 -> turn.number = TurnNumber.TWO
            3 -> turn.number = TurnNumber.THREE
        }
        turn.date = Date(Calendar.getInstance().timeInMillis)
        val preparedStatementTurn = SqliteManager.instance.connection!!
                .prepareStatement("INSERT INTO Turn(date,number) VALUES(?,?)")
        try {
            preparedStatementTurn.setDate(1,turn.date )
            preparedStatementTurn.setInt(2,turn.number.number)
            preparedStatementTurn.execute()
        }
        catch (exception : SQLException){
            println(exception.message)
            return Turn()
        }
        finally {
            preparedStatementTurn.close()
            SqliteManager.instance.closeConnection()
        }
        return turn
    }

}