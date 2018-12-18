package com.example.hunting.testavos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;

import java.util.List;

public class rankAdapter extends BaseAdapter {
    private Context rContext;
    private List<AVObject> rList;

    public rankAdapter(List<AVObject> list, Context context) {
        super();
        this.rContext = context;
        this.rList = list;
    }

    @Override
    public int getCount() {
        return rList.size();
    }

    @Override
    public Object getItem(int position) {
        return rList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder ;
        if (convertView == null) {
            convertView = LayoutInflater.from(rContext)
                    .inflate(R.layout.list_rankitem,parent,false);
            viewholder = new ViewHolder();
            viewholder.hname= convertView.findViewById(R.id.list_item_rankname);
            viewholder.hscore= convertView.findViewById(R.id.list_item_rankscore);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.hname.setText(rList.get(position).getString("username") == null ?
                "用户不存在" : rList.get(position).getString("username"));
        viewholder.hscore.setText(rList.get(position).getInt("bestScore") == 0 ?
                "0" : String.valueOf(rList.get(position).getInt("bestScore")));
        return convertView;
    }

    /** 创建 ViewHolder */
    class ViewHolder{
        TextView hname;
        TextView hscore;
    }
}
