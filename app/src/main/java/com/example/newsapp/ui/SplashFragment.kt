import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSplashBinding
import com.example.newsapp.ui.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private lateinit var biometricManager: BiometricManager

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        biometricManager = BiometricManager.from(requireContext())

        when (biometricManager.canAuthenticate(Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                // Biometric authentication is supported on this device
                if (biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
                    showBiometricPrompt()
                } else {
                    navigateHome()
                }
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                navigateHome()
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                navigateHome()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                navigateHome()
            }
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                navigateHome()
            }
            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                navigateHome()
            }
            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                navigateHome()
            }
        }
    }

    private fun showBiometricPrompt() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.finger_title))
            .setDescription(getString(R.string.finger_desc))
            .setNegativeButtonText(getString(R.string.cancel))
            .build()

        val biometricPrompt = BiometricPrompt(
            this,
            ContextCompat.getMainExecutor(requireContext()),
            authenticationCallback
        )
        biometricPrompt.authenticate(promptInfo)
    }

    private val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            navigateHome()
        }

        override fun onAuthenticationFailed() {
            showToast(getString(R.string.authentication_failed))
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            showToast(errString.toString())
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun navigateHome() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }
}
