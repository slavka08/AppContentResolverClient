package by.tms.appcontentresolver

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.tms.appcontentresolver.adapter.adapterRv
import by.tms.appcontentresolver.databinding.ActivityMainBinding
import by.tms.appcontentresolver.entity.Item

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: adapterRv? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        getContentProviderData()
        setOnclickListeners()
    }

    private fun getContentProviderData() {
        val listItems = mutableListOf<Item>()
        val rs = contentResolver.query(CONTENT_URI, null, null, null)
        if (rs?.moveToFirst() == true) {
            listItems.add(Item(rs.getInt(0), rs.getString(1)))
            while (rs.moveToNext()) listItems.add(Item(rs.getInt(0), rs.getString(1)))
            adapter?.submitList(listItems)
        }
    }

    private fun setAdapter() {
        adapter = adapterRv()
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(this)
    }

    private fun setOnclickListeners() {
        binding.buttonAdd.setOnClickListener {
            val newValues = ContentValues().apply {
                put(COLUMN_PATIENT, binding.tvInput.text.toString())
                put(COLUMN_ID, 33)
            }
            val newUri = contentResolver.insert(
                CONTENT_URI,
                newValues
            )
        }
    }

    companion object {
        private const val PROVIDER_NAME = "by.tms.appcontentserver"
        private const val PROVIDER_AUTH = "provider.CustomContentProvider"
        private const val URL = "content://$PROVIDER_NAME.$PROVIDER_AUTH/*"
        val CONTENT_URI = Uri.parse(URL)
        const val TABLE_NAME = "patient_table"
        const val COLUMN_ID = "id"
        const val COLUMN_PATIENT = "patient"
    }
}