package com.janus.bookCake.domain.interactors.practitioner;

import com.janus.bookCake.data.repositories.PractitionerListRepository;
import com.janus.bookCake.data.repositories.TagListRepository;
import com.janus.bookCake.domain.interactors.Params;
import com.janus.bookCake.domain.interactors.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Implementation of {@link BaseUseCase} that represents a UseCase/Interactor
 */
public class GetPractitionerListUseCase extends BaseUseCase {

  private final PractitionerListRepository practitonerListRepository;

  @Inject
  public GetPractitionerListUseCase(PractitionerListRepository practitionerListRepository) {
    this.practitonerListRepository = practitionerListRepository;
  }

  @Override public Observable getObservable(Params params) {
    return this.practitonerListRepository.getPractitionerList();
  }
}