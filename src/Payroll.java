//Abbligale Ried - ID#2301010638
//Kwame Harriott - ID#2301011566
//Shamoy Shea -  ID#2201011505
//Ricardo Wright - ID#2201010833

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Payroll {
    private JPanel mainPanel;
    private JTextField JtFstName, JtLstName, JtSSN, JtGrossSales, JtCommissionRate, JtWage, JtHours, JtWeeklySalary, JtBaseSalary;
    private JTextField JtPartNumber, JtPartDescription, JtQuantity, JtPricePerItem, JtContractorName;
    private JTable JtblEmpRecords, JtblInvoiceRecords;
    private JButton JbAddEmp, JbRemoveEmp, JbAddInvoice, JbGeneratePayStub;
    private JScrollPane JsEmployeeScrollPane;
    private JList<String> listEmpType; // Changed to JList<String>
    private JScrollPane JsInvoiceScrollPane;
    private JLabel JlEmpInfoHeader;
    private JLabel JlInvoiceInfoHeader;
    private final DefaultListModel<String> empTypeModel; // Model list used to manage the employment types
    private final DefaultTableModel tableModel;
    private final DefaultTableModel invoiceTableModel; // Model table used to manage save data
    private final ArrayList<Payable> employees;

        public Payroll() {
        employees = new ArrayList<>();

        // Initialize the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 1, 1)); // 0 rows (dynamic), 2 columns, horizontal and vertical gaps

        // Initialize text fields with placeholders
        JtFstName = createTextField("First Name");
        JtLstName = createTextField("Last Name");
        JtSSN = createTextField("SSN (9 digits)");
        JtGrossSales = createTextField("Gross Sales");
        JtCommissionRate = createTextField("Commission Rate");
        JtWage = createTextField("Hourly Wage");
        JtHours = createTextField("Hours Worked");
        JtWeeklySalary = createTextField("Weekly Salary");
        JtBaseSalary = createTextField("Base Salary");
        JtPartNumber = createTextField("Part Number");
        JtPartDescription = createTextField("Part Description");
        JtQuantity = createTextField("Quantity");
        JtPricePerItem = createTextField("Price Per Item");
        JtContractorName = createTextField("Contractor Name");

        // Initialize the DefaultListModel for employment types
        empTypeModel = new DefaultListModel<>();
        empTypeModel.addElement("Commission");
        empTypeModel.addElement("Hourly");
        empTypeModel.addElement("Salaried");
        empTypeModel.addElement("BasePlusCommission");

        // Initialize the JList with the DefaultListModel
        listEmpType = new JList<>(empTypeModel);
        listEmpType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEmpType.setSelectedIndex(0);
        listEmpType.setVisibleRowCount(1);
        JScrollPane empTypeScrollPane = new JScrollPane(listEmpType); // Make the JList scrollable
        empTypeScrollPane.setPreferredSize(new Dimension(150, 15)); // Set preferred size for the scroll pane

        // Adding JLabels for headers above employee and invoice
        JlEmpInfoHeader = new JLabel("Employee Information", SwingConstants.CENTER);
        JlEmpInfoHeader.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(JlEmpInfoHeader);
        mainPanel.add(new JPanel()); // Adds a blank space

        // Add components to the panel
        mainPanel.add(new JLabel("First Name:"));
        mainPanel.add(JtFstName);
        mainPanel.add(new JLabel("Last Name:"));
        mainPanel.add(JtLstName);
        mainPanel.add(new JLabel("SSN #:"));
        mainPanel.add(JtSSN);
        mainPanel.add(new JLabel("Employment Type:")); // Label for employment type
        mainPanel.add(empTypeScrollPane); // Add the scroll pane with the JList
        mainPanel.add(new JLabel("Gross Sales:"));
        mainPanel.add(JtGrossSales);
        mainPanel.add(new JLabel("Commission Rate:"));
        mainPanel.add(JtCommissionRate);
        mainPanel.add(new JLabel("Base Salary:"));
        mainPanel.add(JtBaseSalary);
        mainPanel.add(new JLabel("Hourly Wage:"));
        mainPanel.add(JtWage);
        mainPanel.add(new JLabel("Hours Worked:"));
        mainPanel.add(JtHours);
        mainPanel.add(new JLabel("Weekly Salary:"));
        mainPanel.add(JtWeeklySalary);
        // Adding JLabel for invoice header
        JlInvoiceInfoHeader = new JLabel("Invoice Information", SwingConstants.CENTER);
        JlInvoiceInfoHeader.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(JlInvoiceInfoHeader);
        mainPanel.add(new JPanel()); // Adds a blank space
        mainPanel.add(new JLabel("Contractor Name:"));
        mainPanel.add(JtContractorName);
        mainPanel.add(new JLabel("Part Number:"));
        mainPanel.add(JtPartNumber);
        mainPanel.add(new JLabel("Part Description:"));
        mainPanel.add(JtPartDescription);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(JtQuantity);
        mainPanel.add(new JLabel("Price Per Item:"));
        mainPanel.add(JtPricePerItem);

        // Initialize buttons
        JbAddEmp = new JButton("Add Employee");
        JbRemoveEmp = new JButton("Remove Employee");
        JbAddInvoice = new JButton("Add Invoice");
        JbGeneratePayStub = new JButton("Generate Paystub/Invoice");


        // Initialize table for employee records
        String[] employeeColumnNames = {"Name", "SSN", "Payment"};
       tableModel = new DefaultTableModel(employeeColumnNames, 0); // No rows initially
       JtblEmpRecords = new JTable(tableModel);
       JtblEmpRecords.setFillsViewportHeight(true); // Ensure the table fills the viewport

       // Scroll pane for employee table
       JScrollPane JsEmployeeScrollPane = new JScrollPane(JtblEmpRecords);
       this.JsEmployeeScrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size for the scroll pane


       // Initialize table for invoice records
       String[] invoiceColumnNames = {"Contractor Name", "Part Number", "Description", "Quantity", "Price", "Total"};
       invoiceTableModel = new DefaultTableModel(invoiceColumnNames, 0); // No rows initially
       JtblInvoiceRecords = new JTable(invoiceTableModel);
       JtblInvoiceRecords.setFillsViewportHeight(true); // Ensure the table fills the viewport

       // Scroll pane for invoice table
       JScrollPane JsInvoiceScrollPane = new JScrollPane(JtblInvoiceRecords);
       this.JsInvoiceScrollPane.setPreferredSize(new Dimension(400,300 )); // Set preferred size for the scroll pane

        // Add components to the panel
        mainPanel.add(JbAddEmp);
        mainPanel.add(JbRemoveEmp);
        mainPanel.add(JbAddInvoice);
        mainPanel.add(JbGeneratePayStub);
        mainPanel.add(JsEmployeeScrollPane); // Add the employee table scroll pane
        mainPanel.add(JsInvoiceScrollPane); // Add the invoice table scroll pane

        // Initialize frame
        JFrame frame = new JFrame("Payroll Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 1000);
        frame.add(mainPanel);
        frame.setVisible(true);

        // Add action listeners for buttons
        JbAddEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        JbRemoveEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEmployee();
            }
        });

        JbAddInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInvoice();
            }
        });

        JbGeneratePayStub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePayStub();
            }
        });
    }
    // Utility method to create JTextField with placeholder text
    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.addMouseListener(new MouseAdapter() {

            // Clear placeholder when text field is clicked
            @Override
            public void mouseClicked(MouseEvent e) {

                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }
        });
        return textField;
    }
    // method to reset employee field once a record is submitted

    private void resetEmployeeFields() {
        JtFstName.setText("First Name");
        JtLstName.setText("Last Name");
        JtSSN.setText("SSN (9 digits)");
        JtGrossSales.setText("Gross Sales");
        JtCommissionRate.setText("Commission Rate");
        JtWage.setText("Hourly Wage");
        JtHours.setText("Hours Worked");
        JtWeeklySalary.setText("Weekly Salary");
        JtBaseSalary.setText("Base Salary");
    }

    // method to reset Invoice field once a record is submitted
    private void resetInvoiceFields() {
        JtContractorName.setText("Contractor Name");
        JtPartNumber.setText("Part Number");
        JtPartDescription.setText("Part Description");
        JtQuantity.setText("Quantity");
        JtPricePerItem.setText("Price Per Item");
    }
        // initializing instance of an employee
    private void addEmployee() {
        String firstName = JtFstName.getText().trim();
        String lastName = JtLstName.getText().trim();
        String ssn = JtSSN.getText().trim();
        String empType = listEmpType.getSelectedValue();
        double payment = 0;

        // Validate inputs for employee data
        StringBuilder errorMessage = new StringBuilder();
        if (firstName.isEmpty()) {
            errorMessage.append("First Name is required.\n");
        }
        if (lastName.isEmpty()) {
            errorMessage.append("Last Name is required.\n");
        }
        if (ssn.isEmpty()) {
            errorMessage.append("SSN is required.\n");
        } else if (ssn.length() != 9 || !ssn.matches("\\d{9}")) {
            errorMessage.append("SSN must be 9 digits.\n");
        }
        if (empType.isEmpty()) {
            errorMessage.append("Employment Type is required.\n");
        }

        // Show validation errors if any
        if (!errorMessage.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, errorMessage.toString(), "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
          // validation and error handling for each employee class
        try {
            if (empType.equalsIgnoreCase("Commission")) {
                double grossSales = Double.parseDouble(JtGrossSales.getText());
                double commissionRate = Double.parseDouble(JtCommissionRate.getText());
                payment = grossSales * commissionRate;
                CommissionEmployee employee = new CommissionEmployee(firstName, lastName, ssn, grossSales, commissionRate);
                employees.add(employee);
                tableModel.addRow(new Object[]{firstName + " " + lastName, ssn, payment});
            } else if (empType.equalsIgnoreCase("Hourly")) {
                double wage = Double.parseDouble(JtWage.getText());
                double hours = Double.parseDouble(JtHours.getText());
                payment = wage * hours;
                HourlyEmployee employee = new HourlyEmployee(firstName, lastName, ssn, wage, hours);
                employees.add(employee);
                tableModel.addRow(new Object[]{firstName + " " + lastName, ssn, payment});
            } else if (empType.equalsIgnoreCase("Salaried")) {
                double weeklySalary = Double.parseDouble(JtWeeklySalary.getText());
                payment = weeklySalary;
                SalariedEmployee employee = new SalariedEmployee(firstName, lastName, ssn, weeklySalary);
                employees.add(employee);
                tableModel.addRow(new Object[]{firstName + " " + lastName, ssn, payment});
            } else if (empType.equalsIgnoreCase("BasePlusCommission")) {
                double grossSales = Double.parseDouble(JtGrossSales.getText());
                double commissionRate = Double.parseDouble(JtCommissionRate.getText());
                double baseSalary = Double.parseDouble(JtBaseSalary.getText());
                BasePlusCommissionEmployee employee = new BasePlusCommissionEmployee(firstName, lastName, ssn, grossSales, commissionRate, baseSalary);
                payment = employee.getPaymentAmount();
                employees.add(employee);
                tableModel.addRow(new Object[]{firstName + " " + lastName, ssn, payment});
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Invalid employee type.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            resetEmployeeFields(); // Reset fields after adding employee

            // clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidDataException e) {
            JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        // initialize remove employee method
    private void removeEmployee() {
        int selectedRow = JtblEmpRecords.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            employees.remove(selectedRow);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Please select an employee to remove.");
        }
    }
        // initialize instance of a contractor add invoice
    private void addInvoice() {
        String partNumber = JtPartNumber.getText().trim();
        String partDescription = JtPartDescription.getText().trim();
        String quantityText = JtQuantity.getText().trim();
        String priceText = JtPricePerItem.getText().trim();
        String contractorName = JtContractorName.getText().trim();

        // add invoice error handling and validation
        if (partNumber.isEmpty() || partDescription.isEmpty() || quantityText.isEmpty() || priceText.isEmpty() || contractorName.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double pricePerItem = Double.parseDouble(priceText);
            if (quantity < 0 || pricePerItem < 0) {
                JOptionPane.showMessageDialog(mainPanel, "Quantity and price cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalAmount = quantity * pricePerItem;
            invoiceTableModel.addRow(new Object[]{contractorName, partNumber, partDescription, quantity, pricePerItem, totalAmount});

            // clearInvoiceFields();
            resetInvoiceFields(); // Reset invoice fields after adding

            // Show confirmation message after successful addition of the invoice
            JOptionPane.showMessageDialog(mainPanel, "Invoice added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generatePayStub() {
        int selectedEmployeeRow = JtblEmpRecords.getSelectedRow(); // Get the selected employee row
        int selectedInvoiceRow = JtblInvoiceRecords.getSelectedRow(); // Get the selected invoice row

        // Handle generation of pay stub
        if (selectedEmployeeRow != -1) {
            Payable item = employees.get(selectedEmployeeRow); // Get the selected payable item

            if (item instanceof Employee employee) { // Check if item is an Employee
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("paystub.txt", true))) {

                    // Header for writing the employee pay stub
                    writer.write("=========================");
                    writer.newLine();
                    writer.write("          PAY STUB");
                    writer.newLine();
                    writer.write("=========================");
                    writer.newLine();

                    // Call the write method from the Employee class
                    employee.writeToFile(writer);
                    JOptionPane.showMessageDialog(null, "Pay stub generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selected item is not an employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Handle generation of invoice
        if (selectedInvoiceRow != -1) {

            // Retrieve the invoice from the table model
            String contractorName = (String) invoiceTableModel.getValueAt(selectedInvoiceRow, 0);
            String partNumber = (String) invoiceTableModel.getValueAt(selectedInvoiceRow, 1);
            String partDescription = (String) invoiceTableModel.getValueAt(selectedInvoiceRow, 2);
            int quantity = (Integer) invoiceTableModel.getValueAt(selectedInvoiceRow, 3);
            double pricePerItem = (Double) invoiceTableModel.getValueAt(selectedInvoiceRow, 4);

            Payable invoice = new Invoice(contractorName, partNumber, partDescription, quantity, pricePerItem); // Create a new Invoice object

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("paystub.txt", true))) {

                // Header for writing the invoice
                writer.write("=========================");
                writer.newLine();
                writer.write("          INVOICE");
                writer.newLine();
                writer.write("=========================");
                writer.newLine();

                // Call the write method from the Invoice class
                invoice.writeToFile(writer);
                JOptionPane.showMessageDialog(mainPanel, "Invoice generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Error writing to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // If neither row is selected
        if (selectedEmployeeRow == -1 && selectedInvoiceRow == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select an employee or an invoice to generate.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Payroll::new);
    }
}