package com.nabwera.retrofit2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.nabwera.retrofit2.R;
import com.nabwera.retrofit2.api.model.GithubRepo;
import com.nabwera.retrofit2.api.service.GithubClient;
import com.nabwera.retrofit2.ui.adapter.GithubRepoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listview that we use for the display of the data
        listView = (ListView) findViewById(R.id.pagination_list);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        GithubClient client = retrofit.create(GithubClient.class);

        // Fetch a list of the Github repositories.
        Call<List<GithubRepo>> call = client.reposForUser("TheDancerCodes");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                // The network call was a success and we got a response
                List<GithubRepo> repos = response.body();

                // pass the data to the list view
                listView.setAdapter(new GithubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                // the network call was a failure
                Toast.makeText(MainActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
