package inc.maso.mangiare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.Logger;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;


import inc.maso.mangiare.model.PhotosCategories;
import inc.maso.mangiare.utils.Constants;

public class RestaurantCategoryActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTextView;
    private FloatingActionButton mFab;

    String mUrl = "http://goo.gl/gEgYUd";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setup Firebase
        Firebase.setAndroidContext(this);

        // Para ver que pasa en firebase esto se debe quietar en produccion

        // Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);
        //
        setContentView(R.layout.activity_restaurant_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.restaurantCategoryImageView);
        mTextView = (TextView) findViewById(R.id.restaurantCategoryTextView);
        mFab = (FloatingActionButton) findViewById(R.id.fab);


        Firebase listNameRef = new Firebase(Constants.FIREBASE_URL).child("restaurantCategories/photos");


        listNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PhotosCategories photosCategories = dataSnapshot.getValue(PhotosCategories.class);

                mTextView.setText(photosCategories.getCategory());
                //Log.e("ENTRE y el valor es " + photosCategories.getPhoto_url())
                mUrl = photosCategories.getPhoto_url();

                // mImageView.setImageBitmap(@Drawable/photosCategories.getPhoto_url());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Glide.with(this).load(mUrl).into(mImageView);


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //wi will populate the first category  the snackbarr is hidend
                /*Firebase ref = new Firebase(Constants.FIREBASE_URL);

                PhotosCategories photosCategories = new PhotosCategories("Suchito", "Fast Food", "https://goo.gl/iX1JXs", 0);
                ref.child("restaurantCategories/photos").setValue(photosCategories);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();


                Intent intent = new Intent(getActivit(), MapStatic.class);
                startActivity(intent);}

            );
            return rootView;
            */
                mFab.setClickable(false); // Avoid naughty guys clicking FAB again and again...
                Intent intent = new Intent(RestaurantCategoryActivity.this,MapStatic.class);
                startActivity(intent);
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //TODO REFACTOR THIS CLASS

    private void SetUpUserName() {
        SharedPreferences pref = getApplication().getSharedPreferences("ToDoPrefs", 0);
        String username = pref.getString("username", null);
        if (username == null) {
            Random r = new Random();
            username = "AndroidUserName" + r.nextInt(100000);
            pref.edit().putString("username", username).commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RestaurantCategory Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://inc.maso.mangiare/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RestaurantCategory Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://inc.maso.mangiare/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
