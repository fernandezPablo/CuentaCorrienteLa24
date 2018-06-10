package Model

import java.sql.Date
import java.util.*
import kotlin.collections.ArrayList

data class Turn
(
        var number: TurnNumber = TurnNumber.ONE,
        var date: Date = Date(Calendar.getInstance().timeInMillis),
        var transactions: ArrayList<Transaction> = ArrayList()
)