package com.example.myproject.classes;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebase {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference1;
    List<foodinfo> list= new ArrayList<>();

    public interface datastatus {
        void dataIsloaded(List<String> key,List<foodinfo> finfo);

//        void dataisInserted();
//        void dataIsupdated();
//        void dataIsDeleted();

    }
    public firebase() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("detail");
        databaseReference1=FirebaseDatabase.getInstance().getReference("user_info");
    }

    public void readdata(final datastatus ds){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keynode:snapshot.getChildren()){
                     keys.add(keynode.getKey());
                        foodinfo fi=keynode.getValue(foodinfo.class);
                        list.add(fi);

                }
                ds.dataIsloaded(keys,list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
