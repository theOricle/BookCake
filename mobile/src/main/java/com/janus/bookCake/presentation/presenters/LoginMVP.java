package com.janus.bookCake.presentation.presenters;

import android.content.Context;

import com.janus.bookCake.domain.models.UserModel;
import com.janus.bookCake.presentation.ui.base.BaseMvpView;

/**
 * Created by Matin on 08/05/2016.
 */
public interface LoginMVP {

    interface View extends BaseMvpView {
        void setErrorEmailField();
        void setErrorPasswordField();
        void onSuccess();
        void onFailure();
    }

    interface Presenter {
        void login(String email, String password);
        void onLoginSuccess(UserModel userModel);
        void onLoginFailure(Throwable e);

        void onLoginSuccessTracking(UserModel userModel);
        void onLoginFailureTracking(Throwable e);

        void goToBucket(Context context);
    }
}
