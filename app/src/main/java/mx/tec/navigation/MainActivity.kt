package mx.tec.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import mx.tec.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationExample()
                }
            }
        }
    }
}

@Composable
fun NavigationExample() {
    // this will be the main manager for our views
    // first we need to declare a controller
    // the controller is the object in charge of changing the views
    // and data exchange
    val navController = rememberNavController()

    // a host is a structure in which several interfaces live
    NavHost(
        navController = navController,
        startDestination = "mainMenu"
    ) {
        // within the navhost we are going to declare several composables to navigate
        // using the composable macro
        composable("mainMenu") {
            MainMenu(
                kittenInterfaceButtonLogic = {
                    navController.navigate("kittenInterface")
                },
                puppyInterfaceButtonLogic = {
                    navController.navigate("puppyInterface")
                }
            )
        }
        composable("kittenInterface") {
            KittenInterface(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable("puppyInterface") {
            PuppyInterface(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun MainMenu(
    kittenInterfaceButtonLogic : () -> Unit,
    puppyInterfaceButtonLogic : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(
            onClick = kittenInterfaceButtonLogic
        ) {
            Text("go to kitten interface")
        }
        Button(
            onClick = puppyInterfaceButtonLogic
        ) {
            Text("go to puppy interface")
        }
    }
}

@Composable
fun KittenInterface(
    goBack : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://www.warrenphotographic.co.uk/photography/sqrs/14819.jpg",
            contentDescription = "a kitten"
        )
        Button(
            onClick = goBack
        ) {
            Text("go back")
        }
    }
}

@Composable
fun PuppyInterface(
    goBack : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://www.warrenphotographic.co.uk/photography/sqrs/41644.jpg",
            contentDescription = "a puppy"
        )
        Button(
            onClick = goBack
        ) {
            Text("go back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationExamplePreview() {
    NavigationTheme {
        NavigationExample()
    }
}