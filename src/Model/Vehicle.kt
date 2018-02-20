package Model

class Vehicle(var domain: String, var manufacturer: String, var version: String, var year: Int,
              var customer: Customer, var transaction: ArrayList<Transaction>)
{
    init {
        this.domain = ""
        this.manufacturer = ""
        this.version = ""
        this.year = 0
        this.customer = Customer()
        this.transaction = ArrayList()
    }

    override fun toString(): String {
        return "Vehicle(domain='$domain', manufacturer='$manufacturer'," +
                " version='$version', year=$year, customer=$customer, transaction=$transaction)"
    }


}