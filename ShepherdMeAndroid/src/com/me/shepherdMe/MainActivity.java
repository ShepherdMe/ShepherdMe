package com.me.shepherdMe;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.me.shepherdMe.ShepherdMe;

public class MainActivity extends AndroidApplication {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGL20 = true;
		
		initialize(new ShepherdMe(), config);
    }
    
    
    
    
}