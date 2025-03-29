//Abbligale Ried - ID#2301010638
//Kwame Harriott - ID#2301011566
//Shamoy Shea -  ID#2201011505
//Ricardo Wright - ID#2201010833

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;

// implement Abstract employee class
public abstract class Employee implements Payable {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber) throws InvalidDataException {
        if (firstName == null || lastName == null || socialSecurityNumber == null) {
            throw new InvalidDataException("Employee data cannot be null.");
        }

        // initializing setters
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

        // initializing getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    // overwriting payable
    @Override
    public abstract double getPaymentAmount();

      // overwriting write to file
    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        writer.write(String.format("Employee Name: %s %s, SSN: %s, Payment Amount: %.2f, Date of Payment: %s%n",
                firstName, lastName, socialSecurityNumber, getPaymentAmount(), LocalDate.now()));
    }
}

