package com.paolomanlunas.androiddemo1;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   private static final String LOG_TAG = "LOG_TAG";

   private EditText mInterestRate;
   private EditText mCurrentAge;
   private EditText mRetirementAge;
   private EditText mMonthlySavings;
   private EditText mCurrentSavings;
   private TextView mResultText;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      AppCenter.start(getApplication(), "b848ee0d-5c05-4564-a8d2-28e7ce469f76",
              Analytics.class, Crashes.class);

      mInterestRate = findViewById(R.id.interestEditText);
      mCurrentAge = findViewById(R.id.ageEditText);
      mRetirementAge = findViewById(R.id.retirementEditText);
      mMonthlySavings = findViewById(R.id.monthlySavingsEditText);
      mCurrentSavings = findViewById(R.id.currentSavingsEditText);
      mResultText = findViewById(R.id.resultTextView);

   }

   public void calculateSavings(View view) {
      Log.d(LOG_TAG, "Calculate Button CLICKED!");
      Toast.makeText(this, "Calculate Button is CLICKED! ", Toast.LENGTH_SHORT).show();
//      Crashes.generateTestCrash();
      try {

         String interestRate = mInterestRate.getText().toString();
         float interestFloat = Float.parseFloat(interestRate);

         String currentAge = mCurrentAge.getText().toString();
         int currentAgeInt = Integer.parseInt(currentAge);

         String retirementAge = mRetirementAge.getText().toString();
         int retirementAgeInt = Integer.parseInt(retirementAge);

         String monthlySavings = mMonthlySavings.getText().toString();
         float monthlySavingsFloat = Float.parseFloat(monthlySavings);

         String currentSavings = mCurrentSavings.getText().toString();
         float currentSavingsFloat = Float.parseFloat(currentSavings);

         //----- Use-of 'Properties' in Analytics.trackEvent()
         // Theses 'Properties' will ALL be sent to AppCenter as 'Events' when an error is triggered.
         // This is important to see the CONTEXT of which the error/s occurred as the user is on the app.
         HashMap<String, String> properties = new HashMap<>();
         properties.put("interest_rate", String.valueOf(interestFloat));
         properties.put("current_age", currentAge);
         properties.put("retirement_age", retirementAge);
         properties.put("monthly_savings", String.valueOf(monthlySavingsFloat));
         properties.put("current_savings", String.valueOf(currentSavingsFloat));

         //----- Track Event will register in App Center's Analytics.
         // It will show the name as tag-names of the registered events.
         if (interestFloat <= 0) {
            // Track Event
            Analytics.trackEvent("wrong_interest_rate", properties);
         }
         if (retirementAgeInt <= currentAgeInt) {
            Analytics.trackEvent("wrong_age", properties);
         }

         // Calculate future saving:


         //--- Result Text
         mResultText.setText(String.format("At current rate of %s%%, saving $%s a month.", interestFloat, monthlySavingsFloat));
      } catch (Exception e) {
         Analytics.trackEvent(e.getMessage());
      }

   }
}
