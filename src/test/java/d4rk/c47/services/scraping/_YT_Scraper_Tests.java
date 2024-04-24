package d4rk.c47.services.scraping;


import d4rk.c47.tunecraft.services.scraping._YT_Scraper;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class _YT_Scraper_Tests {

    @Test
    void testBaseCaseExtractToken() {
        String testUrl = "https://www.youtube.com/watch?v=T0k3n";
        String testUrlToken = "T0k3n";

        String token = _YT_Scraper.getVideoId(testUrl);

        assertEquals(testUrlToken, token);
    }

    @Test
    void testBaseCaseExtractTokenInAList() {
        String testUrl = "https://www.youtube.com/watch?v=T0k3n&list=1is7&start_radio=1";
        String testUrlToken = "T0k3n";

        String token = _YT_Scraper.getVideoId(testUrl);

        assertEquals(testUrlToken, token);
    }

    @Test
    void testBaseCaseExtractTokenInEmbeddedFormat() {
        String testUrl = "https://www.youtube.com/embed/T0k3n";
        String testUrlToken = "T0k3n";

        String token = _YT_Scraper.getVideoId(testUrl);

        assertEquals(testUrlToken, token);
    }

    @Test
    void testBaseCaseShortUrlExtractToken() {
        String testUrl = "https://youtu.be/T0k3n";
        String testUrlToken = "T0k3n";

        String token = _YT_Scraper.getVideoId(testUrl);

        assertEquals(testUrlToken, token);
    }

    @Test
    void testBaseCaseShortUrlExtractTokenInAList() {
        String testUrl = "https://youtu.be/T0k3n?list=l157";
        String testUrlToken = "T0k3n";

        String token = _YT_Scraper.getVideoId(testUrl);

        assertEquals(testUrlToken, token);
    }

    @Test
    void testForEmptyTokensInsideOfURL() {
        String testUrl = "https://www.youtube.com/watch?v=&list=1is7&start_radio=1";

        assertThrows(IllegalArgumentException.class, () -> {
            _YT_Scraper.getVideoId(testUrl);
        });
    }

    @Test
    void testForSpecialCharactersInsideOfTheUrl() {
        String testUrl = "https://www.youtube.com/†oken";

        assertThrows(IllegalArgumentException.class, () -> {
            _YT_Scraper.getVideoId(testUrl);
        });
    }
}
