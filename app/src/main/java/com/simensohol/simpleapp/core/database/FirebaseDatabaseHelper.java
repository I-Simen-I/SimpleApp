package com.simensohol.simpleapp.core.database;

import android.content.Context;
import com.firebase.client.*;
import com.simensohol.simpleapp.core.entity.FirebaseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Søhol
 */
public class FirebaseDatabaseHelper {
    private Firebase firebase;

    public FirebaseDatabaseHelper(Context context) {
        Firebase.setAndroidContext(context);
        firebase = new Firebase("https://simen-simpleapp.firebaseio.com");
    }

    public FirebaseObject pushToFirebase(FirebaseObject objectToSave) {
        Firebase objects = firebase.child("Objects");
        Firebase id = objects.push();
        objectToSave.setId(id.toString());
        id.setValue(objectToSave);

        return objectToSave;
    }

    public void updateFirebase(FirebaseObject firebaseObject) {
        Firebase id = new Firebase(firebaseObject.getId());
        id.setValue(firebaseObject);
    }

    public void delete(FirebaseObject firebaseObject) {
        Firebase id = new Firebase(firebaseObject.getId());
        id.removeValue();
    }

    public List<FirebaseObject> getAll() {
        List<FirebaseObject> firebaseObjects = new ArrayList<>();

        Firebase objects = firebase.child("Objects");
        Query query = objects.orderByChild("name");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return firebaseObjects;
    }
}
