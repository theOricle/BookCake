package com.janus.bookCake.domain.interactors.task;

import com.janus.bookCake.data.repositories.TaskRepository;
import com.janus.bookCake.domain.interactors.Params;
import com.janus.bookCake.domain.interactors.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Implementation of {@link BaseUseCase} that represents a UseCase/Interactor
 */
public class DeleteTaskUseCase extends BaseUseCase {

  public final static String PARAMS_KEY_TASK_ID = "task_id";

  private final TaskRepository taskRepository;

  @Inject
  public DeleteTaskUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override public Observable getObservable(Params params) {
    return this.taskRepository.deleteTask(params.getString(PARAMS_KEY_TASK_ID, "0"));
  }
}