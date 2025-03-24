import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePlusCommissionEmployee extends CommissionEmployee {
    private final double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                                      double grossSales, double commissionRate, double baseSalary)
            throws InvalidDataException {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
        if (baseSalary < 0) {
            throw new InvalidDataException("Base salary cannot be negative.");
        }
        this.baseSalary = baseSalary;
    }

    @Override
    public double getPaymentAmount() {
        return baseSalary + super.getPaymentAmount();
    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        double paymentAmount = getPaymentAmount();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        writer.write("Employee: " + getFirstName() + " " + getLastName());
        writer.newLine();
        writer.write("SSN: " + getSocialSecurityNumber());
        writer.newLine();
        writer.write("Payment Amount: $" + paymentAmount);
        writer.newLine();
        writer.write("Date of Payment: " + date);
        writer.newLine();
        writer.write("Base Salary: $" + baseSalary);
        writer.newLine();
        writer.write("------------------------------");
        writer.newLine();
    }
    }
