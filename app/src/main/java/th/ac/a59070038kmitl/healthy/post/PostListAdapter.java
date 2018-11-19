package th.ac.a59070038kmitl.healthy.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import th.ac.a59070038kmitl.healthy.R;
import th.ac.a59070038kmitl.healthy.menu.Weight;

public class PostListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Post> posts = new ArrayList<Post>();
    public PostListAdapter(@NonNull Context context,
                         int resource,
                         @NonNull List<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.posts = (ArrayList<Post>) objects;
    }
    @NonNull
    public View getView(int position, @NonNull View convertView,
                        @NonNull ViewGroup parent){
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.post_list,
                parent,
                false);

        TextView id = (TextView) weightItem.findViewById(R.id.post_id);
        TextView title = (TextView) weightItem.findViewById(R.id.post_title);
        TextView body = (TextView) weightItem.findViewById(R.id.post_body);
        Post row = posts.get(position);

        id.setText(row.getId());
        title.setText(String.valueOf(row.getTitle()));
        body.setText(row.getBody());
        return weightItem;
    }

}
