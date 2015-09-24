package com.example.nielgong.myapplication;

import java.util.ArrayList;

/**
 * Created by nielgong on 9/22/15.
 */
public class Group {
    private int img;
    private ArrayList<SoftWare> child;
    private String title;
    private boolean isChecked;

    public Group(int i, String title){
        this.img = i;
        this.child = new ArrayList<SoftWare>();
        this.title = title;
        this.isChecked = false;
    }

    public void setChecked(Boolean b){
        this.isChecked = b;
    }

    public void toggle(){
        this.isChecked = !isChecked;
    }

    public boolean getChecked(){
        return this.isChecked;
    }

    public void setImg(int a){
        this.img = a;
    }

    public int getImg(){
        return this.img;
    }

    public void setTitle(String s){
        this.title = s;
    }

    public String getTitle(){
        return this.title;
    }

    public void addChildrenItem(SoftWare s){
        child.add(s);
    }

    public int getChildrenCount(){
        return child.size();
    }

    public SoftWare getChildItem(int index) {
        return child.get(index);

    }

}
