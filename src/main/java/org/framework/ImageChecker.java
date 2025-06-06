package org.framework;

import org.pages.BasePage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImageChecker extends BasePage {


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

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            log.error("Something went wrong checking the image.");
            return false;
        }
    }
}
