package com.janus.bookCake.data.repositories.datasource;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.janus.bookCake.data.entities.PractitionerListEntity;
import com.janus.bookCake.data.entities.TagListEntity;
import com.janus.bookCake.firebase.RxFirebase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Matin on 24/02/2017.
 */

public class PractitionerListDataSourceRemote extends BaseFirebaseDataSource {

    /**
     * The target node for a given service
     */
    private DatabaseReference childReference = null;

    private FirebaseDatabase firebaseDatabase;

    @Inject
    public PractitionerListDataSourceRemote(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public DatabaseReference getChildReference() {
        if (childReference == null) {
            childReference = this.firebaseDatabase.
                    getReference()
                    .child(FIREBASE_CHILD_KEY_PRACTITIONER_LIST);
        }

        return childReference;
    }

    /**
     * Allows to get the list of {@link PractitionerListEntity}
     * @return
     */
    public Observable<PractitionerListEntity> getPractitionerList() {
        return RxFirebase.getObservableForSingleEvent(getChildReference(), PractitionerListEntity.class);
    }
}
