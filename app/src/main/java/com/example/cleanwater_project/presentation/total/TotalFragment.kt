package com.example.cleanwater_project.presentation.total

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cleanwater_project.R
import com.example.data.index.entities.IndexValue
import com.example.data.repository.Repositories
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat

class TotalFragment : Fragment(R.layout.total_fragment) {

    private val args by navArgs<TotalFragmentArgs>()

    private val researchId by lazy { args.researchId }

//    private val researchId = MutableLiveData<Long>()

    private val indexEPT = MutableLiveData<Double>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

//        GlobalScope.launch {
//            Repositories.researchRepository.getLastResearchId().collect {
//                researchId.postValue(it)
//            }
//        }

//        researchId.observe(requireActivity()) {
//            getIndexEPT(it)
//        }

        getIndexEPT(researchId)

        indexEPT.observe(requireActivity()) {
            setIndexEPT(view, it)
            addIndexEPT(researchId, it)
        }

        view.findViewById<MaterialButton>(R.id.finish_button).setOnClickListener {
            findNavController().navigate(R.id.action_totalFragment_to_inputResearchFragment)
        }
    }


    private fun getIndexEPT(id: Long) {
        GlobalScope.launch {
            Repositories.probeRepository.getIndexEPT(id).collect {
                indexEPT.postValue(it)
            }
        }
    }

    private fun addIndexEPT(id: Long, index: Double) {
        GlobalScope.launch {
            Repositories.indexValueRepository.addIndexValue(
                IndexValue(
                    id,
                    1,
                    index,
                    ""
                )
            )
        }
    }

    private fun setIndexEPT(view: View, index: Double?) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        view.findViewById<TextView>(R.id.ept_index)?.text = df.format(index).toString()
    }
}