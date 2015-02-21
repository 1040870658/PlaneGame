package com.example.planegame2;

import com.example.planegame2.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;

public abstract class MovingAbs {
	protected Context context;
	protected Bitmap bitmap;
	protected float locat_x;// 飞机当前横坐标
	protected float locat_y;// 飞机当前纵坐标
	protected float width;
	protected float height;
	protected int viewH;
	protected int viewW;
	protected float mov_speed = 15;
	protected int animation_src;
	protected AnimationDrawable anim;
	protected Bitmap b0;
	protected Bitmap b1;
	protected Bitmap b2;
	protected Bitmap b3;

	public MovingAbs(Context context,int viewH,int viewW){
		this.context = context;
		this.viewH = viewH;
		this.viewW = viewW;
	};
	
	public void Boom(MyView myView, float x, float y) {

		myView.setLocation((int) y + 30, (int) x + 30);
		myView.setVisibility(View.INVISIBLE);
		myView.setBackgroundResource(animation_src);
		anim = (AnimationDrawable) myView.getBackground();
		anim.start();
		myView.setVisibility(View.VISIBLE);
		anim.stop();
	}
	
	public abstract void movAbs(float speed);
	public abstract void DrawAbs(Canvas canvas);

	protected Bitmap getBitmap() {
		return bitmap;
	}

	protected void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	protected float getLocat_x() {
		return locat_x;
	}

	protected void setLocat_x(float locat_x) {
		this.locat_x = locat_x;
	}

	protected float getLocat_y() {
		return locat_y;
	}

	protected void setLocat_y(float locat_y) {
		this.locat_y = locat_y;
	}

	protected float getWidth() {
		return width;
	}

	protected void setWidth(float width) {
		this.width = width;
	}

	protected float getHeight() {
		return height;
	}

	protected void setHeight(float height) {
		this.height = height;
	}

	protected float getMov_speed() {
		return mov_speed;
	}

	protected void setMov_speed(float mov_speed) {
		this.mov_speed = mov_speed;
	}
	
}
