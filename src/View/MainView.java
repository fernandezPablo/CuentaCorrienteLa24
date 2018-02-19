package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by pablo on 15/02/2018.
 */
public class MainView {

    private JPanel MainPanel;
    private JLabel title;
    private JLabel turnInfo;
    private int turnNumber;
    private Date time;
    private JTable customersTable;
    private JScrollPane tableScroll;
    private DefaultTableModel customersModel;

    public MainView() {
        this.customersModel = new DefaultTableModel(new Object[]{"Nombre","Saldo"},0 );
        this.time = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm☺");
        this.turnInfo.setText(dateFormat.format(time));
        this.initComponents();
    }

    private void initComponents(){

        JFrame frame = new JFrame("MainView");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600,600));
        frame.setMinimumSize(new Dimension(600,600));
        frame.setTitle("SISTEMA DE CUENTA CORRIENTE - LA 24 GNC");
        frame.setIconImage(new ImageIcon(getClass().getResource(
                "/Images/icon-la-24.png")).getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.customersTable.setModel(this.customersModel);
    }

    public static void main(String[] args) {
       MainView mainView = new MainView();
    }

}
