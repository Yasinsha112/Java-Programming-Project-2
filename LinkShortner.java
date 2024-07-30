import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    private Map<String, String> urlMap;
    private Map<String, String> shortUrlMap;

    public LinkShortener() {
        urlMap = new HashMap<>();
        shortUrlMap = new HashMap<>();
    }

    public String shortenUrl(String longUrl) {
        if (urlMap.containsKey(longUrl)) {
            return urlMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        while (shortUrlMap.containsKey(shortUrl)) {
            shortUrl = generateShortUrl();
        }

        urlMap.put(longUrl, shortUrl);
        shortUrlMap.put(shortUrl, longUrl);

        return shortUrl;
    }

    public String expandUrl(String shortUrl) {
        return shortUrlMap.getOrDefault(shortUrl, "Short URL not found!");
    }

    private String generateShortUrl() {
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return shortUrl.toString();
    }

    public static void main(String[] args) {
        LinkShortener shortener = new LinkShortener();
        String longUrl = "https://www.example.com/some/long/url";
        String shortUrl = shortener.shortenUrl(longUrl);
        System.out.println("Short URL: " + shortUrl);

        String expandedUrl = shortener.expandUrl(shortUrl);
        System.out.println("Expanded URL: " + expandedUrl);
    }
}
