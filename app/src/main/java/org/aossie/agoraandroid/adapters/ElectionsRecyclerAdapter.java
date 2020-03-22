package org.aossie.agoraandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.displayelections.ElectionActivity;
import org.aossie.agoraandroid.models.Election;
import org.aossie.agoraandroid.models.ElectionData;

public class ElectionsRecyclerAdapter
    extends RecyclerView.Adapter<ElectionsRecyclerAdapter.ElectionsViewHolder> {

  private final ArrayList<Election> electionList;
  private Context mContext;

  public ElectionsRecyclerAdapter(ArrayList<Election> electionList, Context context) {
    this.electionList = electionList;
    mContext = context;
  }

  @NonNull
  @Override
  public ElectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = (LayoutInflater) parent.getContext()
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    assert layoutInflater != null;
    View itemView = layoutInflater.inflate(R.layout.list_item_election_details, parent, false);

    return new ElectionsViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ElectionsViewHolder holder, final int position) {

    final Election election = electionList.get(position);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    final SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");

    holder.mElectionName.setText(election.getName());
    holder.mElectionDescription.setText(election.getDescription());
    try {
      holder.mStartDate.setText(f2.format(formatter.parse(election.getStart())));
      holder.mEndDate.setText(f2.format(formatter.parse(election.getEnd())));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    final StringBuilder candidateNames = new StringBuilder();
    for(int i = 0; i < election.getCandidates().size(); i++){
      candidateNames.append(election.getCandidates().get(i)).append("\n");
    }
    holder.mCandidateList.setText(candidateNames.toString().trim());
    switch (election.getElectionType().toLowerCase()) {
      case "active":
        holder.active.setVisibility(View.VISIBLE);
        break;
      case "finished":
        holder.finished.setVisibility(View.VISIBLE);
        break;
      case "pending":
        holder.pending.setVisibility(View.VISIBLE);
        break;

    }

    holder.electionLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(mContext, ElectionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("election_name", election.getName());
        intent.putExtra("election_description", election.getDescription());
        try {
          intent.putExtra("start_date", f2.format(formatter.parse(election.getStart())));
          intent.putExtra("end_date", f2.format(formatter.parse(election.getEnd())));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        intent.putExtra("candidates", candidateNames.toString().trim());
        intent.putExtra("status", election.getElectionType());
        intent.putExtra("id", election.getId());
        mContext.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return electionList.size();
  }

  class ElectionsViewHolder extends RecyclerView.ViewHolder {
    final TextView mElectionName, mElectionDescription, mStartDate, mEndDate,
        mCandidateList;
    final CardView electionLayout;
    final ImageView active, finished, pending;

    ElectionsViewHolder(@NonNull View itemView) {
      super(itemView);
      mElectionName = itemView.findViewById(R.id.text_view_election_name);
      mElectionDescription = itemView.findViewById(R.id.text_view_election_description);
      mStartDate = itemView.findViewById(R.id.text_view_start_date);
      mEndDate = itemView.findViewById(R.id.text_view_end_date);
      active = itemView.findViewById(R.id.active_status_tag);
      finished = itemView.findViewById(R.id.finished_status_tag);
      pending = itemView.findViewById(R.id.pending_status_tag);
      mCandidateList = itemView.findViewById(R.id.text_view_candidate_list);
      electionLayout = itemView.findViewById(R.id.election_card);
    }
  }
}
