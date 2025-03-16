import java.io.IOException;
import java.io.BufferedWriter;

public interface Payable {
    double getPaymentAmount();
    void writeToFile(BufferedWriter writer) throws IOException; // Updated to use BufferedWriter
}