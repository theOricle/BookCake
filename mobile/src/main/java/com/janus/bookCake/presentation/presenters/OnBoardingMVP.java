package com.janus.bookCake.presentation.presenters;

import android.content.Context;

import com.janus.bookCake.presentation.ui.base.BaseMvpView;

/**
 * Created by Matin on 08/05/2016.
 */
public interface OnBoardingMVP {

    interface View extends BaseMvpView {
        void userLogged();
    }

    interface Presenter {
        void goToBucket(Context context);
        void checkIfUserIsLogged();

        boolean isInMaintenance();
    }
}
