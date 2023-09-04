package mx.tec.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.tec.navigation.ui.theme.NavigationTheme

import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.AsyncImage

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintLayoutExample()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutExample() {
    // to do a constraint layout we use the constraint layout composable
    // container similar to column or row
    ConstraintLayout {

        // in order to arrange items (composables) using constraint layout
        // we are going to need references
        val (button, text, image) = createRefs()

        Button(
            onClick = {
                Log.wtf("BUTTON", "HI!")
            },
            // this is were we establish a relationship between a reference
            // and a composable item
            modifier = Modifier.constrainAs(button){
                // each constraint has its own object here
                // remember - our constrained objects should have at least
                // one constraint
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                // dp ?!?!?!
                // density-independent pixels
                top.linkTo(parent.top, margin = 30.dp)
            }
        ) {
            Text("HEY GUYS!")
        }
        Text(
            "SOME SAMPLE TEXT",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 15.dp)
                end.linkTo(button.start, margin = 10.dp)
            }
        )
        Text(
            "SOME MORE TEXT",
            modifier = Modifier.constrainAs(image){
                end.linkTo(parent.end, margin = 20.dp)
                bottom.linkTo(parent.bottom, margin = 20.dp)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationTheme {
        ConstraintLayoutExample()
    }
}