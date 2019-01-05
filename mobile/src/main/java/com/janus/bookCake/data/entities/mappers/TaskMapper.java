package com.janus.bookCake.data.entities.mappers;

import com.janus.bookCake.data.entities.TaskEntity;
import com.janus.bookCake.domain.models.TaskModel;

/**
 * Created by Matin on 23/02/2017.
 */
public class TaskMapper {

    public static TaskModel transform(TaskEntity taskEntity) {
        TaskModel taskModel = null;
        if (taskEntity != null) {
            taskModel = new TaskModel(taskEntity.getTitle());
            taskModel.setId(taskEntity.getId());
            taskModel.setDeadline(taskEntity.getDeadline());
            taskModel.setTag(taskEntity.getTag());
            taskModel.setDeadlineMs(taskEntity.getDeadlineMs());
            taskModel.setPriority(TaskPriorityMapper.transform(taskEntity.getPriority()));
        }

        return taskModel;
    }
}
