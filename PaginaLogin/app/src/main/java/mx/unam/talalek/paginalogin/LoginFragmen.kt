package mx.unam.talalek.paginalogin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import mx.unam.talalek.paginalogin.databinding.FragmentLoginBinding


class LoginFragmen : Fragment(R.layout.fragment_login) {

    private var fragmenLoginBinding: FragmentLoginBinding? = null

    private val usuariosRegistrados = listOf<String>("usuario1", "usuario2", "usuario3", "usuario4")
    private val claves = listOf<String>("primero", "segundo", "tercero", "cuarto")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        fragmenLoginBinding = binding

        var usuario: String
        var contrasena: String
        val boton = binding.botonIngreso
        var valida: Boolean = false

        boton.setOnClickListener {

           // binding.botonIngreso.isVisible = false
            //obtenemos los datos del layout
            usuario = binding.editUsuario.text.toString()
            contrasena = binding.contras.text.toString()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                val elemento: Int = usuariosRegistrados.indexOf(usuario)

                if (elemento >= 0) {
                    valida = validaUsuario(usuario, contrasena, elemento)
                    Log.d("valor", valida.toString())
                } else {
                    val toast = Toast.makeText(
                        requireActivity(),
                        "Usuario no encontrado", Toast.LENGTH_LONG
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }

                if (valida == true) {
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    val fragmentoCorrecto = CorrectoFragment()
                    transaction.replace(binding.fragmentLogin.id, fragmentoCorrecto)
                    transaction.addToBackStack(null)
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transaction.commit()
                } else {
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    val fragmentoIncorrecto = IncorrectoFragment()
                    transaction.replace(binding.fragmentLogin.id, fragmentoIncorrecto)
                    transaction.addToBackStack(null)
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    transaction.commit()
                }

            } else {
                val toast = Toast.makeText(
                    requireActivity(),
                    "Es necesario llenar todos los campos", Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            binding.editUsuario.text.clear()
            binding.contras.text.clear()
        }

    }

    override fun onDestroyView() {
        fragmenLoginBinding = null
        super.onDestroyView()
    }

    private fun validaUsuario(usuario: String, contrasena: String, elemento: Int): Boolean {

        return (usuario == usuariosRegistrados[elemento]) && (contrasena == claves[elemento])
    }

}
