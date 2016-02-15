//package com.example.administrator.game_4_in_a_row;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
//
//public class ListAdapter extends BaseAdapter {
//
//
//    // object like struct
//    static class RowHolder {
//        TextView name;
//        TextView win;
//        TextView loss;
//        TextView standoff;
//        TextView percent_win;
//    }
//
//    private ArrayList<Row> listData;
//    private LayoutInflater layoutInflater;
//
//    public ListAdapter(Context aContext, ArrayList<Row> listData) {
//        this.listData = listData;
//        layoutInflater = LayoutInflater.from(aContext);
//    }
//
//    public ArrayList<Row> getListData(){
//        return this.listData;
//    }
//
//    @Override
//    public int getCount() {
//        return listData.size();
//    }
//
//    @Override
//    public Object getItem(int location) {
//        return listData.get(location);
//    }
//
//    @Override
//    public long getItemId(int location) {
//        return location;
//    }
//
//
//    // implement from BaseAdapter
//    public View getView(int location, View convertView, ViewGroup parent) {
//        RowHolder holder;
//        if (convertView == null) {
//            convertView = layoutInflater.inflate(R.layout.row, null);
//            holder = new ViewHolder();
//            holder.date = (TextView) convertView.findViewById(R.id.date);
//            holder.time = (TextView) convertView.findViewById(R.id.cTime);
//            holder.temperature = (TextView) convertView.findViewById(R.id.temp);
//            holder.info = (TextView)convertView.findViewById(R.id.infoText);
//            holder.image = (ImageView)convertView.findViewById(R.id.img);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.date.setText(listData.get(location).getDate());
//        holder.time.setText(listData.get(location).getTime());
//        holder.temperature.setText(listData.get(location).getTemperature());
//        holder.info.setText(listData.get(location).getInfo());
//        //Loading image from below url into imageView
//
//          Picasso.with(layoutInflater.getContext())
//                  .load(listData.get(location).get_iconUrl())
//                  .into(holder.image);
//
//
//        return convertView;
//    }
//
//}
