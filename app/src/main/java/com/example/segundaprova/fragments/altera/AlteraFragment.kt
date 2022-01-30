package com.example.segundaprova.fragments.altera

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.segundaprova.R
import com.example.segundaprova.databinding.FragmentAlteraBinding
import com.example.segundaprova.databinding.FragmentCadastraBinding
import com.example.segundaprova.model.Estado
import com.example.segundaprova.viewModel.EstadoViewModel

class AlteraFragment : Fragment() {

    lateinit var binding: FragmentAlteraBinding
    private lateinit var estadoViewModel: EstadoViewModel

    private val args by navArgs<AlteraFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_altera, container, false)
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        Log.i("args", "${args.currentEstado}")


            Log.i("args", "${args.currentEstado}")
            binding.editTextUpdateUf.setText(args.currentEstado.unidadeFederativa)

            binding.editTextUpAbreviacao.setText(args.currentEstado.abreviacao)
            binding.editTextUpCapital.setText(args.currentEstado.capital)
            binding.editTextUpArea.setText(args.currentEstado.area.toString())
            binding.editTextUpPopulacao.setText(args.currentEstado.populacao.toString())
            binding.editTextUpPib.setText(args.currentEstado.pib.toString())




        binding.buttonAtuallizarEstado.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem(){
        val uf = binding.editTextUpdateUf.text.toString()
        val abreviacao = binding.editTextUpAbreviacao.text.toString()
        val capital = binding.editTextUpCapital.text.toString()
        val area = binding.editTextUpArea.text.toString()
        val populacao = binding.editTextUpPopulacao.text.toString()
        val pib = binding.editTextUpPib.text.toString()

        if (inputCheck(uf, abreviacao, capital, area, populacao, pib)) {
            val estado = Estado(args.currentEstado.id, uf, abreviacao, capital, area.toFloat(), populacao.toLong(), pib.toLong())


            estadoViewModel.updateEstado(estado)
            Toast.makeText(requireContext(), "Estado atualizado com sucesso!!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_alteraFragment_to_homeFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "erro ao cadastra o Estado, por favor verifique os campos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun inputCheck(
        uf: String,
        abreviacao: String,
        capital: String,
        area: String,
        populacao: String,
        pib: String
    ): Boolean {
        return !(uf.isEmpty() && abreviacao.isEmpty() && capital.isEmpty() && area.isEmpty() && populacao.isEmpty() && pib.isEmpty())
    }


}