package org.aossie.agoraandroid.displayelections.fragments;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.adapters.ElectionsRecyclerAdapter;
import org.aossie.agoraandroid.createelection.ElectionDetailsSharedPrefs;
import org.aossie.agoraandroid.models.Election;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingElectionsFragment extends Fragment {

  private final ArrayList<Election> electionList = new ArrayList<>();

  public PendingElectionsFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_pending_election, container, false);
    Gson gson = new Gson();

    ElectionDetailsSharedPrefs electionDetailsSharedPrefs =
        new ElectionDetailsSharedPrefs(getApplicationContext());
    RecyclerView rvElectionDetails = view.findViewById(R.id.rv_pending_elections);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    rvElectionDetails.setLayoutManager(mLayoutManager);
    try {
      JSONObject jsonObject = new JSONObject(electionDetailsSharedPrefs.getElectionDetails());
      JSONArray electionsJsonArray = jsonObject.getJSONArray("elections");

      for (int i = 0; i < electionsJsonArray.length(); i++) {
        StringBuilder mCandidateName = new StringBuilder();
        JSONObject singleElectionJsonObject = electionsJsonArray.getJSONObject(i);

        Election data = gson.fromJson(singleElectionJsonObject.toString(), Election.class);
        data.setElectionType("Pending");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date formattedStartingDate = formatter.parse(singleElectionJsonObject.getString("start"));
        Date currentDate = Calendar.getInstance().getTime();

        if (currentDate.before(formattedStartingDate)) {
          electionList.add(data);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if(electionList.isEmpty()){
      TextView noList = view.findViewById(R.id.no_id_list);
      noList.setVisibility(View.VISIBLE);
      rvElectionDetails.setVisibility(View.GONE);
    }
    ElectionsRecyclerAdapter
        electionsRecyclerAdapter = new ElectionsRecyclerAdapter(electionList, getApplicationContext());
    rvElectionDetails.setAdapter(electionsRecyclerAdapter);
    return view;
  }
}
