import java.io.BufferedWriter;
import java.io.IOException;

public class CommissionEmployee extends Employee {
    private final double grossSales;
    private final double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate)
            throws InvalidDataException {
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
        writer.write(String.format("Commission Employee: %s %s\nSSN: %s\nGross Sales: $%.2f\nCommission Rate: %.2f",
                getFirstName(), getLastName(), getSocialSecurityNumber(), grossSales, commissionRate));
    }

}