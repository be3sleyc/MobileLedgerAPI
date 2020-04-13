package info.chorimeb.mobileledgerapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import info.chorimeb.mobileledgerapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.pref_main, rootKey);
    }


}
