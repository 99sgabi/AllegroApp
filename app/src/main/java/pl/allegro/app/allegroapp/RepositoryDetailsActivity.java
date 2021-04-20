package pl.allegro.app.allegroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Callable;

import pl.allegro.app.allegroapp.githubapi.GithubService;
import pl.allegro.app.allegroapp.githubapi.Repository;
import pl.allegro.app.allegroapp.githubapi.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryDetailsActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView sizeTextView;
    private TextView defaultBranchTextView;
    private TextView idTextView;
    private TextView privateTextView;
    private TextView fullNameTextView;
    private TextView stargazersCountTextView;
    private TextView ownerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);

        Intent intent = getIntent();
        prepareActivity();
        connectToRetrofit(
                intent.getStringExtra(MainActivity.REPOSITORY_NAME_EXTRA));
    }

    private void prepareActivity()
    {
        nameTextView = findViewById(R.id.repository_name);
        descriptionTextView = findViewById(R.id.repository_description);
        sizeTextView = findViewById(R.id.repository_size);
        defaultBranchTextView = findViewById(R.id.repository_default_branch);
        privateTextView = findViewById(R.id.private_repository);
        fullNameTextView = findViewById(R.id.repository_full_name);
        idTextView = findViewById(R.id.repository_id);
        stargazersCountTextView = findViewById(R.id.repository_stargazers_count);
        ownerTextView = findViewById(R.id.repository_owner);
    }

    private void connectToRetrofit(String name)
    {
        GithubService githubService = RetrofitInstance.getRetrofit().create(GithubService.class);
        Call<Repository> repositoryCall = githubService.getRepository(name);
        repositoryCall.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                updateActivityViews(response.body());
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.e( "error","failure while working with github api");
                Log.e( "error",t.getMessage());
            }
        });
    }

    private void updateActivityViews(Repository repository)
    {
        nameTextView.setText(repository.getName());
        descriptionTextView.setText(repository.getDescription());
        sizeTextView.setText(Integer.toString(repository.getSize()));
        defaultBranchTextView.setText(repository.getDefaultBranch());
        String privacy = String.format(
                getString(R.string.is_private_repository),
                repository.isPrivate()? getString(R.string.yes_label) : getString(R.string.no_label)
        );
        privateTextView.setText(privacy);
        fullNameTextView.setText(repository.getFullName());
        idTextView.setText(Long.toString(repository.getId()));
        stargazersCountTextView.setText(Integer.toString(repository.getStargazers()));
        ownerTextView.setText(repository.getOwner().toString());
    }
}