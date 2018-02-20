package Model

import java.util.*

class Transaction(var number: Int, var amount: Double, var time: Date, var type: TransactionType,
                  var account: Account = Account(), var vehicle: Vehicle? = null, var turn: Turn = Turn()) {

    init {
        this.number = 0
        this.amount = 0.0
        this.time = Date()
        this.type = TransactionType.CARGA
        this.account = Account()
        this.vehicle = null
        this.turn = Turn()
    }

    override fun toString(): String {
        return "Transaction(number=$number, amount=$amount, time=$time, type=$type, account=$account," +
                " vehicle=$vehicle, turn=$turn)"
    }
}