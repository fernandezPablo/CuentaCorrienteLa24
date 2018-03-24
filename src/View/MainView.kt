package View

import java.awt.*
import java.awt.event.ActionEvent
import javax.swing.*
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel

class MainView : JFrame("Cuentas Corriente - LA 24") {

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
    val columnsTableCustomers : Array<String> = arrayOf("NOMBRE","SALDO")
    val customersModel : TableModel = DefaultTableModel(columnsTableCustomers,2)
    val infoLabel : JLabel = JLabel("Turno n: x - dd/mm/aa")


    init{
        this.initComponents()
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
        this.customersTable.model = this.customersModel
        this.customersTable.rowHeight = 30
        this.mainPanel.add(this.scrollTable,gbc)

        gbc.gridx = 0
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

    fun generateMenuBar(): JMenuBar{
        this.menuBar.add(this.menuCustomersManagment)
        this.menuBar.add(this.menuExit)
        this.menuExit.add(this.itemMenuExit)
        this.itemMenuExit.addActionListener { e -> exitApplication(e) }
        this.menuCustomersManagment.add(this.itemMenuNewCustomer)
        this.menuCustomersManagment.add(this.itemMenuEditCustomer)
        this.menuCustomersManagment.add(this.itemMenuDeleteCustomer)
        this.itemMenuNewCustomer.addActionListener{e -> showMessage()}
        return this.menuBar
    }

    private fun exitApplication(e: ActionEvent)
    {
        println("Bye bye...")
        this.dispose()
    }


    private fun showMessage() = print("Hello...")

}