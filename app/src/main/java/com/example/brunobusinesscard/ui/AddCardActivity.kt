package com.example.brunobusinesscard.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.brunobusinesscard.App
import com.example.brunobusinesscard.data.Card
import com.example.brunobusinesscard.databinding.ActivityAddCardBinding
import com.example.brunobusinesscard.ui.viewmodel.MainViewModel
import com.example.brunobusinesscard.ui.viewmodel.MainViewModelFactory

class AddCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCardBinding
    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory((application as App).repository) }
    private var idCard: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recoverData()
        setupBTclose()
        setupBTsave()
    }

    private fun recoverData() {
        intent.getParcelableExtra<Card>(CARD_ID)?.let {
            idCard = it.id
            binding.tilName.editText?.text ?: it.nome
            binding.tilEmail.editText?.text ?: it.email
            binding.tilPhone.editText?.text ?: it.telefone
            binding.tilCompany.editText?.text ?: it.empresa
        }
    }

    private fun setupBTclose() {
        binding.btClose.setOnClickListener {
            finish()
        }
    }

    private fun setupBTsave(){
        binding.btSave.setOnClickListener {
            val card = newCard()
            if (idCard > 0) {
                mainViewModel.update(card)
                Toast.makeText(this, "Cartão atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                mainViewModel.insert(card)
                Toast.makeText(this, "Cartão criado com sucesso!", Toast.LENGTH_SHORT).show()
            }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun newCard(): Card {
        return Card(
            id = idCard,
            nome = binding.tilName.editText?.text.toString(),
            telefone = binding.tilPhone.editText?.text.toString(),
            email = binding.tilEmail.editText?.text.toString(),
            empresa = binding.tilCompany.editText?.text.toString(),

        )
    }

    companion object {
        const val CARD_ID = "card_id"
    }
}