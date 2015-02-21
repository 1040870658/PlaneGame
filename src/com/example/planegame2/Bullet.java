package com.example.planegame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Bullet extends MovingAbs{

	public Bullet(Context context, int viewH, int viewW) {
		super(context, viewH, viewW);
		
		mov_speed = -30;
		locat_x = 0;
		locat_y = 0;
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet2);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
	}
	
	public void Boom(MyView myView, float x, float y) {}
	
	public void destroy(){
		if(bitmap.isRecycled() == false)
			bitmap.recycle();
	}

	@Override
	public void movAbs(float speed) {
		locat_y += speed;
	}

	@Override
	public void DrawAbs(Canvas canvas) {
		canvas.drawBitmap(bitmap, locat_x, locat_y, null);
	}
	
}
