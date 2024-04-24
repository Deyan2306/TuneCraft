package d4rk.c47.tunecraft.services.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static d4rk.c47.tunecraft.enums._URLs.YOUTUBE_URL;
import static d4rk.c47.tunecraft.enums._URLs.YOUTUBE_URL_SHORTENED;

public class _YT_Tokenizer {

    private static String INVALID_URL = "The provided url is invalid!";

    public Document fetchWebPage(String url) throws IOException {
        StringBuilder link = new StringBuilder(url);

        if (!url.contains(YOUTUBE_URL)) {
            link.append(YOUTUBE_URL);
            if (url.charAt(0) != '/') {
                link.append('/');
            }
            link.append(url);
        }

        return Jsoup.connect(link.toString()).get();
    }

    /**
     * Function for quick YouTube token grepping.
     * It matches the token based on a few different patterns
     * @param url - URL to the YouTube video, no matter the format.
     *            For the current implementation it has to include some initials
     *            to specify that the token is from YouTube.
     * @throws IllegalArgumentException - If the specified url is not from YouTube. Make sure
     *            that you have included a path, that specifies that the video is coming from YouTube (youtube.com or youtu.be)
     * */
    public String getVideoId(String url) {
        String id = "";
        url = url.replace("www.", "");

        // https://www.youtube.com/
        if (url.contains(YOUTUBE_URL)) {

            // watch?v=id
            if (url.contains("watch")) {
                id = url.split("=")[1].trim();

                // watch?v=id&list=list_from&start_radio=1
                if (id.contains("list")) {
                    id = id.split("&")[0].trim();
                }

                // embed/id
            } else if (url.contains("embed")) {
                id = url.split("/")[url.split("/").length - 1].trim();
            }

            // https://youtu.be/
        } else if (url.contains(YOUTUBE_URL_SHORTENED)) {
            // /id
            id = url.split("/")[url.split("/").length - 1].trim();

            // /id?list=list_from
            if (id.contains("list")) {
                id = id.split("\\?")[0].trim();
            }
        } else {
            throw new IllegalArgumentException(INVALID_URL);
        }

        // Test for empty id, ex /?list=list_from
        if (id.isEmpty()) {
            throw new IllegalArgumentException(INVALID_URL);
        }

        // Test for special characters in the id
        for (char letter : id.toCharArray()) {
            if (!Character.isDigit(letter) && !Character.isAlphabetic(letter)) {
                throw new IllegalArgumentException(INVALID_URL);
            }
        }

        return id;
    }

    /**
     * @deprecated This method is deprecated, due to YouTube's infrastructure
     * */
    private String getDownloadLink(String videoId) {
        return String.format("https://www.youtube.com/get_video?video_id=%s", videoId);
    }
}
