package com.hasan.onlineshopping.StartingScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hasan.onlineshopping.Others.MyCartListActivity;
import com.hasan.onlineshopping.Others.MyOfferListActivity;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.fragments.ImagesResourceFragment;
import com.hasan.onlineshopping.mapping.ShopIdMap;
import com.hasan.onlineshopping.resource.ImageResource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ViewPager viewPager;
    TabLayout tabLayout;
    private String shopId;
    public static int howManyCartItems = 0;
    private int tabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int id = ShopIdMap.getShopIdMap().getShopId();
        //Toast.makeText(MainActivity.this,"shop id : " + id,Toast.LENGTH_SHORT).show();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout mainLayout = findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mainLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        mainLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        tabPosition = getIntent().getIntExtra("PREV_TAB",0);

        if(viewPager != null){
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
            //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }




    }

    @Override
    public void onBackPressed() {
        DrawerLayout mainLayout = findViewById(R.id.activity_main);
        if(mainLayout.isDrawerOpen(GravityCompat.START)){
            mainLayout.closeDrawer(GravityCompat.START);
        }
        else{
            //super.onBackPressed();
            //Toast.makeText(this,"here back pressed called",Toast.LENGTH_SHORT).show();
            //ShopIdMap.getShopIdMap().setShopId(0);
            startActivity(new Intent(this,LoginActivity.class));

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_item,menu);
        return true;
    }

    /////kaj baki ase///
    //////////////////////////
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem cart = menu.findItem(R.id.action_item_cart);
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_item_cart){
            startActivity(new Intent(this, MyCartListActivity.class));
            return true;
        }
        else if(id == R.id.action_item_offer){
            if (ShopIdMap.getShopIdMap().getFreqItemsMaps().size() >= 2) {
                startActivity(new Intent(this, MyOfferListActivity.class));
            }
            else {
                Toast.makeText(MainActivity.this,"No Offer Today" ,Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else if(id == R.id.action_item_search){
            return true;

        }
        else{
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_drawer_item1) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_drawer_item2) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_drawer_item3) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_drawer_item4) {
            viewPager.setCurrentItem(3);
        }
        else if(id == R.id.my_cart){
            startActivity(new Intent(this,MyCartListActivity.class));
        }

        DrawerLayout mainLayout = findViewById(R.id.activity_main);
        mainLayout.closeDrawer(GravityCompat.START);
        return true;
    }




    private void setupViewPager(ViewPager viewPager) {

        Adapter adapter = new Adapter(getSupportFragmentManager());
        ImagesResourceFragment fragment = new ImagesResourceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("item_type", 1);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_1));

        fragment = new ImagesResourceFragment();
        bundle = new Bundle();
        bundle.putInt("item_type", 2);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_2));

        fragment = new ImagesResourceFragment();
        bundle = new Bundle();
        bundle.putInt("item_type", 3);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_3));

        fragment = new ImagesResourceFragment();
        bundle = new Bundle();
        bundle.putInt("item_type", 4);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_4));

        /*fragment = new ImagesResourceFragment();
        bundle = new Bundle();
        bundle.putInt("item_type", 5);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_5));*/


        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(tabPosition);

    }

    class Adapter extends FragmentPagerAdapter{

        List<Fragment>arrayFragments = new ArrayList<>();
        List<String>arrayPageTitles = new ArrayList<>();

        public Adapter(FragmentManager fm){
            super(fm);
        }

        public void addFragment(Fragment f,String t){
            arrayFragments.add(f);
            arrayPageTitles.add(t);
        }

        @Override
        public Fragment getItem(int position) {
            return arrayFragments.get(position);
        }

        @Override
        public int getCount() {
            return arrayFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arrayPageTitles.get(position);
        }
    }

}
