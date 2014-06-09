package test.students;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class List_Students extends Activity {
	private SQLiteDatabase mDatabase;
	private ListView students_lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gotolist);
		
		mDatabase = openOrCreateDatabase("students.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		mDatabase.setLocale(Locale.getDefault());
		mDatabase.setLockingEnabled(true);
		mDatabase.setVersion(3);
		
		students_lv = (ListView)findViewById(R.id.list_Students);
		
		String search = "select name from sqlite_master where type='table' and name='students';";
		Cursor c = mDatabase.rawQuery(search, null);
		startManagingCursor(c);
		if(c.getCount()==0){
			String sql = "create table students(_id integer primary key autoincrement, name text not null, grade text not null, callnum text not null);";
			mDatabase.execSQL(sql);
			Toast.makeText(this, "create Table", Toast.LENGTH_SHORT).show();
		}
		list_create();
		
	}
	private void list_create() {
		Cursor c = mDatabase.query("students", null, null, null, null, null, "_id");
		startManagingCursor(c);
		
		ArrayList<String> list = new ArrayList<String>();
		
		c.moveToFirst();
		while(!(c.isAfterLast())){
			String data="";
			for (int i = 0; i < c.getColumnCount(); i++) {
				data=data.concat(c.getString(i)+",");
			}
			list.add(data);
			c.moveToNext();
		}
		students_lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list));
		
		students_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				Intent intentInfo = new Intent(List_Students.this,Info_Students.class);
				intentInfo.putExtra("info", ((TextView)v).getText());
//				intentInfo.putExtra("info1", ((TextView)v).getText());
//				intentInfo.putExtra("info2", ((TextView)v).getText());
//				intentInfo.putExtra("info3", ((TextView)v).getText());
				//intentInfo.putExtra("info1",getText(position));
				startActivity(intentInfo);
				
				
				
			}
		});
	}

}
