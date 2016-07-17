package com.lentach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;

public class VKPermissionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vkpermissions);
        VKSdk.login(this, VKScope.WALL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
// Пользователь успешно авторизовался
                SharedPreferences sPref = getSharedPreferences("Default",MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(VKApiConst.ACCESS_TOKEN, res.accessToken);
                ed.apply();
               // VkApiRequestUtil.getUserInfo(VKPermissionsActivity.this);
                Toast.makeText(VKPermissionsActivity.this, "Token is "+res.accessToken, Toast.LENGTH_SHORT).show();

              //  finishActivity(1);
                finish();

            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
