package test.students;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update_Student extends Activity {
	private SQLiteDatabase mDatabase;
	private EditText update_name;
	private EditText update_grade;
	private EditText update_callNum;
	private TextView update_no;
	
	private Button updateOkBtn;
	private Button gotoListBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_student);
		
		update_no=(TextView)findViewById(R.id.deleteNoView);
		update_name=(EditText)findViewById(R.id.et_name_update);
		update_grade=(EditText)findViewById(R.id.et_grade_update);
		update_callNum=(EditText)findViewById(R.id.et_callNum_update);
		updateOkBtn=(Button)findViewById(R.id.updateOkBtn);
		gotoListBtn=(Button)findViewById(R.id.listgoBtn_update);
		
		mDatabase = openOrCreateDatabase("students.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		mDatabase.setLocale(Locale.getDefault());//기본 지역
		mDatabase.setLockingEnabled(true);//스레드에 락 설정
		mDatabase.setVersion(3);//버전 설정
		
		update_no.setText(getInfo());
		update_name.setText(getInfo1());
		update_grade.setText(getInfo2());
		update_callNum.setText(getInfo3());
		
		updateOkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put("name", update_name.getText().toString());
				values.put("grade", update_grade.getText().toString());
				values.put("callnum", update_callNum.getText().toString());
				
				int result = mDatabase.update("students", values, "_id=?",
						new String[] {update_no.getText().toString()});
				
				Log.i("testLog", "update result>>>"+result);
				Toast.makeText(getApplicationContext(), "행 수정 성공", Toast.LENGTH_SHORT).show();
				
				update_name.setText("");
				update_grade.setText("");
				update_callNum.setText("");
				
				
			}
		});
		
		gotoListBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent listintent=new Intent(Update_Student.this,List_Students.class);
				startActivity(listintent);
				
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
