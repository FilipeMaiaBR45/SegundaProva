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
import com.example.segundaprova.model.Estado
import com.example.segundaprova.viewModel.EstadoViewModel


class HomeFragment : Fragment() {

    //lateinit var floatButton : View
    lateinit var binding: FragmentHomeBinding
    private lateinit var estadoViewModel: EstadoViewModel
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

        //EstadoViewModel
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)
        estadoViewModel.readAllData.observe(viewLifecycleOwner, Observer { estado ->
            adapter.setData(estado)
        })




        binding.recyclerview.addOnItemTouchListener(NovoRecycleViewClickListener(requireContext(), binding.recyclerview, object : NovoRecycleViewClickListener.OnItemClickListener{


            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "clique curto item:${position}", Toast.LENGTH_SHORT).show()

            }


            override fun onItemLongClick(view: View, position: Int) {
//                //estadoList[position] = estadoViewModel.listEstadoById(currentItem.id)
//
//               // val currentItem = estadoList[position]
//                estadoViewModel.currentItem.observe(viewLifecycleOwner, Observer {
//
//                  var estado = estadoViewModel.listEstadoById(position.toLong() + 1) as Estado
//                    val action = HomeFragmentDirections.actionHomeFragmentToAlteraFragment(estado)
//                    findNavController().navigate(action)
//                })



            //                //val currentItem = estadoList[position]
//                adapter.setEstadoListById(estadoViewModel.currentItem)
//                estadoViewModel.listEstadoById()?.observe(viewLifecycleOwner, Observer {
//                    Log.i("estado", "$it")
//                    val action = HomeFragmentDirections.actionHomeFragmentToAlteraFragment(it)
//                    findNavController().navigate(action)
//                })
//
//               // estadoViewModel.listEstadoById()
//
//
//
//
//                Toast.makeText(requireContext(), "clique longo item:${position}", Toast.LENGTH_SHORT).show()


            }
        }))






            setHasOptionsMenu(true)
            return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            Navigation.findNavController(requireView()))
                || super.onOptionsItemSelected(item)
    }
}

