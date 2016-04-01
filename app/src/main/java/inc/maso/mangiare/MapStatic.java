package inc.maso.mangiare;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by mariosoberanis on 3/23/16.
 * This is just to get started ...
 */
public class MapStatic extends AppCompatActivity implements OnMapReadyCallback {

    boolean mapReady = false;
    GoogleMap m_map;

    MarkerOptions mRestaurantMarker;
    LatLng mLatLngRestaurant = new LatLng(32.5120, -117.0216);
    public String mRestaurant = "Los moon's";



    // For sharing the current track data.
    private ShareActionProvider mShareActionProvider;
    //Menu Options
    // MenuItem mShareTrackMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_static);



        mRestaurantMarker = new MarkerOptions()
                .position(mLatLngRestaurant)
                .title(mRestaurant)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant_black_24dp));


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;
        mLatLngRestaurant = new LatLng(32.5120, -117.0216);
        int timef = 5000;
        CameraPosition target = CameraPosition.builder()
                .target(mLatLngRestaurant)
                .zoom(17)
                .build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        m_map.addMarker(mRestaurantMarker);
        // m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), timef, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_map_static, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        //mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        //mShareActionProvider.setShareHistoryFileName("custom_share_history.xml");


        return true;
    }


    // Somewhere in the application.
    public void doShare(Intent shareIntent) {
        // When you want to share set the share intent.
        mShareActionProvider.setShareIntent(shareIntent);
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
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
}
