package nl.hava.dapaj.bankapp.utils;

import java.math.BigInteger;

public class IBANGenerator {
    private static final String COUNTRY_CODE = "NL";
    private static final String dapajBankCode = "DPAJ";
    private static final int MAX_LENGTH_BANK_ACCOUNT = 10;
    private static int incrementalID;

    public static int getIncrementalID() {
        return incrementalID;
    }

    private static String makeBBAN() {
        String temp = String.valueOf(incrementalID);
        int remainingLength = MAX_LENGTH_BANK_ACCOUNT - temp.length();

        return "0".repeat(Math.max(0, remainingLength)) + (incrementalID);
    }


    private static String makeControlNumber() {
        String dpagToNumber = "13251019";
        String nlToNumber = "2321";
        String numericReference = dpagToNumber + makeBBAN() + nlToNumber + "00";

        BigInteger numRef = new BigInteger(numericReference);
        BigInteger divisor = new BigInteger("97");
        BigInteger numMod = numRef.mod(divisor);
        BigInteger controlNumber = divisor.subtract(numMod);

        return String.valueOf(controlNumber);
    }

    public static String generateIBAN() {
        incrementalID++;
        return COUNTRY_CODE + makeControlNumber() + dapajBankCode + makeBBAN();
    }


}
