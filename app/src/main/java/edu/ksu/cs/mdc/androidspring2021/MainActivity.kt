package edu.ksu.cs.mdc.androidspring2021

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Sets up a listener for click events on this button
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            // When clicked, executes this section of code
            // Creates an intent (Android's way of communicating between and within apps)
            val sendIntent: Intent = Intent().apply {
                // Send action tells the system that this information should be sent to someone
                // Apps declare compatibility with different actions in their Manifest
                action = Intent.ACTION_SEND
                // Include the text you want to share here
                putExtra(Intent.EXTRA_TEXT, "Example Text")
                putExtra(Intent.EXTRA_TITLE, "Example Title Text")
                // The MIME type of the content to share
                type = "text/plain"
            }

            // Create another intent to pull up a chooser
            val shareIntent = Intent.createChooser(sendIntent, null)
            // Start the intent you want to start
            startActivity(shareIntent)

            // Snackbar from previous example
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}