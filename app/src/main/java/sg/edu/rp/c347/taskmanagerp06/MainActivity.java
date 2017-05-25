package sg.edu.rp.c347.taskmanagerp06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int requestCodes = 1;
    ListView lv;
    ArrayList<Task> tasks;
    ArrayAdapter<String> aa;
    Button add;
    EditText tn, de;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button)findViewById(R.id.buttonAdd);
        tn = (EditText)findViewById(R.id.editTextTaskname);
        de = (EditText)findViewById(R.id.editTextDescr);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity.this);


                String row_affected = String.valueOf(dbh.insertTask(tn.getText().toString(),  de.getText().toString()));                dbh.close();
                Toast.makeText(getBaseContext(), "Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        DBHelper db = new DBHelper(MainActivity.this);

        tasks  = new ArrayList<Task>();
        lv = (ListView)findViewById(R.id.lvTasks);
        aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, tasks);

        tasks.addAll(db.getAllTasks());
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        db.close();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == this.requestCodes ) {
            DBHelper db = new DBHelper(MainActivity.this);
            tasks = db.getAllTasks();
           aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1 , tasks);
            lv.setAdapter(aa);
        }
    }
}

