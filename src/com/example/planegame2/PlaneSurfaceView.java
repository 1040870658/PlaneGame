package com.example.planegame2;

import java.util.ArrayList;

import com.example.planegame2.Result;
import com.example.planegame2.Record;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.StaticLayout;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

public class PlaneSurfaceView extends SurfaceView implements Callback{

	private Background background;
	private Hero hero;
	private ArrayList<Bullet> al_bt = new ArrayList<Bullet>();
	private ArrayList<Enemy> al_ey = new ArrayList<Enemy>();
	private ArrayList<Enemy> al_bm = new ArrayList<Enemy>(); 
	private final int stamp = 50;
	private int height;
	private int width;
	private SurfaceHolder sfhHolder;
	private Paint paint;
	private MyThread thread;
	private Context context;
	private Record record;
	boolean flag;
	private Canvas canvas;
	int enemyfactory = 0;
	
	public PlaneSurfaceView(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		this.context = context;
		sfhHolder = this.getHolder();
		sfhHolder.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		record = new Record();
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	    
		height = getHeight();
		width = getWidth();
		
		background = new Background(context,height,width);
		hero = new Hero(context,height,width);
		
		flag = true;
		thread = new MyThread();
		thread.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		flag = false;
	}
	
	private void myDraw(){
		canvas = sfhHolder.lockCanvas();
		try {
			background.MoveBackground(canvas);
			hero.DrawAbs(canvas);
			for(int i = 0;i != al_bt.size();i ++){
				al_bt.get(i).DrawAbs(canvas);
			}
			for(int i = 0;i != al_ey.size();i ++){
				al_ey.get(i).DrawAbs(canvas);
			}
			for(int i = 0;i != al_bm.size();i ++){
				al_bm.get(i).DrawAni(canvas);
			}
			al_bm.clear();
		} catch (Exception e) {
		}
		finally{
			if(canvas != null){
				sfhHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//mov hero
		float speed;
		speed = hero.getMov_speed();
		if (hero.getLocat_x() + hero.getWidth() / 2 > event.getX())
			speed = -speed;
		hero.movAbs(speed);
		
		Bullet b1 = new Bullet(context, height, width);
		Bullet b2 = new Bullet(context, height, width);
		hero.shoot(al_bt, b1, b2);
		return super.onTouchEvent(event);
	}
	 
	private void logic(){
		enemyfactory ++;
		Log.e("num", enemyfactory+"");
		background.moveY();
		
		
		for(int i = 0;i != al_bt.size();i ++){
			Bullet bullet = al_bt.get(i);
			if(bullet.getLocat_y() <= -20){
				al_bt.remove(i);
				bullet.destroy();
			}
			else{
				bullet.movAbs(bullet.getMov_speed());
			}
		}
		
		if(20 == enemyfactory){
			Enemy enemy = new Enemy(context, height, width);
			enemyfactory = 0;
			al_ey.add(enemy);
		}
		for(int j = 0;j != al_ey.size();j ++){
			Enemy enemy = al_ey.get(j);
			if(enemy.getLocat_y() > height){
				al_ey.remove(j);
				enemy.destroy();
				record.addPoint(-1);
			}
			else {
				enemy.movAbs(enemy.getMov_speed());
			}
		}
		for(int i = 0;i != al_bt.size();i ++){
			Bullet bullet = al_bt.get(i);
			float blx = bullet.getLocat_x();
			float bly = bullet.getLocat_y();
			float bw = bullet.getWidth();
			float bh = bullet.getHeight();
			RectF r1 = new RectF(blx,bly,blx+bw,bly+bh);
			for(int j = 0;j != al_ey.size();j ++){
				
				Enemy enemy = al_ey.get(j);
				float elx = enemy.getLocat_x();
				float ely = enemy.getLocat_y();
				float ew = enemy.getWidth();
				float eh = enemy.getHeight();
				RectF r2 = new RectF(elx,ely,elx+ew,ely+eh);
				
				if(bumpWithRect(r1, r2)){
					al_bm.add(enemy);
					al_bt.remove(i);
					al_ey.remove(j);
					bullet.destroy();
					record.addCount(enemy.getType());
					record.addPoint(enemy.getPoint());
				}
			}
		}
		for (int i = 0; i != al_ey.size(); i++) {
			Enemy enemy = al_ey.get(i);
			float elx = enemy.getLocat_x();
			float ely = enemy.getLocat_y();
			float ew = enemy.getWidth();
			float eh = enemy.getHeight();
			RectF r2 = new RectF(elx,ely,elx+ew,ely+eh);
			
			float hlx = hero.getLocat_x();
			float hly = hero.getLocat_y();
			float hw = hero.getWidth();
			float hh = hero.getHeight();
			RectF r3 = new RectF(hlx, hly, hlx + hw, hly + hh);
			if (bumpWithRect(r2, r3)) {
				Bundle data = new Bundle();
				data.putSerializable("record", record);
				Intent intent = new Intent(context, Result.class);
				intent.putExtras(data);
				context.startActivity(intent);
				flag = false;
			}
		}
	}
	
	public boolean bumpWithRect(RectF rect1,RectF rect2)
	{
		float x1=rect1.left,y1=rect1.top,w1=rect1.width(),h1=rect1.height();
		float x2=rect2.left,y2=rect2.top,w2=rect2.width(),h2=rect2.height();
		if(x1>=x2 && x1>=x2+w2)
			return false;
		else if(x1<=x2 && x1+w1<=x2)
			return false;
		else if(y1>=y2 && y1>=y2+h2)
			return false;
		else if(y1<=y2 && y2>=y1+h1)
			return false;
		return true;
	}
	
	private class MyThread extends Thread{
		public void run(){
			while(flag){
				try {
					long begin = System.currentTimeMillis();
					myDraw();
					logic();
					long end = System.currentTimeMillis();
					if(end - begin < stamp){
						Thread.sleep(stamp-(end - begin));
					}
				} catch (Exception e) {
				}
			}
		}
	}
}
