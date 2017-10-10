package capstoneproject.sp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddProcedure extends AppCompatActivity {
    TextView recipeName;
    EditText process;
    Button addProcedure;
    ListView showProcedureList;

    DatabaseReference databaseProcedures;

    List<Procedure> procedureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_procedure);

        recipeName = (TextView) findViewById(R.id.recipeName);
        process = (EditText) findViewById(R.id.process);
        showProcedureList = (ListView) findViewById(R.id.showProcedureList);
        addProcedure = (Button) findViewById(R.id.addProcedure);


        Intent intent = getIntent();

        procedureList = new ArrayList<>();

        String id = intent.getStringExtra(MainActivity.RECIPE_ID);
        String name = intent.getStringExtra(MainActivity.RECIPE_NAME);

        recipeName.setText(name);

        databaseProcedures = FirebaseDatabase.getInstance().getReference("recipeProcedures").child(id);

        addProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProcedure();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseProcedures.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                procedureList.clear();
                for(DataSnapshot procedureSnapshot : dataSnapshot.getChildren()){
                    Procedure procedure = procedureSnapshot.getValue(Procedure.class);
                    procedureList.add(procedure);
                }

                ProcedureList procedureListAdapter = new ProcedureList(AddProcedure.this, procedureList);
                showProcedureList.setAdapter(procedureListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveProcedure(){
        String procDesc = process.getText().toString();
        if(!TextUtils.isEmpty(procDesc)){
            String id = databaseProcedures.push().getKey();

            Procedure procedure = new Procedure(id, procDesc);

            databaseProcedures.child(id).setValue(procedure);
        }else{
            Toast.makeText(this, "Input Fields Required", Toast.LENGTH_LONG).show();
        }
    }

}
