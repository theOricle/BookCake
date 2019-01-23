package com.janus.bookCake.data.repositories;

import com.janus.bookCake.data.entities.PractitionerListEntity;
import com.janus.bookCake.data.entities.TagListEntity;
import com.janus.bookCake.data.entities.mappers.PractitionerListMapper;
import com.janus.bookCake.data.entities.mappers.TagListMapper;
import com.janus.bookCake.data.repositories.datasource.PractitionerListDataSourceRemote;
import com.janus.bookCake.domain.models.PractitionerListModel;
import com.janus.bookCake.domain.models.TagListModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * The repository is in charge of getting the data from the DataSource,
 * It will also map data from entity to model
 * Created by Matin on 23/02/2017.
 */
@Singleton
public class PractitionerListRepository {

    private final PractitionerListDataSourceRemote practitionerListDataSourceRemote;

    @Inject
    public PractitionerListRepository(PractitionerListDataSourceRemote practitionerListDataSourceRemote) {
        this.practitionerListDataSourceRemote = practitionerListDataSourceRemote;
    }

    public Observable<PractitionerListModel> getPractitionerList() {

        return practitionerListDataSourceRemote.getPractitionerList().map(new Function<PractitionerListEntity, PractitionerListModel>() {
            @Override
            public PractitionerListModel apply(PractitionerListEntity practitionerListEntity) throws Exception {
                return PractitionerListMapper.transform(practitionerListEntity);
            }
        });
    }
}
