package com.janus.bookCake.presentation.presenters;

import android.app.DatePickerDialog;
import android.speech.RecognitionListener;

import com.janus.bookCake.domain.models.PractitionerListModel;
import com.janus.bookCake.domain.models.PractitionerModel;
import com.janus.bookCake.domain.models.TagListModel;
import com.janus.bookCake.domain.models.TaskModel;
import com.janus.bookCake.presentation.ui.base.BaseMvpView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matin on 08/05/2016.
 */
public interface CreateTaskMVP {

    interface View extends BaseMvpView, DatePickerDialog.OnDateSetListener, RecognitionListener {
        void onSuccess();
        void onFailure();

        void showMessageInvalidTaskTitle();

        void showPractitionerList(PractitionerModel[] practitioners);
        void showPractitionerListError();

        void showTagList(String[] tags);
        void showTagListError();

        void initDeadlineDatePicker();
        void initReminderTimePicker();
        void startVoiceRecognition();

        boolean isReminderSet();
        Date getReminderDate();
    }

    interface Presenter {
        void getPractitionerList();
        void onGetPractitionerListSuccess(PractitionerListModel practitionerListModel);
        void onGetPractitionerListFailure(Throwable e);

        void onGetPractitionerListSuccessTracking();
        void onGetPractitionerListFailureTracking(Throwable e);

        void getTagList();
        void onGetTagListSuccess(TagListModel tagListModel);
        void onGetTagListFailure(Throwable e);

        void onGetTagListSuccessTracking();
        void onGetTagListFailureTracking(Throwable e);

        void createTask(String title,
                        Calendar deadline,
                        String tag,
                        int idPriority);
        void onCreateTaskSuccess(TaskModel taskModel);
        void onCreateTaskFailure(Throwable e);

        void onCreateTaskSuccessTracking(TaskModel taskModel);
        void onCreateTaskFailureTracking(Throwable e);

        void setReminder(TaskModel taskModel);
    }
}
