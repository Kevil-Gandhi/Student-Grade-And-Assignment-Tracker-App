//package com.example.myapplication.Student
//
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Bundle
//import android.view.View
//import android.webkit.ValueCallback
//import android.webkit.WebChromeClient
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageButton
//import android.widget.LinearLayout
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapplication.R
//import java.util.jar.Manifest
//
//class student_main : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_student_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//
//
//            requestStoragePermission()
//
//            val webView2 = findViewById<WebView>(R.id.webView2)
//
//            webView2.settings.javaScriptEnabled = true
//            webView2.settings.allowFileAccess = true
//            webView2.settings.allowContentAccess = true
//            webView2.settings.domStorageEnabled = true
//
//            webView2.webViewClient = WebViewClient()
//
//            webView2.webChromeClient = object : WebChromeClient() {
//                override fun onShowFileChooser(
//                    webView: WebView?,
//                    filePathCallback: ValueCallback<Array<Uri>>?,
//                    fileChooserParams: FileChooserParams?
//                ): Boolean {
//                    this@student_main.filePathCallback  = filePathCallback
//                    openFilePicker()
//                    return true
//                }
//
//            }
//
//            webView2.loadUrl("https://temp2.priyatal.buzz/student.php")
//
//            insets
//        }
//
//
//        Toast.makeText(this, "Student", Toast.LENGTH_SHORT).show()
//
//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        val webView1: WebView = findViewById(R.id.webView1)
////        val webView2: WebView = findViewById(R.id.webView2)
//
//        webView1.settings.javaScriptEnabled = true
//        webView1.webViewClient = WebViewClient()
//
//        webView1.loadUrl("https://temp2.priyatal.buzz/view.php")
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener(View.OnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//        })
//
//        img_btn2.setOnClickListener(View.OnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//        })
//
//        img_btn3.setOnClickListener(View.OnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        })
//    }
//    private fun openFilePicker() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "application/pdf"
//        startActivityForResult(intent, FILE_REQUEST_CODE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK) {
//            val uri = data?.data
//            uri?.let {
//                filePathCallback?.onReceiveValue(arrayOf(it))
//                filePathCallback = null
//            }
//        } else {
//            filePathCallback?.onReceiveValue(null)
//            filePathCallback = null
//        }
//    }
//
//    private fun requestStoragePermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
//        }
//    }
//}


//_______________________________________ Main Working Code _______________________________________

//*******

//package com.example.myapplication.Student
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Bundle
//import android.view.View
//import android.webkit.ValueCallback
//import android.webkit.WebChromeClient
//import android.webkit.WebResourceRequest
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageButton
//import android.widget.LinearLayout
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapplication.R
//
//class student_main : AppCompatActivity() {
//
//    private var filePathCallback: ValueCallback<Array<Uri>>? = null
//    private val FILE_REQUEST_CODE = 100
//
//    private lateinit var webView2: WebView
//
//    @SuppressLint("SetJavaScriptEnabled")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_student_main)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        requestStoragePermission()
//
//        webView2 = findViewById(R.id.webView2)
//
//        setupWebView()
//
//        val webView1: WebView = findViewById(R.id.webView1)
//        webView1.settings.javaScriptEnabled = true
//        webView1.webViewClient = WebViewClient()
//        webView1.loadUrl("https://temp2.priyatal.buzz/view.php")
//
//        Toast.makeText(this, "Student", Toast.LENGTH_SHORT).show()
//
//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//        }
//
//        img_btn2.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//        }
//
//        img_btn3.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        }
//    }
//
//    // Set up WebView for better file upload handling
//    private fun setupWebView() {
//        webView2.settings.apply {
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            allowFileAccess = true
//            allowContentAccess = true
//            useWideViewPort = true
//            loadWithOverviewMode = true
//        }
//
//        webView2.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                return false
//            }
//        }
//
//        webView2.webChromeClient = object : WebChromeClient() {
//            override fun onShowFileChooser(
//                webView: WebView?,
//                filePathCallback: ValueCallback<Array<Uri>>?,
//                fileChooserParams: FileChooserParams?
//            ): Boolean {
//                this@student_main.filePathCallback = filePathCallback
//                openFilePicker()
//                return true
//            }
//        }
//
//        // Ensure the page reload doesn't reset the input fields
//        if (webView2.url == null) {
//            webView2.loadUrl("https://temp2.priyatal.buzz/student.php")
//        }
//    }
//
//    // Open the PDF picker
//    private fun openFilePicker() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
//            type = "application/pdf"
//            addCategory(Intent.CATEGORY_OPENABLE)
//        }
//        startActivityForResult(intent, FILE_REQUEST_CODE)
//    }
//
//    // Handle the PDF selection
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK) {
//            val uri = data?.data
//            filePathCallback?.onReceiveValue(arrayOf(uri!!))
//        } else {
//            filePathCallback?.onReceiveValue(null)
//        }
//        filePathCallback = null
//    }
//
//    // Request permission for external storage
//    private fun requestStoragePermission() {
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                101
//            )
//        }
//    }
//}

//_________________________________________________________________________________


// _______________________________________________ Main PDF code All Functionality ________________________________

//package com.example.myapplication.Student
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Bundle
//import android.view.View
//import android.webkit.ValueCallback
//import android.webkit.WebChromeClient
//import android.webkit.WebResourceRequest
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageButton
//import android.widget.LinearLayout
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapplication.R
//
//class student_main : AppCompatActivity() {
//
//    private var filePathCallback: ValueCallback<Array<Uri>>? = null
//    private val FILE_REQUEST_CODE = 100
//
//    private lateinit var webView1: WebView
//    private lateinit var webView2: WebView
//
//    @SuppressLint("SetJavaScriptEnabled")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_student_main)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        requestStoragePermission()
//
//        webView1 = findViewById(R.id.webView1)
//        webView2 = findViewById(R.id.webView2)
//
//        setupWebView(webView1, "https://temp2.priyatal.buzz/view.php")   // Section 1 (View PDF)
//        setupWebView(webView2, "https://temp2.priyatal.buzz/student.php") // Section 2 (Student PDF)
//
//        Toast.makeText(this, "Student", Toast.LENGTH_SHORT).show()
//
//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//        }
//
//        img_btn2.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//        }
//
//        img_btn3.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        }
//    }
//
//    // Set up WebView for opening PDFs inside WebView using Google Docs Viewer
//    private fun setupWebView(webView: WebView, url: String) {
//        webView.settings.apply {
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            allowFileAccess = true
//            allowContentAccess = true
//            useWideViewPort = true
//            loadWithOverviewMode = true
//        }
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                val url = request?.url.toString()
//
//                // If it's a PDF file, open it using Google Docs Viewer
//                if (url.endsWith(".pdf")) {
//                    val pdfViewerUrl = "https://docs.google.com/gview?embedded=true&url=$url"
//                    view?.loadUrl(pdfViewerUrl)
//                    return true
//                }
//                return false
//            }
//        }
//
//        webView.webChromeClient = object : WebChromeClient() {
//            override fun onShowFileChooser(
//                webView: WebView?,
//                filePathCallback: ValueCallback<Array<Uri>>?,
//                fileChooserParams: FileChooserParams?
//            ): Boolean {
//                this@student_main.filePathCallback = filePathCallback
//                openFilePicker()
//                return true
//            }
//        }
//
//        // Load the URL if it's not already loaded
//        if (webView.url == null) {
//            webView.loadUrl(url)
//        }
//    }
//
//    // Set up WebView for better file upload handling and PDF support
//    private fun setupWebView() {
//        webView2.settings.apply {
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            allowFileAccess = true
//            allowContentAccess = true
//            useWideViewPort = true
//            loadWithOverviewMode = true
//        }
//
//        // Handle PDF file opening
//        webView2.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                val url = request?.url.toString()
//
//                if (url.endsWith(".pdf")) {
//                    try {
//                        // Try to open the PDF in Google Docs Viewer
//                        val pdfViewerUrl = "https://docs.google.com/gview?embedded=true&url=$url"
//                        view?.loadUrl(pdfViewerUrl)
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                        openPdfExternally(url) // Open with external PDF viewer if Google Viewer fails
//                    }
//                    return true
//                }
//                return false
//            }
//        }
//
//        webView2.webChromeClient = object : WebChromeClient() {
//            override fun onShowFileChooser(
//                webView: WebView?,
//                filePathCallback: ValueCallback<Array<Uri>>?,
//                fileChooserParams: FileChooserParams?
//            ): Boolean {
//                this@student_main.filePathCallback = filePathCallback
//                openFilePicker()
//                return true
//            }
//        }
//
//        // Ensure the page reload doesn't reset the input fields
//        if (webView2.url == null) {
//            webView2.loadUrl("https://temp2.priyatal.buzz/student.php")
//        }
//    }
//
//    // Open PDF using external applications (native PDF viewer)
//    private fun openPdfExternally(pdfUrl: String) {
//        try {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
//            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//            startActivity(intent)
//        } catch (e: Exception) {
//            Toast.makeText(this, "No PDF viewer found", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    // Open the PDF picker
//    private fun openFilePicker() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
//            type = "application/pdf"
//            addCategory(Intent.CATEGORY_OPENABLE)
//        }
//        startActivityForResult(intent, FILE_REQUEST_CODE)
//    }
//
//    // Handle the PDF selection
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK) {
//            val uri = data?.data
//            filePathCallback?.onReceiveValue(arrayOf(uri!!))
//        } else {
//            filePathCallback?.onReceiveValue(null)
//        }
//        filePathCallback = null
//    }
//
//    // Request permission for external storage
//    private fun requestStoragePermission() {
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                101
//            )
//        }
//    }
//
//    // Handle back navigation in WebView
//    override fun onBackPressed() {
//        when {
//            webView1.visibility == View.VISIBLE && webView1.canGoBack() -> webView1.goBack() // Go back in webView1
//            webView2.visibility == View.VISIBLE && webView2.canGoBack() -> webView2.goBack() // Go back in webView2
//            else -> super.onBackPressed() // Default behavior (exit the activity)
//        }
//    }
//}

//____________________________________________________________________________________

//package com.example.myapplication.Student
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.view.View
//import android.webkit.WebResourceRequest
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.ImageButton
//import android.widget.LinearLayout
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.myapplication.R
//
//class student_main : AppCompatActivity() {
//
//    private lateinit var webView1: WebView
//    private lateinit var webView2: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_student_main)
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)  // Upload Assignment
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)  // View Assignment
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)  // Update Profile
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        webView1 = findViewById(R.id.webView1)  // Upload Assignment
//        webView2 = findViewById(R.id.webView2)  // View Assignment
//
//        // Set up WebView for PDF handling
//        setupWebView(webView1)
//        setupWebView(webView2)
//
//        // Load initial pages
//        webView1.loadUrl("https://temp2.priyatal.buzz/upload.php")
//        webView2.loadUrl("https://temp2.priyatal.buzz/student.php")
//
//        // Initial visibility
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        // Upload Assignment Button
//        img_btn1.setOnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//            webView1.loadUrl("https://temp2.priyatal.buzz/upload.php")
//        }
//
//        // View Assignment Button
//        img_btn2.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//            webView2.loadUrl("https://temp2.priyatal.buzz/student.php")
//        }
//
//        // Update Profile Button
//        img_btn3.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        }
//    }
//
//    // Set up WebView with PDF handling (opens PDFs in Chrome tab)
//    private fun setupWebView(webView: WebView) {
//        webView.settings.apply {
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            allowFileAccess = true
//            allowContentAccess = true
//            loadWithOverviewMode = true
//            useWideViewPort = true
//        }
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                val url = request?.url.toString()
//
//                // Open PDF (Google Docs Viewer) in Chrome tab
//                if (url.endsWith(".pdf", ignoreCase = true)) {
//                    val pdfViewerUrl = "https://docs.google.com/gview?embedded=true&url=$url"
//                    openInChromeTab(pdfViewerUrl)
//                    return true
//                }
//
//                // Allow WebView to handle other URLs
//                return false
//            }
//        }
//    }
//
//    // Open URL in a new Chrome tab (or default browser if Chrome is unavailable)
//    private fun openInChromeTab(url: String) {
//        try {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // Ensure it opens in a new tab
//            intent.setPackage("com.android.chrome")  // Specifically open in Chrome
//            startActivity(intent)
//        } catch (e: Exception) {
//            e.printStackTrace()
//
//            // Fallback: Open in the default browser if Chrome is unavailable
//            val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            startActivity(fallbackIntent)
//        }
//    }
//
//    // Handle back navigation for WebView
//    override fun onBackPressed() {
//        when {
//            findViewById<LinearLayout>(R.id.section1).visibility == View.VISIBLE && webView1.canGoBack() -> webView1.goBack()
//            findViewById<LinearLayout>(R.id.section2).visibility == View.VISIBLE && webView2.canGoBack() -> webView2.goBack()
//            else -> super.onBackPressed()
//        }
//    }
//}


