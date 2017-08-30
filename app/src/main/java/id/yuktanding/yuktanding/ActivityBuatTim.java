package id.yuktanding.yuktanding;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityBuatTim extends AppCompatActivity {

    TextInputEditText gk, anchor, leftFlank, rightFlank, pivot;
    TextView textViewAnggota;
    Spinner spinnerOlahraga, spinnerDaerah;
    Button buttonBuatTim;

//    public String[] anggotaValue = {null, null, null, null, null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_tim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewAnggota = (TextView) findViewById(R.id.txt_tambah_anggota_tim);
        gk = (TextInputEditText) findViewById(R.id.edit_pos_gk);
        anchor = (TextInputEditText) findViewById(R.id.edit_pos_anchor);
        leftFlank = (TextInputEditText) findViewById(R.id.edit_pos_left_flank);
        rightFlank = (TextInputEditText) findViewById(R.id.edit_pos_right_flank);
        pivot = (TextInputEditText) findViewById(R.id.edit_pos_pivot);

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
                        gk.setVisibility(View.VISIBLE);
                        anchor.setVisibility(View.VISIBLE);
                        leftFlank.setVisibility(View.VISIBLE);
                        rightFlank.setVisibility(View.VISIBLE);
                        pivot.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViewAnggota.setVisibility(View.GONE);
                gk.setVisibility(View.GONE);
                anchor.setVisibility(View.GONE);
                leftFlank.setVisibility(View.GONE);
                rightFlank.setVisibility(View.GONE);
                pivot.setVisibility(View.GONE);
            }
        });

        gk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK=validate(new EditText[]{gk,anchor,leftFlank,rightFlank,pivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        anchor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK=validate(new EditText[]{gk,anchor,leftFlank,rightFlank,pivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        leftFlank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK=validate(new EditText[]{gk,anchor,leftFlank,rightFlank,pivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        rightFlank.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK=validate(new EditText[]{gk,anchor,leftFlank,rightFlank,pivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        pivot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean fieldsOK=validate(new EditText[]{gk,anchor,leftFlank,rightFlank,pivot});
                Log.d("YukTanding", "Edit Text: " + fieldsOK);

                if(fieldsOK == true) buttonBuatTim.setEnabled(true);
                else  buttonBuatTim.setEnabled(false);
            }
        });

        buttonBuatTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean validate(EditText[] fields){
        for(int i=0; i<fields.length; i++){
            EditText currentField=fields[i];
            if(currentField.getText().toString().length()<=0){
                return false;
            }
        }
        return true;
    }

}
