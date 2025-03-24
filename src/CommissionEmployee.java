import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommissionEmployee extends Employee {
    private final double grossSales;
    private final double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                              double grossSales, double commissionRate) throws InvalidDataException {
        super(firstName, lastName, socialSecurityNumber);
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double getPaymentAmount() {
        return grossSales * commissionRate;
    }

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