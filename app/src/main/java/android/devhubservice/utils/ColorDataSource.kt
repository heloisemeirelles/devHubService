package android.devhubservice.utils

import android.raywenderlich.devhubservice.R
import android.raywenderlich.devhubservice.databinding.ListItemBinding
import androidx.core.content.ContextCompat

class ColorDataSource(private val color: String, private val binding: ListItemBinding) {

    fun setColor() {
        when (color) {
            "Orange" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.orange
                    )
                )
            }
            "Green" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.green
                    )
                )
            }

            "Grey" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.grey
                    )
                )
            }

            "Blue" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.blue
                    )
                )
            }

            "Black" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
            }

            "Yellow" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.yellow
                    )
                )
            }

            "Purple" -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.purple_200
                    )
                )
            }
            else -> {
                binding.tvListName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.light_grey
                    )
                )
            }
        }
    }
}