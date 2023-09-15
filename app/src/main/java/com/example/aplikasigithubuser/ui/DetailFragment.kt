package com.example.aplikasigithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.databinding.FragmentHomeBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ARG_USERNAME = "username"

        fun newInstance(username: String): DetailFragment {
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tvName = binding.root.findViewById<TextView>(R.id.tv_name)
        val username = arguments?.getString(ARG_USERNAME)

        // Gunakan data sesuai kebutuhan, misalnya:
        if (username != null) {

            // Lakukan sesuatu dengan username
            Toast.makeText(requireContext(), "Username: $username", Toast.LENGTH_SHORT).show()

            // Mendapatkan referensi ke TextView


            // Mengatur teks TextView dengan nilai username
            tvName.text = username
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}