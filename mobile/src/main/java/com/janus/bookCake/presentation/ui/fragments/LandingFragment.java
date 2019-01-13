package com.janus.bookCake.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.janus.bookCake.App;
import com.janus.bookCake.R;
import com.janus.bookCake.di.user.DaggerUserComponent;
import com.janus.bookCake.di.user.UserComponent;
import com.janus.bookCake.di.user.UserModule;
import com.janus.bookCake.presentation.presenters.OnBoardingMVP;
import com.janus.bookCake.presentation.presenters.impl.OnBoardingPresenter;
import com.janus.bookCake.presentation.ui.base.BaseFragment;
import com.phonenumberui.PhoneNumberActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LandingFragment#newInstance()} factory method to
 * create an instance of this fragment.
 */
public class LandingFragment extends BaseFragment
        implements OnBoardingMVP.View {

    public static final String TAG = LandingFragment.class.getName();
    private static final int REQUEST_PHONE_VERIFICATION = 1080;


    OnShowMessageListener onShowMessageListener;
    OnChangeFragment onChangeFragment;

    @BindView(R.id.imageview_app_logo)
    ImageView imageViewAppLogo;

    @OnClick(R.id.button_cta_login)
    public void clickCtaLogin() {
//        onChangeFragment.onChangeFragment(LoginFragment.TAG, getTransitionPair());
        Intent intent = new Intent(getActivity(), PhoneNumberActivity.class);
        //Optionally you can add toolbar title
        intent.putExtra("TITLE", getResources().getString(R.string.app_name));
        //Optionally you can pass phone number to populate automatically.
        intent.putExtra("PHONE_NUMBER", "");
        startActivityForResult(intent, REQUEST_PHONE_VERIFICATION);
    }
    @Inject
    OnBoardingPresenter presenter;
    UserComponent component;

    @Override
    public void injectDependencies() {
        if (component == null) {
            component = DaggerUserComponent.builder()
                    .appComponent(App.get(getContext()).getComponent())
                    .userModule(new UserModule(this))
                    .build();
            component.inject(this);
        }
    }

    @Override
    public void attachToPresenter() {
        this.presenter.attachView(this);
    }

    @Override
    public void detachFromPresenter() {
        this.presenter.detachView();
    }

    public LandingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TransactionsFragment.
     */
    public static LandingFragment newInstance() {
        LandingFragment fragment = new LandingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getArgs(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_onboarding, container, false);
        ButterKnife.bind(this, v);
        if (presenter.isInMaintenance()) {
            showMessage("TODO: In maintenance");
        }
//        else {
//            presenter.checkIfUserIsLogged();
//        }

        LottieAnimationView animationView = (LottieAnimationView) v.findViewById(R.id.animation_view);
        lottieProgressConfig(animationView);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        this.injectDependencies();
        this.attachToPresenter();
        super.onAttach(context);
        if (context instanceof OnShowMessageListener) {
            onShowMessageListener = (OnShowMessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnShowMessageListener");
        }
        if (context instanceof OnChangeFragment) {
            onChangeFragment = (OnChangeFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnChangeFragment");
        }
    }

    @Override
    public void onDetach() {
        detachFromPresenter();
        super.onDetach();
    }

    @Override
    public void getArgs(Bundle _bundle) {
        // Empty intentionally.
    }

    @Override
    public void onLandscape() {
        // Empty intentionally.
    }

    @Override
    public void onPortrait() {
        // Empty intentionally.
    }

    @Override
    public void showLoading() {
        // Empty intentionally.
    }

    @Override
    public void hideLoading() {
        // Empty intentionally.
    }

    @Override
    public void showMessage(String message) {
        if (onShowMessageListener != null) {
            onShowMessageListener.onShowMessage(message);
        }
    }

    @Override
    public void showNoNetwork() {
        // Empty intentionally.
    }

    @Override
    public void userLogged() {
        presenter.goToBucket(getContext());
        getActivity().finish();
    }

    private Pair getTransitionPair() {
        Pair<View, String> pair = Pair.create((View)this.imageViewAppLogo,
                getResources().getString(R.string.transition_logo_onboarding_to_login_and_register));
        return pair;
    }

    private void lottieProgressConfig(final LottieAnimationView animationView) {
        animationView.loop(false);
        LottieComposition.Factory.fromAssetFileName(Objects.requireNonNull(getActivity()), "animation-w200-h200.json",
                new OnCompositionLoadedListener() {
                    @Override public void onCompositionLoaded(LottieComposition composition) {
                        animationView.setComposition(composition);
                    }
                });

        if (animationView.getProgress() == 1f) {
            animationView.setProgress(0f);
        }
        animationView.resumeAnimation();

    }
}