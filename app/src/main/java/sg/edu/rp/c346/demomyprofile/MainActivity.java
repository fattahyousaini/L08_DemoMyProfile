package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText etName;
EditText etGPA;
RadioGroup rg;
Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rg = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.buttonSave);

        setContentView(R.layout.activity_main);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int selectedID = rg.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", selectedID);

                prefEdit.commit();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());

        // Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 1c: Obtian an instance of the SHaredPreferences Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1d: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);

        int selectedId = rg.getCheckedRadioButtonId();
        RadioButton selectedRB = findViewById(selectedId);
        prefEdit.putString("gender", selectedRB.getText().toString());

        // Step 1e: Call commit() to save changes into SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the Shared preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreference object
        String msg = prefs.getString("name", "No Name Entered");
        Float gpa = prefs.getFloat("gpa", 0);
        int genderID = prefs.getInt("gender", R.id.radioButtonGenderMale);

        // Step 2c: Update the UI element with the value
        etName.setText(msg);
        etGPA.setText(gpa + "");
        rg.check(genderID);
    }
}
