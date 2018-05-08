package View

import Model.Customer
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.table.DefaultTableModel

class AccountView(owner: JFrame?, title : String?, customerDni : Long) : JDialog(owner,title), ActionListener
{
    override fun actionPerformed(e: ActionEvent?) {
        if (e != null) {
            if(e.source.equals(this.closeButton)){
                this.dispose()
            }
        }
    }

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

    init {
//        TODO("Find customer and load it")
        this.customer = Customer(35521288,"Fernandez Pablo")
        this.initComponents()
        this.closeButton.addActionListener{
            this.closeWindow(it)
        }
    }

    private fun closeWindow(e : ActionEvent) {
        this.dispose()
    }

    private fun initComponents() {
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

        this.balanceLabel.text = "SALDO($): xx.xx"
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

}