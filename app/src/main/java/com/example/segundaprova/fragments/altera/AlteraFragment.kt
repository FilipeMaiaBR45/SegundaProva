package com.example.segundaprova.fragments.altera

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.segundaprova.R
import com.example.segundaprova.databinding.FragmentAlteraBinding
import com.example.segundaprova.databinding.FragmentCadastraBinding
import com.example.segundaprova.fragments.detalhe.DetalhesFragmentArgs
import com.example.segundaprova.fragments.detalhe.DetalhesViewModel
import com.example.segundaprova.model.Estado
import com.example.segundaprova.viewModel.EstadoViewModel

class AlteraFragment : Fragment() {

    lateinit var binding: FragmentAlteraBinding
    lateinit var viewModel: AlteraViewModel

    //private val args by navArgs<AlteraFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args : DetalhesFragmentArgs by navArgs()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_altera, container, false)
        val viewModelFactory = AlteraViewModel.AlteraFragmentViewModelFactory(requireActivity().application, args.id)
        viewModel = ViewModelProvider(this, viewModelFactory ).get(AlteraViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        binding.buttonAtuallizarEstado.setOnClickListener {
//
//            viewModel.alterarEstado()
//            Navigation.findNavController(it).navigate(AlteraFragmentDirections.actionAlteraFragmentToHomeFragment())
//        }


        viewModel.eventAlteraEstado.observe(viewLifecycleOwner) {
            if (it == true) {
                Navigation.findNavController(requireView())
                    .navigate(AlteraFragmentDirections.actionAlteraFragmentToHomeFragment())
                //esta funcao eh para estar aqui e n fora do if como estava anteriormente no video de refactory
                viewModel.onAlteraEstadoComplet()
            }

        }




        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.ajuda_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.opcao_menu_ajuda){
            Toast.makeText(context, "Ajuda da tela de Detalhes", Toast.LENGTH_SHORT)
        }

        return super.onOptionsItemSelected(item)
    }

}