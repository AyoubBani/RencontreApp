package app.rencontre.com.rencontreapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.rencontre.com.rencontreapp.R;
import app.rencontre.com.rencontreapp.adapter.CustomListAdapter;
import app.rencontre.com.rencontreapp.entities.Post;

/**
 * Created by famille on 5/30/2018.
 */

public class HomeFragment extends Fragment {
    Context mContext;
    private ListView postListView;
    private ArrayList<Post> postList;
    CustomListAdapter customListAdapter;
    FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference postsDatabaseReference;
    public HomeFragment() {
        postList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        postsDatabaseReference=mFirebaseDatabase.getReference().child("posts").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
        customListAdapter = new CustomListAdapter(mContext, R.layout.post_item, postList);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.home, container, false);
        postListView = (ListView) result.findViewById(R.id.postlist);

        postListView.setAdapter(customListAdapter);
        fetchData();
        return result;
    }

    private void fetchData() {
        postsDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    customListAdapter.add(data.getValue(Post.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
