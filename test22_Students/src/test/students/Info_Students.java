package test.students;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Info_Students extends Activity{
	private Button gotoUpdateBtn;
	private Button gotoDeleteBtn;
	private Button gotoListBtn;
	
	private TextView tv_no;
	private TextView tv_name;
	private TextView tv_grade;
	private TextView tv_callnum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_students);
		
		gotoUpdateBtn = (Button)findViewById(R.id.gotoUpdateBtn);
		gotoDeleteBtn = (Button)findViewById(R.id.gotoDeleteBtn);
		gotoListBtn = (Button)findViewById(R.id.gotoListBtn);
		tv_name=(TextView)findViewById(R.id.nameView);
		tv_grade=(TextView)findViewById(R.id.gradeView);
		tv_callnum=(TextView)findViewById(R.id.callNumView);
		tv_no = (TextView)findViewById(R.id.noView);
		
		tv_no.setText(getInfo1());
		tv_name.setText(getInfo2());
		tv_grade.setText(getInfo3());
		tv_callnum.setText(getInfo4());
		
		
		gotoUpdateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent updateIntent = new Intent(Info_Students.this,Update_Student.class);
				updateIntent.putExtra("info", (tv_no).getText());
				updateIntent.putExtra("info1", (tv_name).getText());
				updateIntent.putExtra("info2", (tv_grade).getText());
				updateIntent.putExtra("info3", (tv_callnum).getText());
				startActivity(updateIntent);
			}
		});
		gotoDeleteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent deleteIntent = new Intent(Info_Students.this,Delete_Student.class);
				deleteIntent.putExtra("info", (tv_no).getText());
				deleteIntent.putExtra("info1", (tv_name).getText());
				deleteIntent.putExtra("info2", (tv_grade).getText());
				deleteIntent.putExtra("info3", (tv_callnum).getText());
				startActivity(deleteIntent);
				
			}
		});
		gotoListBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent listIntent = new Intent(Info_Students.this,List_Students.class);
				startActivity(listIntent);
				
			}
		});
	}

	private CharSequence getInfo4() {
		Intent intent = getIntent();
		String result = "no 実特";
		if(intent.getStringExtra("info")!=null){
			result=intent.getStringExtra("info");
		}
		String[] kkk = result.split(",");
		Log.i("testLog", "split : "+result);
		return kkk[3];
	}

	private CharSequence getInfo3() {
		Intent intent = getIntent();
		String result = "no 実特";
		if(intent.getStringExtra("info")!=null){
			result=intent.getStringExtra("info");
		}
		String[] kkk = result.split(",");
		Log.i("testLog", "split : "+result);
		return kkk[2];
	}

	private CharSequence getInfo2() {
		Intent intent = getIntent();
		String result = "no 実特";
		if(intent.getStringExtra("info")!=null){
			result=intent.getStringExtra("info");
		}
		String[] kkk = result.split(",");
		Log.i("testLog", "split : "+result);
		return kkk[1];
	}

	private CharSequence getInfo1() {
		Intent intent = getIntent();
		String result = "no 実特";
		if(intent.getStringExtra("info")!=null){
			result=intent.getStringExtra("info");
		}
		String[] kkk = result.split(",");
		Log.i("testLog", "split : "+result);
		return kkk[0];
		
		
	}
	

}
