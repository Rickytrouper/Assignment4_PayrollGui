import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice implements Payable {
    private final String contractorName;
    private final String partNumber;
    private final String partDescription;
    private final int quantity;
    private final double pricePerItem;

    public Invoice(String contractorName, String partNumber, String partDescription, int quantity, double pricePerItem) {
        this.contractorName = contractorName;
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem; // Total amount due for the invoice
    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
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
        writer.write("Total Amount: $" + getPaymentAmount());
        writer.newLine();
        writer.write("Date of Payment: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        writer.newLine();
        writer.write("-------------------------");
        writer.newLine();
    }
}