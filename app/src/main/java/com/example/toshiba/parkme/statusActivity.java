package com.example.toshiba.parkme;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class statusActivity extends ArrayAdapter {

    private Activity context;
    private List<User> userinfo;

   public statusActivity(Activity context, List<User> userinfo){
       super(context,R.layout.status_layout, userinfo);

       this.context = context;
       this.userinfo =userinfo;
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.status_layout,null,true);

        TextView name  = (TextView) listViewItem.findViewById(R.id.name1);
        TextView email = (TextView) listViewItem.findViewById(R.id.email1);
        TextView phone = (TextView) listViewItem.findViewById(R.id.phonenumber1);
        TextView from = (TextView) listViewItem.findViewById(R.id.bookedfrom);
        TextView to = (TextView) listViewItem.findViewById(R.id.bookedto);

        User user = userinfo.get(position);
        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone_number());
        from.setText(user.getFrom());
        to.setText(user.getTo());

        return listViewItem;
    }
}
