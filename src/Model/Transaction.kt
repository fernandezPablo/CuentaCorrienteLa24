package Model

import java.util.*

data class Transaction
(
        var number: Int = 0,
        var amount: Double = 0.0,
        var time: Date = Date(),
        var type: TransactionType = TransactionType.CARGA,
        var account: Account = Account(),
        var vehicle: Vehicle? = null,
        var turn: Turn = Turn()
)