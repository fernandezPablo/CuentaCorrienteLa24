package View

import java.awt.*
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.text.Format
import javax.swing.*
import javax.swing.border.TitledBorder
import javax.swing.table.DefaultTableModel

class NewCustomerView(owner: Frame?, title: String?) : JDialog(owner, title), FocusListener
{
    override fun focusLost(e: FocusEvent?) {
        if (e?.source == this.vehicleYearTextField ){
            println(this.vehicleYearTextField.value)
        }
    }

    override fun focusGained(e: FocusEvent?) {
        if(e?.source == this.vehicleYearTextField){
            this.vehicleYearTextField.text = ""
        }
    }

    private val mainPanel : JPanel = JPanel(GridBagLayout())
    private val title : JLabel = JLabel("REGISTRO DE NUEVOS CLIENTES")
    private val customerDataPanel: JPanel = JPanel(GridBagLayout())
    private val vehicleDataPanel : JPanel = JPanel(GridBagLayout())
    private val registerButton : JButton = JButton("REGISTRAR")
    private val cancelButton : JButton = JButton("CANCELAR")
    private val customerNameTextField : JTextField = JTextField()
    private val customerDniTextField : JFormattedTextField = JFormattedTextField(Integer(0))
    private val vehicleDomainTextField : JTextField = JTextField()
    private val vehicleManufacturerTextField : JTextField = JTextField()
    private val vehicleVersionTextField : JTextField = JTextField()
    private val vehicleYearTextField : JFormattedTextField = JFormattedTextField(Integer(1991))
    private val addVehicleButton : JButton = JButton("AGREGAR")
    private val vehiclesTable : JTable = JTable()
    private val vehiclesTableScroll : JScrollPane = JScrollPane(this.vehiclesTable)
    private val vehiclesTableModel : DefaultTableModel = this.vehiclesTable.model as DefaultTableModel

    init {
        this.initComponents()
        this.vehicleYearTextField.addFocusListener(this)
    }

