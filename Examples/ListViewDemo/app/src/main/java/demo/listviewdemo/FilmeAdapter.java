package demo.listviewdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by felipe on 11/8/17.
 */

public class FilmeAdapter extends ArrayAdapter<Filme> {

    static class ViewHolder {
        public TextView textView1;
        public TextView textView2;
    }

    public FilmeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Filme[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, @NonNull View convertView, ViewGroup parent) {
        View view = null;

        Filme filme = getItem(position);
        if (filme != null) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);

                ViewHolder holder = new ViewHolder();
                holder.textView1 = (TextView) view.findViewById(android.R.id.text1);
                holder.textView2 = (TextView) view.findViewById(android.R.id.text2);
                view.setTag(holder);
            }

            ViewHolder holder = (ViewHolder) view.getTag();
            holder.textView1.setText(filme.titulo);
            holder.textView2.setText(Integer.toString(filme.anoLancamento));
        }

        return view;
    }

}