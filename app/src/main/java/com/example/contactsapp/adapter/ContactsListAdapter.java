package com.example.contactsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.R;
import com.example.contactsapp.model.ContactDataBean;

import java.util.ArrayList;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.MyViewHolder> {
    private final String TAG = ContactsListAdapter.class.getName();
    private Context context;
    private List<ContactDataBean> list = new ArrayList<>();

    public ContactsListAdapter(Context context, List<ContactDataBean> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsListAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getStrName());
        holder.tvPhoneNumber.setText(list.get(position).getStrPhoneNo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhoneNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_list_items_name);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tv_list_items_phone_number);
        }
    }
}
