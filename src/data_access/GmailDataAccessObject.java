package data_access;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.Gson;
import use_case.create_project.CreateProjectDataAccessInterface;
import use_case.create_project.CreateProjectGmailDataAccessInterface;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class GmailDataAccessObject implements CreateProjectGmailDataAccessInterface {
    private static String APPLICATION_NAME;
    private static String USER_ID;

    private Gmail service;

    public GmailDataAccessObject() throws GeneralSecurityException, IOException {
        serviceAccount = new FileInputStream("credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        service = new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), (HttpRequestInitializer) credentials
        ).setApplicationName(APPLICATION_NAME).build();
    }

    private class AppConfig {
        private String clientId;
        private String projectId;
        private String clientSecret;
        private String emailFromAddress;

        String getClientId() {
            return clientId;
        }

        String getProjectId() {
            return projectId;
        }
        String getEmailFromAddress() {
            return emailFromAddress;
        }
        String getClientSecret() {
            return clientSecret;
        }
    }

    private AppConfig loadConfig(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, AppConfig.class);
        } catch (Exception e) {
            return null;
        }
    }

    private Message createMessage(String to, String from, String subject, String body) throws IOException {
        Message message = new Message();
        String email = createEmail(to,from,subject,body);
        message.setRaw(Base64.getUrlEncoder().encodeToString(email.getBytes()));
        return message;
    }

    private String createEmail(String to, String from, String subject, String body) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("From: ").append(from).append("\n");
        emailContent.append("To: ").append(to).append("\n");
        emailContent.append("Subject: ").append(subject).append("\n");
        emailContent.append("Content-Type: text/html; charset=utf-8\n\n");
        emailContent.append(body);
        return emailContent.toString();
    }

    public void sendProjectCreationEmail(String to, String from, String projectName) throws IOException {
        String subject = "Project Invited";
        String body = "You have been invited to collaborate in" + projectName + ". Please email " + to + " if you have any questions";
        Message message = createMessage(to, from, subject, body);
        service.users().messages().send(USER_ID, message).execute();
    }
}
