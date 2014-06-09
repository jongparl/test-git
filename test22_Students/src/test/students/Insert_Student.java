package test.students;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_Student extends Activity{
	private SQLiteDatabase mDatabase;
	
	private EditText et_name;
	private EditText et_grade;
	private EditText et_callnum;
	
	private Button add_Btn;
	private Button gotoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_student);
		
		mDatabase = openOrCreateDatabase("students.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		mDatabase.setLocale(Locale.getDefault());//기본 지역
		mDatabase.setLockingEnabled(true);//스레드에 락 설정
		mDatabase.setVersion(3);//버전 설정
		
		et_name = (EditText)findViewById(R.id.et_name);
		et_grade = (EditText)findViewById(R.id.et_grade);
		et_callnum = (EditText)findViewById(R.id.et_callNum);
		add_Btn = (Button)findViewById(R.id.insertOkBtn);
		gotoList = (Button)findViewById(R.id.listgoBtn);
		
		String search = "select name from sqlite_master where type='table' and name='students';";
		Cursor c = mDatabase.rawQuery(search, null);
		startManagingCursor(c);
		if(c.getCount()==0){
			String sql = "create table students(_id integer primary key autoincrement, name text not null, grade text not null, callnum text not null);";
			mDatabase.execSQL(sql);
			Toast.makeText(this, "create Table", Toast.LENGTH_SHORT).show();
		}
		add_Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				
				values.put("name", et_name.getText().toString());
				values.put("grade", et_grade.getText().toString());
				values.put("callnum", et_callnum.getText().toString());
				
				long result = mDatabase.insert("students", null, values);
				Log.i("testLog", "insert result >>>"+result);
				Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();
				
				et_name.setText("");
				et_grade.setText("");
				et_callnum.setText("");
				
			}
		});
		gotoList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent ListIntent = new Intent(Insert_Student.this,List_Students.class);
				startActivity(ListIntent);
				
			}
		});
	}//end onCreate

}//end class
