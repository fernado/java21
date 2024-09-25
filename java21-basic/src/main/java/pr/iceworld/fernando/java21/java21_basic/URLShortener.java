package pr.iceworld.fernando.java21.java21_basic;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    // Base62字符集
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = BASE62.length();
    
    // 用于存储短URL和长URL的映射
    private Map<String, String> urlMap = new HashMap<>();
    private Map<String, String> reverseMap = new HashMap<>();
    
    private static long urlCounter = 10001308L;

    // 将长URL转换为短URL
    public String shortenURL(String longUrl) {
        if (reverseMap.containsKey(longUrl)) {
            return reverseMap.get(longUrl);  // 如果已存在，则直接返回之前生成的短链接
        }

        // 生成唯一ID
        String shortUrl = encode(urlCounter++);
        urlMap.put(shortUrl, longUrl);  // 存储短URL -> 长URL的映射
        reverseMap.put(longUrl, shortUrl);  // 也存储长URL -> 短URL的映射
        return shortUrl;
    }

    // 将短URL还原为长URL
    public String expandURL(String shortUrl) {
        return urlMap.getOrDefault(shortUrl, "URL not found!");
    }

    // 将数字编码为Base62的短字符串
    private String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(BASE62.charAt((int) (num % BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();
        
        String longUrl = "https://www.example.com/some-long-url-that-needs-shortening";
        
        // 生成短URL
        String shortUrl = shortener.shortenURL(longUrl);
        System.out.println("Short URL: " + shortUrl);
        
        // 还原长URL
        String expandedUrl = shortener.expandURL(shortUrl);
        System.out.println("Expanded URL: " + expandedUrl);
    }
}
