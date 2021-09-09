package by.tms.appcontentresolver

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.tms.appcontentresolver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rs = contentResolver.query(CONTENT_URI, arrayOf(_id, INFO, IMAGE), null, null)
        if (rs?.moveToFirst() == true) {
            binding.tvText.text =
                String.format("%s, %s, %s", rs.getString(0), rs.getString(1), rs.getString(2))
        }
    }

    companion object    {
        val PROVIDER_NAME = "by.tms.appcontentserver.CustomContentProvider"
        val URL = "content://$PROVIDER_NAME/TMSdb"
        val CONTENT_URI = Uri.parse(URL)

        val _id = "_id"
        val INFO = "INFO"
        val IMAGE = "IMAGE"
    }
}