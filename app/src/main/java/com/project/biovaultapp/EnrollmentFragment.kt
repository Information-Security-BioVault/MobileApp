package com.project.biovaultapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.biovaultapp.databinding.FragmentEnrollmentBinding

class EnrollmentFragment : Fragment() {

    lateinit var binding: FragmentEnrollmentBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEnrollmentBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        binding.run {
            editTextTextNameValue.setText(MyApplication.name)
            editTextTextMajorValue.setText(MyApplication.major)
            editTextTextStudentNumValue.setText(MyApplication.studentNum)
            editTextTextCollegeValue.setText(MyApplication.college)

            buttonEnrollment.setOnClickListener {
                MyApplication.name = editTextTextNameValue.text.toString()
                MyApplication.major = editTextTextMajorValue.text.toString()
                MyApplication.studentNum = editTextTextStudentNumValue.text.toString()
                MyApplication.college = editTextTextCollegeValue.text.toString()

                MyApplication.preferences.setString("enrolledInfo", "true")
                MyApplication.preferences.setString("name", "${editTextTextNameValue.text.toString()}")
                MyApplication.preferences.setString("major", "${editTextTextMajorValue.text.toString()}")
                MyApplication.preferences.setString("studentNum", "${editTextTextStudentNumValue.text.toString()}")
                MyApplication.preferences.setString("college", "${editTextTextCollegeValue.text.toString()}")

                val mainFragment = MainFragment()

                val transaction = mainActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, mainFragment)
                transaction.addToBackStack("")
                transaction.commit()
            }
        }

        return binding.root
    }
}