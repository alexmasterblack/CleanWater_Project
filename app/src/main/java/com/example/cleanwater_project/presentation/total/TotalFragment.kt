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

    private val indexEPT = MutableLiveData<Double>()

    private var waterQuality = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

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
                    waterQuality
                )
            )
        }
    }

    private fun setIndexEPT(view: View, index: Double?) {
        val indexEPT = view.findViewById<TextView>(R.id.ept_index)

        if (index != null) {
            if (index > 60) {
                waterQuality = "Очень хорошее"
                indexEPT?.setTextColor(resources.getColor(R.color.very_good_quality))
            } else if (index > 55 && index <= 60) {
                waterQuality = "Хорошее"
                indexEPT?.setTextColor(resources.getColor(R.color.good_quality))
            } else if (index > 40 && index <= 55) {
                waterQuality = "Посредственное"
                indexEPT?.setTextColor(resources.getColor(R.color.not_bad_quality))
            } else if (index > 7 && index <= 40) {
                waterQuality = "Плохое"
                indexEPT?.setTextColor(resources.getColor(R.color.bad_quality))
            } else if (index <= 7) {
                waterQuality = "Очень плохое"
                indexEPT?.setTextColor(resources.getColor(R.color.very_bad_quality))
            }

            indexEPT?.text = waterQuality
        }
    }
}