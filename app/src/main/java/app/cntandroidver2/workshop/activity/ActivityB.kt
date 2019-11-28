package app.cntandroidver2.workshop.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import app.cntandroidver2.workshop.R
import app.cntandroidver2.workshop.ui.tab2.Tab2Fragment
import kotlinx.android.synthetic.main.activity_b.*


class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
//        centerToolbarTitle(toolbar)

        val message = intent.getStringExtra(Tab2Fragment.KEY_MESSAGE)

        activityBTxtParameter.text = message


    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
//        super.onBackPressed()

        val intent = Intent()
        intent.putExtra(Tab2Fragment.KEY_MESSAGE, "I am is returned from B")
        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}
