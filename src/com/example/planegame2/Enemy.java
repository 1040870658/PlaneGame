package com.example.planegame2;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Enemy extends MovingAbs{

	private int type;
	private int point;
	public Enemy(Context context, int viewH, int viewW) {
		super(context, viewH, viewW);
		
		type = (int)(Math.random()*3);
		if (0 == type) {
			bitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy1);
			height = bitmap.getHeight();
			width = bitmap.getWidth();
			b0 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy1_down1);
			b1 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy1_down2);
			b2 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy1_down3);
			b3 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy1_down4);
			mov_speed = 12;
			point = 1;
		}
		if (1 == type) {
			bitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy2);
			height = bitmap.getHeight();
			width = bitmap.getWidth();
			b0 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy2_down1);
			b1 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy2_down2);
			b2 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy2_down3);
			b3 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy2_down4);
			mov_speed = 10;
			point = 5;
		}
		if (2 == type) {
			bitmap = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy3);
			height = bitmap.getHeight();
			width = bitmap.getWidth();
			b0 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy3_down1);
			b1 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy3_down2);
			b2 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy3_down3);
			b3 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.enemy3_down4);
			mov_speed = 8;
			point = 3;
		}
		locat_x = (float) Math.random()*viewW;
		locat_y = height;
	}

	@Override
	public void movAbs(float speed) {
		locat_y += mov_speed;
		//rectF.set(locat_x, locat_y, locat_x + width, locat_y + height);
	}

	@Override
	public void DrawAbs(Canvas canvas) {
		canvas.drawBitmap(bitmap,locat_x,locat_y,	null);
	}

	public void destroy(){
		if(bitmap.isRecycled() == false)
			bitmap.recycle();
	}

	public void DrawAni(Canvas canvas){
		canvas.drawBitmap(b0,locat_x,locat_y,	null);
		canvas.drawBitmap(b1,locat_x,locat_y,	null);
		canvas.drawBitmap(b2,locat_x,locat_y,	null);
		canvas.drawBitmap(b3,locat_x,locat_y,	null);
	}
	public int getType(){
		return type;
	}
	public int getPoint(){
		return point;
	}
}
