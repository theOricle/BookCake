package com.janus.bookCake.di.user;

import android.support.v4.app.Fragment;

import com.janus.bookCake.external.AnalyticsInterface;
import com.janus.bookCake.external.ConfigInterface;
import com.janus.bookCake.data.repositories.UserRepository;
import com.janus.bookCake.data.repositories.datasource.UserDataSourceRemote;
import com.janus.bookCake.domain.interactors.user.CheckUserUseCase;
import com.janus.bookCake.domain.interactors.user.LoginUserUseCase;
import com.janus.bookCake.domain.interactors.user.LogoutUserUseCase;
import com.janus.bookCake.domain.interactors.user.RegisterUserUseCase;
import com.janus.bookCake.domain.interactors.user.WriteUserUseCase;
import com.janus.bookCake.presentation.presenters.impl.LoginPresenter;
import com.janus.bookCake.presentation.presenters.impl.OnBoardingPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private Fragment fragment;

    public UserModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    OnBoardingPresenter provideOnBoardingPresenter(CheckUserUseCase checkUserUseCase,
                                                   AnalyticsInterface analyticsInterface,
                                                   ConfigInterface configInterface) {
        return new OnBoardingPresenter(checkUserUseCase,
                analyticsInterface,
                configInterface);
    }

    @Provides
    LoginPresenter provideLoginPresenter(LoginUserUseCase loginUserUseCase,
                                         AnalyticsInterface analyticsInterface) {
        return new LoginPresenter(loginUserUseCase, analyticsInterface);
    }

    @Provides
    CheckUserUseCase provideCheckUserUseCase(UserRepository repository) {
        return new CheckUserUseCase(repository);
    }

    @Provides
    LoginUserUseCase provideLoginUserUseCase(UserRepository repository) {
        return new LoginUserUseCase(repository);
    }

    @Provides
    LogoutUserUseCase provideLogoutUserUseCase(UserRepository repository) {
        return new LogoutUserUseCase(repository);
    }

    @Provides
    RegisterUserUseCase provideRegisterUserUseCase(UserRepository repository) {
        return new RegisterUserUseCase(repository);
    }

    @Provides
    WriteUserUseCase provideWriteUserUseCase(UserRepository repository) {
        return new WriteUserUseCase(repository);
    }

    @Provides
    UserDataSourceRemote provideDataSource(FirebaseAuth firebaseAuth,
                                                  FirebaseDatabase firebaseDatabase) {
        return new UserDataSourceRemote(firebaseAuth, firebaseDatabase);
    }

    @Provides
    UserRepository provideRepository(UserDataSourceRemote dataSource) {
        return new UserRepository(dataSource);
    }

}