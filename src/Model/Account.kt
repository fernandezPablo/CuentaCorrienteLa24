package Model

class Account(var id: Int = 0, var balance: Double = 0.0,
              var owner: Customer = Customer(), var transactions: ArrayList<Transaction> = ArrayList()) {

    init {
        this.id = 0
        this.balance = 0.0
        this.owner = Customer()
        this.transactions = ArrayList()
    }

    override fun toString(): String {
        return "Account(id=$id, balance=$balance, owner=$owner, transactions=$transactions)"
    }

}