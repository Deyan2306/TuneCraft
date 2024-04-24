package d4rk.c47.tunecraft;

import d4rk.c47.tunecraft.services.scraping._YT_Scraper;

import java.io.IOException;
import java.util.Scanner;

public class Debug {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Debug run --");

        System.out.print("[Provide a test token]: ");
        String url = scanner.nextLine();

        String videoId = _YT_Scraper.getVideoId(url);

        System.out.println(String.format("[%s] | %s | --> %s %n", url, videoId, _YT_Scraper.getDownloadLink(videoId)));
    }
}

