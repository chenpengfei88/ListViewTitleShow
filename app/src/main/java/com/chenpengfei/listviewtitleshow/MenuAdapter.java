package com.chenpengfei.listviewtitleshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 2015/5/13.
 */
public class MenuAdapter extends BaseExpandableListAdapter {

    private LayoutInflater lf;
    private ArrayList<Parent> list = new ArrayList<Parent>();

    public MenuAdapter(LayoutInflater lf, ArrayList<Parent> list){
        this.lf = lf;
        this.list = list;
    }
    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildArrayList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildArrayList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = lf.inflate(R.layout.title, null);
        }
        ((TextView)convertView.findViewById(R.id.tid)).setText(((Parent)getGroup(groupPosition)).getName());
        convertView.setTag("parent");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = lf.inflate(R.layout.item, null);
        }
        Parent groupName = (Parent)getGroup(groupPosition);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(groupName.getChildArrayList().get(childPosition).getName());
        convertView.setTag("child="+groupName.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
