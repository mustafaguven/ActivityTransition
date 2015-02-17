package com.example.mustafaguven.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<User> userList = User.getUserList(this);
        lvMain = (ListView) findViewById(R.id.lvMain);
        UserAdapter adapter = new UserAdapter(this, userList);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int[] screenLocation = new int[2];
                ImageView imgUser = (ImageView) view.findViewById(R.id.imgUser);
                imgUser.getLocationOnScreen(screenLocation);

                Intent i = new Intent(MainActivity.this, PhotoActivity.class);
                i.putExtra("resourceId", userList.get(position).getDrawableId()).
                  putExtra("left", screenLocation[0]).
                  putExtra("top", screenLocation[1]).
                  putExtra("width", imgUser.getWidth()).
                  putExtra("height", imgUser.getHeight()).
                  putExtra("backgroundColor", userList.get(position).getBackgroundColor()).
                  putExtra("description", userList.get(position).getFullName());
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
