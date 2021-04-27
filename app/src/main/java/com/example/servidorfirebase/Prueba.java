package com.example.servidorfirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Prueba {
    public static void main(String[] args) {
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("C:\\Users\\rubi_\\Downloads\\fir-c2486-firebase-adminsdk-dno8y-16f739ffdd.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FirebaseOptions options = null;
//        try {
//            options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.getApplicationDefault())
//                    .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
//                    .build();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        FirebaseApp defaultApp = FirebaseApp.initializeApp(options);

        // Initialize the default app
//        FirebaseApp defaultApp = FirebaseApp.initializeApp(defaultOptions);

        System.out.println(defaultApp.getName());  // "[DEFAULT]"

// Retrieve services by passing the defaultApp variable...
        FirebaseAuth defaultAuth = FirebaseAuth.getInstance(defaultApp);
//        FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance(defaultApp);

// ... or use the equivalent shorthand notation
        defaultAuth = FirebaseAuth.getInstance();
//        defaultDatabase = FirebaseDatabase.getInstance();

        // This registration token comes from the client FCM SDKs.
        String registrationToken = "cQMQzmuKQbq6AlzOzMvBZL:APA91bHkT6A16RG2RS3SONbpU5r18WDiWBmRiFBdXJZOw-YepfQcFmnRj-jflEow9dkTb7V1EoiSKIcLJC0JtYi-t1mxfMGCCywc6BHehEzmF7bi5FQOYX0D6rEhXctQG51ATIq9QM-G";

// See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("asunto", "Notificacion")
                .putData("contenido", "firebase te notifica")
                .setToken(registrationToken)
                .build();

// Send a message to the device corresponding to the provided
// registration token.
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
// Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
    }
}
