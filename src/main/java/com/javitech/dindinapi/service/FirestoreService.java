package com.javitech.dindinapi.service;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore firestore;

    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String save(String collection, String document, Map<String, String> data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collection).document(document);
        docRef.set(data).get();
        return docRef.getId();
    }

    public DocumentSnapshot get(String collection, String document) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(collection).document(document);
        return docRef.get().get();
    }
}