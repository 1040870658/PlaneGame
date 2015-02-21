package com.example.planegame2;

import android.content.Context;
import android.widget.ImageView;

public class MyView extends ImageView{

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void setLocation(int x,int y){
		this.setFrame(x, y, x+100, y+100);
	}
}
