package com.example.brush;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PublicProfileActivity extends AppCompatActivity {

    private ImageButton userProfileImage, userMessage, userUpdates, userGallery, userShop;
    //private ImageButton SendFriendRequestButton, DeclineFriendRequestButton;
    private Button FollowButton, FollowingButton;
    private TextView userProfileName, username, userBio;

    private DatabaseReference profileRef, FollowerRef, FollowRef, FollowingRef, CurrentUserRef;
    private FirebaseAuth mAuth;

    private String currentUserId, userID, saveCurrentDate, TAG = "333";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_profile);

        userID = getIntent().getStringExtra("userID"); // Value of user we're visiting
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid(); // Person logged in
        profileRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        FollowRef = FirebaseDatabase.getInstance().getReference().child("Followers");
        CurrentUserRef = FirebaseDatabase.getInstance().getReference().child("Followers").child(currentUserId).child("following");

        userProfileImage = (ImageButton) findViewById(R.id.public_profile_default_pic);
        userMessage = (ImageButton) findViewById(R.id.public_profile_message);
        userUpdates = (ImageButton) findViewById(R.id.public_profile_updates);
        userGallery = (ImageButton) findViewById(R.id.public_profile_gallery);
        userShop = (ImageButton) findViewById(R.id.public_profile_shop);
        FollowButton = (Button) findViewById(R.id.public_profile_follow);

        userProfileName = (TextView) findViewById(R.id.public_profile_full_name);
        username = (TextView) findViewById(R.id.public_profile_username);
        userBio = (TextView) findViewById(R.id.public_profile_bio);

        Log.d(TAG, "currentuserID Key: " + FollowRef.child(userID).child("followers").child(currentUserId).getKey());

        CurrentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d(TAG,"dataSnapshot child: " + dataSnapshot.child(userID));
                Log.d(TAG, "Before IF");

                if(dataSnapshot.hasChild(userID))
                {
                    FollowButton.setText("Following");
                    Log.d(TAG, "Inside IF");
                }
                else
                {
                    FollowButton.setText("Follow");
                    Log.d(TAG, "Inside ELSE");
                }

                Log.d(TAG, "After IF/ELSE");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String myProfileImage = dataSnapshot.child("profilePicture").getValue().toString();
                    String myUserName = dataSnapshot.child("Username").getValue().toString();
                    String myProfileName = dataSnapshot.child("Name").getValue().toString();
                    String myBio = dataSnapshot.child("Bio").getValue().toString();

                    Picasso.get().load(myProfileImage).placeholder(R.drawable.dfp).into(userProfileImage);
                    userProfileName.setText(myProfileName);
                    username.setText("@" + myUserName);
                    userBio.setText(myBio);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(FollowButton.getText().toString().equals("Follow"))
                {
                    FollowRef.child(userID).child("followers").child(currentUserId).setValue("true");
                    FollowRef.child(currentUserId).child("following").child(userID).setValue("true");

                    Calendar calFordDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
                    saveCurrentDate = currentDate.format(calFordDate.getTime());

                    FollowRef.child(userID).child("followers").child(currentUserId).child("date").setValue(saveCurrentDate);
                    FollowRef.child(currentUserId).child("following").child(userID).child("date").setValue(saveCurrentDate);

                    FollowButton.setText("Following");
                }
                else if(FollowButton.getText().toString().equals("Following"))
                {
                    FollowRef.child(currentUserId).child("following").child(userID).removeValue();
                    FollowRef.child(userID).child("followers").child(currentUserId).removeValue();

                    FollowButton.setText("Follow");
                }
            }
        });


        userMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToMessageActivity();
            }
        });

        userUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToUpdatesActivity();
            }
        });

        userGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToGalleryActivity();
            }
        });

        userShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToShopActivity();
            }
        });
    }

    private void SendUserToMessageActivity() {
        //Intent intent = new Intent(UserProfileActivity.this, MessageActivity.class);
        //startActivity(intent);
    }

    private void SendUserToUpdatesActivity() {
        //Intent intent = new Intent(UserProfileActivity.this, UpdatesActivity.class);
        //startActivity(intent);
    }

    private void SendUserToGalleryActivity() {
        //Intent intent = new Intent(UserProfileActivity.this, GalleryActivity.class);
        //startActivity(intent);
    }

    private void SendUserToShopActivity() {
        //Intent intent = new Intent(UserProfileActivity.this, ShopActivity.class);
        //startActivity(intent);
    }
}



