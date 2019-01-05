package com.janus.bookCake.data.entities.mappers;

import com.janus.bookCake.data.entities.BucketEntity;
import com.janus.bookCake.data.entities.TaskEntity;
import com.janus.bookCake.domain.models.BucketModel;
import com.janus.bookCake.domain.models.TaskModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Matin on 23/02/2017.
 */
public class BucketMapper {

    public static BucketModel transform(BucketEntity bucketEntity) {
        BucketModel bucketModel = null;
        if (bucketEntity != null) {
            bucketModel = new BucketModel();
            if (bucketEntity.getTasks() != null) {
                bucketModel.setTasks(transform(bucketEntity.getTasks()));
            }
        }

        return bucketModel;
    }

    private static HashMap<String, TaskModel> transform(HashMap<String, TaskEntity> tasks) {
        HashMap<String, TaskModel> result = new HashMap<>();
        Iterator it = tasks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, TaskEntity> pair = (Map.Entry)it.next();
            result.put(pair.getKey(), TaskMapper.transform(pair.getValue()));
        }

        return result;
    }
}
