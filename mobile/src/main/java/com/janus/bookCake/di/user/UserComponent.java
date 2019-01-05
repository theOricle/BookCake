package com.janus.bookCake.di.user;

import com.janus.bookCake.data.repositories.UserRepository;
import com.janus.bookCake.data.repositories.datasource.UserDataSourceRemote;
import com.janus.bookCake.di.app.component.AppComponent;
import com.janus.bookCake.di.scope.ActivityScope;
import com.janus.bookCake.domain.interactors.user.CheckUserUseCase;
import com.janus.bookCake.domain.interactors.user.LoginUserUseCase;
import com.janus.bookCake.domain.interactors.user.LogoutUserUseCase;
import com.janus.bookCake.domain.interactors.user.RegisterUserUseCase;
import com.janus.bookCake.domain.interactors.user.WriteUserUseCase;
import com.janus.bookCake.presentation.presenters.impl.LoginPresenter;
import com.janus.bookCake.presentation.presenters.impl.OnBoardingPresenter;
import com.janus.bookCake.presentation.presenters.impl.RegisterPresenter;
import com.janus.bookCake.presentation.ui.fragments.LandingFragment;
import com.janus.bookCake.presentation.ui.fragments.LoginFragment;

import dagger.Component;

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = UserModule.class
)
public interface UserComponent {

    //Fragments
    void inject(LandingFragment view);
    void inject(LoginFragment view);

    // Presenters
    void inject(OnBoardingPresenter presenter);
    void inject(LoginPresenter presenter);
    void inject(RegisterPresenter presenter);

    // UseCases/Interactors
    void inject(CheckUserUseCase useCase);
    void inject(LoginUserUseCase useCase);
    void inject(LogoutUserUseCase useCase);
    void inject(RegisterUserUseCase useCase);
    void inject(WriteUserUseCase useCase);

    // Repositories
    void inject(UserRepository repository);

    // DataSources
    void inject(UserDataSourceRemote dataSource);
}