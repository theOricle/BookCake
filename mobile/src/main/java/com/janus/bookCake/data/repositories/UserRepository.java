package com.janus.bookCake.data.repositories;

import com.janus.bookCake.data.entities.UserEntity;
import com.janus.bookCake.data.entities.mappers.UserMapper;
import com.janus.bookCake.data.repositories.datasource.UserDataSourceRemote;
import com.janus.bookCake.domain.models.UserModel;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * The repository is in charge of getting the data from the {@link UserDataSourceRemote},
 * It will also map data from entity to model
 * Created by Matin on 23/02/2017.
 */
@Singleton
public class UserRepository {

    private final UserDataSourceRemote userDataSourceRemote;

    @Inject
    public UserRepository(UserDataSourceRemote userDataSourceRemote) {
        this.userDataSourceRemote = userDataSourceRemote;
    }

    public Observable<UserModel> register(final String email,
                                          final String password) {
        return this.userDataSourceRemote.register(new UserEntity(email, password))
                .map(new Function<AuthResult, UserEntity>() {
            @Override
            public UserEntity apply(AuthResult authResult) throws Exception {
                return UserMapper.transform(authResult);
            }
        })
                .map(new Function<UserEntity, UserModel>() {
            @Override
            public UserModel apply(UserEntity userEntity) throws Exception {
                return UserMapper.transform(userEntity);
            }
        });
    }

    public Observable<UserModel> login(final String email,
                                       final String password) {
        return this.userDataSourceRemote.login(new UserEntity(email, password))
                .map(new Function<AuthResult, UserEntity>() {
            @Override
            public UserEntity apply(AuthResult authResult) throws Exception {
                return UserMapper.transform(authResult);
            }
        })
                .map(new Function<UserEntity, UserModel>() {
            @Override
            public UserModel apply(UserEntity userEntity) throws Exception {
                return UserMapper.transform(userEntity);
            }
        });
    }

    public Observable<Boolean> isUserLogged() {
        return this.userDataSourceRemote.isUserLogged();
    }

    public Observable<Void> logoutUser() {
        this.userDataSourceRemote.logoutUser();
        return null;
    }

    public Observable<Object> writeUserInDatabase(final String uid,
                                                final String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUid(uid);
        userEntity.setUsername(username);
        return this.userDataSourceRemote.writeUserInDatabase(userEntity);
    }
}
