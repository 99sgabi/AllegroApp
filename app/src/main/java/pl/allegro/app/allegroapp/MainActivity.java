package pl.allegro.app.allegroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Callable;

import pl.allegro.app.allegroapp.githubapi.GithubService;
import pl.allegro.app.allegroapp.githubapi.Repository;
import pl.allegro.app.allegroapp.githubapi.RepositoryList;
import pl.allegro.app.allegroapp.githubapi.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    Context context = this;
    RecyclerView list;
    RepositoryAdapter adapterForRecyclerView;
    public static String REPOSITORY_NAME_EXTRA = "RepositoryName";
    public static String REPOSITORY_CHOSEN = "Repository";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list_view);
        list.setLayoutManager(new LinearLayoutManager(context));
        GithubService githubService = RetrofitInstance.getRetrofit().create(GithubService.class);
        Call<List<Repository>> reposList = githubService.getRepositories();
        if(list.getAdapter() == null)
        {
            adapterForRecyclerView = new RepositoryAdapter();
            list.setAdapter(adapterForRecyclerView);
        }

        reposList.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> repositories) {
                adapterForRecyclerView.setRepositories(repositories.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.e( "error","failure while working with github api");
                Log.e( "error",t.getMessage());
            }
        });
    }

    private class RepositoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private Repository repository;

        public RepositoryViewHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater.inflate(R.layout.single_repository,parent,false));
            itemView.setOnClickListener(this);
            nameTextView = itemView.findViewById(R.id.repository_name);
            descriptionTextView = itemView.findViewById(R.id.repository_description);
        }

        public void bind(Repository repository)
        {
            this.repository = repository;
            nameTextView.setText(repository.getName());
            descriptionTextView.setText(repository.getDescription());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, RepositoryDetailsActivity.class);
            intent.putExtra(REPOSITORY_NAME_EXTRA, repository.getName());
            startActivity(intent);
        }
    }

    private class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder>
    {
        RepositoryList repositories = new RepositoryList();

        @NonNull
        @Override
        public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            return new RepositoryViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
            holder.bind(repositories.getRepositories().get(position));
        }

        @Override
        public int getItemCount() {
            return repositories.size();
        }

        public void setRepositories(List<Repository> repList)
        {
            this.repositories.setRepositories(repList);
            notifyDataSetChanged();
        }
    }
}