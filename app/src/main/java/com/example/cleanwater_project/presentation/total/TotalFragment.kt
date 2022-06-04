package com.example.cleanwater_project.presentation.total

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cleanwater_project.R
import com.example.data.index.entities.IndexValue
import com.example.data.repository.Repositories
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*

class TotalFragment : Fragment(R.layout.total_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val args by navArgs<TotalFragmentArgs>()

    private val researchId by lazy { args.researchId }

    private val indexEPT = MutableLiveData<Double?>()

    private var waterQuality = ""

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        getIndexEPT(researchId)

        indexEPT.observe(requireActivity()) {
            setIndexEPT(view, it)
        }

        view.findViewById<MaterialButton>(R.id.finish_button).setOnClickListener {
            indexEPT.observe(requireActivity()) {
                addIndexEPT(it)
            }
            setAlert()
        }
    }

    private fun setAlert() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Готово")
        builder.setMessage("Вы уверены, что хотите завершить исследование?")
        builder.setPositiveButton("Да") { _, _ ->
            findNavController().navigate(R.id.action_totalFragment_to_inputResearchFragment)
        }
        builder.setNegativeButton("Нет") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }


    private fun getIndexEPT(id: Long) {
        coroutineScope.launch {
            Repositories.probeRepository.getIndexEPT(id).collect {
                indexEPT.postValue(it)
            }
        }
    }

    private fun addIndexEPT(index: Double?) {
        lifecycleScope.launch {
            Repositories.indexValueRepository.addIndexValue(
                IndexValue(
                    researchId,
                    1,
                    index!!,
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
                indexEPT?.setTextColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.very_good_quality
                    )
                )
            } else if (index > 55 && index <= 60) {
                waterQuality = "Хорошее"
                indexEPT?.setTextColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.good_quality
                    )
                )
            } else if (index > 40 && index <= 55) {
                waterQuality = "Посредственное"
                indexEPT?.setTextColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.not_bad_quality
                    )
                )
            } else if (index > 7 && index <= 40) {
                waterQuality = "Плохое"
                indexEPT?.setTextColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.bad_quality
                    )
                )
            } else if (index <= 7) {
                waterQuality = "Очень плохое"
                indexEPT?.setTextColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.very_bad_quality
                    )
                )
            }

            indexEPT?.text = waterQuality
        }
    }
}