//  ____________________________ Perfectly Works ******* _____________________________________

package com.example.myapplication.Student

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Admin.StudentAdapter
import com.example.myapplication.Admin.TeacherAdapter
import com.example.myapplication.R
import com.example.myapplication.StudentData
import com.example.myapplication.TeacherData
import com.example.myapplication.WebAppInterface
import com.example.myapplication.changePass
import com.example.myapplication.login
import com.example.myapplication.noticeAdapter
import com.example.myapplication.viewNotices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class student_main : AppCompatActivity() {

    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val FILE_REQUEST_CODE = 100

    private lateinit var webView1: WebView
    private lateinit var webView2: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        requestStoragePermission()

        val notice = findViewById<FloatingActionButton>(R.id.fab)
        notice.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, viewNotices::class.java)
            startActivity(intent)
        })

        val logoutBtn = findViewById<FloatingActionButton>(R.id.fab2)

//        logoutBtn.setOnClickListener {
//            val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.clear() // Clears all stored values
//            editor.apply()
//
//            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
//
//            // Redirect to login screen and clear back stack
//            val intent = Intent(this, login::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//            finish()
//        }

        logoutBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Logout")
            builder.setMessage("Are you sure you want to logout?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                // Clear SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

                // Redirect to login screen
                val intent = Intent(this, login::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()

                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss() // Close the dialog
            }
            val dialog = builder.create()
            dialog.show()
        }

        val gradeLayout = findViewById<LinearLayout>(R.id.section1)
        val uploadAssLayout = findViewById<LinearLayout>(R.id.section2)
        val profileLayout = findViewById<LinearLayout>(R.id.section3)

        val grade:LinearLayout = findViewById(R.id.grade)
        val upload:LinearLayout = findViewById(R.id.uploadAss)
        val profile:LinearLayout = findViewById(R.id.profile)

        gradeLayout.visibility = View.VISIBLE
        uploadAssLayout.visibility = View.GONE
        profileLayout.visibility = View.GONE

        grade.setOnClickListener{
            gradeLayout.visibility = View.VISIBLE
            uploadAssLayout.visibility = View.GONE
            profileLayout.visibility = View.GONE

            grade.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
            upload.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            profile.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))

            loadStudentPage()
        }

        upload.setOnClickListener{
            gradeLayout.visibility = View.GONE
            uploadAssLayout.visibility = View.VISIBLE
            profileLayout.visibility = View.GONE

            grade.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            upload.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))
            profile.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))

            loadStudentPage()
        }

        profile.setOnClickListener{
            gradeLayout.visibility = View.GONE
            uploadAssLayout.visibility = View.GONE
            profileLayout.visibility = View.VISIBLE

            grade.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            upload.setBackgroundColor(ContextCompat.getColor(this, R.color.nevyBlue))
            profile.setBackgroundColor(ContextCompat.getColor(this, R.color.BlueGreen))

            loadStudentPage()
        }

        webView1 = findViewById(R.id.webView1)
        webView2 = findViewById(R.id.webView2)

        webView1.settings.javaScriptEnabled = true      //  *
        webView1.addJavascriptInterface(WebAppInterface(this), "AndroidInterface")      //      *

        webView2.settings.javaScriptEnabled = true      //  *
        webView2.addJavascriptInterface(WebAppInterface(this), "AndroidInterface")      //      *

        setupWebView(webView1)
        setupWebView(webView2)

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//        val studentName = sharedPreferences.getString("student_name", null)
        val studentName = sharedPreferences.getString("name", null)

