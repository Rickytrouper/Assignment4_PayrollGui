//Abbligale Ried - ID#2301010638
//Kwame Harriott - ID#2301011566
//Shamoy Shea -  ID#2201011505
//Ricardo Wright - ID#2201010833

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalariedEmployee extends Employee {
    private final double weeklySalary;
 // initialize constructor
    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary)
            throws InvalidDataException {
        super(firstName, lastName, socialSecurityNumber);
        if (weeklySalary < 0) {
            throw new InvalidDataException("Salary cannot be negative.");
        }
        this.weeklySalary = weeklySalary;
    }
    // overwrite payable
    @Override
    public double getPaymentAmount() {
        return weeklySalary;
    }

    // overwrite write to file
    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        double paymentAmount = getPaymentAmount();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        writer.write("Salaried Employee: " + getFirstName() + " " + getLastName());
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