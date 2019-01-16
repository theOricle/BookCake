package com.janus.bookCake.data.repositories.datasource;

import com.janus.bookCake.data.entities.UserEntity;
import com.janus.bookCake.firebase.RxFirebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by Matin on 24/02/2017.
 */

public class UserDataSourceRemote extends BaseFirebaseDataSource {

    public static FirebaseDatabase firebaseDatabase;
    public static FirebaseAuth firebaseAuth;

    @Inject
    public UserDataSourceRemote(FirebaseAuth fireAuth, FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        firebaseAuth = fireAuth;
    }

    /**
     * Allows to register a user with a given {@link UserEntity}
     * @param userEntity
     */
    public Observable<AuthResult> register(final UserEntity userEntity) {
        return RxFirebase.getObservable(firebaseAuth.createUserWithEmailAndPassword(userEntity.getEmail(),
                userEntity.getPassword()));
    }

    /**
     * Allows to login a user with a given {@link UserEntity}
     * @param userEntity
     */
    public Observable<AuthResult> login(final UserEntity userEntity) {
        return RxFirebase.getObservable(firebaseAuth.signInWithEmailAndPassword(userEntity.getEmail(),
                userEntity.getPassword()));
    }

    /**
     * Allows to know if the current user already exists or not
     * @return
     */
    public Observable<Boolean> isUserLogged() {
        return Observable.defer(new Callable<ObservableSource<? extends Boolean>>() {
            @Override
            public ObservableSource<? extends Boolean> call() throws Exception {
                return Observable.just(firebaseAuth.getCurrentUser()!=null);
            }
        });
    }

    /**
     * Allows to logout the current user
     * @return
     */
    public Observable<Void> logoutUser() {
        return Observable.defer(new Callable<ObservableSource<? extends Void>>() {
            @Override
            public ObservableSource<? extends Void> call() throws Exception {
                firebaseAuth.signOut();
                return Observable.just(null);
            }
        });
    }

    /**
     * Allows to write a user in the database
     * @param userEntity
     */
    public Observable<Object> writeUserInDatabase(final UserEntity userEntity) {

        DatabaseReference targetChild = this.firebaseDatabase.
                getReference()
                .child(FIREBASE_CHILD_KEY_USERS)
                .child(userEntity.getUid());

        return RxFirebase.getObservable(targetChild.setValue(userEntity), new RxFirebase.FirebaseTaskResponseSuccess());
    }
}
