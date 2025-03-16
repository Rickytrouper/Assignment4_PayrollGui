import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

public abstract class Employee implements Payable {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber) throws InvalidDataException {
        if (firstName == null || lastName == null || socialSecurityNumber == null) {
            throw new InvalidDataException("Employee data cannot be null.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    @Override
    public abstract double getPaymentAmount();

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        writer.write(String.format("Employee Name: %s %s, SSN: %s, Payment Amount: %.2f, Date of Payment: %s%n",
                firstName, lastName, socialSecurityNumber, getPaymentAmount(), LocalDate.now()));
    }
}

   /* public Object getEmployeeType() {
        return null;
    }

    public abstract void writeToFile(BufferedWriter writer) throws IOException;
}*/
