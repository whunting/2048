package com.example.hunting.testavos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;

import java.util.List;

class UserAdapter extends BaseAdapter {
    private Context mContext;
    private List<AVObject> mList;

    public UserAdapter(List<AVObject> list, Context context) {
        super();
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder ;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item,parent,false);
            viewholder = new ViewHolder();
            viewholder.hname= convertView.findViewById(R.id.list_item_name);
            viewholder.hscore= convertView.findViewById(R.id.list_item_score);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.hname.setText(mList.get(position).getAVUser("owner") == null ?
                "" : mList.get(position).getAVUser("owner").getUsername());
        viewholder.hscore.setText(mList.get(position).getInt("score") == 0 ?
                "" : String.valueOf(mList.get(position).getInt("score")));
        return convertView;
    }

    /** 创建 ViewHolder */
    class ViewHolder{
        TextView hname;
        TextView hscore;
    }
}
