import java.io.BufferedWriter;
import java.io.IOException;


public class Invoice implements Payable {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;
    private String contractorName;

    public Invoice(String contractorName, String partNumber, String partDescription, int quantity, double pricePerItem)
            throws InvalidDataException {
        if (quantity < 0 || pricePerItem < 0) {
            throw new InvalidDataException("Quantity and price per item cannot be negative.");
        }
        this.contractorName = contractorName;
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public String getContractorName() {
        return contractorName;
    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        writer.write("Contractor Name: %s, Part Number: %s, Description: %s, Quantity: %d, " +
                        "Price Per Item: %.2f, Payment Amount: %.2f, Date of Payment: %s%n"
        );
    }
}