//        val semester = sharedPreferences.getInt("student_semester", 0)
        val semester = sharedPreferences.getInt("sem", 0)
        val encodedName = Uri.encode(studentName)

        Toast.makeText(this, studentName+semester, Toast.LENGTH_SHORT).show()

//        val studentUrl = "http://kevil.infinityfreeapp.com/student2.php?student_name=$encodedName&semester=$semester"

//        val studentUrl = "http://kevil.infinityfreeapp.com/student3.php?student_name=$encodedName&semester=$semester"
//        val studentUrl = "http://kevil.infinityfreeapp.com/student4.php?student_name=$encodedName&semester=$semester"
        val studentUrl = "http://kevil.infinityfreeapp.com/student5.php?student_name=$encodedName&semester=$semester"  // remember


//        webView1.loadUrl(studentUrl)

//        webView1.loadUrl("https://temp2.priyatal.buzz/view.php")

        webView1.loadUrl("http://kevil.infinityfreeapp.com/view.php") // remember

//        webView2.loadUrl("https://temp2.priyatal.buzz/student.php")
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/all_assignment.php")
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/all_assignment2.php")
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/all_assignment2.php")        //      Remember

        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade.php?student=")

        loadStudentPage()
        webView2.loadUrl(studentUrl)          //  remember
        Toast.makeText(this, "Student", Toast.LENGTH_SHORT).show()

