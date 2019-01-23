package com.janus.bookCake.di.task;

import android.support.v4.app.Fragment;

import com.janus.bookCake.data.repositories.PractitionerListRepository;
import com.janus.bookCake.data.repositories.datasource.PractitionerListDataSourceRemote;
import com.janus.bookCake.domain.interactors.practitioner.GetPractitionerListUseCase;
import com.janus.bookCake.external.AnalyticsInterface;
import com.janus.bookCake.data.repositories.TagListRepository;
import com.janus.bookCake.data.repositories.TaskRepository;
import com.janus.bookCake.data.repositories.datasource.TagListDataSourceRemote;
import com.janus.bookCake.data.repositories.datasource.TaskDataSourceRemote;
import com.janus.bookCake.domain.interactors.taglist.GetTagListUseCase;
import com.janus.bookCake.domain.interactors.task.CreateTaskUseCase;
import com.janus.bookCake.domain.interactors.task.DeleteTaskUseCase;
import com.janus.bookCake.external.TaskReminderInterface;
import com.janus.bookCake.presentation.presenters.impl.CreateTaskPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {

    private Fragment fragment;

    public TaskModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    CreateTaskPresenter provideCreateTaskPresenter(GetPractitionerListUseCase getPractitionerListUseCase,
                                                   GetTagListUseCase getTagListUseCase,
                                                   CreateTaskUseCase createTaskUseCase,
                                                   AnalyticsInterface analyticsInterface,
                                                   TaskReminderInterface taskReminderInterface) {
        return new CreateTaskPresenter(getPractitionerListUseCase,
                getTagListUseCase,
                createTaskUseCase,
                analyticsInterface,
                taskReminderInterface);
    }

    @Provides
    GetTagListUseCase provideGetTagListUseCase(TagListRepository repository) {
        return new GetTagListUseCase(repository);
    }

    @Provides
    CreateTaskUseCase provideCreateTaskUseCase(TaskRepository repository) {
        return new CreateTaskUseCase(repository);
    }

    @Provides
    DeleteTaskUseCase provideDeleteTaskUseCase(TaskRepository repository) {
        return new DeleteTaskUseCase(repository);
    }

    @Provides
    TagListDataSourceRemote provideTagListDataSource(FirebaseDatabase firebaseDatabase) {
        return new TagListDataSourceRemote(firebaseDatabase);
    }
    @Provides
    PractitionerListDataSourceRemote providePractitionerListDataSource(FirebaseDatabase firebaseDatabase) {
        return new PractitionerListDataSourceRemote(firebaseDatabase);
    }

    @Provides
    TaskDataSourceRemote provideTaskDataSource(FirebaseAuth firebaseAuth,
                                           FirebaseDatabase firebaseDatabase) {
        return new TaskDataSourceRemote(firebaseAuth, firebaseDatabase);
    }

    @Provides
    TagListRepository provideTagListRepository(TagListDataSourceRemote dataSource) {
        return new TagListRepository(dataSource);
    }
    @Provides
    PractitionerListRepository providePractitionerListRepository(PractitionerListDataSourceRemote dataSource) {
        return new PractitionerListRepository(dataSource);
    }

    @Provides
    TaskRepository provideTaskRepository(TaskDataSourceRemote dataSource) {
        return new TaskRepository(dataSource);
    }

}