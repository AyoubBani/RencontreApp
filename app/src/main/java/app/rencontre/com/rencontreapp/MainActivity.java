package app.rencontre.com.rencontreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.rencontre.com.rencontreapp.entities.Post;
import app.rencontre.com.rencontreapp.fragments.HomeFragment;
import app.rencontre.com.rencontreapp.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

//    private TextView mTextMessage;
//    private TextView logIn;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("posts");
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
//        Post p1 = new Post("Post Ahmed Num Dt",mFirebaseAuth.getCurrentUser().getDisplayName(),"05/06/18");
//        Post p2 = new Post("HellO world",mFirebaseAuth.getCurrentUser().getDisplayName(),"05/07/18");
//        Post p3 = new Post("I'm new user here everyone!!!",mFirebaseAuth.getCurrentUser().getDisplayName(),"15/10/18");
//        Post p4 = new Post("what's upp homies",mFirebaseAuth.getCurrentUser().getDisplayName(),"25/06/18");
//        Post p5 = new Post("Hey guys!!",mFirebaseAuth.getCurrentUser().getDisplayName(),"05/06/18");
//        Post p6 = new Post("Wow that's cool",mFirebaseAuth.getCurrentUser().getDisplayName(),"19/03/18");
//        Post p7 = new Post("Hello World How are you doing??",mFirebaseAuth.getCurrentUser().getDisplayName(),"17/06/18");
//        Post p8 = new Post("anyone wanna talk",mFirebaseAuth.getCurrentUser().getDisplayName(),"20/01/18");
//        myRef.child(mFirebaseAuth.getCurrentUser().getUid()).push().setValue(p4);
//        myRef.child(mFirebaseAuth.getCurrentUser().getUid()).push().setValue(p5);
//        myRef.child(mFirebaseAuth.getCurrentUser().getUid()).push().setValue(p6);
//        myRef.child(mFirebaseAuth.getCurrentUser().getUid()).push().setValue(p7);
//        myRef.child(mFirebaseAuth.getCurrentUser().getUid()).push().setValue(p8);

//        mTextMessage = (TextView) findViewById(R.id.message);
//        logIn= (TextView) findViewById(R.id.login);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            ProfileFragment selectedFragment = null;
            HomeFragment homeFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeFragment=new HomeFragment();
                    homeFragment.setmContext(MainActivity.this);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.contentLayout, homeFragment);
                    fragmentTransaction.commit();
//                    fragmentManager.executePendingTransactions();
//                    mTextMessage.setText(R.string.acceuil);
                    return true;
                case R.id.message:
//                    mTextMessage.setText(R.string.message);
                    return true;
                case R.id.matches:
//                    mTextMessage.setText(R.string.matches);
                    return true;
                case R.id.profile:
                    selectedFragment=new ProfileFragment();
                    selectedFragment.setmContext(MainActivity.this);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.contentLayout, selectedFragment);
                    fragmentTransaction.commit();
//                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    if (user != null) {
//                        logIn.setText(user.getDisplayName());
//                    }

//                    mTextMessage.setText(R.string.profile);
                    return true;
            }


//                    ph = new PassangerHome();
            //AddNewPost addNewPost = new AddNewPost();
            //profileFragment.setInfo(passanger, mFirebaseAuth.getCurrentUser().getDisplayName(), mFirebaseAuth.getCurrentUser().getEmail());
/* LATER

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(layout, selectedFragment);
            fragmentTransaction.commit();
*/
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        LoginManager.getInstance().logOut();
//        FirebaseAuth.getInstance().signOut();
        Intent i =new Intent(MainActivity.this,Auth.class);
        startActivity(i);
        finish();
//        AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Intent i =new Intent(MainActivity.this,Auth.class);
//                        startActivity(i);
//                    }
//                });


    }

}
