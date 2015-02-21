package com.example.planegame2;

import java.io.Serializable;

public  class Record implements Serializable{
	private int point;
	private int type;
	private int[] count = new int[4];
	public Record(){
		point = 0;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public void addPoint(int point){
		this.point += point;
	}
	public void addCount(int type){
		this.count[type]++;
	}
	public int[] getCount(){
		return count;
	}
}
