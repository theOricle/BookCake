package com.janus.bookCake.domain.interactors.user;

import com.janus.bookCake.data.repositories.UserRepository;
import com.janus.bookCake.domain.interactors.Params;
import com.janus.bookCake.domain.interactors.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Implementation of {@link BaseUseCase} that represents a UseCase/Interactor
 */
public class CheckUserUseCase extends BaseUseCase {

  private final UserRepository userRepository;

  @Inject
  public CheckUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override public Observable getObservable(Params params) {
      return this.userRepository.isUserLogged();
  }
}