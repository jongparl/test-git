package test.students;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Delete_Student extends Activity {
	private SQLiteDatabase mDatabase;
	
	private TextView deleteNo;
	private TextView deleteName;
	private TextView deleteGrade;
	private TextView deleteCallNum;
	
	private Button deleteOkBtn;
	private Button listOkBtn;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_student);
		
		deleteNo = (TextView)findViewById(R.id.noView_delete);
		deleteName = (TextView)findViewById(R.id.nameView_delete);
		deleteGrade = (TextView)findViewById(R.id.gradeView_delete);
		deleteCallNum = (TextView)findViewById(R.id.callNumView_delete);
		
		deleteOkBtn = (Button)findViewById(R.id.DeleteOkBtn);
		listOkBtn=(Button)findViewById(R.id.gotoListBtn_delete);
		
		mDatabase = openOrCreateDatabase("students.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		mDatabase.setLocale(Locale.getDefault());//기본 지역
		mDatabase.setLockingEnabled(true);//스레드에 락 설정
		mDatabase.setVersion(3);//버전 설정
		
		deleteNo.setText(getInfo());
		deleteName.setText(getInfo1());
		deleteGrade.setText(getInfo2());
		deleteCallNum.setText(getInfo3());
		
		deleteOkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int result = mDatabase.delete("students", "_id=?",
						new String[] {deleteNo.getText().toString()});
				Log.i("testLog", "delete result>>>"+result);
				Toast.makeText(getApplicationContext(), "행 삭제 성공", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(Delete_Student.this,List_Students.class);
				startActivity(intent);
				
			}
		});
		listOkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Delete_Student.this,List_Students.class);
				startActivity(intent);
				
				
				
			}
		});
	}
	
	private CharSequence getInfo3() {
		Intent intent = getIntent();
		String result = "no 셋팅";
		if(intent.getStringExtra("info3")!=null){
			result=intent.getStringExtra("info3");
		}
		return result;
	}
	private CharSequence getInfo2() {
		Intent intent = getIntent();
		String result = "no 셋팅";
		if(intent.getStringExtra("info2")!=null){
			result=intent.getStringExtra("info2");
		}
		return result;
	}

	private CharSequence getInfo1() {
		Intent intent = getIntent();
		String result = "no 셋팅";
		if(intent.getStringExtra("info1")!=null){
			result=intent.getStringExtra("info1");
		}
		return result;
	}

	private CharSequence getInfo() {
		Intent intent = getIntent();
		String result = "no 셋팅";
		if(intent.getStringExtra("info")!=null){
			result=intent.getStringExtra("info");
		}
		return result;
	}
}
