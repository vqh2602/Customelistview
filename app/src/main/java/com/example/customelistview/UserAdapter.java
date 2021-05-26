package com.example.customelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<com.example.customelistview.User> {
    Context context;
    List<com.example.customelistview.User> lsUser;
    int resource;
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<com.example.customelistview.User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lsUser = objects;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return lsUser.size();
    }

    @Nullable
    @Override
    public com.example.customelistview.User getItem(int position) {
        return lsUser.get(position);
    }

    @Override
    public int getPosition(@Nullable com.example.customelistview.User item) {
        return lsUser.indexOf(item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        //lay ra doi tuong control va view
        TextView txtFullName = convertView.findViewById(R.id.txtFullName);
        ImageButton call = convertView.findViewById(R.id.callPhone);
        ImageButton sendMsg = convertView.findViewById(R.id.sendMessage);
        //lay du lieu tren tung dong va hien thi
        com.example.customelistview.User user = lsUser.get(position);
        //gan cac gia tri hien thi len giao dien
        txtFullName.setText(user.getUserName());
        //xu ly su kien voi cac nut lenh tren item_row
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //thuc hien cuoc goi o day
                Toast.makeText(context, "call: "+user.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //thuc hien gui tin nhac
                Toast.makeText(context, "send message: "+user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
