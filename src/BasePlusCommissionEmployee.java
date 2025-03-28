//Abbligale Ried - ID#2301010638
//Kwame Harriott - ID#2301011566
//Shamoy Shea -  ID#2201011505
//Ricardo Wright - ID#2201010833

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePlusCommissionEmployee extends CommissionEmployee {
    private final double baseSalary;

    // initialized Constructor
    public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                                      double grossSales, double commissionRate, double baseSalary)
            throws InvalidDataException {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
        if (baseSalary < 0) {
            throw new InvalidDataException("Base salary cannot be negative.");
        }
        this.baseSalary = baseSalary;
    }
    // overwrite get Payment method
    @Override
    public double getPaymentAmount() {
        return baseSalary + super.getPaymentAmount();
    }

    // overwrite the write to file method
    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        double paymentAmount = getPaymentAmount();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        writer.write("Base Plus Commission Employee: " + getFirstName() + " " + getLastName());
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
