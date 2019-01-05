package com.janus.bookCake.data.repositories;

import com.janus.bookCake.data.entities.TaskEntity;
import com.janus.bookCake.data.entities.TaskPriorityEntity;
import com.janus.bookCake.data.entities.mappers.TaskMapper;
import com.janus.bookCake.data.repositories.datasource.TaskDataSourceRemote;
import com.janus.bookCake.domain.models.TaskModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * The repository is in charge of getting the data from the DataSource,
 * It will also map data from entity to model
 * Created by Matin on 23/02/2017.
 */
@Singleton
public class TaskRepository {

    private final TaskDataSourceRemote taskDataSourceRemote;

    @Inject
    public TaskRepository(TaskDataSourceRemote taskDataSourceRemote) {
        this.taskDataSourceRemote = taskDataSourceRemote;
    }

    public Observable createTask(String title,
                                 String deadline,
                                 long deadlineMs,
                                 String tag,
                                 int priorityId,
                                 String priorityLabel) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(title);
        taskEntity.setTag(tag);
        taskEntity.setDeadlineMs(deadlineMs);
        taskEntity.setDeadline(deadline);
        taskEntity.setPriority(new TaskPriorityEntity(priorityId, priorityLabel));
        return taskDataSourceRemote.createTask(taskEntity).map(new Function<TaskEntity, TaskModel>() {
            @Override
            public TaskModel apply(@NonNull TaskEntity taskEntity) throws Exception {
                return TaskMapper.transform(taskEntity);
            }
        });
    }

    public Observable deleteTask(String taskId) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        return taskDataSourceRemote.deleteTask(taskEntity).map(new Function<TaskEntity, TaskModel>() {
            @Override
            public TaskModel apply(@NonNull TaskEntity taskEntity) throws Exception {
                return TaskMapper.transform(taskEntity);
            }
        });
    }
}
