package com.janus.bookCake.data.repositories.datasource;

import com.janus.bookCake.data.entities.BucketEntity;
import com.janus.bookCake.firebase.RxFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.janus.bookCake.data.repositories.datasource.UserDataSourceRemote.firebaseAuth;

/**
 * Created by Matin on 24/02/2017.
 */

public class BucketDataSourceRemote extends BaseFirebaseDataSource {

    /**
     * The target node for a given service
     */
    private DatabaseReference childReference = null;

    private FirebaseDatabase firebaseDatabase;

    @Inject
    public BucketDataSourceRemote(FirebaseAuth fireAuth, FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        firebaseAuth = fireAuth;
    }

    public DatabaseReference getChildReference() {
        if (childReference == null) {
            this.childReference = this.firebaseDatabase.
                    getReference()
                    .child(FIREBASE_CHILD_KEY_USERS)
                    .child(firebaseAuth.getCurrentUser().getUid());
        }

        return childReference;
    }

    /**
     * Allows to get the {@link BucketEntity}
     * @return
     */
    public Observable<BucketEntity> getBucket() {
        return RxFirebase.getObservable(getChildReference(), BucketEntity.class);
    }
}
