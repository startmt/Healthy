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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import th.ac.a59070038kmitl.healthy.post.Post;
import th.ac.a59070038kmitl.healthy.post.PostListAdapter;

public class PostFragment extends Fragment {
    private List<Post> post = new ArrayList<Post>();
    private String url = "https://jsonplaceholder.typicode.com/posts";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView postList = getView().findViewById(R.id.post_list);
        PostListAdapter postAdapter = new PostListAdapter(
                getActivity(),
                R.layout.post_list,
                post
        );
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            getJson(url);
        }catch (Exception e){

        }
        postList.setAdapter(postAdapter);
        postAdapter.notifyDataSetChanged();

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment commentFragment = new CommentFragment();
                Bundle args = new Bundle();
                args.putString("postId", String.valueOf(post.get(position).getId()));
                commentFragment.setArguments(args);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, commentFragment).addToBackStack(null).commit();
            }
        });
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
                int userIdInt = jsonArray.getJSONObject(i).getInt("userId");
                int idInt = jsonArray.getJSONObject(i).getInt("id");
                String titleStr = jsonArray.getJSONObject(i).getString("title");
                String bodyStr = jsonArray.getJSONObject(i).getString("body");
                post.add(new Post(userIdInt, idInt, titleStr, bodyStr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
