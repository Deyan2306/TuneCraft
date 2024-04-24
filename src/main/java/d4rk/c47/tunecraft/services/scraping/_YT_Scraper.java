package d4rk.c47.tunecraft.services.scraping;

import com.github.kiulian.downloader.Config;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.YoutubeCallback;
import com.github.kiulian.downloader.downloader.YoutubeProgressCallback;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.Format;

import java.io.File;

public class _YT_Scraper {

    private static YoutubeDownloader downloader = new YoutubeDownloader();
    private static _YT_Tokenizer tokenizer = new _YT_Tokenizer();

    /**
     * Method, that allows super easy info retrieving. It handles all the edge cases that come with the url.
     * @param videoUrl - YouTube url of any kind. No matter if it's from a playlist or short link.
     * */
    public VideoInfo retrieveVideoInfo(String videoUrl) {
        String videoId = tokenizer.getVideoId(videoUrl);

        RequestVideoInfo request = new RequestVideoInfo(videoId)
                .callback(new YoutubeCallback<VideoInfo>() {
                    @Override
                    public void onFinished(VideoInfo videoInfo) {
                        System.out.println("[+] Finished parsing");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("[-] Error: " + throwable.getMessage());
                    }
                })
                .async();

        Response<VideoInfo> response = downloader.getVideoInfo(request);

        // TODO: Handle response types

        return response.data(); // will block thread
    }

    public void downloadVideo(VideoInfo videoInfo, File output) {
        Format format = videoInfo.bestAudioFormat();

        RequestVideoFileDownload request = new RequestVideoFileDownload(format)
                .saveTo(output)
                .renameTo(videoInfo.details().title())
                .overwriteIfExists(true)
                .callback(new YoutubeProgressCallback<File>() {
                    @Override
                    public void onDownloading(int progress) {
                        System.out.printf("[...] Downloaded %d%%\n", progress);
                    }

                    @Override
                    public void onFinished(File videoInfo) {
                        System.out.println("[+] Finished file: " + videoInfo);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("[!] Error: " + throwable.getLocalizedMessage());
                    }
                })
                .async();

        Response<File> response = downloader.downloadVideoFile(request);
        File data = response.data();
    }

    /**
     * Allows external modification of the download's configuration. Refer to the sealedtx docs for further info
     * */
    public void configureDownloader(Config config) {
        downloader = new YoutubeDownloader(config);
    }

    /**
     * Allows external modification of the download's configuration. Refer to the sealedtx docs for further info
     * */
    public Config getScraperConfiguration() {
        return downloader.getConfig();
    }
}
