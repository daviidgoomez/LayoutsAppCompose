package com.pmdm.layoutsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pmdm.layoutsapp.ui.theme.LayoutsAppTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", Modifier.height(100.dp))
                    MyBox()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column {

        Text(
            text = "Hello $name!",
            modifier = modifier.padding(15.dp)
        )

        Text(
            text = "Proyecto Iberdrola",
            fontWeight = FontWeight .Bold,
            fontSize =  10.sp,
            modifier = modifier
                .background(Color.Red)
                .width(140.dp)
                .padding(28.dp)

        )
    }
}




@Composable
fun GreetingPreview() {
    LayoutsAppTheme {
        Greeting("Android", Modifier.height(100.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun MyPreview() {
    LayoutsAppTheme {
  //  MyBox()
      //  MyComplexLayout()

        ConstraintExample()

    }
}

@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart) {
        Box(modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(Color.Cyan)
            .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center) {
            Text("Esto es un EJEMPLO")
        }
    }


}


@Composable
fun MyComplexLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Cyan))
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.Red))
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.Magenta),
                contentAlignment = Alignment.Center) {
                Text("Hola clase!")
            }

        }
        MySpacer(height = 400)
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color.Gray))
    }
}

@Composable
fun MySpacer(height:Int) {
    Spacer(modifier = Modifier.height(height.dp))
}


@Composable
fun ConstraintExample(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxBlue, boxYellow, boxMagenta) = createRefs()
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)

            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                top.linkTo(boxYellow.bottom)
                end.linkTo(boxYellow.start)
            })
    }
}