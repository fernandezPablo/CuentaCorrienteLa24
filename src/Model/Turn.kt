package Model

import java.util.*
import kotlin.collections.ArrayList

data class Turn
(
        var number: TurnNumber = TurnNumber.ONE,
        var date: Date = Date(),
        var transactions: ArrayList<Transaction> = ArrayList()
)