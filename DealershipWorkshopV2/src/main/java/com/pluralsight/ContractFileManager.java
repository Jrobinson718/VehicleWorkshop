package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractFileManager {
    private static final String dataFolder = "data/contracts/";
    private static final String SALES_CONTRACT_FILE = dataFolder + "salesContract.csv";
    private static final String LEASE_CONTRACT_FILE = dataFolder + "leaseContract.csv";

    public ContractFileManager() {
        new File(dataFolder).mkdirs();
    }

    // Method to save contract based on file type
    public void saveContract (Contract contract) {
         String fileName;

         if (contract instanceof SalesContract) {
             fileName = SALES_CONTRACT_FILE;
         } else if (contract instanceof LeaseContract) {
             fileName = LEASE_CONTRACT_FILE;
         }else {
             System.out.println("\nUnknown contract type. Cannot save contract.");
             return;
         }

         try(FileWriter fw = new FileWriter(fileName, true);
             PrintWriter pw = new PrintWriter(fw)) {

             pw.println(contract.toString());

             System.out.println("\nContract saved successfully to " + fileName);

         }catch (IOException e) {
             System.out.println("\nError saving contract to " + fileName + ": " + e.getMessage());
         }

    }
}
