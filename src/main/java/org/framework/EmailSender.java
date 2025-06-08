package org.framework;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;
import com.mailjet.client.resource.Emailv31;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//use this locally
public class EmailSender {

    private static final Logger log = LogManager.getLogger(EmailSender.class);

    private final String MJ_APIKEY_PUBLIC = System.getenv("MJ_APIKEY_PUBLIC");
    private final String MJ_APIKEY_PRIVATE = System.getenv("MJ_APIKEY_PRIVATE");

    private final String fromEmail = "andrei.marcu1337@gmail.com";
    private final String fromName = "Andrei Marcu";
    private final String toEmail = "andrei.marcu1337@gmail.com";
    private final String toName = "Andrei Marcu";
    private final String subject = "Maven Test Results";

    private String getHTmlContent() {
        try {
            return Files.readString(Paths.get("target/surefire-reports/emailable-report.html"));
        } catch (IOException e) {
            log.error("Could not get HTML content for report.");
            throw new RuntimeException(e);
        }
    }

    //from Mailjet docs (mostly)
    public void sendEmail() throws MailjetException, IOException {
        MailjetClient client = new MailjetClient(
                MJ_APIKEY_PUBLIC,
                MJ_APIKEY_PRIVATE
        );
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", fromEmail)
                                        .put("Name", fromName))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", toEmail)
                                                .put("Name", toName)))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.TEXTPART, "Below is the Maven Surefire Report: ")
                                .put(Emailv31.Message.HTMLPART, getHTmlContent())
));
        MailjetResponse response = client.post(request);

        log.info("SendEmail() response status is: {}", response.getStatus());
        log.info("SendEmail request data is: '{}'", response.getData());
    }
}