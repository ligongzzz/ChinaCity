package com.China.ChinaCity;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import com.China.ChinaCity.Line.*;
import android.widget.AdapterView.*;

public class SubListActivity extends Activity 
{
	//全局变量
	int intenttype=0;
	int yyflist[]=new int[50];
	List alist=new ArrayList();
	int zmt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		//获取信息
		Intent intent =getIntent();
        Bundle bundle= intent.getExtras();
        zmt=bundle.getInt("zmt");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_list);

		switch (zmt)
		{
			case 1:{
					//设置大字
					TextView textView1=(TextView)findViewById(R.id.sublistTextView1);
					TextView textView2=(TextView)findViewById(R.id.sublistTextView2);
					TextView textView3=(TextView)findViewById(R.id.sublistTextView3);

					textView1.setText("地铁线路列表");
					textView2.setText("中国城共有12条地铁线路，39座车站");
					textView3.setText("以下是地铁线路列表，点击线路可查看详情:");

					//设置列表
					intenttype = 2;
					LineSearch linesearch=new LineSearch();
					linesearch.initlog();
					for (int i=1,n=0;i <= linesearch.line[0][0];i++)
					{
						if (linesearch.line[i][0] != 0)
						{
							alist.add(Integer.toString(i) + "号线");
							yyflist[n++] = i;
						}
					}
					break;
				}
			case 2:{
				    int yyf=bundle.getInt("yyf");
				    LineSearch linesearch=new LineSearch();
					linesearch.initlog();
					//设置大字
					TextView textView1=(TextView)findViewById(R.id.sublistTextView1);
					TextView textView2=(TextView)findViewById(R.id.sublistTextView2);
					TextView textView3=(TextView)findViewById(R.id.sublistTextView3);

					textView1.setText(Integer.toString(yyf)+"号线");
					textView2.setText(Integer.toString(yyf)+"号线的运行区间为"+linesearch.destination[yyf][0]+"至"+linesearch.destination[yyf][1]);
					textView3.setText("以下是该线路的车站列表，点击车站可查看详情:");

					//设置表
					intenttype = 3;
					
					for (int i=1,n=0;i <= linesearch.line[yyf][0];i++)
					{
						if (linesearch.line[yyf][i] != 0)
						{
							alist.add(linesearch.slist[linesearch.line[yyf][i]].name + "站");
							yyflist[n++] =linesearch.line[yyf][i];
						}
					}
					break;
				}
			case 3:{
				    int yyf=bundle.getInt("yyf");
				    LineSearch linesearch=new LineSearch();
					linesearch.initlog();
					//设置大字
					TextView textView1=(TextView)findViewById(R.id.sublistTextView1);
					TextView textView2=(TextView)findViewById(R.id.sublistTextView2);
					TextView textView3=(TextView)findViewById(R.id.sublistTextView3);

					textView1.setText(linesearch.slist[yyf].name+"站");
					textView2.setText("共有"+linesearch.slist[yyf].sl[0]+"条线路经过"+linesearch.slist[yyf].name+"站，车站的水平坐标为("+(int)linesearch.slist[yyf].sx+","+(int)linesearch.slist[yyf].sy+")");
					textView3.setText("以下是该车站的线路列表，点击线路可查看详情:");

					//设置表
					intenttype = 2;

					for (int i=1,n=0;i <= linesearch.slist[yyf].sl[0];i++)
					{
						if (linesearch.slist[yyf].sl[i]!= 0)
						{
							alist.add(Integer.toString(linesearch.slist[yyf].sl[i])+"号线");
							yyflist[n++] =linesearch.slist[yyf].sl[i] ;
						}
					}
					break;
				}
		}
		//列表统一显示器
		ListAdapter adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alist);
        ListView listView = (ListView) findViewById(R.id.sublistListView1);
		listView.setAdapter(adapter1);

		//列表统一控制器
		listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> l, View v, int position, long id)
				{
					Intent intent1 = new Intent(SubListActivity.this, SubListActivity.class);
					intent1.putExtra("zmt", intenttype);
					intent1.putExtra("yyf", yyflist[position]);
					startActivity(intent1);
				}
			});
    }
	
	public void onClickSubListReturn(View view){
		Intent intent1 = new Intent(SubListActivity.this, Sub2Activity.class);
	    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent1);
		finish();
	}
	
	public void onClickSubListButtonBack(View view){
		finish();
	}
}
