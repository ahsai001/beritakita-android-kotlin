package com.ahsailabs.beritakita_kotlin.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ahsailabs.beritakita_kotlin.R
import com.ahsailabs.beritakita_kotlin.configs.Config
import com.ahsailabs.beritakita_kotlin.ui.login.models.LoginData
import com.ahsailabs.beritakita_kotlin.ui.login.models.LoginResponse
import com.ahsailabs.beritakita_kotlin.utils.HttpUtil
import com.ahsailabs.beritakita_kotlin.utils.InfoUtil
import com.ahsailabs.beritakita_kotlin.utils.SessionUtil
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private var tilUserName: TextInputLayout? = null
    private var etUsername: TextInputEditText? = null
    private var tilPassword: TextInputLayout? = null
    private var etPassword: TextInputEditText? = null
    private var btnLogin: MaterialButton? = null
    private var llLoginForm: LinearLayout? = null

    private var llLoadingPanel: LinearLayout? = null
    private var pbLoadingIndicator: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViews(view)
        registerClickListener()
    }


    private fun loadViews(root: View) {
        tilUserName = root.findViewById(R.id.tilUserName)
        etUsername = root.findViewById(R.id.etUsername)
        tilPassword = root.findViewById(R.id.tilPassword)
        etPassword = root.findViewById(R.id.etPassword)
        btnLogin = root.findViewById(R.id.btnLogin)
        llLoginForm = root.findViewById(R.id.llLoginForm)

        llLoadingPanel = root.findViewById(R.id.llLoadingPanel)
        pbLoadingIndicator = root.findViewById(R.id.pbLoadingIndicator)
    }

    private fun registerClickListener() {
        btnLogin?.setOnClickListener {
            //TODO : get all data, then validate, then login process
            tilPassword!!.error = null
            tilUserName!!.error = null

            //get all data
            val strUserName = etUsername!!.text.toString()
            val strPassword = etPassword!!.text.toString()

            //validasi inputan
            if (TextUtils.isEmpty(strUserName)) {
                tilUserName!!.error = "Username wajib diisi"
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(strPassword)) {
                tilPassword!!.error = "Password wajib diisi"
                return@setOnClickListener
            }

            doLogin(strUserName, strPassword)
        }
    }


    private fun showLoading() {
        llLoginForm!!.visibility = View.INVISIBLE
        btnLogin!!.visibility = View.INVISIBLE
        llLoadingPanel!!.visibility = View.VISIBLE
        pbLoadingIndicator!!.progress = 50
    }

    private fun hideLoading() {
        llLoginForm!!.visibility = View.VISIBLE
        btnLogin!!.visibility = View.VISIBLE
        llLoadingPanel!!.visibility = View.INVISIBLE
        pbLoadingIndicator!!.progress = 0
    }

    private fun doLogin(strUserName: String, strPassword: String) {
        showLoading()
        AndroidNetworking.post(Config.loginUrl)
            .setOkHttpClient(HttpUtil.getCLient(requireContext()))
            .addBodyParameter("username", strUserName)
            .addBodyParameter("password", strPassword)
            .setTag("login")
            .setPriority(Priority.HIGH)
            .build()
            .getAsObject(LoginResponse::class.java, object : ParsedRequestListener<LoginResponse> {
                override fun onResponse(response: LoginResponse) {
                    if (response.status == 1) {
                        //TODO : handleSuccessLogin
                        val loginData: LoginData? = response.data
                        loginData?.let { SessionUtil.login(requireContext(), it) }
                        InfoUtil.showToast(context, "Login anda berhasil")
                        InfoUtil.showSnackBar(view, "Login anda berhasil")
                        //TODO : close login page & show news list page
                        val navOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.nav_home, true)
                            .build()
                        findNavController().navigate(R.id.nav_home, null, navOptions)
                        //or in this case, we can use navigateUp()
                        //findNavController().navigateUp()
                    } else {
                        when (response.status) {
                            -6 -> InfoUtil.showToast(context, "Username atau password anda salah")
                            -1 -> InfoUtil.showToast(context, "Anda tidak berhak menggunakan api ini")
                            else -> InfoUtil.showToast(context, response.message)
                        }
                    }
                    hideLoading()
                }

                override fun onError(anError: ANError) {
                    InfoUtil.showToast(context, anError.message)
                    hideLoading()
                }
            })
    }

    override fun onDestroyView() {
        AndroidNetworking.cancel("login")
        super.onDestroyView()
    }
}