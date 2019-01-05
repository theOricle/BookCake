package com.janus.bookCake.presentation.presenters;

import com.janus.bookCake.domain.models.BucketModel;
import com.janus.bookCake.domain.models.TaskModel;
import com.janus.bookCake.presentation.ui.base.BaseMvpView;

import java.util.ArrayList;

/**
 * Created by Matin on 08/05/2016.
 */
public interface BucketMVP {

    interface View extends BaseMvpView {
        void showBucket(ArrayList<TaskModel> model);
        void showBucketEmpty();
        void showBucketEmptyFirstTime();
        void showBucketError();

        void showTaskDeleted();
        void showTaskDeletedError(String message);

        void onItemSwiped(int position);
    }

    interface Presenter {
        void getBucket();
        void onGetBucketSuccess(BucketModel bucketModel);
        void onGetBucketFailure(Throwable e);

        void onGetBucketSuccessTracking();
        void onGetBucketFailureTracking(Throwable e);

        void deleteTask(String taskId);
        void onDeleteTaskSuccess();
        void onDeleteTaskFailure(Throwable e);

        void onDeleteTaskSuccessTracking();
        void onDeleteTaskFailureTracking(Throwable e);
    }
}
