package Model

data class Vehicle
(
        var domain: String = "",
        var manufacturer: String = "",
        var version: String = "",
        var year: Int = 0,
        var customer: Customer = Customer(),
        var transaction: ArrayList<Transaction> = ArrayList()
)
