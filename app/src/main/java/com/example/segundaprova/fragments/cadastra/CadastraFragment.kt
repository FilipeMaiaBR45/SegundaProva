package com.example.segundaprova.fragments.cadastra

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.segundaprova.R
import com.example.segundaprova.databinding.FragmentCadastraBinding
import com.example.segundaprova.model.Estado
import com.example.segundaprova.viewModel.EstadoViewModel


class CadastraFragment : Fragment() {

    lateinit var binding: FragmentCadastraBinding
   private lateinit var estadoViewModel: EstadoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cadastra, container, false)
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        binding.buttonCadastrarEstado.setOnClickListener {
            isertDataToDatabase()
        }

        return binding.root
    }

    private fun isertDataToDatabase() {
        val uf = binding.editTextUniaoFederal.text.toString()
        val abreviacao = binding.editTextAbreviacao.text.toString()
        val capital = binding.editTextCapital.text.toString()
        val area = binding.editTextArea.text.toString()
        val populacao = binding.editTextPopulacao.text.toString()
        val pib = binding.editTextPib.text.toString()



        if (inputCheck(uf, abreviacao, capital, area, populacao, pib)) {
            val estado = Estado(0,uf, abreviacao, capital, area.toFloat(), populacao.toLong(), pib.toLong())


            estadoViewModel.addEstado(estado)
            Toast.makeText(requireContext(), "Estado adicionado com sucesso!!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_cadastraFragment_to_homeFragment)
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