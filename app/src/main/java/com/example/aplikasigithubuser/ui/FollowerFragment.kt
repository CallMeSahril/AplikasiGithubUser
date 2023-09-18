import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.data.response.ItemFollowers
import com.example.aplikasigithubuser.data.response.Itemsitem
import com.example.aplikasigithubuser.databinding.FragmentFollowerBinding
import com.example.aplikasigithubuser.ui.adapter.FollowersAdapter
import com.example.aplikasigithubuser.ui.adapter.UserAdapter
import com.example.aplikasigithubuser.ui.fragment.DetailFragment
import com.example.aplikasigithubuser.ui.viewmodel.MainViewModel

class FollowerFragment : Fragment(), FollowersAdapter.OnItemClickListener {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FollowerFragment", "onCreateView called")

        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDataFollowers("CallMeSahril")

        // Tambahkan kode khusus yang ingin Anda jalankan saat onViewCreated di sini
        // Contoh: menampilkan pesan Toast saat fragment dibuat
        Toast.makeText(requireContext(), "Fragment Follower Created", Toast.LENGTH_SHORT).show()

//        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_follower)
//        val adapter = FollowersAdapter(this)

        // Mengatur LinearLayoutManager
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollower.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)


        viewModel.listReviewFolower.observe(viewLifecycleOwner) { consumerReviews ->
            Log.i("FollowerFragment", "Data changed: ${consumerReviews.size} items")
            setReviewData(consumerReviews)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }



    }
    private fun setReviewData(consumerReviews: List<ItemFollowers>) {
        val adapter = FollowersAdapter(this)
        adapter.submitList(consumerReviews)
        binding.rvFollower.adapter = adapter

    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: ItemFollowers) {
        val username = item.login

        // Membuat instance fragment detail dan mengirim data
        val detailFragment = DetailFragment.newInstance("$username")

        // Mengganti fragment saat item diklik
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
