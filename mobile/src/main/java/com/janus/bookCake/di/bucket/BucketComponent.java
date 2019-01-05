package com.janus.bookCake.di.bucket;

import com.janus.bookCake.data.repositories.BucketRepository;
import com.janus.bookCake.data.repositories.TaskRepository;
import com.janus.bookCake.data.repositories.datasource.BucketDataSourceRemote;
import com.janus.bookCake.data.repositories.datasource.TaskDataSourceRemote;
import com.janus.bookCake.di.app.component.AppComponent;
import com.janus.bookCake.di.scope.ActivityScope;
import com.janus.bookCake.domain.interactors.bucket.GetBucketUseCase;
import com.janus.bookCake.domain.interactors.task.DeleteTaskUseCase;
import com.janus.bookCake.presentation.presenters.impl.BucketPresenter;
import com.janus.bookCake.presentation.ui.fragments.BucketFragment;

import dagger.Component;

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = BucketModule.class
)
public interface BucketComponent {

    //Fragments
    void inject(BucketFragment view);

    // Presenters
    void inject(BucketPresenter presenter);

    // UseCases/Interactors
    void inject(GetBucketUseCase useCase);
    void inject(DeleteTaskUseCase useCase);

    // Repositories
    void inject(BucketRepository repository);
    void inject(TaskRepository repository);

    // DataSources
    void inject(BucketDataSourceRemote dataSource);
    void inject(TaskDataSourceRemote dataSource);
}