package org.aossie.agoraandroid.createelection;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;
import org.aossie.agoraandroid.R;

@SuppressWarnings("ConstantConditions")
public class CreateElectionOne extends AppCompatActivity {
  private ElectionDetailsSharedPrefs electionDetailsSharedPrefs;

  private EditText electionNameET, electionDescriptionET;
  private TextInputLayout electionDescriptionTil;
  private TextView startDate, endDate;

  private int sDay, sMonth, sYear;
  private int eDay, eMonth, eYear;
  private String mElectionName;
  private String mElectionDescription;
  private String mStartDate;
  private String mEndDate;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_election_one);

    Toolbar toolbar  = findViewById(R.id.toolbar);
    if(toolbar != null){
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onBackPressed();
        }
      });
    }
    electionDetailsSharedPrefs = new ElectionDetailsSharedPrefs(getApplication());
    electionNameET = findViewById(R.id.election_name_et);
    electionDescriptionET = findViewById(R.id.election_description_et);
    electionDescriptionTil = findViewById(R.id.election_description_til);
    startDate = findViewById(R.id.start_date);
    endDate = findViewById(R.id.end_date);
    Button mNextButton = findViewById(R.id.next_button);
    startDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        handleStartDateTime();
      }
    });
    endDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        handleEndDateTime();
      }
    });
    mNextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mElectionName = electionNameET.getText().toString();
        mElectionDescription = electionDescriptionTil.getEditText().getText().toString();
        mStartDate = startDate.getText().toString();
        mEndDate = endDate.getText().toString();
        if (mElectionName.isEmpty()) {
          electionNameET.setError("Please enter Election Name");
        } else {
          electionNameET.setError(null);
        }

        if (mElectionDescription.isEmpty()) {
          electionDescriptionET.setError("Please enter description");
        } else {
          electionDescriptionET.setError(null);
        }

        if (mStartDate.isEmpty()) {
          startDate.setError("Please enter start date");
          Toast.makeText(CreateElectionOne.this, "Enter start date", Toast.LENGTH_SHORT).show();
        } else {
          startDate.setError(null);
        }

        if (mEndDate.isEmpty()) {
          endDate.setError("Please enter end date");
          Toast.makeText(CreateElectionOne.this, "Enter end date", Toast.LENGTH_SHORT).show();
        }

        if(startDate.getError() == null && endDate.getError() == null && electionNameET.getError() == null && electionDescriptionET.getError() == null){
          endDate.setError(null);
          electionDetailsSharedPrefs.saveElectionName(mElectionName);
          electionDetailsSharedPrefs.saveElectionDesc(mElectionDescription);
          startActivity(new Intent(CreateElectionOne.this, CreateElectionTwo.class));
        }
      }
    });
  }

  private void handleStartDateTime() {
    Calendar calendar = Calendar.getInstance();
    int YEAR = calendar.get(Calendar.YEAR);
    int MONTH = calendar.get(Calendar.MONTH);
    int DATE = calendar.get(Calendar.DATE);
    int HOUR = calendar.get(Calendar.HOUR);
    int MINUTE = calendar.get(Calendar.MINUTE);
    DatePickerDialog datePickerDialog =
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mStartDate = year + "-" + month + "-" + dayOfMonth;
            sDay = dayOfMonth;
            sMonth = month;
            sYear = year;
          }
        }, YEAR, MONTH, DATE);

    TimePickerDialog timePickerDialog =
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
          @Override
          public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mStartDate = mStartDate + "\n" + hourOfDay + ":" + minute;
            startDate.setText(mStartDate);

            //Formatting the starting date in Date-Time format
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar2.set(Calendar.MINUTE, minute);
            calendar2.set(Calendar.YEAR, sYear);
            calendar2.set(Calendar.MONTH, sMonth);
            calendar2.set(Calendar.DAY_OF_MONTH, sDay);
            CharSequence charSequence = DateFormat.format("yyyy-MM-dd'T'HH:mm:ss'Z'", calendar2);

            electionDetailsSharedPrefs.saveStartTime(charSequence.toString());
          }
        }, HOUR, MINUTE, true);
    timePickerDialog.show();
    datePickerDialog.show();
  }

  private void handleEndDateTime() {
    Calendar calendar = Calendar.getInstance();
    int YEAR = calendar.get(Calendar.YEAR);
    int MONTH = calendar.get(Calendar.MONTH);
    int DATE = calendar.get(Calendar.DATE);
    int HOUR = calendar.get(Calendar.HOUR);
    int MINUTE = calendar.get(Calendar.MINUTE);
    DatePickerDialog datePickerDialog =
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
          @Override
          public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mEndDate = year + "-" + month + "-" + dayOfMonth;
            eYear = year;
            eMonth = month;
            eDay = dayOfMonth;
          }
        }, YEAR, MONTH, DATE);

    TimePickerDialog timePickerDialog =
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
          @Override
          public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mEndDate = mEndDate + "\n" + hourOfDay + ":" + minute;
            endDate.setText(mEndDate);

            //Formatting the ending date in Date-Time format
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar3.set(Calendar.MINUTE, minute);
            calendar3.set(Calendar.YEAR, eYear);
            calendar3.set(Calendar.MONTH, eMonth);
            calendar3.set(Calendar.DAY_OF_MONTH, eDay);
            CharSequence charSequence2 = DateFormat.format("yyyy-MM-dd'T'HH:mm:ss'Z'", calendar3);

            electionDetailsSharedPrefs.saveEndTime(charSequence2.toString());
          }
        }, HOUR, MINUTE, true);
    timePickerDialog.show();
    datePickerDialog.show();
  }
}
