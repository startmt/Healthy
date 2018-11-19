package th.ac.a59070038kmitl.healthy.comment;

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
import th.ac.a59070038kmitl.healthy.post.Post;

public class CommentAdapter extends ArrayAdapter<Comment> {
    Context context;
    ArrayList<Comment> comment = new ArrayList<Comment>();
    public CommentAdapter(@NonNull Context context,
                           int resource,
                           @NonNull List<Comment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.comment = (ArrayList<Comment>) objects;
    }
    @NonNull
    public View getView(int position, @NonNull View convertView,
                        @NonNull ViewGroup parent){
        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.comment_list,
                parent,
                false);
        Log.d("POSTFRAGMENT", "OK");
        TextView postId = (TextView) weightItem.findViewById(R.id.post_postid);
        TextView id = (TextView) weightItem.findViewById(R.id.post_id);
        TextView body = (TextView) weightItem.findViewById(R.id.post_body);
        TextView name = (TextView) weightItem.findViewById(R.id.post_name);
        TextView email = (TextView) weightItem.findViewById(R.id.post_email);
        Comment row = comment.get(position);

        postId.setText(String.valueOf(row.getPostId()));
        id.setText(String.valueOf(row.getId()));
        body.setText(String.valueOf(row.getBody()));
        name.setText("Name : " + row.getName());
        email.setText("Email : " + row.getEmail());
        return weightItem;
    }
}
