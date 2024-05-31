package com.project.biovaultapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.biovaultapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        binding.run {
            textViewNameValue.text = MyApplication.preferences.getString("name", "")
            textViewMajorValue.text = MyApplication.preferences.getString("major", "")
            textViewStudentNumValue.text = MyApplication.preferences.getString("studentNum", "")
            textViewCollegeValue.text = MyApplication.preferences.getString("college", "")
            buttonEdit.setOnClickListener {
                val enrollFragment = EnrollmentFragment()

                val transaction = mainActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, enrollFragment)
                transaction.addToBackStack("")
                transaction.commit()
            }
        }

        return binding.root
    }

}