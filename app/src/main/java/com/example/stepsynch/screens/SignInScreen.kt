package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignInScreen(navController: NavController) {

    val lightestGreen = Color(0xFFEAF4E0)
    val forestGreen = Color(0xFF709255)
    val deepGreen = Color(0xFF3E5622)
    val paleWhiteGreen = Color(0xFFF6FAF2)
    val darkGreen = Color(0xFF172815)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightestGreen)
            .padding(24.dp)
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = deepGreen
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp)) // Extra space at top

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(72.dp)
                    .background(deepGreen, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsWalk,
                    contentDescription = "Footprint icon",
                    tint = Color.White,
                    modifier = Modifier.size(38.dp)
                )
            }

            Text(
                text = "Welcome back",
                color = deepGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Continue your adventure",
                color = forestGreen.copy(alpha = 0.9f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = forestGreen.copy(alpha = 0.5f)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(paleWhiteGreen, shape = RoundedCornerShape(12.dp)),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = darkGreen),
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = forestGreen.copy(alpha = 0.5f)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(paleWhiteGreen, shape = RoundedCornerShape(12.dp)),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = darkGreen),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = forestGreen
                        )
                    )
                    Text(
                        text = "Remember me",
                        color = darkGreen,
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Forgot password?",
                    color = forestGreen,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* TODO */ }
                )
            }

            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = deepGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(28.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Sign in", fontWeight = FontWeight.Medium)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(
                    color = forestGreen.copy(alpha = 0.5f),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = " OR ",
                    color = forestGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Divider(
                    color = forestGreen.copy(alpha = 0.5f),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = deepGreen
                ),
                shape = RoundedCornerShape(28.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Sign in with Google", fontWeight = FontWeight.Medium)
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = darkGreen,
                    fontSize = 14.sp
                )
                Text(
                    text = "Sign up",
                    color = forestGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("signup") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
