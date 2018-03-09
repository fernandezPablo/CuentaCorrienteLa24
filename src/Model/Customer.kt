package Model

data class Customer
(
        var dni: Long = 0,
        var name: String = "",
        var vehicles: ArrayList<Vehicle> = ArrayList(),
        var account: Account = Account()
)
