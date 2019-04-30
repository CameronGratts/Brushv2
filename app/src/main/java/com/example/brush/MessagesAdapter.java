package com.example.brush;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<Messages> UserMessagesList;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersDatabaseRef;

    private String TAG = "-9999";

    public MessagesAdapter(List<Messages> UserMessagesList){

        this.UserMessagesList = UserMessagesList;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView SenderMessageText, ReceiverMessageText;
        public CircleImageView ReceiverProfileImage;

        public MessageViewHolder(View itemView){

            super(itemView);

            SenderMessageText = (TextView)itemView.findViewById(R.id.sender_message_text);
            ReceiverMessageText = (TextView)itemView.findViewById(R.id.receiver_message_text);
            ReceiverProfileImage = (CircleImageView)itemView.findViewById(R.id.message_profile_image);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout_of_users,
                parent, false);

        mAuth = FirebaseAuth.getInstance();

        return new MessageViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, int position){

        Log.d(TAG, "TESTING");

        String MessageSenderId = mAuth.getCurrentUser().getUid();
        Messages messages = UserMessagesList.get(position);

        String FromUserId = messages.getfrom();
        String FromMessageType = messages.gettype();

        UsersDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(FromUserId);

        Log.d(TAG, FromUserId + " " + FromMessageType);

        UsersDatabaseRef.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    //String image = dataSnapshot.child("Profile Picture").getValue().toString();

                    //Picasso.with(holder.ReceiverProfileImage.getContext()).load(image)
                     //       .placeholder(R.drawable.default_profile_picture).into(holder.ReceiverProfileImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(FromMessageType.equals("text"))
        {
            holder.ReceiverMessageText.setVisibility(View.INVISIBLE);
            holder.ReceiverProfileImage.setVisibility(View.INVISIBLE);

            if(FromUserId.equals(MessageSenderId))
            {
                holder.SenderMessageText.setBackgroundResource(R.drawable.sender_message_text_background);
                holder.SenderMessageText.setTextColor(Color.WHITE);
                holder.SenderMessageText.setGravity(Gravity.RIGHT);
                holder.SenderMessageText.setText(messages.getmessage());
            }
            else
            {
                holder.SenderMessageText.setVisibility(View.INVISIBLE);

                holder.ReceiverMessageText.setVisibility(View.VISIBLE);
                holder.ReceiverProfileImage.setVisibility(View.VISIBLE);

                holder.ReceiverMessageText.setBackgroundResource(R.drawable.receiver_message_text_background);
                holder.ReceiverMessageText.setTextColor(Color.WHITE);
                holder.ReceiverMessageText.setGravity(Gravity.LEFT);
                holder.ReceiverMessageText.setText(messages.getmessage());
            }
        }
    }

    @Override
    public int getItemCount() {

        return UserMessagesList.size();
    }
}
