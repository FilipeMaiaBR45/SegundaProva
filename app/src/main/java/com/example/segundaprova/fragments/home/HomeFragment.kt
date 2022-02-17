package com.example.segundaprova.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.segundaprova.R
import com.example.segundaprova.databinding.FragmentHomeBinding
import com.example.segundaprova.fragments.detalhe.DetalhesViewModel
import com.example.segundaprova.model.Estado
import com.example.segundaprova.repository.EstadoRemoteRepository
import com.example.segundaprova.viewModel.EstadoRemoteViewModel
import com.example.segundaprova.viewModel.EstadoViewModel
import com.example.segundaprova.viewModel.MainViewModelFactory


class HomeFragment : Fragment() {

    //lateinit var floatButton : View
    lateinit var binding: FragmentHomeBinding
    private lateinit var estadoViewModel: EstadoViewModel
    private lateinit var viewModelRemote : EstadoRemoteViewModel
    private var estadoList = emptyList<Estado>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val listAdapter = ListAdapter()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cadastraFragment)
            //Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_cadastraFragment)


        }

        var adapter = ListAdapter()
        //var holder = ListViewHolder(view!!)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())


        //EstadoRemoteViewModel
        val repository = EstadoRemoteRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModelRemote = ViewModelProvider(this, viewModelFactory).get(EstadoRemoteViewModel::class.java)
        viewModelRemote.getEstado()

        viewModelRemote.myResponse.observe(this, Observer { response ->
            for (i in 0 until response.size ) {
                Log.i("RESPONSE", "${response[i].id}")
                Log.i("RESPONSE", "${response[i].unidadeFederativa}")
                Log.i("RESPONSE", "${response[i].abreviacao}")
                Log.i("RESPONSE", "${response[i].capital}")
                Log.i("RESPONSE", "${response[i].area}")
                Log.i("RESPONSE", "${response[i].populacao}")
                Log.i("RESPONSE", "${response[i].pib}")
            }

        })


        //EstadoViewModel
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        estadoViewModel.readAllData.observe(viewLifecycleOwner, Observer { estado ->
            adapter.setData(estado)
        })




        binding.recyclerview.addOnItemTouchListener(
            NovoRecycleViewClickListener(
                requireContext(),
                binding.recyclerview,
                object : NovoRecycleViewClickListener.OnItemClickListener {


                    override fun onItemClick(view: View, position: Int) {
                        Navigation.findNavController(binding.recyclerview).navigate(HomeFragmentDirections.actionHomeFragmentToDetalhesFragment(adapter.getItemId(position)))


                        Toast.makeText(
                            requireContext(),
                            "clique curto item:${position}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                    override fun onItemLongClick(view: View, position: Int) {
         Navigation.findNavController(binding.recyclerview).navigate(HomeFragmentDirections.actionHomeFragmentToAlteraFragment(adapter.getItemId(position)))
                    }
                })
        )






        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.ajuda_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.opcao_menu_ajuda){
            Toast.makeText(context, "Ajuda da tela de Home", Toast.LENGTH_SHORT)
        }

        return super.onOptionsItemSelected(item)
    }
}

