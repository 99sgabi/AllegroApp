package pl.allegro.app.allegroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pl.allegro.app.allegroapp.githubapi.GithubService;
import pl.allegro.app.allegroapp.githubapi.Owner;
import pl.allegro.app.allegroapp.githubapi.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OwnerInfoActivity extends AppCompatActivity {
    TextView ownerUsernameTextView;
    ImageView profilePicImageView;
    String ownerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info);

        ownerUsernameTextView = findViewById(R.id.owner_name_id_label);
        profilePicImageView = findViewById(R.id.owners_profile_pic);

        Intent intent = getIntent();
        ownerName = intent.getStringExtra(RepositoryDetailsFragment.OWNER_NAME_EXTRA);
        ownerUsernameTextView.setText(ownerName);

        GithubService githubService = RetrofitInstance.getRetrofit().create(GithubService.class);
        Call<Owner> ownerInfo = githubService.getOrganization(ownerName);
        ownerInfo.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                Owner owner = response.body();
                if(owner != null)
                {
                    Picasso.get()
                            .load(owner.getAvatarUrl())
                            .into(profilePicImageView);
                }
                else
                    profilePicImageView.setImageResource(R.mipmap.ic_broken_image);
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                Log.e( "error","failure while working with github api");
                Log.e( "error",t.getMessage());
            }
        });
    }
}