//        val img_btn1: ImageButton = findViewById(R.id.imageButton1)
//        val img_btn2: ImageButton = findViewById(R.id.imageButton2)
//        val img_btn3: ImageButton = findViewById(R.id.imageButton3)
//
//        val sec1: LinearLayout = findViewById(R.id.section1)
//        val sec2: LinearLayout = findViewById(R.id.section2)
//        val sec3: LinearLayout = findViewById(R.id.section3)
//
//        sec1.visibility = View.VISIBLE
//        sec2.visibility = View.GONE
//        sec3.visibility = View.GONE
//
//        img_btn1.setOnClickListener {
//            sec1.visibility = View.VISIBLE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.GONE
//        }
//
//        img_btn2.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.VISIBLE
//            sec3.visibility = View.GONE
//
//            loadStudentPage()
//        }
//
//        img_btn3.setOnClickListener {
//            sec1.visibility = View.GONE
//            sec2.visibility = View.GONE
//            sec3.visibility = View.VISIBLE
//        }

        val emailtxt = findViewById<TextView>(R.id.emailTxt)
        val nametxt = findViewById<TextView>(R.id.nameTxt)
        val contacttxt = findViewById<TextView>(R.id.contactTxt)
        val gendertxt = findViewById<TextView>(R.id.genderTxt)
        val semtxt = findViewById<TextView>(R.id.semTxt)
        val rolltxt = findViewById<TextView>(R.id.rollTxt)
        val roleTxt = findViewById<TextView>(R.id.roleTxt)
        val nameHeading = findViewById<TextView>(R.id.nameHeading)
        val emailHeading = findViewById<TextView>(R.id.emailHeading)

