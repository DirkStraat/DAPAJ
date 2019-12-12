package nl.hava.dapaj.bankapp.utils;

import java.math.BigInteger;

/**
 * @author Pedro Esgueira
 * This class generates a sequencial (Dutch) IBAN.
 * A Dutch IBAN is composed of
 *              a two letter country code,
 *              a four letter bank code
 *              a 10 digit basic bank account number (BBAN)
 */

public class IBANGenerator {
    private static final String COUNTRY_CODE = "NL";
    private static final String dapajBankCode = "DPAJ";
    private static final int MAX_LENGTH_BANK_ACCOUNT = 10;
    private static int incrementalID = 123456; // initial starting point to increment bank account number

    /**
     *
     * @return      A 10 digit numeric String composed of
     *                      a banck account number
     *                      prefixed with zeros if necessary so that it is 10 characters long
     */
    private static String makeBBAN() {
        String temp = String.valueOf(incrementalID);
        int remainingLength = MAX_LENGTH_BANK_ACCOUNT - temp.length();

        // add zeroes at the beginning of the account number, until it reaches a length of 10
        return "0".repeat(Math.max(0, remainingLength)) + (incrementalID);
    }


    /**
     * This method follows the dutch guidelines to generating a two number control check:
     *      It first takes the sequence of bank code + BBAN + country code + adds two zeroes at the end
     *      Then proceeds to transform letters to their equivalent numeric number (A = 10, ... Z = 35)
     *      And divides that number by the module of 97
     *      And finally subtracts that result from 97
     *
     * @return      Returns the String value of the end result
     */
    private static String makeControlNumber() {
        String dpagToNumber = "13251019";
        String nlToNumber = "2321";
        String numericReference = dpagToNumber + makeBBAN() + nlToNumber + "00";

        BigInteger numRef = new BigInteger(numericReference);
        BigInteger divisor = new BigInteger("97");
        BigInteger numMod = numRef.mod(divisor);
        BigInteger controlNumber = divisor.subtract(numMod);
        if (String.valueOf(controlNumber).length() == 1){
            return String.format("0"+String.valueOf(controlNumber));
        } else return String.valueOf(controlNumber);
    }

    /**
     *
     * @return     the String of an IBAN and increments the basic account number
     */
    public static String generateIBAN() {
        incrementalID++;
        return COUNTRY_CODE + makeControlNumber() + dapajBankCode + makeBBAN();
    }

}
