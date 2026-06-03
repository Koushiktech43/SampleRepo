package com.android.sampletestapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.sampletestapplication.ui.theme.SampleTestApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onBasketClick: () -> Unit = {}) {
    var searchQuery by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onBasketClick) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Basket",
                        tint = Color(0xFFFFA451)
                    )
                }
                Text("My basket", fontSize = 10.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Welcome Text
        Text(
            text = "Hello Tony, What fruit salad combo do you want today?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp,
            color = Color(0xFF27214D)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Search and Filter
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                placeholder = { Text("Search for fruit salad combos", fontSize = 14.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF3F4F9),
                    unfocusedContainerColor = Color(0xFFF3F4F9),
                    disabledContainerColor = Color(0xFFF3F4F9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp)
            )
            // Filter icon placeholder
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFFF3F4F9), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Filter icon would go here
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Recommended Combo
        Text(
            text = "Recommended Combo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF27214D)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(end = 24.dp)
        ) {
            item {
                ComboCard(
                    name = "Honey lime combo",
                    price = "2,000",
                    color = Color(0xFFFFFFFF)
                )
            }
            item {
                ComboCard(
                    name = "Berry mango combo",
                    price = "8,000",
                    color = Color(0xFFFFFFFF)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Tabs
        var selectedTab by remember { mutableIntStateOf(0) }
        val tabs = listOf("Hottest", "Popular", "New combo", "Top")

        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            edgePadding = 0.dp,
            containerColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->
                if (selectedTab < tabPositions.size) {
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = Color(0xFFFFA451)
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) Color(0xFF27214D) else Color(0xFF938DB5)
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tab Content Items
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                FruitItemCard(
                    name = "Quinoa fruit salad",
                    price = "10,000",
                    color = Color(0xFFFFFAEB)
                )
            }
            item {
                FruitItemCard(
                    name = "Tropical fruit salad",
                    price = "10,000",
                    color = Color(0xFFFEF0F0)
                )
            }
            item {
                FruitItemCard(
                    name = "Melon fruit salad",
                    price = "10,000",
                    color = Color(0xFFF1EFF6)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ComboCard(name: String, price: String, color: Color) {
    Card(
        modifier = Modifier
            .size(152.dp, 183.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Image Placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(40.dp))
            )

            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF27214D)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₦ $price",
                    fontSize = 14.sp,
                    color = Color(0xFFFFA451),
                    fontWeight = FontWeight.Medium
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFFF2E6), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+", color = Color(0xFFFFA451), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FruitItemCard(name: String, price: String, color: Color) {
    Card(
        modifier = Modifier
            .size(140.dp, 150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Image Placeholder
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(32.dp))
            )

            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF27214D)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₦ $price",
                    fontSize = 12.sp,
                    color = Color(0xFFFFA451),
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFFFE2C8), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", color = Color(0xFFFFA451), fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SampleTestApplicationTheme {
        HomeScreen()
    }
}
