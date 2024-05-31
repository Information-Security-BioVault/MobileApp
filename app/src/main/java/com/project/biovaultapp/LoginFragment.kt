package com.project.biovaultapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.biovaultapp.api.ApiClient
import com.project.biovaultapp.api.model.RequestModel
import com.project.biovaultapp.api.model.ResponseModel
import com.project.biovaultapp.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        MyApplication.preferences = PreferenceUtil(mainActivity)

        binding.run {
            buttonCheck.setOnClickListener {
                check()
            }
        }
        return binding.root
    }

    // 심박수 등록 API 연동
    fun check() {
        var apiClient = ApiClient(requireContext())

        var checkData = RequestModel(binding.editTextTextDeviceId.text.toString())

        apiClient.apiService.check(checkData)?.enqueue(object :
            Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    // 정상적으로 통신이 성공된 경우
                    var result: ResponseModel? = response.body()
                    Log.d("##", "onResponse 성공: " + result?.toString())

                    if(MyApplication.preferences.getString("enrolledInfo", "false") == "true") {
                        var mainFragment = MainFragment()

                        val transaction = mainActivity.supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.fragmentContainer, mainFragment)
                        transaction.addToBackStack("")
                        transaction.commit()
                    } else {
                        var enrollmentFragment = EnrollmentFragment()

                        val transaction = mainActivity.supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.fragmentContainer, enrollmentFragment)
                        transaction.addToBackStack("")
                        transaction.commit()
                    }
                } else {
                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                    var result: ResponseModel? = response.body()
                    Log.d("##", "onResponse 실패")
                    Log.d("##", "onResponse 실패: " + response.code())
                    Log.d("##", "onResponse 실패: " + response.body())
                    val errorBody = response.errorBody()?.string() // 에러 응답 데이터를 문자열로 얻음
                    Log.d("##", "Error Response: $errorBody")

                    Toast.makeText(requireContext(), "다시 시도해주세요.", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                // 통신 실패
                Log.d("##", "onFailure 에러: " + t.message.toString());
            }
        })
    }
}