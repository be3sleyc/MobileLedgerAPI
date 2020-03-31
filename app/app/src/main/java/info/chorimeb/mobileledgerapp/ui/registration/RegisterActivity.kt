package info.chorimeb.mobileledgerapp.ui.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import info.chorimeb.mobileledgerapp.R
import info.chorimeb.mobileledgerapp.ui.login.LoginActivity
import info.chorimeb.mobileledgerapp.ui.login.afterTextChanged

class RegisterActivity : AppCompatActivity() {

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val givenname = findViewById<EditText>(R.id.rFirstName)
        val surname = findViewById<EditText>(R.id.rLastName)
        val email = findViewById<EditText>(R.id.rEmail)
        val password = findViewById<EditText>(R.id.rPassword)
        val submit = findViewById<Button>(R.id.rSubmit)
        val loading = findViewById<ProgressBar>(R.id.loading)

        registrationViewModel = ViewModelProvider(this, RegistrationViewModelFactory())
            .get(RegistrationViewModel::class.java)

        registrationViewModel.registrationFormState.observe(this@RegisterActivity, Observer {
            val registrationState = it ?: return@Observer

            submit.isEnabled = registrationState.isDataValid

            if (registrationState.givennameError != null) {
                givenname.error = getString(registrationState.givennameError)
            }
            if (registrationState.surnameError != null) {
                surname.error = getString(registrationState.surnameError)
            }
            if (registrationState.emailError != null) {
                email.error = getString(registrationState.emailError)
            }
            if (registrationState.passwordError != null) {
                password.error = getString(registrationState.passwordError)
            }
        })

        registrationViewModel.registrationResult.observe(this@RegisterActivity, Observer {
            val registrationResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (registrationResult.error != null) {
                showRegistrationFailed(registrationResult.error)
            }
            if (registrationResult.success != null) {
                updateUIWithRegistrationSuccess()
            }
        })

        givenname.afterTextChanged {
            registrationViewModel.registrationDataChanged(
                givenname.text.toString(),
                surname.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        surname.afterTextChanged {
            registrationViewModel.registrationDataChanged(
                givenname.text.toString(),
                surname.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        email.afterTextChanged {
            registrationViewModel.registrationDataChanged(
                givenname.text.toString(),
                surname.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                registrationViewModel.registrationDataChanged(
                    givenname.text.toString(),
                    surname.text.toString(),
                    email.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        registrationViewModel.register(
                            givenname.text.toString(),
                            surname.text.toString(),
                            email.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            submit.setOnClickListener {
                loading.visibility = View.VISIBLE
                registrationViewModel.register(
                    givenname.text.toString(),
                    surname.text.toString(),
                    email.text.toString(),
                    password.text.toString()
                )
            }
        }
    }

    private fun updateUIWithRegistrationSuccess() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        //TODO : Show toast to notify of successful registration
        Toast.makeText(applicationContext, R.string.register_success, Toast.LENGTH_SHORT).show()
    }

    private fun showRegistrationFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}
