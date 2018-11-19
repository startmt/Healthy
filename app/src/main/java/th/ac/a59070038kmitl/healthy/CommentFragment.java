package th.ac.a59070038kmitl.healthy;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import th.ac.a59070038kmitl.healthy.comment.Comment;
import th.ac.a59070038kmitl.healthy.comment.CommentAdapter;
import th.ac.a59070038kmitl.healthy.post.Post;

public class CommentFragment extends Fragment {
    private  static  final  String TAG = "COMMENT";
    private List<Comment> comment = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            String postId = bundle.getString("postId");
            String urI = "https://jsonplaceholder.typicode.com/posts/" + postId + "/comments";
            Log.d(TAG, urI);
            ListView commentList = getView().findViewById(R.id.comment_list);
            CommentAdapter commentAdapter = new CommentAdapter(
                    getActivity(),
                    R.layout.comment_list,
                    comment
            );
            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new
                        StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            try {
                getJson(urI);
            }catch (Exception e){

            }
            commentList.setAdapter(commentAdapter);
            commentAdapter.notifyDataSetChanged();

        }
        initbackButton();
    }
    private  void  initbackButton(){
        Button backBtn = getActivity().findViewById(R.id.button_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }
    private void getJson(String url){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            JSONArray jsonArray = new JSONArray(response.body().string());
            for(int i = 0; i<jsonArray.length();i++){
                int postIdInt = jsonArray.getJSONObject(i).getInt("postId");
                int idInt = jsonArray.getJSONObject(i).getInt("id");
                String nameStr = jsonArray.getJSONObject(i).getString("name");
                String bodyStr = jsonArray.getJSONObject(i).getString("body");
                String emailStr = jsonArray.getJSONObject(i).getString("email");
                comment.add(new Comment(nameStr,emailStr, bodyStr, postIdInt, idInt));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
