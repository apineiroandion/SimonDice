package com.example.simondice.ui.theme

import androidx.compose.ui.graphics.Color


enum class Colors (val id: Int, val nombre: String, val color: Color, val colorPressed: Color){
    RED(0, "Red", Color(0xFFE57373), Color(0xFFB71C1C)),
    BLUE(1, "Blue", Color(0xFF64B5F6), Color(0xFF0D47A1)),
    GREEN(2, "Green", Color(0xFF81C784), Color(0xFF1B5E20)),
    YELLOW(3, "Yellow", Color(0xFFFFF176), Color(0xFFF57F17));
}