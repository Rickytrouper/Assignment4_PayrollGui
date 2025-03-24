import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HourlyEmployee extends Employee {
    private final double wage;
    private final double hours;

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, double wage, double hours)
            throws InvalidDataException {
        super(firstName, lastName, socialSecurityNumber);
        if (wage < 0 || hours < 0) {
            throw new InvalidDataException("Wage and hours cannot be negative.");
        }
        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public double getPaymentAmount() {
        return wage * hours;
    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        double paymentAmount = getPaymentAmount();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        writer.write("Hourly Employee: " + getFirstName() + " " + getLastName());
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