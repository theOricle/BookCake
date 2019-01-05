package com.janus.bookCake.data.entities.mappers;

import com.janus.bookCake.data.entities.TagListEntity;
import com.janus.bookCake.domain.models.TagListModel;

/**
 * Created by Matin on 23/02/2017.
 */
public class TagListMapper {

    public static TagListModel transform(TagListEntity tagListEntity) {
        TagListModel tagListModel = new TagListModel();
        if (tagListEntity != null) {
            tagListModel.setTags(tagListEntity.getTags());
        }

        return tagListModel;
    }
}
