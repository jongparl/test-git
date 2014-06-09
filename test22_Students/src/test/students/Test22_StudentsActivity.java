package test.students;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Test22_StudentsActivity extends Activity {
    private Button gotoInsertBtn;
    private Button gotoListBtn;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        gotoInsertBtn=(Button)findViewById(R.id.gotoInsertBtn);
        gotoListBtn = (Button)findViewById(R.id.gotoListBtn);
        
        gotoInsertBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent insertIntent = new Intent(Test22_StudentsActivity.this,Insert_Student.class);
				startActivity(insertIntent);
			}
		});
        gotoListBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent ListIntent = new Intent(Test22_StudentsActivity.this,List_Students.class);
				startActivity(ListIntent);
				
			}
		});
    }
}