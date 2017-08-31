package id.yuktanding.yuktanding;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityBuatTim extends AppCompatActivity {

    TextInputEditText editGk, editAnchor, editLeftFlank, editRightFlank, editPivot, editNamaTim;
    TextView textViewAnggota;
    Spinner spinnerOlahraga, spinnerDaerah;
    Button buttonBuatTim;

    DatabaseReference databaseTim;

//    public String[] anggotaValue = {null, null, null, null, null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_tim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewAnggota = (TextView) findViewById(R.id.txt_tambah_anggota_tim);
        editGk = (TextInputEditText) findViewById(R.id.edit_pos_gk);
        editAnchor = (TextInputEditText) findViewById(R.id.edit_pos_anchor);
        editLeftFlank = (TextInputEditText) findViewById(R.id.edit_pos_left_flank);
        editRightFlank = (TextInputEditText) findViewById(R.id.edit_pos_right_flank);
        editPivot = (TextInputEditText) findViewById(R.id.edit_pos_pivot);
        editNamaTim = (TextInputEditText) findViewById(R.id.edit_nama_tim);

        buttonBuatTim = (Button) findViewById(R.id.button_buat_tim);

        spinnerOlahraga = (Spinner) findViewById(R.id.spinner_olahraga);
        spinnerDaerah = (Spinner) findViewById(R.id.spinner_daerah);

        spinnerDaerah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) spinnerOlahraga.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerOlahraga.setVisibility(View.GONE);
            }
        });

        spinnerOlahraga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("YukTanding", "" + position);
                switch (position){
                    case 1: {
                        textViewAnggota.setVisibility(View.VISIBLE);
                        editGk.setVisibility(View.VISIBLE);
                        editAnchor.setVisibility(View.VISIBLE);
                        editLeftFlank.setVisibility(View.VISIBLE);
                        editRightFlank.setVisibility(View.VISIBLE);
                        editPivot.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViewAnggota.setVisibility(View.GONE);
                editGk.setVisibility(View.GONE);
                editAnchor.setVisibility(View.GONE);
                editLeftFlank.setVisibility(View.GONE);
                editRightFlank.setVisibility(View.GONE);
                editPivot.setVisibility(View.GONE);
            }
        });

        editGk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK= cekEditText(new EditText[]{editGk, editAnchor, editLeftFlank, editRightFlank, editPivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        editAnchor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK= cekEditText(new EditText[]{editGk, editAnchor, editLeftFlank, editRightFlank, editPivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        editLeftFlank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK= cekEditText(new EditText[]{editGk, editAnchor, editLeftFlank, editRightFlank, editPivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        editRightFlank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK= cekEditText(new EditText[]{editGk, editAnchor, editLeftFlank, editRightFlank, editPivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        editPivot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK= cekEditText(new EditText[]{editGk, editAnchor, editLeftFlank, editRightFlank, editPivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        buttonBuatTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namaTim = editNamaTim.getText().toString();
                String olahraga = spinnerOlahraga.getSelectedItem().toString();
                String daerah = spinnerDaerah.getSelectedItem().toString();

                Log.d("YukTanding", "Bottom Tambah Tim Pressed: " + olahraga);

                databaseTim = FirebaseDatabase.getInstance().getReference("DaftarTim" + olahraga);
                String idTim = databaseTim.push().getKey();
//                String idTim = user.uuid;

                if(olahraga.equals("Futsal")){
                    String gk = editGk.getText().toString();
                    String anchor = editAnchor.getText().toString();
                    String leftFlank = editLeftFlank.getText().toString();
                    String rightFlank = editRightFlank.getText().toString();
                    String pivot = editPivot.getText().toString();

                    ItemTambahTimAdapter itemTambahTimAdapter =
                            new ItemTambahTimAdapter(idTim, namaTim, gk, anchor, leftFlank, rightFlank, pivot, daerah, olahraga);
                    databaseTim.child(idTim).setValue(itemTambahTimAdapter);

                    Log.d("YukTanding", "Tambah Tim Futsal Berhasil");
                    finish();
                }
                else if(olahraga == "Badminton") {

                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean cekEditText(EditText[] fields){
        for(int i=0; i<fields.length; i++){
            EditText currentField=fields[i];
            if(currentField.getText().toString().length()<=0){
                return false;
            }
        }
        return true;
    }

}
