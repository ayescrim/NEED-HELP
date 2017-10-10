package capstoneproject.sp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProcedureList extends ArrayAdapter<Procedure> {

    private Activity context;

    List<Procedure> procedureList;

    public ProcedureList(Activity context, List<Procedure> procedureList){
        super(context, R.layout.procedure_layout, procedureList);
        this.context = context;
        this.procedureList = procedureList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewProc = inflater.inflate(R.layout.procedure_layout, null, true);
        TextView textViewProcedure = (TextView) listViewProc.findViewById(R.id.textViewProcedure);

        Procedure procedure = procedureList.get(position);

        textViewProcedure.setText(procedure.getProcDesc());

        return listViewProc;
    }
}
