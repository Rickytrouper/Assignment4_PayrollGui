//Abbligale Ried - ID#2301010638
//Kwame Harriott - ID#2301011566
//Shamoy Shea -  ID#2201011505
//Ricardo Wright - ID#2201010833

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommissionEmployee extends Employee {
    private final double grossSales;
    private final double commissionRate;

    // initialize Constructor
    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                              double grossSales, double commissionRate) throws InvalidDataException {
        super(firstName, lastName, socialSecurityNumber);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    // overwite getpaymnet method
    @Override
    public double getPaymentAmount() {
        return grossSales * commissionRate;
    }

    // overwrite write to file method
    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        double paymentAmount = getPaymentAmount();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        writer.write(" Commissioned Employee: " + getFirstName() + " " + getLastName());
        writer.newLine();
        writer.write("SSN: " + getSocialSecurityNumber());
        writer.newLine();
        writer.write("Payment Amount: $" + paymentAmount);
        writer.newLine();
        writer.write("Date of Payment: " + date);
        writer.newLine();
        writer.write("------------------------------");
        writer.newLine();
    }
}