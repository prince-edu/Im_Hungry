package com.example.imhungry.gui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.R;
import com.example.imhungry.databinding.ActivityMainMenuVendedorBinding;
import com.example.imhungry.ui.profile.MyProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainMenuVendedorActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfigurationVendedor;

    private ActivityMainMenuVendedorBinding binding;

    private String matriculaRecibida;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuVendedorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainVendedor.toolbarVendedor);

        // Recibe el Intent que inició esta actividad
        Intent intent = getIntent();

        // Obtiene el String extra del Intent
        if(intent != null && intent.hasExtra("matricula")) {
            matriculaRecibida = intent.getStringExtra("matricula");
        }



        DrawerLayout drawer = binding.drawerLayoutVendedor;
        NavigationView navigationView = binding.navViewVendedor;
        mAppBarConfigurationVendedor = new AppBarConfiguration.Builder(
                R.id.nav_my_products, R.id.nav_my_profile, R.id.nav_my_orders_vendedor, R.id.nav_register_product, R.id.nav_my_statistics,R.id.nav_my_chats, R.id.log_out)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_vendedor);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfigurationVendedor);
        NavigationUI.setupWithNavController(navigationView, navController);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_vendedor);
        return NavigationUI.navigateUp(navController, mAppBarConfigurationVendedor)
                || super.onSupportNavigateUp();
    }
}
