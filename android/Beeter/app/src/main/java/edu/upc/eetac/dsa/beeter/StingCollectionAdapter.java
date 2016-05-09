package edu.upc.eetac.dsa.beeter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.beeter.client.entity.StingCollection;

public class StingCollectionAdapter extends BaseAdapter {
    private StingCollection stingCollection;
    private LayoutInflater layoutInflater;

    public StingCollectionAdapter(Context context, StingCollection stingCollection){
        layoutInflater = LayoutInflater.from(context);
        this.stingCollection = stingCollection;
    }

    @Override
    public int getCount() {
        return stingCollection.getStings().size();
    }

    @Override
    public Object getItem(int position) {
        return stingCollection.getStings().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_stings, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = stingCollection.getStings().get(position).getCreator();
        String subject = stingCollection.getStings().get(position).getSubject();
        String date = new SimpleDateFormat("MM/dd/yyyy").format(stingCollection.getStings().get(position).getCreationTimestamp());


        viewHolder.textViewName.setText(name);
        viewHolder.textViewSubject.setText(subject);
        viewHolder.textViewDate.setText(date);
        return convertView;
    }

    class ViewHolder{
        TextView textViewName;
        TextView textViewSubject;
        TextView textViewDate;

        ViewHolder(View row){
            this.textViewName = (TextView) row
                    .findViewById(R.id.textViewName);
            this.textViewSubject = (TextView) row
                    .findViewById(R.id.textViewSubject);
            this.textViewDate = (TextView) row
                    .findViewById(R.id.textViewDate);
        }
    }
}
