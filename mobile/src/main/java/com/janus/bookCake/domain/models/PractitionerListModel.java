package com.janus.bookCake.domain.models;

import java.util.ArrayList;
import java.util.HashMap;

public final class PractitionerListModel {

    private HashMap<String, PractitionerModel> practitioners = null;

    public PractitionerListModel() {
        // Intentionally empty.
    }

    public HashMap<String, PractitionerModel> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(HashMap<String, PractitionerModel> practitioners) {
        this.practitioners = practitioners;
    }

    public boolean isEmpty() {
        return this.practitioners.isEmpty();
    }

    public PractitionerModel[] toArray() {
        ArrayList<PractitionerModel> list = new ArrayList<PractitionerModel>(this.practitioners.values());
        PractitionerModel[] strings = list.toArray(new PractitionerModel[this.practitioners.size()]);
        return strings;
    }
}