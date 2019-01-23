package com.janus.bookCake.data.entities.mappers;

import com.janus.bookCake.data.entities.PractitionerListEntity;
import com.janus.bookCake.data.entities.TagListEntity;
import com.janus.bookCake.domain.models.PractitionerListModel;
import com.janus.bookCake.domain.models.TagListModel;

/**
 * Created by Matin on 23/02/2017.
 */
public class PractitionerListMapper {

    public static PractitionerListModel transform(PractitionerListEntity practitionerListEntity) {
        PractitionerListModel practitionerListModel = new PractitionerListModel();
        if (practitionerListEntity != null) {
            practitionerListModel.setPractitioners(practitionerListEntity.getPractitioners());
        }

        return practitionerListModel;
    }
}
