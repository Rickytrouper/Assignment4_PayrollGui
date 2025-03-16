import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("ALL")
public class Payroll {
    private JPanel mainPanel;
    private JTextField JtFstName, JtLstName, JtSSN, JtGrossSales, JtCommissionRate, JtWage, JtHours, JtWeeklySalary, JtEmpType;
    private JTextField JtPartNumber, JtPartDescription, JtQuantity, JtPricePerItem, JtContractorName;
    private JTable JtblEmpRecords, JtblInvoiceRecords;
    private JButton JbAddEmp, JbRemoveEmp, JbAddInvoice, JbGeneratePayStub;
    private JTextField JtBaseSalary;
    private DefaultTableModel tableModel, invoiceTableModel;
    private ArrayList<Payable> employees;

    public Payroll() {
        employees = new ArrayList<>();

        // Initialize the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(25, 2)); // Adjusted row count to match form

        // Initialize text fields
        JtFstName = new JTextField();
        JtLstName = new JTextField();
        JtSSN = new JTextField();
        JtGrossSales = new JTextField();
        JtCommissionRate = new JTextField();
        JtWage = new JTextField();
        JtHours = new JTextField();
        JtWeeklySalary = new JTextField();
        JtBaseSalary = new JTextField();
        JtEmpType = new JTextField();
        JtPartNumber = new JTextField();
        JtPartDescription = new JTextField();
        JtQuantity = new JTextField();
        JtPricePerItem = new JTextField();
        JtContractorName = new JTextField();

        // Initialize labels
        mainPanel.add(new JLabel("First Name:"));
        mainPanel.add(JtFstName);
        mainPanel.add(new JLabel("Last Name:"));
        mainPanel.add(JtLstName);
        mainPanel.add(new JLabel("SSN #:"));
        mainPanel.add(JtSSN);
        mainPanel.add(new JLabel("Gross Sales:"));
        mainPanel.add(JtGrossSales);
        mainPanel.add(new JLabel("Commission Rate:"));
        mainPanel.add(JtCommissionRate);
        mainPanel.add(new JLabel("Base Salary"));
        mainPanel.add(JtBaseSalary);
        mainPanel.add(new JLabel("Hourly Wage:"));
        mainPanel.add(JtWage);
        mainPanel.add(new JLabel("Hours Worked:"));
        mainPanel.add(JtHours);
        mainPanel.add(new JLabel("Weekly Salary:"));
        mainPanel.add(JtWeeklySalary);
        mainPanel.add(new JLabel("Employment Type:"));
        mainPanel.add(JtEmpType);
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
        JbGeneratePayStub = new JButton("Generate Paystub");

        // Initialize table for employee records
        String[] employeeColumnNames = {"Name", "SSN", "Payment"};
        tableModel = new DefaultTableModel(employeeColumnNames, 0);
        JtblEmpRecords = new JTable(tableModel);
        JtblEmpRecords.setFillsViewportHeight(true); // Ensure the table fills the viewport

// Initialize table for invoice records
        String[] invoiceColumnNames = {"Contractor Name", "Part Number", "Description", "Quantity", "Price", "Total"};
        invoiceTableModel = new DefaultTableModel(invoiceColumnNames, 0);
        JtblInvoiceRecords = new JTable(invoiceTableModel);
        JtblInvoiceRecords.setFillsViewportHeight(true); // Ensure the table fills the viewport

// Create JScrollPane for employee table with preferred size
        JScrollPane employeeScrollPane = new JScrollPane(JtblEmpRecords);
        employeeScrollPane.setPreferredSize(new Dimension(700, 200)); // Set preferred size (width, height)

// Create JScrollPane for invoice table with preferred size
        JScrollPane invoiceScrollPane = new JScrollPane(JtblInvoiceRecords);
        invoiceScrollPane.setPreferredSize(new Dimension(700, 200)); // Set preferred size (width, height)

// Add components to the panel
        mainPanel.add(JbAddEmp);
        mainPanel.add(JbRemoveEmp);
        mainPanel.add(JbAddInvoice);
        mainPanel.add(JbGeneratePayStub);
        mainPanel.add(employeeScrollPane); // Add the employee table scroll pane
        mainPanel.add(invoiceScrollPane); // Add the invoice table scroll pane

        // Initialize frame
        JFrame frame = new JFrame("Payroll Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
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

    private void addEmployee() {
        String firstName = JtFstName.getText().trim();
        String lastName = JtLstName.getText().trim();
        String ssn = JtSSN.getText().trim();
        String empType = JtEmpType.getText().trim();
        double payment = 0;

        // Validate inputs
        StringBuilder errorMessage = new StringBuilder();
        if (firstName.isEmpty()) {
            errorMessage.append("First Name is required.\n");
        }
        if (lastName.isEmpty()) {
            errorMessage.append("Last Name is required.\n");
        }
        if (ssn.isEmpty()) {
            errorMessage.append("SSN is required.\n");
        }
        if (empType.isEmpty()) {
            errorMessage.append("Employment Type is required.\n");
        }

        // Show validation errors if any
        if (errorMessage.length() > 0) {
            JOptionPane.showMessageDialog(mainPanel, errorMessage.toString(), "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidDataException e) {
            JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeEmployee() {
        int selectedRow = JtblEmpRecords.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            employees.remove(selectedRow);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Please select an employee to remove.");
        }
    }

    private void addInvoice() {
        String partNumber = JtPartNumber.getText().trim();
        String partDescription = JtPartDescription.getText().trim();
        String quantityText = JtQuantity.getText().trim();
        String priceText = JtPricePerItem.getText().trim();
        String contractorName = JtContractorName.getText().trim();

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
            clearInvoiceFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generatePayStub() {
        int selectedEmployeeRow = JtblEmpRecords.getSelectedRow(); // Get the selected employee row
        int selectedInvoiceRow = JtblInvoiceRecords.getSelectedRow(); // Get the selected invoice row

        if (selectedEmployeeRow != -1) {
            // Generate Pay Stub
            Payable item = employees.get(selectedEmployeeRow); // Get the selected Payable item

            if (item instanceof Employee) { // Check if item is an Employee
                Employee employee = (Employee) item; // Cast to Employee

                // Retrieve employee information
                String firstName = employee.getFirstName();
                String lastName = employee.getLastName();
                String ssn = employee.getSocialSecurityNumber();
                double paymentAmount = employee.getPaymentAmount();
                String dateOfPayment = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                // Create the pay stub string
                String payStub = String.format("Employee: %s %s\nSSN: %s\nPayment Amount: $%.2f\nDate of Payment: %s",
                        firstName, lastName, ssn, paymentAmount, dateOfPayment);

                // Write pay stub to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("paystub.txt", true))) {
                    // Write header for pay stub
                    writer.write("=========================");
                    writer.newLine();
                    writer.write("          PAY STUB");
                    writer.newLine();
                    writer.write("=========================");
                    writer.newLine();

                    // Write employee details
                    writer.write(payStub);
                    writer.newLine(); // Add a new line after the pay stub details

                    JOptionPane.showMessageDialog(null, payStub, "Pay Stub", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selected item is not an employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (selectedInvoiceRow != -1) {
            // Generate Invoice
            String contractorName = (String) invoiceTableModel.getValueAt(selectedInvoiceRow, 0);
            String partNumber = (String) invoiceTableModel.getValueAt(selectedInvoiceRow, 1);
            String partDescription = (String) invoiceTableModel.getValueAt(selectedInvoiceRow, 2);
            int quantity = (Integer) invoiceTableModel.getValueAt(selectedInvoiceRow, 3);
            double pricePerItem = (Double) invoiceTableModel.getValueAt(selectedInvoiceRow, 4);
            double totalAmount = (Double) invoiceTableModel.getValueAt(selectedInvoiceRow, 5);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("paystub.txt", true))) {
                writer.write("=========================");
                writer.newLine();
                writer.write("        INVOICE");
                writer.newLine();
                writer.write("=========================");
                writer.newLine();
                writer.write("Contractor: " + contractorName);
                writer.newLine();
                writer.write("Part Number: " + partNumber);
                writer.newLine();
                writer.write("Description: " + partDescription);
                writer.newLine();
                writer.write("Quantity: " + quantity);
                writer.newLine();
                writer.write("Price Per Item: $" + pricePerItem);
                writer.newLine();
                writer.write("Total Amount: $" + totalAmount);
                writer.newLine();
                writer.write("Date of Payment: " + date);
                writer.newLine();
                writer.write("-------------------------");
                writer.newLine();
                JOptionPane.showMessageDialog(mainPanel, "Invoice generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Error writing to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Please select an employee or an invoice to generate.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        JtFstName.setText("");
        JtLstName.setText("");
        JtSSN.setText("");
        JtGrossSales.setText("");
        JtCommissionRate.setText("");
        JtWage.setText("");
        JtHours.setText("");
        JtWeeklySalary.setText("");
        JtEmpType.setText("");
        JtBaseSalary.setText(""); // Clear Base Salary field
    }

    private void clearInvoiceFields() {
        JtPartNumber.setText("");
        JtPartDescription.setText("");
        JtQuantity.setText("");
        JtPricePerItem.setText("");
        JtContractorName.setText(""); // Clear contractor name field
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Payroll::new);
    }
}