package mx.tec.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
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
                    DecoupledConstraintExample()
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

// decoupled constraints
// in constraint layouts you can define the constraints by themselves
// and then use them on a specific layout

private fun myConstraints(leftMargin : Dp, topMargin : Dp) : ConstraintSet {

    return ConstraintSet {

        val button1 = createRefFor("button1")
        val button2 = createRefFor("button2")
        val button3 = createRefFor("button3")

        constrain(button1) {
            top.linkTo(parent.top, margin = topMargin)
            start.linkTo(parent.start, margin = leftMargin)
        }

        constrain(button2) {
            top.linkTo(button1.bottom, margin = topMargin)
            start.linkTo(button1.end, margin = leftMargin)
        }

        constrain(button3) {
            top.linkTo(button2.bottom, margin = topMargin)
            end.linkTo(button2.start, margin = leftMargin)
        }
    }
}

// in order to be able to actually use a constrain set we need
// a composable that uses it!
@Composable
fun DecoupledConstraintExample() {

    // layout that fits availabe space on parent
    BoxWithConstraints {

        // in order to use a constraint set we need to declare one
        //val constraints = myConstraints(20.dp, 20.dp)
        //val constraints = myConstraints(0.dp, 0.dp)
        val constraints = myConstraints(50.dp, 50.dp)

        ConstraintLayout(constraints) {

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button1")
            ) {
                Text("Button 1")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button2")
            ) {
                Text("Button 2")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button3")
            ) {
                Text("Button 3")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationTheme {
        ConstraintLayoutExample()
    }
}