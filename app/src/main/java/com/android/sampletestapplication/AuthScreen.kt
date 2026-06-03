package com.android.sampletestapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.sampletestapplication.ui.theme.SampleTestApplicationTheme

@Composable
fun AuthScreen(onStartOrdering: (String) -> Unit = {}) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Orange Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(Color(0xFFFFA451)),
            contentAlignment = Alignment.Center
        ) {
            // Fruit Image Placeholder
            Box(
                modifier = Modifier
                    .size(260.dp)
                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(130.dp))
            )
        }

        // Bottom Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "What is your firstname?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF27214D)
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                placeholder = { Text("Tony", color = Color(0xFFC2BDBD)) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF3F1F1),
                    unfocusedContainerColor = Color(0xFFF3F1F1),
                    disabledContainerColor = Color(0xFFF3F1F1),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { if (name.isNotBlank()) onStartOrdering(name) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA451))
            ) {
                Text(
                    text = "Start Ordering",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    SampleTestApplicationTheme {
        AuthScreen()
    }
}
