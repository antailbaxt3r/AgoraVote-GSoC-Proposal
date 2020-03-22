package org.aossie.agoraandroid.displayelections;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.invitevoters.InviteVotersActivity;
import org.aossie.agoraandroid.result.ResultViewModel;
import org.aossie.agoraandroid.utilities.SharedPrefs;

public class ElectionActivity extends AppCompatActivity {
  private TextView mElectionName, mElectionDescription, mStartDate, mEndDate, mCandidateName;
  private String id, status, token;
  private DisplayElectionViewModel displayElectionViewModel;
  private ResultViewModel resultViewModel;
  private ImageView active, pending, finished;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rough_ss);

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

    SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
    displayElectionViewModel = new DisplayElectionViewModel(getApplication(), this);
    resultViewModel = new ResultViewModel(getApplication(), this);
    token = sharedPrefs.getToken();
    mElectionName = findViewById(R.id.tv_election_name);
    mElectionDescription = findViewById(R.id.tv_description);
    mStartDate = findViewById(R.id.tv_start_date);
    mEndDate = findViewById(R.id.tv_end_date);
    mCandidateName = findViewById(R.id.tv_candidate_list);
    active = findViewById(R.id.active_status_tag);
    pending = findViewById(R.id.pending_status_tag);
    finished = findViewById(R.id.finished_status_tag);

    Button mDeleteElection = findViewById(R.id.button_delete);
    Button mInviteVoters = findViewById(R.id.button_invite_voters);
    Button mVoters = findViewById(R.id.button_voters);
    Button mBallot = findViewById(R.id.button_ballot);
    Button mResult = findViewById(R.id.button_result);
    mBallot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        displayElectionViewModel.getBallot(token, id);
      }
    });
    mVoters.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        displayElectionViewModel.getVoter(token, id);
      }
    });
    mInviteVoters.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (status.equals("Finished")) {
          Toast.makeText(ElectionActivity.this, "Election is Finished", Toast.LENGTH_SHORT).show();
        } else {
          Intent intent = new Intent(getApplicationContext(), InviteVotersActivity.class);
          intent.putExtra("id", id);
          intent.putExtra("token", token);
          startActivity(intent);
        }
      }
    });
    mResult.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (status.equals("Pending")) {
          Toast.makeText(ElectionActivity.this, "Election is not started yet", Toast.LENGTH_SHORT)
              .show();
        } else {
          resultViewModel.getResult(token, id);
        }
      }
    });

    mDeleteElection.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (status) {
          case "Active":
            Toast.makeText(ElectionActivity.this, "Active Elections Cannot Be Deleted",
                Toast.LENGTH_SHORT).show();
            break;
          case "Finished":
          case "Pending":
            displayElectionViewModel.deleteElection(token, id);
            break;
        }
      }
    });

    getIncomingIntent();
  }

  private void getIncomingIntent() {

    if (getIntent().hasExtra("election_name") && getIntent().hasExtra("election_description")
        && getIntent().hasExtra("start_date") && getIntent().hasExtra("end_date")
        && getIntent().hasExtra("candidates") && getIntent().hasExtra("status")) {

      String electionName = getIntent().getStringExtra("election_name");
      String electionDescription = getIntent().getStringExtra("election_description");
      String startDate = getIntent().getStringExtra("start_date");
      String endDate = getIntent().getStringExtra("end_date");
      String candidateName = getIntent().getStringExtra("candidates");
      status = getIntent().getStringExtra("status");
      id = getIntent().getStringExtra("id");

      mElectionName.setText(electionName);
      mElectionDescription.setText(electionDescription);
      mStartDate.setText(startDate);
      mEndDate.setText(endDate);
      mCandidateName.setText(candidateName);
      switch(status){
        case "Active":
          active.setVisibility(View.VISIBLE);
          break;
        case "Pending":
          pending.setVisibility(View.VISIBLE);
          break;
        case "Finished":
          finished.setVisibility(View.VISIBLE);
      }
    }
  }
}
