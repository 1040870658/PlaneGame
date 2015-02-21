package com.example.planegame2;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Background {
	private Bitmap bitmap;
	private Context context;
	private int count;
	private int move = 2;
	private int height;
	private int width;
	private int resetB;
	private int X;
	private int Y;
	private Matrix matrix;
	
	public Background(Context context,int height,int width){
		this.height = height;
		this.width = width;
		this.context = context;
		X = 0;
		Y = 0;
		resetB = height/5;
		count = 0;
		matrix = new Matrix();
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		matrix.postScale((float)1.5*width/bitmap.getWidth(),(float)1.5*height/bitmap.getHeight(),(float) width/2,(float) height/2);
 	}
	public void MoveBackground(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bitmap, matrix,null);
	}
	public void moveY(){
		if(count > resetB){
			matrix.reset();
			matrix.postScale((float)1.5*width/bitmap.getWidth(),(float)1.5*height/bitmap.getHeight(),(float) width/2,(float) height/2);
			count = 0;
		}
		count += move;
		matrix.postTranslate(0, move);
	}
}
