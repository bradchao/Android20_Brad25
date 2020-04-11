package tw.org.iii.brad.brad25;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;
    private int i = 0;
    private TextView mesg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesg = findViewById(R.id.mesg);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.v("brad", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.v("brad", "Failed to read value.", error.toException());
            }
        });
    }

    public void test1(View view) {
        myRef.setValue("Hello, World!");
    }

    public void test2(View view) {
        myRef2 = database.getReference("member");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member obj = dataSnapshot.getValue(Member.class);
                //Log.v("brad", "" + obj.getAge());

                mesg.setText("" + obj.getAge() + "\n");
                for (String name : obj.getNames()) {
                    mesg.append(name + "\n");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Member member = new Member();
        member.setAge((int)(Math.random()*100));
        member.addName("Brad");
        member.addName("Eric");
        member.addName("Tony");
        myRef2.setValue(member);

    }
}
