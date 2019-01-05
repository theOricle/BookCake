package com.janus.bookCake.di.bucket;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.janus.bookCake.external.AnalyticsInterface;
import com.janus.bookCake.data.repositories.BucketRepository;
import com.janus.bookCake.data.repositories.TaskRepository;
import com.janus.bookCake.data.repositories.datasource.BucketDataSourceRemote;
import com.janus.bookCake.data.repositories.datasource.TaskDataSourceRemote;
import com.janus.bookCake.domain.interactors.bucket.GetBucketUseCase;
import com.janus.bookCake.domain.interactors.task.DeleteTaskUseCase;
import com.janus.bookCake.presentation.presenters.impl.BucketPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class BucketModule {

    private Fragment fragment;

    public BucketModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    BucketPresenter provideBucketPresenter(GetBucketUseCase getBucketUseCase,
                                           DeleteTaskUseCase deleteTaskUseCase,
                                           AnalyticsInterface analyticsInterface,
                                           SharedPreferences sharedPreferences) {
        return new BucketPresenter(getBucketUseCase,
                deleteTaskUseCase,
                analyticsInterface,
                sharedPreferences);
    }

    @Provides
    GetBucketUseCase provideGetBucketUseCase(BucketRepository repository) {
        return new GetBucketUseCase(repository);
    }

    @Provides
    BucketDataSourceRemote provideBucketDataSource(FirebaseAuth firebaseAuth,
                                             FirebaseDatabase firebaseDatabase) {
        return new BucketDataSourceRemote(firebaseAuth, firebaseDatabase);
    }

    @Provides
    BucketRepository provideBucketRepository(BucketDataSourceRemote dataSource) {
        return new BucketRepository(dataSource);
    }

    @Provides
    DeleteTaskUseCase provideDeleteTaskUseCase(TaskRepository repository) {
        return new DeleteTaskUseCase(repository);
    }

    @Provides
    TaskDataSourceRemote provideTaskDataSource(FirebaseAuth firebaseAuth,
                                             FirebaseDatabase firebaseDatabase) {
        return new TaskDataSourceRemote(firebaseAuth, firebaseDatabase);
    }

    @Provides
    TaskRepository provideTaskRepository(TaskDataSourceRemote dataSource) {
        return new TaskRepository(dataSource);
    }

}