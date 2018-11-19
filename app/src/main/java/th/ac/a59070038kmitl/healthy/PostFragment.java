package th.ac.a59070038kmitl.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        final ListView postList = getView().findViewById(R.id.post_list);
        final PostListAdapter postAdapter = new PostListAdapter(
                getActivity(),
                R.layout.post_list,
                post
        );
        try {
            getJson(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getJson(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("POSTFRAGMENT", String.valueOf(response.body().string()));
            }
        });
    }

}
