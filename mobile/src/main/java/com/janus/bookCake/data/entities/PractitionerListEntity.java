package com.janus.bookCake.data.entities;

import android.support.annotation.Keep;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;
import com.janus.bookCake.domain.models.PractitionerModel;

import java.util.ArrayList;
import java.util.HashMap;

@Keep
public final class PractitionerListEntity {

    @SerializedName("practitioners") private HashMap<String, PractitionerModel> practitioners = null;

    public PractitionerListEntity() {
        // Intentionally empty.
    }

    public HashMap<String, PractitionerModel> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(HashMap<String, PractitionerModel> practitioners) {
        this.practitioners = practitioners;
    }

    @Exclude
    public boolean isEmpty() {
        return this.practitioners.isEmpty();
    }

    @Exclude
    public PractitionerModel[] toArray() {
        ArrayList<PractitionerModel> list = new ArrayList<PractitionerModel>(this.practitioners.values());
        PractitionerModel[] strings = list.toArray(new PractitionerModel[this.practitioners.size()]);
        return strings;
    }

}