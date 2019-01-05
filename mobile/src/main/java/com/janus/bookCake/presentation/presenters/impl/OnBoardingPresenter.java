package com.janus.bookCake.presentation.presenters.impl;

import android.content.Context;

import com.janus.bookCake.external.AnalyticsInterface;
import com.janus.bookCake.external.ConfigInterface;
import com.janus.bookCake.external.FirebucketConfig;
import com.janus.bookCake.domain.interactors.Params;
import com.janus.bookCake.domain.interactors.user.CheckUserUseCase;
import com.janus.bookCake.presentation.presenters.OnBoardingMVP;
import com.janus.bookCake.presentation.presenters.base.BasePresenter;
import com.janus.bookCake.presentation.ui.activities.BucketActivity;
import com.janus.bookCake.rx.DefaultObserver;

import javax.inject.Inject;

/**
 * Created by Matin on 08/05/2016.
 */
public final class OnBoardingPresenter extends BasePresenter<OnBoardingMVP.View>
        implements OnBoardingMVP.Presenter {
    private final static String TAG = OnBoardingPresenter.class.getName();

    private final CheckUserUseCase checkUserUseCase;
    private final AnalyticsInterface analyticsInterface;
    private final ConfigInterface configInterface;

    @Inject
    public OnBoardingPresenter(CheckUserUseCase checkUserUseCase,
                               AnalyticsInterface analyticsInterface,
                               ConfigInterface configInterface) {
        this.checkUserUseCase = checkUserUseCase;
        this.analyticsInterface = analyticsInterface;
        this.configInterface = configInterface;
    }

    @Override
    public void attachView(OnBoardingMVP.View view) {
        super.attachView(view);
        analyticsInterface.trackPageView(AnalyticsInterface.VIEW_ONBOARDING);
    }

    @Override
    public void detachView() {
        checkUserUseCase.dispose();
        super.detachView();
    }

    @Override
    public void goToBucket(Context context) {
        BucketActivity.startMe(context);
    }

    @Override
    public void checkIfUserIsLogged() {
        checkUserUseCase.execute(new CheckUserObserver(), Params.EMPTY);
    }

    @Override
    public boolean isInMaintenance() {
        final String value = configInterface.getString(FirebucketConfig.MAINTENANCE.getKey());
        return (!value.equals(FirebucketConfig.MAINTENANCE.getDefaultValue()));
    }

    private final class CheckUserObserver extends DefaultObserver<Boolean> {

        @Override public void onComplete() {
            super.onComplete();
        }

        @Override public void onError(Throwable e) {
            super.onError(e);
        }

        @Override public void onNext(Boolean value) {
            super.onNext(value);
            if (value) {
                view.userLogged();
            }
        }
    }
}