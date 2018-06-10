package Model

import java.sql.Date
import java.util.*

data class Transaction
(
        var number: Int = 0,
        var amount: Double = 0.0,
        var time: Date = Date(Calendar.getInstance().timeInMillis), /*Calendar.getInstance().timeInMillis obtiene la fecha y hora del sistema*/
        var type: TransactionType = TransactionType.CARGA,
        var account: Account = Account(),
        var vehicle: Vehicle? = null,
        var turn: Turn = Turn()
)