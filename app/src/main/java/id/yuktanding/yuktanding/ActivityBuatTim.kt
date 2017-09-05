package id.yuktanding.yuktanding

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ActivityBuatTim : AppCompatActivity() {

    internal lateinit var editGk: TextInputEditText
    internal lateinit var editAnchor: TextInputEditText
    internal lateinit var editLeftFlank: TextInputEditText
    internal lateinit var editRightFlank: TextInputEditText
    internal lateinit var editPivot: TextInputEditText
    internal lateinit var editNamaTim: TextInputEditText
    internal lateinit var textViewAnggota: TextView
    internal lateinit var spinnerOlahraga: Spinner
    internal lateinit var spinnerDaerah: Spinner
    internal lateinit var buttonBuatTim: Button

    internal var user: FirebaseUser? = null

    lateinit var databaseTim: DatabaseReference

    //    public String[] anggotaValue = {null, null, null, null, null};

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buat_tim)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        textViewAnggota = findViewById(R.id.txt_tambah_anggota_tim) as TextView
        editGk = findViewById(R.id.edit_pos_gk) as TextInputEditText
        editAnchor = findViewById(R.id.edit_pos_anchor) as TextInputEditText
        editLeftFlank = findViewById(R.id.edit_pos_left_flank) as TextInputEditText
        editRightFlank = findViewById(R.id.edit_pos_right_flank) as TextInputEditText
        editPivot = findViewById(R.id.edit_pos_pivot) as TextInputEditText
        editNamaTim = findViewById(R.id.edit_nama_tim) as TextInputEditText

        buttonBuatTim = findViewById(R.id.button_buat_tim) as Button

        spinnerOlahraga = findViewById(R.id.spinner_olahraga) as Spinner
        spinnerDaerah = findViewById(R.id.spinner_daerah) as Spinner

        spinnerDaerah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) spinnerOlahraga.visibility = View.VISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                spinnerOlahraga.visibility = View.GONE
            }
        }

        spinnerOlahraga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d("YukTanding", "" + position)
                when (position) {
                    1 -> {
                        textViewAnggota.visibility = View.VISIBLE
                        editGk.visibility = View.VISIBLE
                        editAnchor.visibility = View.VISIBLE
                        editLeftFlank.visibility = View.VISIBLE
                        editRightFlank.visibility = View.VISIBLE
                        editPivot.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                textViewAnggota.visibility = View.GONE
                editGk.visibility = View.GONE
                editAnchor.visibility = View.GONE
                editLeftFlank.visibility = View.GONE
                editRightFlank.visibility = View.GONE
                editPivot.visibility = View.GONE
            }
        }

        editGk.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val fieldsOK = cekEditText(arrayOf(editGk, editAnchor, editLeftFlank, editRightFlank, editPivot))
                Log.d("YukTanding", "Edit Text: " + fieldsOK)

                if (fieldsOK == true)
                    buttonBuatTim.isEnabled = true
                else
                    buttonBuatTim.isEnabled = false
            }
        })

        editAnchor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val fieldsOK = cekEditText(arrayOf(editGk, editAnchor, editLeftFlank, editRightFlank, editPivot))
                Log.d("YukTanding", "Edit Text: " + fieldsOK)

                if (fieldsOK == true)
                    buttonBuatTim.isEnabled = true
                else
                    buttonBuatTim.isEnabled = false
            }
        })

        editLeftFlank.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val fieldsOK = cekEditText(arrayOf(editGk, editAnchor, editLeftFlank, editRightFlank, editPivot))
                Log.d("YukTanding", "Edit Text: " + fieldsOK)

                if (fieldsOK == true)
                    buttonBuatTim.isEnabled = true
                else
                    buttonBuatTim.isEnabled = false
            }
        })

        editRightFlank.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val fieldsOK = cekEditText(arrayOf(editGk, editAnchor, editLeftFlank, editRightFlank, editPivot))
                Log.d("YukTanding", "Edit Text: " + fieldsOK)

                if (fieldsOK == true)
                    buttonBuatTim.isEnabled = true
                else
                    buttonBuatTim.isEnabled = false
            }
        })

        editPivot.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val fieldsOK = cekEditText(arrayOf(editGk, editAnchor, editLeftFlank, editRightFlank, editPivot))
                Log.d("YukTanding", "Edit Text: " + fieldsOK)

                if (fieldsOK == true)
                    buttonBuatTim.isEnabled = true
                else
                    buttonBuatTim.isEnabled = false
            }
        })

        buttonBuatTim.setOnClickListener {
            val namaTim = editNamaTim.text.toString()
            val olahraga = spinnerOlahraga.selectedItem.toString()
            val daerah = spinnerDaerah.selectedItem.toString()
            var userUID: String? = null

            user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                userUID = user!!.uid
            }

            Log.d("YukTanding", "Bottom Tambah Tim Pressed: " + olahraga)

            databaseTim = FirebaseDatabase.getInstance().getReference("DaftarTim" + olahraga)
            //                String idTim = databaseTim.push().getKey();
            //                String idTim = user.uuid;

            if (olahraga == "Futsal") {
                val gk = editGk.text.toString()
                val anchor = editAnchor.text.toString()
                val leftFlank = editLeftFlank.text.toString()
                val rightFlank = editRightFlank.text.toString()
                val pivot = editPivot.text.toString()

                val itemTambahTimAdapter = ItemTambahTimAdapter(userUID, namaTim, gk, anchor, leftFlank, rightFlank, pivot, daerah, olahraga)
                databaseTim.child(userUID!!).setValue(itemTambahTimAdapter)

                Log.d("YukTanding", "Tambah Tim Futsal Berhasil")
                finish()
            } else if (olahraga == "Badminton") {

            }
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun cekEditText(fields: Array<EditText>): Boolean {
        for (i in fields.indices) {
            val currentField = fields[i]
            if (currentField.text.toString().length <= 0) {
                return false
            }
        }
        return true
    }

}
