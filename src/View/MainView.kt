package View

import Model.Customer
import Presenter.MainPresenter
import java.awt.*
import java.awt.event.*
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.*
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class MainView : JFrame("Cuentas Corriente - LA 24"), IMainView, WindowListener, MouseListener {

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {
            if(e.source == this.customersTable){
                var row = this.customersTable.selectedRow
                AccountView(this,"Cuenta Corriente de ${this.customersModel.getValueAt(row,1)}",
                        this.customersModel.getValueAt(row,0).toString().toLong()).isVisible = true
            }
        }
    }

    override fun mouseExited(e: MouseEvent?) {
    }

    override fun mousePressed(e: MouseEvent?) {
    }


    override fun windowDeiconified(e: WindowEvent?) {
        println("Window Deiconified")
    }

    override fun windowClosing(e: WindowEvent?) {
        println("Window Closing")
    }

    override fun windowClosed(e: WindowEvent?) {
        println("Window Closed")
    }

    override fun windowActivated(e: WindowEvent?) {
        println("Window Activated")
        this.clearCustomerTable()
        this.presenter.initDataView()
    }

    override fun windowDeactivated(e: WindowEvent?) {
        println("Window Deactivated")
    }

    override fun windowOpened(e: WindowEvent?) {
        println("Window Opened")
    }

    override fun windowIconified(e: WindowEvent?) {
        println("Window Iconified")
    }

    val presenter : MainPresenter = MainPresenter(this)
    val mainPanel : JPanel = JPanel(GridBagLayout())
    val menuBar : JMenuBar = JMenuBar()
    val menuCustomersManagment : JMenu= JMenu("Gestion de Clientes")
    val menuExit : JMenu = JMenu("Salir")
    val itemMenuExit : JMenuItem = JMenuItem("Cerrar aplicacion")
    val itemMenuNewCustomer : JMenuItem = JMenuItem("Nuevo Cliente")
    val itemMenuEditCustomer : JMenuItem = JMenuItem("Editar Cliente")
    val itemMenuDeleteCustomer : JMenuItem = JMenuItem("Borrar Cliente")
    val titleLabel: JLabel = JLabel("SISTEMA CUENTA CORRIENTE - LA 24")
    val customersTable : JTable = JTable()
    val scrollTable : JScrollPane = JScrollPane(this.customersTable)
    val customersModel : DefaultTableModel = this.customersTable.model as DefaultTableModel
    var turnNumber : Int = 2
    var date : Date = Date()
    val infoLabel : JLabel = JLabel()
    val changeTurnButton : JButton = JButton("CAMBIAR TURNO")


    init{
        val option = JOptionPane.showOptionDialog(this,"¿Que turno esta abierto en este momento?",
                "Seleccion de turno",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,
                null, arrayOf("NOCHE","MAÑANA","TARDE"),"MAÑANA")
        this.turnNumber = option + 1
        this.infoLabel.text = "Turno n: ${this.turnNumber} - ${SimpleDateFormat("dd/MM/yy").format(this.date)}"
        this.initComponents()
        this.presenter.initDataView()
        this.addWindowListener(this)
        this.customersTable.addMouseListener(this)
    }

    fun initComponents(){
        this.preferredSize = Dimension(1200,1000)
        this.minimumSize = Dimension(800,600)
        this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.location = Point((Toolkit.getDefaultToolkit().screenSize.getWidth()/4  ).toInt(),10)
        this.layout = FlowLayout()
        this.isVisible = true
        this.iconImage = Toolkit.getDefaultToolkit().getImage(javaClass.getResource("/Images/icon-la-24.png"))
        this.mainPanel.background = Color(196,196,196)
        this.mainPanel.size = this.size
        this.contentPane = this.mainPanel
        this.jMenuBar = this.generateMenuBar()

        var gbc = GridBagConstraints()

        gbc.gridx = 0
        gbc.gridy = 0
        gbc.gridwidth = 2
        gbc.fill = GridBagConstraints.CENTER
        gbc.insets = Insets(10,10,10,10)

        this.titleLabel.font = Font(Font.SANS_SERIF,Font.BOLD,20)
        this.mainPanel.add(this.titleLabel,gbc)

        gbc.gridx = 0
        gbc.gridy = 1
        gbc.gridwidth = 2
        gbc.gridheight = 4
        gbc.fill = GridBagConstraints.BOTH
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.insets = Insets(30,30,30,30)

        this.customersTable.background = Color(194,194,194)
        this.customersTable.rowHeight = 30
        this.customersModel.addColumn("DNI")
        this.customersModel.addColumn("NOMBRE")
        this.customersModel.addColumn("SALDO")
        this.mainPanel.add(this.scrollTable,gbc)

        gbc.gridx = 0
        gbc.gridy = 5
        gbc.gridwidth = 1
        gbc.weighty = 0.0
        gbc.gridheight = 1
        gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING
        gbc.fill = GridBagConstraints.NONE
        gbc.insets = Insets(0,30,30,0)

        this.changeTurnButton.addActionListener { e -> changeTurn(e) }
        this.mainPanel.add(this.changeTurnButton,gbc)

        gbc.gridx = 1
        gbc.gridy = 5
        gbc.gridwidth = 1
        gbc.weighty = 0.0
        gbc.gridheight = 1
        gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING
        gbc.fill = GridBagConstraints.NONE
        gbc.insets = Insets(0,0,30,30)

        this.infoLabel.font = Font(Font.SANS_SERIF,Font.BOLD,16)
        this.mainPanel.add(this.infoLabel,gbc)

        this.pack()
    }

    private fun changeTurn(e: ActionEvent) {
        val option = JOptionPane.showOptionDialog(this,
                "Esta a punto de cambiar de turno ¿Esta seguro? ",
                "Cambio de turno",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                arrayOf("Aceptar","Cancelar"),
                "Cancelar")
        if(option == 0){
            this.turnNumber = when (this.turnNumber){
                1 -> 2
                2 -> 3
                3 -> 1
                else -> this.turnNumber
            }
            this.infoLabel.text = "Turno n: ${this.turnNumber} - ${SimpleDateFormat("dd/MM/yy").format(this.date)}"
        }


    }

    fun generateMenuBar(): JMenuBar{
        this.menuBar.add(this.menuCustomersManagment)
        this.menuBar.add(this.menuExit)
        this.menuExit.add(this.itemMenuExit)
        this.itemMenuExit.addActionListener { e -> exitApplication(e) }
        this.menuCustomersManagment.add(this.itemMenuNewCustomer)
        this.menuCustomersManagment.add(this.itemMenuEditCustomer)
        this.menuCustomersManagment.add(this.itemMenuDeleteCustomer)
        this.itemMenuNewCustomer.addActionListener{e -> openNewCustomerDialog(e)}
        return this.menuBar
    }

    private fun exitApplication(e: ActionEvent)
    {
        println("Bye bye...")
        this.dispose()
    }
    
    private fun openNewCustomerDialog(e: ActionEvent){
        val newCustomerDialog = NewCustomerView(this,"NUEVO CLIENTE")
    }

    override fun fillCustomersTable(customers: ArrayList<Customer>) {
        for (customer in customers){
            this.customersModel.addRow(arrayOf(customer.dni,customer.name,customer.account.balance))
        }
    }

    private fun clearCustomerTable(){
        var row = this.customersModel.rowCount-1
        do {
            this.customersModel.removeRow(row)
            row --
        } while (row >= 0)
    }

}