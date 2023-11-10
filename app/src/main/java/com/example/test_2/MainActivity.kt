package com.example.test_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_2.databinding.ActivityMainBinding
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var container = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    fun setUp() {
        val btnSave = binding.btnSave
        val btnOutput = binding.btnOutput
        val userInput = binding.etInput
        val result = binding.etResult

        btnSave.setOnClickListener {
            if(isFilled(userInput.text.toString())) {
                container.add(userInput.text.toString())
            }
            userInput.setText("")
        }

        btnOutput.setOnClickListener {
            val newContainer = setAnagrams(container)
            result.text = "${newContainer.size}"
        }
    }

    fun isFilled(input: String): Boolean {
        return input.isNotEmpty()
    }

    fun setAnagrams(list: MutableList<String>): List<List<String>> {
        val result = mutableListOf<List<String>>()

        for(firstIndex in list.indices) {
            val subList = mutableListOf(list[firstIndex])
            for(secondIndex in list.indices) {
                if(firstIndex == secondIndex) {
                    continue
                }
                if(isAnagram(list[firstIndex], list[secondIndex])) {
                    subList.add(list[secondIndex])
                }
            }
            result.add(subList)
        }
        return result
    }

    //Function to check if two strings are anagrams
    fun isAnagram(firstString: String, secondString: String): Boolean {
        if(firstString.length != secondString.length) {
            return false
        }

        val firstStringArray = firstString.toCharArray()
        val secondStringArray = secondString.toCharArray()

        Arrays.sort(firstStringArray)
        Arrays.sort(secondStringArray)

        val sortedFirstString = String(firstStringArray)
        val sortedSecondString = String(secondStringArray)

        return sortedFirstString == sortedSecondString
    }
}