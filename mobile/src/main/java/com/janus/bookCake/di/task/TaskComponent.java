package com.janus.bookCake.di.task;

import com.janus.bookCake.data.repositories.TagListRepository;
import com.janus.bookCake.data.repositories.TaskRepository;
import com.janus.bookCake.data.repositories.datasource.TagListDataSourceRemote;
import com.janus.bookCake.data.repositories.datasource.TaskDataSourceRemote;
import com.janus.bookCake.di.app.component.AppComponent;
import com.janus.bookCake.di.scope.ActivityScope;
import com.janus.bookCake.domain.interactors.taglist.GetTagListUseCase;
import com.janus.bookCake.domain.interactors.task.CreateTaskUseCase;
import com.janus.bookCake.domain.interactors.task.DeleteTaskUseCase;
import com.janus.bookCake.presentation.presenters.impl.CreateTaskPresenter;
import com.janus.bookCake.presentation.ui.fragments.CreateTaskFragment;

import dagger.Component;

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = TaskModule.class
)
public interface TaskComponent {

    //Fragments
    void inject(CreateTaskFragment view);

    // Presenters
    void inject(CreateTaskPresenter presenter);

    // UseCases/Interactors
    void inject(GetTagListUseCase useCase);
    void inject(CreateTaskUseCase useCase);
    void inject(DeleteTaskUseCase useCase);

    // Repositories
    void inject(TaskRepository repository);
    void inject(TagListRepository repository);

    // DataSources
    void inject(TaskDataSourceRemote dataSource);
    void inject(TagListDataSourceRemote dataSource);
}