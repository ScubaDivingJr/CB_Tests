package org.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pages.BasePage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ImageChecker extends BasePage {

    private static final Logger log = LogManager.getLogger(ImageChecker.class);

    //method that makes http call to the source of an image. if it's 200, the image is probably fine.
    public boolean isImageLoaded(String src) {

        try {
            URI uri = URI.create(src);
            URL url = uri.toURL();

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Image with src '{}' returned 200 OK.", src);
            }
            return true;
        } catch (IOException e) {
            log.error("Something went wrong checking image {}", src);
            return false;
        }
    }
}
