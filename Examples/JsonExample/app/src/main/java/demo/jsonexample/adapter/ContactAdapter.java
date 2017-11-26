package demo.jsonexample.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import demo.jsonexample.domain.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {

    static class ViewHolder {
        public TextView txtName;
        public TextView txtTelephone;
    }

    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Contact contact = getItem(position);

        if (contact != null) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);

                ViewHolder holder = new ViewHolder();
                holder.txtName = (TextView) view.findViewById(android.R.id.text1);
                holder.txtTelephone = (TextView) view.findViewById(android.R.id.text2);
                view.setTag(holder);
            }

            ViewHolder holder = (ViewHolder) view.getTag();
            holder.txtName.setText(contact.getName());
            holder.txtTelephone.setText(contact.getTelephone());
        }

        return view;
    }

}