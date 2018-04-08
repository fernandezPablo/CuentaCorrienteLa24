package Model

data class Account
(
        var id: Int = 0,
        var balance: Double = 0.0,
        var transactions: ArrayList<Transaction> = ArrayList()
)