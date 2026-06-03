package com.android.sampletestapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.sampletestapplication.ui.theme.SampleTestApplicationTheme

@Composable
fun SuccessScreen(
    onTrackOrderClick: () -> Unit = {},
    onContinueShoppingClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Success Icon
        Box(
            modifier = Modifier
                .size(164.dp)
                .background(Color(0xFFE0FFE5), RoundedCornerShape(82.dp)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFF4CD964), RoundedCornerShape(50.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Congratulations!!!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF27214D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your order have been taken and is being attended to",
            fontSize = 16.sp,
            color = Color(0xFF27214D),
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(56.dp))

        Button(
            onClick = onTrackOrderClick,
            modifier = Modifier
                .width(133.dp)
                .height(56.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA451))
        ) {
            Text("Track order", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(
            onClick = onContinueShoppingClick,
            modifier = Modifier
                .width(181.dp)
                .height(56.dp),
            shape = RoundedCornerShape(10.dp),
            border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFFA451))),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFFA451))
        ) {
            Text("Continue shopping", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    SampleTestApplicationTheme {
        SuccessScreen()
    }
}
