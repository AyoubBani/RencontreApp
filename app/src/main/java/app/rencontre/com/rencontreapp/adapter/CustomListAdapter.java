package app.rencontre.com.rencontreapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import app.rencontre.com.rencontreapp.R;
import app.rencontre.com.rencontreapp.entities.Post;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by famille on 5/30/2018.
 */

public class CustomListAdapter extends ArrayAdapter<Post> {
    Context context;
    ArrayList<Post> postsList;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Post> postsList) {
        super(context, resource, postsList);
        this.postsList = postsList;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Post getItem(int i) {
        return postsList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.post_item, null, true);
        }
        TextView username = (TextView) convertView.findViewById(R.id.user);
        TextView postText = (TextView) convertView.findViewById(R.id.post);
        TextView date = (TextView) convertView.findViewById(R.id.datepost);
        username.setText(postsList.get(position).getUsername());
        postText.setText(postsList.get(position).getPost());
        date.setText(postsList.get(position).getDate());
        return convertView;
    }
}
