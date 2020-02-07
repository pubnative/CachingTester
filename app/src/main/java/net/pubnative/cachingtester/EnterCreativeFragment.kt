package net.pubnative.cachingtester

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.pubnative.cachingtester.databinding.EnterCreativeFragmentBinding

class EnterCreativeFragment : Fragment() {

    private lateinit var binding: EnterCreativeFragmentBinding
    private lateinit var creativeURL: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.enter_creative_fragment,
            container,
            false
        )

        binding.cacheButton.setOnClickListener {
            Log.i("EnterCreativeFragment", "Clicked cache button")
            creativeURL = binding.creativeEditText.text.toString()
            Toast.makeText(context,"Creative cached",Toast.LENGTH_SHORT).show()
        }

        binding.showButton.setOnClickListener {
            Log.i("EnterCreativeFragment", "Clicked show button")
            if (TextUtils.isEmpty(creativeURL)) {
                Toast.makeText(context,"No creative has been added.",Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(
                    EnterCreativeFragmentDirections
                        .actionEnterCreativeFragmentToWebViewFragment(creativeURL)
                )
            }
        }

        binding.pasteButton.setOnClickListener{
            binding.creativeEditText.setText(ClipboardUtils.copyFromClipboard(context!!))
            Toast.makeText(context,"Clipboard pasted",Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

}