    private fun initComponents(){
        this.setSize(600,600)
        this.minimumSize = Dimension(600,600)
        this.isVisible = true
        this.setLocationRelativeTo(owner)
        this.layout = FlowLayout()
        this.mainPanel.background = Color(196,196,196)
        this.contentPane = mainPanel


        var gbc  = GridBagConstraints()

        /*TITLE*/
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.CENTER
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 2
        this.title.font = Font(Font.SANS_SERIF,Font.BOLD,20)
        this.mainPanel.add(this.title,gbc)

        /*CUSTOMER DATA PANEL*/
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.fill = GridBagConstraints.BOTH
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 3
        gbc.gridheight = 2
        gbc.weighty = 0.5
        gbc.weightx = 1.0
        this.customerDataPanel.border = TitledBorder("DATOS DEL CLIENTE")
        this.mainPanel.add(this.customerDataPanel,gbc)

        /*CUSTOMER NAME TEXTFIELD IN CUSTOMER DATA PANEL*/
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridwidth = 2
        gbc.gridheight = 1
        gbc.weightx = 1.0
        this.customerNameTextField.border = TitledBorder("NOMBRE Y APELLIDO")
        this.customerNameTextField.font = Font("Arial",Font.BOLD,25)
        this.customerNameTextField.toolTipText = "NOMBRE Y APELLIDO DEL CLIENTE"
        this.customerDataPanel.add(this.customerNameTextField,gbc)

        /*CUSTOMER DNI TEXTFIELD IN CUSTOMER DATA PANEL*/
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridwidth = 2
        gbc.gridheight = 1
        gbc.weightx = 1.0
        this.customerDniTextField.border = TitledBorder("DNI")
        this.customerDniTextField.font = Font("Arial",Font.BOLD,25)
        this.customerDniTextField.toolTipText = "NUMERO DE DOCUMENTO DEL CLIENTE"
        this.customerDataPanel.add(this.customerDniTextField,gbc)

        /*VEHICLE DATA PANEL*/
        gbc.gridx = 0
        gbc.gridy = 3
        gbc.fill = GridBagConstraints.BOTH
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 3
        gbc.gridheight = 2
        gbc.weighty = 1.0
        gbc.weightx = 1.0
        this.vehicleDataPanel.border = TitledBorder("DATOS DE LOS VEHICULOS")
        this.mainPanel.add(this.vehicleDataPanel,gbc)

        /*ADD VEHICLE BUTTON IN VEHICLE DATA PANEL*/
        gbc = GridBagConstraints()
        gbc.gridx = 1
        gbc.gridy = 2
        gbc.fill = GridBagConstraints.NONE
        gbc.insets = Insets(0,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING
        this.addVehicleButton.toolTipText = "AGREGAR UN VEHICULO"
        this.vehicleDataPanel.add(this.addVehicleButton,gbc)

        /*VEHICLE DOMAIN TEXTFIELD IN VEHICLE DATA PANEL*/
        gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weightx = 1.0
        this.vehicleDomainTextField.border = TitledBorder("DOMINIO")
        this.vehicleDomainTextField.font = Font("Arial",Font.BOLD,20)
        this.vehicleDomainTextField.toolTipText = "DOMINIO DEL VEHICULO"
        this.vehicleDataPanel.add(this.vehicleDomainTextField,gbc)

        /*VEHICLE MANUFACTURED TEXTFIELD IN VEHICLE DATA PANEL*/
        gbc = GridBagConstraints()
        gbc.gridx = 1
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weightx = 1.0
        this.vehicleManufacturerTextField.border = TitledBorder("FABRICANTE")
        this.vehicleManufacturerTextField.font = Font("Arial",Font.BOLD,20)
        this.vehicleManufacturerTextField.toolTipText = "FABRICANTE DEL VEHICULO"
        this.vehicleDataPanel.add(this.vehicleManufacturerTextField,gbc)

        /*VEHICLE VERSION TEXTFIELD IN VEHICLE DATA PANEL*/
        gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weightx = 1.0
        this.vehicleVersionTextField.border = TitledBorder("MODELO")
        this.vehicleVersionTextField.font = Font("Arial",Font.BOLD,20)
        this.vehicleVersionTextField.toolTipText = "VERSION DEL VEHICULO"
        this.vehicleDataPanel.add(this.vehicleVersionTextField,gbc)

        /*VEHICLE YEAR TEXTFIELD IN VEHICLE DATA PANEL*/
        gbc = GridBagConstraints()
        gbc.gridx = 1
        gbc.gridy = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weightx = 1.0
        this.vehicleYearTextField.border = TitledBorder("AÑO")
        this.vehicleYearTextField.font = Font("Arial",Font.BOLD,20)
        this.vehicleYearTextField.toolTipText = "AÑO DE FABRICACION DEL VEHICULO. INGRESAR SOLO NUMEROS"
        this.vehicleDataPanel.add(this.vehicleYearTextField,gbc)

        /*VEHICLE SCROLL TABLE IN VEHICLE DATA PANEL*/
        gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 3
        gbc.gridwidth = 2
        gbc.gridheight = 2
        gbc.fill = GridBagConstraints.BOTH
        gbc.insets = Insets(10,10,10,10)
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        this.vehiclesTableModel.addColumn("DOMINIO")
        this.vehiclesTableModel.addColumn("FABRICANTE")
        this.vehiclesTableModel.addColumn("MODELO")
        this.vehiclesTableModel.addColumn("AÑO")
        this.vehicleDataPanel.add(this.vehiclesTableScroll,gbc)
        /*TODO reducir el tamaño de la tabla vehiculos*/

        /*REGISTER BUTTON*/
        gbc.gridx = 0
        gbc.gridy = 5
        gbc.fill = GridBagConstraints.NONE
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weighty = 0.0
        gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING
        this.registerButton.toolTipText = "REGISTRAR CLIENTE"
        this.mainPanel.add(this.registerButton,gbc)

        /*CANCEL BUTTON*/
        gbc.gridx = 0
        gbc.gridy = 5
        gbc.fill = GridBagConstraints.NONE
        gbc.insets = Insets(10,10,10,10)
        gbc.gridwidth = 1
        gbc.gridheight = 1
        gbc.weighty = 0.0
        gbc.anchor = GridBagConstraints.BELOW_BASELINE_LEADING
        this.cancelButton.toolTipText = "CANCELAR EL REGISTRO DEL CLIENTE"
        this.mainPanel.add(this.cancelButton,gbc)

        this.pack()
    }


}