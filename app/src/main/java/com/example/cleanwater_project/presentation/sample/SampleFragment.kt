package com.example.cleanwater_project.presentation.sample

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanwater_project.R
import com.example.cleanwater_project.presentation.sample.adapters.RecyclerViewAdapter
import com.example.data.hydrobionts.entities.Hydrobiont
import com.example.data.probe.entities.Probe
import com.example.data.repository.Repositories
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.*

class SampleFragment : Fragment(R.layout.sample_fragment) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val researchId = MutableLiveData<Long>()

    private val data = MutableLiveData<List<Probe>>()

    private val hydrobionts = MutableLiveData<List<Hydrobiont>>()

    private val adapter = RecyclerViewAdapter {
        val ids =
            SampleFragmentDirections.actionSampleFragmentToSampleDetailsFragment(
                it.researchId,
                it.hydrobiontId,
                it.amount
            )
        findNavController().navigate(ids)
    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            setUpAlert()
        }

        coroutineScope.launch {
            Repositories.researchRepository.getLastResearchId().collect {
                researchId.postValue(it)
            }
        }

        coroutineScope.launch {
            Repositories.hydrobiontRepository.getHydrobionts().collect {
                hydrobionts.postValue(it)
            }
        }

        researchId.observe(requireActivity()) {
            getProbeDataById(it)
        }

        setRecyclerViewAdapter(view)

        view.findViewById<MaterialButton>(R.id.finish_button).setOnClickListener {
            if (adapter.getAmount() != 0) {
                researchId.observe(requireActivity()) {
                    findNavController().navigate(
                        SampleFragmentDirections.actionSampleFragmentToCheckResearchFragment(
                            it
                        )
                    )
                }
            } else {
                setPushAlert()
            }
        }
    }

    private fun setUpAlert() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Подтверждение")
        builder.setMessage("Вы уверены, что хотите отменить исследование?")
        builder.setPositiveButton("Да") { _, _ ->
            coroutineScope.launch {
                Repositories.researchRepository.deleteLastResearch()
            }
            findNavController().navigateUp()
        }
        builder.setNegativeButton("Нет") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun setPushAlert() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Предупреждение")
        builder.setMessage("Вы не ввели ни одной пробы.")
        builder.setPositiveButton("Ок") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun setRecyclerViewAdapter(view: View) {
        val divider =
            MaterialDividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)

        view.findViewById<RecyclerView>(R.id.samples).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SampleFragment.adapter
            divider.dividerInsetStart = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                90f,
                resources.displayMetrics
            ).toInt()
            divider.dividerInsetEnd = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20f,
                resources.displayMetrics
            ).toInt()
            addItemDecoration(divider)
        }

        data.observe(requireActivity()) {
            adapter.setData(it)
        }
    }

    private fun getProbeDataById(id: Long) {
        lifecycleScope.launch {
            Repositories.probeRepository.getAllProbe(id).collect {
                data.postValue(it)
            }
        }
    }
}