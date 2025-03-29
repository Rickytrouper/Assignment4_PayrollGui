//Abbligale Ried - ID#2301010638
//Kwame Harriott - ID#2301011566
//Shamoy Shea -  ID#2201011505
//Ricardo Wright - ID#2201010833

import java.io.IOException;
import java.io.BufferedWriter;
// initialize constructor
public interface Payable {
    double getPaymentAmount();
    void writeToFile(BufferedWriter writer) throws IOException; // implementing  BufferedWriter
}