package com.example.planegame2;

import java.util.ArrayList;

import com.example.planegame2.Bullet;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Hero extends MovingAbs{
	
	private Bullet bullet;
	private final int adj = 18;
	public Hero(Context context,int viewH,int viewW){
		super(context,viewH,viewW);
		
		//构造可移动位图
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.hero1);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		
		locat_x = viewW/2;
		locat_y = viewH-height;
	}
	
	public void movAbs(float speed){
		locat_x += speed;
	}
	
	public void DrawAbs(Canvas canvas){
		canvas.drawBitmap(bitmap,locat_x,locat_y,	null);
	}
	
	public void shoot(ArrayList<Bullet> al_rect,Bullet b1,Bullet b2){
					
		b1.setLocat_x(locat_x + adj);
		b1.setLocat_y(locat_y);
		
		b2.setLocat_x(locat_x + width - adj);
		b2.setLocat_y(locat_y);
		
		al_rect.add(b1);
		al_rect.add(b2);
	}
}
