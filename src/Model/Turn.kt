package Model

import java.util.*
import kotlin.collections.ArrayList

class Turn(var number: TurnNumber = TurnNumber.ONE, var date: Date = Date(),
           var transactions: ArrayList<Transaction> = ArrayList() ) {

    init {
        this.number = TurnNumber.ONE
        this.date = Date()
        this.transactions = ArrayList()
    }

    override fun toString(): String {
        return "Turn(number=$number, date=$date, transactions=$transactions)"
    }

}