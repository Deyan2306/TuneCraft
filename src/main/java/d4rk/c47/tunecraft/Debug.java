package d4rk.c47.tunecraft;

import com.github.kiulian.downloader.model.videos.VideoInfo;
import d4rk.c47.tunecraft.services.scraping._YT_Scraper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Debug {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String url = "";

        System.out.print("\n Video url: ");
        url = scanner.nextLine();


        _YT_Scraper scraper = new _YT_Scraper();
        VideoInfo info = scraper.retrieveVideoInfo(url);

        scraper.downloadVideo(info, new File("test_videos"));
    }
}

