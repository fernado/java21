package pr.iceworld.fernando.java21.java21_basic;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class ShortUrlLink {


    static String longUrl = "https://mp.csdn.net/mp_blog/manage/article?spm=1000.2115.3001.5448";

    public static void main(String[] args) {

        longUrl.getBytes(StandardCharsets.UTF_8);

        System.out.println(binaryToStr("ab".getBytes(StandardCharsets.UTF_8)));

    }

    static String binaryToStr(byte[] bytes) {
        StringBuilder str = new StringBuilder(64);
        for (int i = 0; i < bytes.length; i++) {
            str.append(String.format("%02X", bytes[i]));
        }
        return str.toString();
    }

}
