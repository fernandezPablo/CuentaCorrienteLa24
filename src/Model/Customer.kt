package Model

class Customer(var dni: Long = 0, var name: String = "",
               var vehicles: ArrayList<Vehicle> = ArrayList(), var account: Account = Account())
{

    init {
        this.dni = 0
        this.name = ""
        this.vehicles = ArrayList()
        this.account = Account()
    }

    override fun toString(): String {
        return "Customer(dni=$dni, name='$name')"
    }
}