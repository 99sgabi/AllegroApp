package pl.allegro.app.allegroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

public class RepositoryDetailsActivity extends BasicActivity {

    @Override
    protected Fragment createFragment() {
        return new RepositoryDetailsFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}