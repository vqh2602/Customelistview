package com.example.customelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.account.service.AccountAuthServiceImpl;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    UserAdapter adapter;
    Button button_logout,button_map;
    TextView textView_displayname;

    String displayname_check ="";
    private AccountAuthService mAuthManager;
    private AccountAuthParams mAuthParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(MainActivity.this,getString(R.string.name_hs) ,Toast.LENGTH_LONG).show();
        // anh xa
        anhxa();

        //listview adapter
        adapter = new UserAdapter(MainActivity.this, R.layout.item_list,initUsers());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = adapter.getItem(i);
                Toast.makeText(MainActivity.this, ""+user.getUserName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    // lấy dl tu intent

        dulieuinten();
    // check dang nhap
        if(displayname_check != null){
            // đã đăng nhập -> đăng xuất
            textView_displayname.setText("Xin Chào: "+displayname_check);
            button_logout.setText(getString(R.string.name_out));
            button_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // tạo servier
                    mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                            .setIdToken()
                            .setAccessToken()
                            .createParams();
                    mAuthManager = AccountAuthManager.getService(MainActivity.this, mAuthParam);
                    // đăng xuất
                    Task<Void> signOutTask = mAuthManager.signOut();
                    signOutTask.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            //Processing after the sign-out.
                            Log.i("MainActivity", "signOut complete");
                            Toast.makeText(MainActivity.this,"Đã Đăng Xuất" ,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
            });
        }
        // đã  đăng xuất -> đăng nhập
        else {
            button_logout.setText(getString(R.string.name_in));
            button_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }


    private List<User> initUsers(){
        List<User> users = new ArrayList<>();
        User user0 = new User("Vương Quang Huy", "123456@gmail.com", "0965674033");
        User user1 = new User("Tràn Nguyễn Anh", "284787347@gmail.com", "0965674034");
        User user2 = new User("Vũ Thị Hạ", "34989388@gmail.com", "0965674054");
        User user3 = new User("Trần Vũ Lành", "267347364@gmail.com", "0965674038");
        //add cac doi tuong vao list user
        users.add(user0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
    private void anhxa(){
        listView = findViewById(R.id.lvview);
        button_logout = findViewById(R.id.button_logout);
        button_map = findViewById(R.id.button_map);
        textView_displayname = findViewById(R.id.textView_displayname);
    }

    private void dulieuinten(){
        Intent intent = getIntent();
        String displayname = intent.getStringExtra("Displayname");
        displayname_check = displayname;
        Toast.makeText(MainActivity.this,"Xin Chào: "+displayname,Toast.LENGTH_LONG).show();
    }
}