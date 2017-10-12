package capstoneproject.sp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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

import capstoneproject.sp.model.Procedure;
import capstoneproject.sp.adapter.ProcedureListAdapter;
import capstoneproject.sp.R;

public class AddProcedureActivity extends AppCompatActivity {
    TextView recipeName;
    EditText process;
    Button addProcedure;
    ListView showProcedureList;

    DatabaseReference databaseProcedures;

    List<Procedure> procedureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_procedure);

        recipeName = (TextView) findViewById(R.id.tvRecipeName);
        process = (EditText) findViewById(R.id.process);
        showProcedureList = (ListView) findViewById(R.id.showProcedureList);
        addProcedure = (Button) findViewById(R.id.addProcedure);


        Intent intent = getIntent();

        procedureList = new ArrayList<>();

        String id = intent.getStringExtra(AddRecipeActivity.RECIPE_ID);
        String name = intent.getStringExtra(AddRecipeActivity.RECIPE_NAME);

        recipeName.setText(name);

        databaseProcedures = FirebaseDatabase.getInstance().getReference("recipeProcedures").child(id);

        addProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProcedure();
            }
        });

        showProcedureList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                final Procedure procedure = procedureList.get(i);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                String id = procedure.getProcedureID();
                                databaseProcedures.child(id).removeValue();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(AddProcedureActivity.this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;
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
                for (DataSnapshot procedureSnapshot : dataSnapshot.getChildren()) {
                    Procedure procedure = procedureSnapshot.getValue(Procedure.class);
                    procedureList.add(procedure);
                }

                ProcedureListAdapter procedureListAdapter = new ProcedureListAdapter(AddProcedureActivity.this, procedureList);
                showProcedureList.setAdapter(procedureListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveProcedure() {
        String procDesc = process.getText().toString();
        if (!TextUtils.isEmpty(procDesc)) {
            String id = databaseProcedures.push().getKey();

            Procedure procedure = new Procedure(id, procDesc);

            databaseProcedures.child(id).setValue(procedure);
        } else {
            Toast.makeText(this, "Input Fields Required", Toast.LENGTH_LONG).show();
        }
    }

}
