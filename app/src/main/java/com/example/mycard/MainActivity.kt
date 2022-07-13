package com.example.mycard

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycard.ui.theme.MyCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    CreateBizCard()
                }

            }
        }
    }
}

@Composable
fun CreateBizCard(){
    val buttonClickedState= remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = androidx.compose.ui.graphics.Color.White,
            elevation = 4.dp,
            ) {
            Column(modifier = Modifier.height(300.dp), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CreateRoundedImage()
                Divider(thickness = 2.dp)
                CreateCardDetails()
                Button(onClick = { buttonClickedState.value = !buttonClickedState.value}) {
                    Text(text = "PORTFOLIO",
                    style = MaterialTheme.typography.button)
                }
                if (buttonClickedState.value){
                    Content()
                }
                else{
                    Box(){

                    }
                }
            }

        }
    }
}

@Composable
private fun CreateCardDetails() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Sanjay",
            Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Android Developer",
            Modifier.padding(5.dp),
            style = MaterialTheme.typography.h6,
            color = Black
        )

    }
}

@Composable
private fun CreateRoundedImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, LightGray),
        elevation = 4.dp,
        color = White,
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Profile Picture",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(3.dp),
                shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                border = BorderStroke(2.dp, LightGray)) {

                    Portfolio(data = listOf("Project1","Project2","Project1","Project2","Project1","Project2"))
            
        }
        
    }
}
@Composable
fun Portfolio(data:List<String>) {
    LazyColumn(){
        items(data){item ->  
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
                shape = RectangleShape,
                
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(color = MaterialTheme.colors.surface)
                    .padding(16.dp)) {
                    CreateRoundedImage(modifier = Modifier.size(80.dp))
                    Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)) {
                        Text(text = item , fontWeight = FontWeight.Bold)
                        Text(text = "A great beginner project")
                    }
                    
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCardTheme {
        CreateBizCard()
    }
}