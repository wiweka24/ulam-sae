package com.senpro.ulamsae.ui.camera

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.senpro.ulamsae.api.ApiConfig
import com.senpro.ulamsae.databinding.ActivityCameraBinding
import com.senpro.ulamsae.ui.ViewModelFactory
import com.senpro.ulamsae.ui.livecamera.LiveCameraActivity
import com.senpro.ulamsae.ui.login.LoginActivityViewModel
import com.senpro.ulamsae.ui.register.RegisterActivityViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.Serializable

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private var getFile: File? = null
    private lateinit var viewModel: CameraViewModel

    companion object {
        const val CAMERA_X_RESULT = 200
        const val GALLERY_RESULT = 201

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.senpro.ulamsae.databinding.ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        setupAction()
        setupViewModel()
    }

    private fun setupAction() {
        binding.cameraXButton.setOnClickListener {
            val intent = Intent(this, CameraXActivity::class.java)
            launcherIntentCameraX.launch(intent)
        }

        binding.uploadButton.setOnClickListener { uploadImage() }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(this))[CameraViewModel::class.java]
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile
            val result = rotateBitmap(
                BitmapFactory.decodeFile(getFile?.path),
                isBackCamera
            )

            binding.previewImageView.setImageBitmap(result)

        } else if (it.resultCode == GALLERY_RESULT) {
            val selectedImg = it.data?.getSerializableExtra("gallery") as File
            Log.d("url image hasil", selectedImg.toString())
            getFile = selectedImg

            val result = rotateBitmap(
                BitmapFactory.decodeFile(getFile?.path),
                false
            )
            binding.previewImageView.setImageBitmap(result)
        }
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val nama = binding.descNama.text.toString()
            val harga = binding.descHarga.text.toString()

            viewModel.addFish(nama, harga, file)
            finish()
        } else {
            Toast.makeText(this@CameraActivity, "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

}