package com.food.oder.ui.component.fragment

import android.view.View
import android.widget.Toast
import com.food.oder.R
import com.food.oder.data.model.CartOrder
import com.food.oder.databinding.FragmentOrderBinding
import com.food.oder.ui.adapter.HistoryAdapter
import com.food.oder.ui.bases.BaseFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentOrderBinding>() {
    private var historyAdapter: HistoryAdapter? = null
    private val database = Firebase.database
    override fun getLayoutFragment() = R.layout.fragment_order

    override fun initViews() {
        super.initViews()

        mBinding.toolbar.imvBack.visibility = View.GONE
        mBinding.toolbar.tvTitle.text = getString(R.string.order)

        historyAdapter = HistoryAdapter()

        mBinding.rcvOrder.adapter = historyAdapter

        database.getReference("cart").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    val cartOrderList = ArrayList<CartOrder>()

                    for (cartSnapshot in snapshot.children) {
                        val cartOrder = cartSnapshot.getValue(CartOrder::class.java)
                        cartOrder?.let {
                            it.id = cartSnapshot.key.toString()
                            cartOrderList.add(it)
                        }
                    }

                    historyAdapter?.submitData(cartOrderList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_not_connect_to_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
}
