package com.example.segundaprova.fragments.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
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
import com.example.segundaprova.utils.NetworkChecker
import com.example.segundaprova.viewModel.EstadoRemoteViewModel
import com.example.segundaprova.viewModel.EstadoViewModel
import com.example.segundaprova.viewModel.MainViewModelFactory


class HomeFragment : Fragment() {

    //lateinit var floatButton : View
    lateinit var binding: FragmentHomeBinding
    private lateinit var estadoViewModel: EstadoViewModel
    private lateinit var viewModelRemote: EstadoRemoteViewModel
    private var estadoList = emptyList<Estado>()

    private val networkChecker by lazy {
        NetworkChecker(getSystemService(requireContext(), ConnectivityManager::class.java))
    }


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
        viewModelRemote =
            ViewModelProvider(this, viewModelFactory).get(EstadoRemoteViewModel::class.java)


        //EstadoViewModel
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)



        networkChecker.performActionIfConnectc {

            binding.SwipeRefreshLayout.isRefreshing = false

            viewModelRemote.getEstado()

            viewModelRemote.myResponse.observe(this, Observer { response ->

                estadoViewModel.addEstadoRemote(response)

                viewModelRemote.getEstado()

                adapter.setData(response)

            })

        }

//        binding.SwipeRefreshLayout.setOnRefreshListener {
//
//            binding.SwipeRefreshLayout.isRefreshing = true
//            networkChecker.performActionIfConnectc {
//                viewModelRemote.getEstado()
//
//                viewModelRemote.myResponse.observe(this, Observer { response ->
//
//                    adapter.setData(response)
//
//                })
//
//            }
//            binding.SwipeRefreshLayout.isRefreshing = false
//        }




        networkChecker.performActionIfNotConnectc {

            estadoViewModel.readAllData.observe(viewLifecycleOwner, Observer { estado ->
                adapter.setData(estado)
            })
        }


//        binding.SwipeRefreshLayout.setOnRefreshListener {
//            binding.SwipeRefreshLayout.isRefreshing = false
//
//            networkChecker.performActionIfNotConnectc {
//
//                binding.SwipeRefreshLayout.isRefreshing = true
//
//                networkChecker.performActionIfNotConnectc {
//
//                    estadoViewModel.readAllData.observe(viewLifecycleOwner, Observer { estado ->
//                        adapter.setData(estado)
//                    })
//
//                }
//
//                binding.SwipeRefreshLayout.isRefreshing = false
//            }
//
//
//        }




        binding.recyclerview.addOnItemTouchListener(
            NovoRecycleViewClickListener(
                requireContext(),
                binding.recyclerview,
                object : NovoRecycleViewClickListener.OnItemClickListener {


                    override fun onItemClick(view: View, position: Int) {
                        Navigation.findNavController(binding.recyclerview).navigate(
                            HomeFragmentDirections.actionHomeFragmentToDetalhesFragment(
                                adapter.getItemId(
                                    position
                                )
                            )
                        )


                        Toast.makeText(
                            requireContext(),
                            "clique curto item:${position}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                    override fun onItemLongClick(view: View, position: Int) {
                        Navigation.findNavController(binding.recyclerview).navigate(
                            HomeFragmentDirections.actionHomeFragmentToAlteraFragment(
                                adapter.getItemId(
                                    position
                                )
                            )
                        )
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
        if (item.itemId == R.id.opcao_menu_ajuda) {
            Toast.makeText(context, "Ajuda da tela de Home", Toast.LENGTH_SHORT)
        }

        return super.onOptionsItemSelected(item)
    }
}

