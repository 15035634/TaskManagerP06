package sg.edu.rp.c347.taskmanagerp06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText taskname, descr;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        taskname = (EditText)findViewById(R.id.editTextTaskname);
        descr = (EditText)findViewById(R.id.editTextDescr);
        add = (Button)findViewById(R.id.buttonAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(SecondActivity.this);


                String row_affected = String.valueOf(dbh.insertTask(taskname.getText().toString(),  descr.getText().toString()));
                dbh.close();
               Toast.makeText(getBaseContext(), "Inserted", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
