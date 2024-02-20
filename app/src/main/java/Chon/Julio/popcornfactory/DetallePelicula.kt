package Chon.Julio.popcornfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Chon.Julio.popcornfactory.databinding.ActivityDetallePeliculaBinding
import Chon.Julio.popcornfactory.databinding.PeliculaBinding
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class DetallePelicula : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePeliculaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        var ns = 0
        var id = -1
        var title = ""
        if (bundle != null) {
            ns = bundle.getInt("numberSeats")
            title = bundle.getString("titulo")!!
            binding.ivPeliculaImagen.setImageResource(bundle.getInt("header"))
            binding.tvNombrePelicula.setText(title)
            binding.tvPeliculaDesc.setText(bundle.getString("sinopsis"))
            binding.seatLeft.setText("$ns seats avaible")
            id = bundle.getInt("pos")
        }
        if (ns == 0) {
            binding.buyTickets.isEnabled = false
        } else {
            binding.buyTickets.isEnabled = true
            binding.buyTickets.setOnClickListener {
                val intent = Intent(this, SeatSelection::class.java).apply {
                    putExtra("id", id)
                    putExtra("name", title)
                }
                this!!.startActivity(intent)
            }
        }
        binding.back.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply {}
            this!!.startActivity(intent)
        }
    }
}
