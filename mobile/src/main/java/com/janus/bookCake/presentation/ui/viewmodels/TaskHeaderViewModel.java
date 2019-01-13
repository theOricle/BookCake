package com.janus.bookCake.presentation.ui.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;

import com.janus.bookCake.domain.models.TaskModel;
import com.janus.bookCake.presentation.ui.base.BaseViewModel;
import com.janus.bookCake.utils.CustomDateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Note: We use this ViewModel with Android Data Binding
 */
public class TaskHeaderViewModel extends BaseViewModel {

    private TaskModel model;

    public TaskHeaderViewModel(TaskModel model) {
        super();
        this.model = model;
    }

    public String getDisplayedDate(@NonNull Context context) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(model.getDeadlineMs()));

        return CustomDateUtils.getDisplayDate(context, calendar);
    }
}