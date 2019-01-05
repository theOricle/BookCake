package com.janus.bookCake.domain.interactors.taglist;

import com.janus.bookCake.data.repositories.TagListRepository;
import com.janus.bookCake.domain.interactors.Params;
import com.janus.bookCake.domain.interactors.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Implementation of {@link BaseUseCase} that represents a UseCase/Interactor
 */
public class GetTagListUseCase extends BaseUseCase {

  private final TagListRepository tagListRepository;

  @Inject
  public GetTagListUseCase(TagListRepository tagListRepository) {
    this.tagListRepository = tagListRepository;
  }

  @Override public Observable getObservable(Params params) {
    return this.tagListRepository.getTagList();
  }
}