package com.food.oder.ui.component.fragment

import android.content.Intent
import android.net.Uri
import android.view.View
import com.food.oder.R
import com.food.oder.data.model.Contact
import com.food.oder.databinding.FragmentCartBinding
import com.food.oder.databinding.FragmentContactBinding
import com.food.oder.databinding.FragmentHomeBinding
import com.food.oder.ui.adapter.ContactAdapter
import com.food.oder.ui.bases.BaseFragment
import com.google.android.gms.common.util.ClientLibraryUtils.getPackageInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(){

    private var contactAdapter : ContactAdapter? = null

    override fun getLayoutFragment() = R.layout.fragment_contact

    override fun initViews() {
        super.initViews()

        mBinding.toolbar.imvBack.visibility = View.GONE
        mBinding.toolbar.tvTitle.text = getString(R.string.contact)

        contactAdapter = ContactAdapter()

        val listContact = arrayListOf<Contact>()

        listContact.clear()
        listContact.add(
            Contact(R.drawable.ic_facebook, getString(R.string.label_facebook))
        )
        listContact.add(
            Contact(R.drawable.ic_hotline, getString(R.string.label_call))
        )

        listContact.add(
            Contact(R.drawable.ic_gmail, getString(R.string.label_gmail))
        )

        listContact.add(
            Contact(R.drawable.ic_skype, getString(R.string.label_skype))
        )

        listContact.add(
            Contact(R.drawable.ic_youtube, getString(R.string.label_youtube))
        )

        listContact.add(
            Contact(R.drawable.ic_whatsapp, getString(R.string.label_whatsapp))
        )

        contactAdapter?.submitData(listContact)

        mBinding.rcvData.adapter = contactAdapter
    }

    override fun onClickViews() {
        super.onClickViews()

        contactAdapter?.fbOnclick = {
            getOpenFacebookIntent()?.let { startActivity(it) }
        }
    }

    private fun getOpenFacebookIntent(): Intent? {
        return try {
            getPackageInfo(requireContext(),"com.facebook.katana")
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=818293978"))
        } catch (e: Exception) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=818293978"))
        }
    }
}
