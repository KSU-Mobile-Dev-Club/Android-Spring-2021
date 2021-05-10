package edu.ksu.cs.mdc.androidspring2021

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        // When created, if no saved state, replace the frame layout with the settings fragment.
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Called when an options item in the top bar is selected (only home, on this activity)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // When the app back/home button is pressed ...
            android.R.id.home -> {
                // Navigate to the previous screen in the app
                // Not super necessary, but still works.
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load preference titles and descriptions from the XML file
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            // sharedPreferences is already storing the state of each preference, but to do things
            // when their state is changed, we will need to set up an onClick or onChange listener
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        }
    }
}