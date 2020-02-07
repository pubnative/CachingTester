package net.pubnative.cachingtester

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import net.pubnative.cachingtester.databinding.WebviewFragmentBinding
import net.pubnative.cachingtester.text.StringEscapeUtils

class WebViewFragment : Fragment(){

    private lateinit var binding: WebviewFragmentBinding

    private lateinit var urlHandler: UrlHandler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.webview_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        urlHandler = UrlHandler(context)

        val scoreFragmentArgs by navArgs<WebViewFragmentArgs>()

        prepareWebView()
        loadWebView(scoreFragmentArgs.creative)
    }

    private fun prepareWebView() {

        val settings = binding.webview.settings
        settings.javaScriptEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(binding.webview, true)
        }
    }

    private fun loadWebView(creative: String) {
        val transformedCreative = StringEscapeUtils.unescapeJava(creative)

        binding.webview.loadData(transformedCreative, "text/html", "UTF-8")
    }


}