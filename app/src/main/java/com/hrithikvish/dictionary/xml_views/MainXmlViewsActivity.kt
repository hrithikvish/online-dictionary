package com.hrithikvish.dictionary.xml_views

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.hrithikvish.dictionary.R
import com.hrithikvish.dictionary.databinding.ActivityMainXmlViewsBinding
import com.hrithikvish.dictionary.presentation.DictionaryUiEvents
import com.hrithikvish.dictionary.presentation.DictionaryViewModel
import com.hrithikvish.dictionary.xml_views.adapter.MeaningRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainXmlViewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainXmlViewsBinding

    private val dictionaryViewModel by viewModels<DictionaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_xml_views)
        binding.lifecycleOwner = this
        binding.dictionaryViewModel = dictionaryViewModel
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = MeaningRvAdapter()
        binding.meaningRv.adapter = adapter

        binding.searchBarLayout.setEndIconOnClickListener {
            dictionaryViewModel.onEvent(DictionaryUiEvents.OnSearchClick)
        }

        binding.searchBarEdt.setOnEditorActionListener { _, action, _ ->
            if(action == EditorInfo.IME_ACTION_SEARCH) {
                dictionaryViewModel.onEvent(DictionaryUiEvents.OnSearchClick)
            }
            true
        }

        val dictionaryStateFlow = dictionaryViewModel.dictionaryState

        lifecycleScope.launch {
            dictionaryStateFlow.collect { dictionaryState ->
                binding.wordItem = dictionaryState.wordItem
                dictionaryState.wordItem?.let { wordItem ->
                    adapter.submitWordItemAndNotify(wordItem.meanings)
                }
            }
        }

    }
}