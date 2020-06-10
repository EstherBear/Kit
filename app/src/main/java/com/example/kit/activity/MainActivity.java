//CPR_entrance
/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.kit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kit.R;


public class MainActivity extends AppCompatActivity {

    Button playCPRButton;
    Switch modeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playCPRButton = (Button) findViewById(R.id.CPR_play_button);
        modeSwitch = (Switch) findViewById(R.id.modeSwitch);

        playCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (modeSwitch.isChecked()) {
                    i = new Intent(MainActivity.this, TrainActivity.class);
                } else {
                    i = new Intent(MainActivity.this, RunningActivity.class);
                }
                startActivity(i);
            }
        });


    }


}
