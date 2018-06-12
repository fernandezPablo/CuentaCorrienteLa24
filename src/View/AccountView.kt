package View

import Model.Customer
import Model.Transaction
import Model.TransactionType
import Model.Turn
import Presenter.AccountPresenter
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.*
import javax.swing.*
import javax.swing.table.DefaultTableModel
import java.sql.Date
import java.text.SimpleDateFormat

class AccountView(owner: JFrame?, title : String?, customerDni : Long, turn : Turn) : JDialog(owner,title), ActionListener, IAcconuntView
{
    private val mainPanel : JPanel = JPanel(GridBagLayout())
    private val titleLabel : JLabel = JLabel("CUENTA CORRIENTE DE ")
    private val balanceLabel : JLabel = JLabel("SALDO($): ")
    private val transactionsTable : JTable = JTable()
    private val transactionsTableModel : DefaultTableModel = this.transactionsTable.model as DefaultTableModel
    private val transactionTableScroll : JScrollPane = JScrollPane(this.transactionsTable)
    private val depositButton : JButton = JButton("DEPOSITO")
    private val chargeButton : JButton = JButton("CARGA")
    private val detailButton : JButton = JButton("DETALLE")
    private val closeButton : JButton = JButton("CERRAR")
    private val buttonsPanel : JPanel = JPanel()
    private lateinit var customer : Customer
    private val presenter : AccountPresenter = AccountPresenter(this)

    init {
        this.loadCustomerInView(customerDni)
        this.initComponents()
        this.presenter.getTransactions(this.customer.account.id,turn.date,turn.number)
        this.closeButton.addActionListener{
            this.closeWindow(it)
        }
        this.depositButton.addActionListener {
            this.balanceDeposit(it)
        }
        this.chargeButton.addActionListener {
            this.charge(it)
        }
    }

    private fun charge(it: ActionEvent?) {

    }

    private fun balanceDeposit(event : ActionEvent) {
        var value = JOptionPane.showInputDialog(this,"INGRESE EL MONTO A DEPOSITAR ($)")
        var amount : Double = 0.0
        val transaction = Transaction()
        try{
            amount = value.toDouble()
            transaction.amount = amount
            transaction.account = this.customer.account
            transaction.type = TransactionType.DEPOSITO
            transaction.time = Date(Calendar.getInstance().timeInMillis)
            transaction.account = this.customer.account
        }
        catch(ex : NumberFormatException){
            JOptionPane.showMessageDialog(this,"Ingrese solo números por favor","ERROR",
                    JOptionPane.ERROR_MESSAGE)
            return
        }
        catch(ex : IllegalStateException){
            return
        }
        presenter.generateTransaction(transaction)
        presenter.modifyCustomerBalance(this.customer.account.balance + amount,this.customer.dni)
        this.refreshViewData()
    }

    private fun refreshViewData(){
        this.loadCustomerInView(this.customer.dni)
        this.titleLabel.text = "CUENTA CORRIENTE DE ${this.customer.name.toUpperCase()}"
        this.balanceLabel.text = "SALDO($): ${this.customer.account.balance}"
    }

    private fun closeWindow(e : ActionEvent) {
        this.dispose()
    }

    private fun initComponents() {
        this.location = parent.location
        this.size = Dimension(800,800)
        this.minimumSize = Dimension(800,600)
        this.mainPanel.location = owner.location
        this.add(this.mainPanel)

        var gbc = GridBagConstraints()

        gbc.gridx = 1
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.NONE
        gbc.anchor = GridBagConstraints.CENTER
        gbc.gridheight = 1
        gbc.gridwidth = 3
        gbc.weighty = 0.0
        gbc.insets = Insets(30,0,40,0)

        this.titleLabel.font = Font("Arial",Font.BOLD,18)
        this.titleLabel.text += customer.name.toUpperCase()
        this.mainPanel.add(this.titleLabel,gbc)

        gbc.gridx = 2
        gbc.gridy = 1
        gbc.fill = GridBagConstraints.NONE
        gbc.gridheight = 1
        gbc.gridwidth = 1
        gbc.anchor = GridBagConstraints.CENTER
        gbc.weightx = 0.0
        gbc.weighty = 0.0
        gbc.insets = Insets(0,0,30,30)

        this.balanceLabel.text = "SALDO($): ${this.customer.account.balance}"
        this.balanceLabel.foreground = Color.GREEN
        this.balanceLabel.font = Font("Arial",Font.BOLD,20)
        this.mainPanel.add(this.balanceLabel,gbc)

        gbc.gridx = 0
        gbc.gridy = 2
        gbc.fill = GridBagConstraints.BOTH
        gbc.gridwidth = 2
        gbc.gridheight = 1
        gbc.anchor = GridBagConstraints.CENTER
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.insets = Insets(0,30,30,30)

        this.transactionsTableModel.addColumn("HORA")
        this.transactionsTableModel.addColumn("TIPO")
        this.transactionsTableModel.addColumn("MONTO")
        this.mainPanel.add(this.transactionTableScroll,gbc)

        gbc.gridx = 2
        gbc.gridy = 2
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridheight = 1
        gbc.gridwidth = 1
        gbc.weightx = 0.0
        gbc.weighty = 0.0
        gbc.anchor = GridBagConstraints.NORTH
        gbc.insets = Insets(0,0,30,40)

        this.buttonsPanel.layout = GridBagLayout()
        this.buttonsPanel.size = Dimension(50,50)
        this.mainPanel.add(this.buttonsPanel,gbc)

        gbc.gridx = 0
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weighty = 0.0

        this.buttonsPanel.add(this.chargeButton,gbc)

        gbc.gridx = 0
        gbc.gridy = 1

        this.buttonsPanel.add(this.depositButton,gbc)

        gbc.gridx = 0
        gbc.gridy = 2

        this.buttonsPanel.add(this.detailButton,gbc)

        gbc.gridx = 1
        gbc.gridy = 3
        gbc.fill = GridBagConstraints.NONE
        gbc.gridwidth = 3
        gbc.gridheight = 1
        gbc.insets = Insets(30,0,30,0)
        gbc.anchor = GridBagConstraints.CENTER

        this.mainPanel.add(this.closeButton,gbc)
    }

    override fun loadCustomerInView(dni : Long) {
        this.customer = presenter.findCustomer(dni)
        this.customer.account = presenter.findCustomerAccount(dni)
    }

    override fun loadTransactionsTable(transactions : ArrayList<Transaction>){
        for(transaction in transactions){
            this.transactionsTableModel.addRow(
                    arrayOf(
                        SimpleDateFormat("HH:mm").format(transaction.time),
                        transaction.type.name,
                        transaction.amount
                    )
            )
        }
    }

    override fun actionPerformed(event: ActionEvent?) {
        if (event != null) {
            if(event.source.equals(this.closeButton)){
                this.dispose()
            }
        }
    }

}