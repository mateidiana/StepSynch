package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stepsynch.repository.AuthRepository

@Composable
fun SignUpScreen(navController: NavController, authRepository: AuthRepository) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val lightestGreen = Color(0xFFEAF4E0)
    val forestGreen = Color(0xFF709255)
    val deepGreen = Color(0xFF3E5622)
    val darkGreen = Color(0xFF172815)
    val paleWhiteGreen = Color(0xFFF6FAF2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightestGreen)
            .padding(24.dp)
    ) {
        // Back button
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
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
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

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Create Account",
                color = deepGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Start your journey to a healthier lifestyle",
                color = forestGreen.copy(alpha = 0.9f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Input fields
            InputField("Full Name", fullName, { fullName = it }, forestGreen, paleWhiteGreen)
            InputField("Email", email, { email = it }, forestGreen, paleWhiteGreen, KeyboardType.Email)
            InputField("Password", password, { password = it }, forestGreen, paleWhiteGreen, isPassword = true)
            InputField("Confirm Password", confirmPassword, { confirmPassword = it }, forestGreen, paleWhiteGreen, isPassword = true)

            Spacer(modifier = Modifier.height(28.dp))

            // Sign Up Button
            Button(
                onClick = {

                    if (password != confirmPassword) {
                        errorMessage = "Passwords do not match"
                        return@Button
                    }
                    if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
                        errorMessage = "All fields are required"
                        return@Button
                    }

                    isLoading = true
                    errorMessage = null

                    authRepository.signUp(email, password, fullName) { success, error ->
                        isLoading = false
                        if (success) {
                            navController.navigate("onboarding") {
                                popUpTo("signup") { inclusive = true }
                            }
                        } else {
                            errorMessage = error ?: "Unknown error"
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = deepGreen, contentColor = Color.White),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text("Create Account", fontWeight = FontWeight.Medium)
                }
            }

            errorMessage?.let {
                Text(it, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // OR Divider
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Divider(
                    color = forestGreen.copy(alpha = 0.4f),
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
                    color = forestGreen.copy(alpha = 0.4f),
                    thickness = 1.dp,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = { /* TODO: Sign up with Google */ },
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White, contentColor = forestGreen),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
            ) {
                Text("Sign up with Google", fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account? ",
                    color = darkGreen,
                    fontSize = 14.sp
                )
                Text(
                    text = "Sign in",
                    color = forestGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("signin") }
                )
            }
        }
    }
}

@Composable
private fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    forestGreen: Color,
    backgroundColor: Color,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = label,
            color = forestGreen,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Enter $label", color = forestGreen.copy(alpha = 0.5f)) },
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = backgroundColor,
                focusedContainerColor = backgroundColor,
                unfocusedIndicatorColor = forestGreen.copy(alpha = 0.6f),
                focusedIndicatorColor = forestGreen,
                cursorColor = forestGreen
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
    }
}

