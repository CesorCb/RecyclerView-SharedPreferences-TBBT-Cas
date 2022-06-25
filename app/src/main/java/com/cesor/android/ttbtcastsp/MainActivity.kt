package com.cesor.android.ttbtcastsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesor.android.ttbtcastsp.databinding.ActivityMainBinding
import com.cesor.android.ttbtcastsp.databinding.DialogInterfaceBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actorAdapter: ActorAdapter
    private lateinit var dialogBinding: DialogInterfaceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSharedPreferences()
    }

    private fun setupRecyclerView() {
        actorAdapter = ActorAdapter(getFullUsers(), this)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = actorAdapter
        }
    }

    private fun setupSharedPreferences() {
        val preferences = getPreferences(MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)

        if (isFirstTime){
            dialogBinding = DialogInterfaceBinding.inflate(layoutInflater)
            val view = dialogBinding.root
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.alert_dialog_title)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_btn_user_register) { _, _ ->
                    val username = dialogBinding.etDialogUsername.text.toString()
                    with(preferences.edit()){
                        putBoolean(getString(R.string.sp_first_time),false)
                        putString(getString(R.string.sp_username), username)
                        apply()
                    }
                    Toast.makeText(this, getString(R.string.Register_success), Toast.LENGTH_LONG)
                        .show()
                }
                .show()
        } else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_LONG).show()
        }
    }

    private fun getFullUsers(): MutableList<Actor> {
        val actorsData = mutableListOf<Actor>()

        val jim = Actor(1, "Jim", "Parsons", "Sheldon Cooper", "https://imgs.search.brave.com/1sJHNyDFsctVE9aI6DNe8c0we8HM79e0fb9HZ-6HRYc/rs:fit:316:225:1/g:ce/aHR0cHM6Ly90c2Uy/Lm1tLmJpbmcubmV0/L3RoP2lkPU9JUC5B/cE1SSUJYaVNRdnpr/SXZtREFRWVl3SGFM/SCZwaWQ9QXBp")
        val johnny = Actor(3, "Johnny", "Galecki", "Leonard Hofstadter", "https://imgs.search.brave.com/MHemZRojtDwqSIbFDDv7hrxTEGCIXSjXPxdfA3Yy3RM/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly9jZG4u/YW1vbWFtYS5jb20v/NzA5NDM2YTBlZDIy/YzEzMzgwMDQxMjE4/NDE2YjQ0YmUuanBl/Zz93aWR0aD0zMDAw/JmhlaWdodD0yMzc4")
        val kaley= Actor(2, "Kaley", "Cuoco","Penny", "https://imgs.search.brave.com/rsfBHBVv2wKgKS4C9OiPLGrh2dK0YYnqIKCFFH3ikM4/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly93d3cu/bWlzcy5hdC93cC1j/b250ZW50L3VwbG9h/ZHMvMjAyMS8wMi9H/b2xkZW4tR2xvYmVz/LUthbGV5LUN1b2Nv/LWJla29tbXQtaWhy/ZS1lcnN0ZS1Ob21p/bmllcnVuZy11bmQt/YnJpY2h0LWluLVRy/YWVuZW4tYXVzLS1z/Y2FsZWQuanBn")
        val simon = Actor(4, "Simon", "Helberg", "Howard Wolowitz",  "https://imgs.search.brave.com/5Q7z99cRCsxX6nA5Pl9Hp03n9UbpIT7g2FjqU6oRBEg/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly93d3cu/Z29sZGVuZ2xvYmVz/LmNvbS9zaXRlcy9k/ZWZhdWx0L2ZpbGVz/L2FydGljbGVzL2Nv/dmVyX2ltYWdlcy9n/ZXR0eWltYWdlcy01/ODc4NDc2NzIuanBn")
        val kunal = Actor(5, "Kunal","Nayyar", "Raj Koothrappali", "https://imgs.search.brave.com/u_xGclN1NES1L37MByYrcBHLgSbtbkABCfiHd7mlEUI/rs:fit:780:1024:1/g:ce/aHR0cDovL3d3dzEu/cGljdHVyZXMuemlt/YmlvLmNvbS9naS83/MHRoK0FubnVhbCtH/b2xkZW4rR2xvYmUr/QXdhcmRzK0Fycml2/YWxzK2dFRVJMWVRl/RG5SeC5qcGc")
        val melissa = Actor(6, "Melissa", "Rauch", "Bernadette Rostenkowski", "https://imgs.search.brave.com/yPSKm3MgfeRwVMZzBQO9D0hJQ4Z4g9YxDHcerHtH2G8/rs:fit:736:837:1/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vNzM2/eC9hOC9kNi83Mi9h/OGQ2NzIyMzU3ZDkx/YmZjMTk3NzAyMGEw/MmMyZDRmNy0tbWVs/aXNzYS1yYXVjaC1m/YW1vdXMtd29tZW4u/anBn")
        val mayim = Actor(7, "Mayim", "Bialik", "Amy Farrah Fowler", "https://imgs.search.brave.com/wvmhRCdB8lhqXE3kTYoI8vL1piKEQ6wzaEbA-Xx6Qwc/rs:fit:1200:1200:1/g:ce/aHR0cHM6Ly93d3cu/a3ZlbGxlci5jb20v/d3AtY29udGVudC91/cGxvYWRzLzIwMTUv/MDUvc2h1dHRlcnN0/b2NrXzgwMjI4NjQ3/LmpwZw")
        val kevin = Actor(8, "Kevin", "Sussman", "Stuart Bloom", "https://imgs.search.brave.com/UGkeg0ubIvlv_8fBcHP9TktDK4mLsx6qN24LgNRxLeU/rs:fit:623:849:1/g:ce/aHR0cDovLzEuYnAu/YmxvZ3Nwb3QuY29t/Ly1IdG1nZXRwMHpY/NC9VWXVnTDExYkVZ/SS9BQUFBQUFBQUNG/Zy9nbEVaMXVZbmRu/Zy9zMTYwMC9LZXZp/bitTdXNzbWFuK2lu/dGVydmlldytUVitT/VE9SRStPTkxJTkUu/cG5n")
        val christine = Actor(9, "Christine", "Baranski", "Dr. Beverly Hofstadter", "https://imgs.search.brave.com/I70ipoua_VLJBtzW6z1Snnz1XoFKVdC0mbkFClKP5Qs/rs:fit:736:1103:1/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vNzM2/eC84OC8wMC9lZS84/ODAwZWU5ZjNlZTk1/NWMxY2VmZWNiM2U5/OGJmNGM3My0tZ29v/ZC13aWZlLW5vdmEu/anBn")

        actorsData.apply {
            add(jim)
            add(johnny)
            add(kaley)
            add(simon)
            add(kunal)
            add(melissa)
            add(mayim)
            add(kevin)
            add(christine)
            add(jim)
            add(johnny)
            add(kaley)
            add(simon)
            add(kunal)
            add(melissa)
            add(mayim)
            add(kevin)
            add(christine)
        }

        return actorsData
    }

    override fun onClick(actor: Actor, position: Int) {
        Toast.makeText(this, "$position: ${actor.getFullName()} como ${actor.role}", Toast.LENGTH_SHORT).show()
    }
}