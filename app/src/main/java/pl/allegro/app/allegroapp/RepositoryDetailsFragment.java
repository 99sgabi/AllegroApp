package pl.allegro.app.allegroapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.allegro.app.allegroapp.githubapi.GithubService;
import pl.allegro.app.allegroapp.githubapi.Repository;
import pl.allegro.app.allegroapp.githubapi.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryDetailsFragment extends Fragment {
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView sizeTextView;
    private TextView defaultBranchTextView;
    private TextView idTextView;
    private TextView privateTextView;
    private TextView fullNameTextView;
    private TextView stargazersCountTextView;
    private TextView ownerTextView;

    public RepositoryDetailsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_repository_details, container, false);
        Intent intent = getActivity().getIntent();
        prepareActivity(view);
        connectToRetrofit(
                intent.getStringExtra(RepositoryListFragment.REPOSITORY_NAME_EXTRA));

        return view;
    }

    private void prepareActivity(View v)
    {
        nameTextView = v.findViewById(R.id.repository_name);
        descriptionTextView = v.findViewById(R.id.repository_description);
        sizeTextView = v.findViewById(R.id.repository_size);
        defaultBranchTextView = v.findViewById(R.id.repository_default_branch);
        privateTextView = v.findViewById(R.id.private_repository);
        fullNameTextView = v.findViewById(R.id.repository_full_name);
        idTextView = v.findViewById(R.id.repository_id);
        stargazersCountTextView = v.findViewById(R.id.repository_stargazers_count);
        ownerTextView = v.findViewById(R.id.repository_owner);
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