//        val sp:Editor = sharedPreferences.edit();

        val email = sharedPreferences.getString("email", "")?.replace("_", "@")?.replace("-", ".");
        emailtxt.text = email
        nametxt.text = sharedPreferences.getString("name", "")
        contacttxt.text = sharedPreferences.getString("contact", "")
        gendertxt.text = sharedPreferences.getString("gender", "")

        semtxt.text = sharedPreferences.getInt("sem",0).toString()
        rolltxt.text = sharedPreferences.getInt("roll",0).toString()

        roleTxt.text = sharedPreferences.getString("user_role", "")
        nameHeading.text = sharedPreferences.getString("name", "")
        emailHeading.text = sharedPreferences.getString("email", "")

        val btnEmail: ImageView = findViewById(R.id.btn_email)
        val btnName: ImageView = findViewById(R.id.nameBtn)
        val btnContact: ImageView = findViewById(R.id.contactBtn)
        val btnGender: ImageView = findViewById(R.id.genderBtn)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.updatepage)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val heading: TextView = dialog.findViewById(R.id.updateHeading)
        val oldData: EditText = dialog.findViewById(R.id.oldData)
        val newData: EditText = dialog.findViewById(R.id.newData)
        val cancel: Button = dialog.findViewById(R.id.cancelBtn)
        val update: Button = dialog.findViewById(R.id.updateBtn)

        cancel.setOnClickListener {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        btnEmail.setOnClickListener {
            oldData.setText(emailtxt.text.toString())
            heading.text = "Email"
            newData.setText("")
            dialog.show()
        }

        btnName.setOnClickListener {
            oldData.setText(nametxt.text.toString())
            heading.text = "Name"
            newData.setText("")
            dialog.show()
        }

        btnGender.setOnClickListener {
            oldData.setText(gendertxt.text.toString())
            heading.text = "Gender"
            newData.setText("")
            dialog.show()
        }

        btnContact.setOnClickListener {
            oldData.setText(contacttxt.text.toString())
            heading.text = "Contact"
            newData.setText("")
            dialog.show()
        }

        val path = "Register_info/Student";

        val existingRoll = sharedPreferences.getInt("roll", 0)
        val existingSem = sharedPreferences.getInt("sem", 0)

        update.setOnClickListener {
            val field = heading.text.toString()
            val updatedValue = newData.text.toString()
            val sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit()
            val oldEmailKey = emailtxt.text.toString()
            val safeOldEmailKey = getSafeEmailKey(oldEmailKey)
            val updates = HashMap<String, Any>()

            val dr: DatabaseReference = FirebaseDatabase.getInstance().getReference(path)

            if (field == "Email") {
                updates[field.lowercase()] = updatedValue
                val newEmailKey = getSafeEmailKey(updatedValue)
                dr.child(safeOldEmailKey).get().addOnSuccessListener { it ->
                    if (it.exists()) {

                        val roll = it.child("roll").value?.toString()?.toIntOrNull() ?: existingRoll
                        val sem = it.child("sem").value?.toString()?.toIntOrNull() ?: existingSem

                        val student = StudentData(
                            it.child("email").value?.toString() ?: "",
                            it.child("password").value?.toString() ?: "",
                            it.child("firstName").value?.toString() ?: "",
                            it.child("lastName").value?.toString() ?: "",
                            it.child("contact").value?.toString() ?: "",
                            it.child("gender").value?.toString() ?: "",
                            roll,
                            sem
                        )

                        if (student != null) {
                            student.email = updatedValue
                            dr.child(newEmailKey).setValue(student).addOnSuccessListener {
                                dr.child(safeOldEmailKey).removeValue()
                                emailtxt.text = updatedValue
                                emailHeading.text = updatedValue
                                sp.putString("email", updatedValue)
                                sp.apply()
                                Toast.makeText(
                                    this,
                                    "Email updated successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Original data not found!", Toast.LENGTH_SHORT).show()
                    }
                }
                updates.clear()
            } else if (field == "Name") {
                val fullName = updatedValue
                val parts = fullName.split(" ")

                val firstName = parts[0]
                val lastName = parts[1]

                updates["firstName"] = firstName;
                updates["lastName"] = lastName;

                val safeKey = getSafeEmailKey(oldEmailKey)
                dr.child(safeKey).updateChildren(updates)
                    .addOnSuccessListener {
                        nametxt.text = updatedValue
                        nameHeading.text = updatedValue
                        sp.putString("name", updatedValue)
                        sp.apply()
                    }.addOnFailureListener {
                        Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
                    }
                dialog.dismiss()
                updates.clear()
            } else {
                updates[field.lowercase()] = updatedValue
                val safeKey = getSafeEmailKey(oldEmailKey)
                dr.child(safeKey).updateChildren(updates)
                    .addOnSuccessListener {
                        when (field) {
                            "Contact" -> {
                                contacttxt.text = updatedValue
                                sp.putString("contact", updatedValue)
                            }

                            "Gender" -> {
                                gendertxt.text = updatedValue
                                sp.putString("gender", updatedValue)
                            }

                        }
                        sp.apply()
                        Toast.makeText(this, "$field updated successfully!", Toast.LENGTH_SHORT)
                            .show()
                        dialog.dismiss()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Update failed: ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                updates.clear()
            }
        }

        val passBtn: Button = findViewById(R.id.passBtn);
        passBtn.setOnClickListener{
            val intent = Intent(this, changePass::class.java)
            intent.putExtra("r","Student")
            intent.putExtra("path",path)
            startActivity(intent)
        }
    }

    private fun loadStudentPage() {

        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val name = sharedPreferences.getString("name", "") ?: ""
        val sem = sharedPreferences.getInt("sem", 0)

        val encodedName = Uri.encode(name)
//        val url = "http://kevil.infinityfreeapp.com/student5.php?student_name=$encodedName&semester=$sem"  // Remember
//        val url = "http://kevil.infinityfreeapp.com/student6.php?student_name=$encodedName&semester=$sem"
//        val url = "http://kevil.infinityfreeapp.com/student7.php?student_name=$encodedName&semester=$sem"     //  r
//        val url = "http://kevil.infinityfreeapp.com/student8.php?student_name=$encodedName&semester=$sem" //  teacher name
        val url = "http://kevil.infinityfreeapp.com/student9.php?student_name=$encodedName&semester=$sem"

        webView2.loadUrl(url)
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade.php?student=$name")    //  Remember
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade2.php?student=$name")
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade3.php?student=$name")     //     Remember
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade5.php?student=$name&semester=$sem")
//        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade6.php?student=$name&semester=$sem")
        webView1.loadUrl("http://kevil.infinityfreeapp.com/student_grade7.php?student=$name&semester=$sem")
    }

    private fun getSafeEmailKey(email: String): String {
        return email.replace("@", "_").replace(".", "-")
    }

    // Set up WebView for both upload and PDF handling
    private fun setupWebView(webView: WebView) {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                if (url.endsWith(".pdf")) {
                    openPdf(url)  // Open PDF externally
                    return true
                }
                return false
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                this@student_main.filePathCallback = filePathCallback
                openFilePicker()
                return true
            }
        }
    }

    // Open PDF in Google Docs Viewer or external viewer
    private fun openPdf(pdfUrl: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/gview?embedded=true&url=$pdfUrl"))
            startActivity(intent)
        } catch (e: Exception) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No PDF viewer available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Open the file picker for PDF uploads
    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, FILE_REQUEST_CODE)
    }


    // Handle the PDF selection
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            val uri = data?.data
            filePathCallback?.onReceiveValue(arrayOf(uri!!))
        } else {
            filePathCallback?.onReceiveValue(null)
        }
        filePathCallback = null
    }

    // Request permission for external storage
    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        }
    }

    // Handle WebView back navigation
    override fun onBackPressed() {
        when {
            findViewById<LinearLayout>(R.id.section1).visibility == View.VISIBLE && webView1.canGoBack() -> webView1.goBack()
            findViewById<LinearLayout>(R.id.section2).visibility == View.VISIBLE && webView2.canGoBack() -> webView2.goBack()
            else -> super.onBackPressed()
        }
    }
}