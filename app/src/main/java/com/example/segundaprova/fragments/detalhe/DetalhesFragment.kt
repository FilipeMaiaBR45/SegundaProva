package com.example.segundaprova.fragments.detalhe

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.segundaprova.R
import com.example.segundaprova.databinding.FragmentDetalhes2Binding


class DetalhesFragment : Fragment() {

    lateinit var binding : FragmentDetalhes2Binding
    lateinit var viewModel: DetalhesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args : DetalhesFragmentArgs by navArgs()
        val id = args.id

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detalhes2, container, false)
        val viewModelFactory = DetalhesViewModel.DetalhesFragmentViewModelFactory(requireActivity().application, args.id)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetalhesViewModel::class.java)

        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

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