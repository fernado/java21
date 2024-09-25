package pr.iceworld.fernando.java21.java21_basic;

import java.math.BigInteger;

public class BinaryToBase94 {
    private static final String BASE_94_CHARS =
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#$";
    private static final int SIZE = BASE_94_CHARS.length();

    // 将字符串转换为二进制表示
    public static String toBinaryString(String input) {
        StringBuilder binaryString = new StringBuilder();
        for (char c : input.toCharArray()) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(c))
                .replace(' ', '0'));
        }
        return binaryString.toString();
    }

    // 将二进制转换为128进制字符串
    public static String toBase94(String binaryString) {
        BigInteger bigInteger = new BigInteger(binaryString, 2); // 解析为大整数
        StringBuilder base128String = new StringBuilder();
        
        // 循环除以128并将余数转换为字符
        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            int remainder = bigInteger.mod(BigInteger.valueOf(SIZE)).intValue();
            base128String.append(BASE_94_CHARS.charAt(remainder));
            bigInteger = bigInteger.divide(BigInteger.valueOf(SIZE));
        }

        return base128String.reverse().toString();
    }


    public static void main(String[] args) {
        String original = "Hello, World!";
        // Step 1: 转换为二进制串
        String binaryString = toBinaryString(original);
        System.out.println("Binary Representation: " + binaryString);

        // Step 2: 转换为128进制
        String base128String = toBase94(binaryString);
        System.out.println("128 Base Representation: " + base128String);
    }
}
