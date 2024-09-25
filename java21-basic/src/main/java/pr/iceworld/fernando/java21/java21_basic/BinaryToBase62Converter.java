package pr.iceworld.fernando.java21.java21_basic;

import java.math.BigInteger;

public class BinaryToBase62Converter {

    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE62 = 62;

    // Convert a string to a binary string
    public static String stringToBinary(String input) {
        StringBuilder binaryStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            String binaryChar = Integer.toBinaryString(ch);
            binaryStr.append(String.format("%8s", binaryChar).replaceAll(" ", "0"));
        }
        return binaryStr.toString();
    }

    // Convert a binary string to a base62 string
    public static String binaryToBase62(String binaryStr) {
        BigInteger decimalValue = new BigInteger(binaryStr, 2);
        StringBuilder base62Str = new StringBuilder();

        while (decimalValue.compareTo(BigInteger.ZERO) > 0) {
            int remainder = decimalValue.mod(BigInteger.valueOf(BASE62)).intValue();
            base62Str.append(BASE62_CHARS.charAt(remainder));
            decimalValue = decimalValue.divide(BigInteger.valueOf(BASE62));
        }

        return base62Str.reverse().toString();
    }

    // Convert a base62 string back to a binary string
    public static String base62ToBinary(String base62Str) {
        BigInteger decimalValue = BigInteger.ZERO;

        for (char ch : base62Str.toCharArray()) {
            decimalValue = decimalValue.multiply(BigInteger.valueOf(BASE62))
                    .add(BigInteger.valueOf(BASE62_CHARS.indexOf(ch)));
        }

        return decimalValue.toString(2); // Convert decimal value back to binary
    }

    public static void main(String[] args) {
        String input = "100013080536";

        // Step 1: Convert string to binary
        String binaryStr = stringToBinary(input);
        System.out.println("Binary Representation: " + binaryStr);

        // Step 2: Convert binary to base62
        String base62Str = binaryToBase62(binaryStr);
        System.out.println("Base62 Representation: " + base62Str);

        // Step 3: Convert base62 back to binary
        String backToBinary = base62ToBinary(base62Str);
        System.out.println("Back to Binary: " + backToBinary);
    }
}
