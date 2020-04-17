package com.android.daggerfullprojectwithmvvm.main.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.base.BaseActivity;
import com.android.daggerfullprojectwithmvvm.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Inject
    ProfileFragment profileFragment;

    @Inject
    PostFragment postFragment;

    private NavController navController;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        drawerLayout = activityMainBinding.drawerLayout;
        navigationView = activityMainBinding.navView;

        init();

    }

    private void init() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        Toast.makeText(this, ""+profileFragment.makeToast(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            navLoginScreen();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else {
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                if (isValidDestination(R.id.profileFragment)) {
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(R.id.main_navigation, true).build();
                    navController.navigate(R.id.profileFragment, null, navOptions);

                }
                break;
            case R.id.nav_posts:
                if (isValidDestination(R.id.postFragment)) {
                    navController.navigate(R.id.postFragment);

                }
                break;
        }
        item.setCheckable(true);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private boolean isValidDestination(int destination) {
        return destination != navController.getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout);
    }

}
