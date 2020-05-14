package com.paolomanlunas.androiddemo1;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      AppCenter.start(getApplication(), "b848ee0d-5c05-4564-a8d2-28e7ce469f76",
              Analytics.class, Crashes.class);


   }

//   public void calculateSavings(View view) throws Exception {
//      throw new Exception("Something went wrong!");
//   }
//
   public void calculateSavings(View view){
//      Crashes.generateTestCrash();
   }
}
