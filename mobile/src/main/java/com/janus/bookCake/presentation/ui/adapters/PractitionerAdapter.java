package com.janus.bookCake.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.janus.bookCake.R;
import com.janus.bookCake.domain.models.PractitionerModel;

import java.util.List;

public class PractitionerAdapter extends RecyclerView.Adapter<PractitionerAdapter.MyViewHolder> {

    private List<PractitionerModel> pList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pName, pDetail;
        public ImageView setIcn;

        public MyViewHolder(View view){
            super(view);
            setIcn = view.findViewById(R.id.icon);
            pName = view.findViewById(R.id.title);
            pDetail = view.findViewById(R.id.detail);
        }
    }


    public PractitionerAdapter(List<PractitionerModel> pracList) {
        this.pList = pracList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_practitioner_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PractitionerModel model = pList.get(position);
        holder.pName.setText(model.getFullName());
        holder.pDetail.setText(model.getProfession());
//        holder.setIcn.setImageResource(Integer.parseInt(model.get()));
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }
}