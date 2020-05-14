package com.paolomanlunas.androiddemo1;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private EditText mInterestRate;
   private EditText mCurrentAge;
   private EditText mRetirementAge;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      AppCenter.start(getApplication(), "b848ee0d-5c05-4564-a8d2-28e7ce469f76",
              Analytics.class, Crashes.class);

      mInterestRate = findViewById(R.id.interestEditText);
      mCurrentAge = findViewById(R.id.ageEditText);
      mRetirementAge = findViewById(R.id.retirementEditText);

   }

   public void calculateSavings(View view){
//      Crashes.generateTestCrash();
      String interestRate = mInterestRate.getText().toString();
      float interestFloat = Float.parseFloat(interestRate);

      String currentAge = mCurrentAge.getText().toString();
      int currentAgeInt = Integer.parseInt(currentAge);

      String retirementAge = mRetirementAge.getText().toString();
      int retirementAgeInt = Integer.parseInt(retirementAge);

      //--- Track Event will register in App Center's Analytics.
      // It will show the name as tag-names of the registered events.
      if (interestFloat <= 0) {
         // Track Event
         Analytics.trackEvent("wrong_interest_rate");
      }

      if (retirementAgeInt <= currentAgeInt) {
         Analytics.trackEvent("wrong_age");
      }

      Toast.makeText(this,"Interest is "+interestFloat, Toast.LENGTH_SHORT).show();
   }
}
