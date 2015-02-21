package com.example.planegame2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Result extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	private String[] enemy = {"enemy_1: ","enemy_2: ","enemy_3: ","Point: "};
	private int[] pic = {R.drawable.enemy1,R.drawable.enemy2,R.drawable.enemy3,R.drawable.hero1};
	private Record record;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		Intent intent = getIntent();
		record = (Record)intent.getSerializableExtra("record");
		int[] count = record.getCount();
		
		for(int i = 0;i < 3;i ++){
			enemy[i] += count[i];
		}
		enemy[3] += record.getPoint();
		List<Map<String,Object>>listItems = new ArrayList<Map<String,Object>>();
		for(int i = 0;i != enemy.length;i ++){
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("header", pic[i]);
			listItem.put("desc", enemy[i]);
			listItems.add(listItem);
		}
		SimpleAdapter adapter = new SimpleAdapter(this,listItems,R.layout.cell,
				new String[]{"header","desc"},new int[]{R.id.image,R.id.txt});
		ListView list =(ListView)findViewById(R.id.list);
		list.setAdapter(adapter);
	}
	public void EXIT(View Source){
		System.exit(0);
	}
}
