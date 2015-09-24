package com.example.nielgong.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nielgong on 9/21/15.
 */
public class Adapter extends BaseExpandableListAdapter {

    //public static List<SoftWare> childData = new ArrayList<SoftWare>();

    private Context context;

    List<String> parent_data;//group............
    Map<String, List<SoftWare>> map;

    public static List<Boolean> isSelected;

    public static Map<String,List<Boolean>> slt;

    private LayoutInflater inflater;

    int[] img = {R.drawable.visualstudio, R.drawable.androidstudio, R.drawable.google,
            R.drawable.notepad, R.drawable.ie, R.drawable.eclipse};



    public void init(){
        parent_data = new ArrayList<String>();
        parent_data.add("Group_1");
        parent_data.add("Group_2");

        map = new HashMap<String,List<SoftWare>>();
        List<SoftWare> list_1 = new ArrayList<SoftWare>();
        list_1.add(new SoftWare(img[0],"Visual Studio",5,3650,4));
        list_1.add(new SoftWare(img[1], "Android Studio", 4, 3850, 4));
        list_1.add(new SoftWare(img[2], "Google Chrome", 3, 3900, 2));
        map.put("Group_1", list_1);

        slt = new HashMap<String,List<Boolean>>();
        List<Boolean> c_list_1 = new ArrayList<Boolean>();
        c_list_1.add(false);
        c_list_1.add(false);
        c_list_1.add(false);
        slt.put("Group_1",c_list_1);

        List<SoftWare> list_2 = new ArrayList<SoftWare>();
        list_2.add(new SoftWare(img[3],"Notepad++",2,3400,2));
        list_2.add(new SoftWare(img[4],"Internet explore",1, 3400,1));
        list_2.add(new SoftWare(img[5],"Eclipse",4,3650,2));
        map.put("Group_2", list_2);

        slt = new HashMap<String,List<Boolean>>();
        List<Boolean> c_list_2 = new ArrayList<Boolean>();
        c_list_2.add(false);
        c_list_2.add(false);
        c_list_2.add(false);
        slt.put("Group_2",c_list_2);


        isSelected = new ArrayList<>();



    }




    public Adapter(Context context){
        super();
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();

    }

    @Override
    public int getGroupCount() {
        return parent_data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = parent_data.get(groupPosition);
        int size = map.get(key).size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parent_data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parent_data.get(groupPosition);
        return map.get(key).get(childPosition);
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
        String title = parent_data.get(groupPosition);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.parent_layout,null);
        }

        TextView tv = (TextView)convertView.findViewById(R.id.parentText);
        tv.setText(title);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        final String key = parent_data.get(groupPosition);

        if(convertView==null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.child_layout,null);
            viewHolder.softImg = (ImageView)view.findViewById(R.id.childImg);
            viewHolder.softName = (TextView)view.findViewById(R.id.softName);
            viewHolder.softInfo = (TextView)view.findViewById(R.id.softInfo);
           // viewHolder.softSelect=(CheckBox)view.findViewById(R.id.softSelect);
////
//            viewHolder.softSelect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CheckBox cb = (CheckBox)v;
//                    cb.isChecked();
//                    slt.get(key).set(childPosition, true);
//
//
//                }
//            });

            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }


        String softInfos = "CPU: " + map.get(key).get(childPosition).getsFCPU();
        softInfos += " GPU: " + map.get(key).get(childPosition).getsFGPU();
        softInfos += " Storage: " + map.get(key).get(childPosition).getsFStorage();
        viewHolder.softName.setText(map.get(key).get(childPosition).getSfName());
        viewHolder.softImg.setBackgroundResource(map.get(key).get(childPosition).getImg());
        viewHolder.softInfo.setText(softInfos);
        //viewHolder.softSelect.setChecked(slt.get(key).get(childPosition));
        notifyDataSetChanged();




        return view;
    }

    public final class ViewHolder
    {
        public ImageView softImg;
        public TextView softName;
        public TextView softInfo;
        //public CheckBox softSelect;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
