import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.databinding.FragmentHomeBinding
import com.example.aplikasigithubuser.ui.MainViewModel
import com.example.aplikasigithubuser.ui.UserAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = UserAdapter()

        // Mengatur LinearLayoutManager
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        // Observe LiveData for isLoading
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                // Tampilkan ProgressBar jika isLoading adalah true
                binding.progressBar.visibility = View.VISIBLE
            } else {
                // Sembunyikan ProgressBar jika isLoading adalah false
                binding.progressBar.visibility = View.GONE
            }
        }

        // Observe LiveData here and update the adapter data
        viewModel.listReview.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
