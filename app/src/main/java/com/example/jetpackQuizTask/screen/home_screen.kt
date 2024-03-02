@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class
)

package com.example.jetpackQuizTask.screen
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpackQuizTask.R
import com.example.jetpackQuizTask.model.quizOfflineResponse.QuizeList
import com.example.jetpackQuizTask.model.quizOfflineResponse.quizeRespose
import com.example.jetpackQuizTask.view.ui.theme.*
import com.example.starbucks_ui.component.AppIconComponent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController
){

    val quiz_type by rememberSaveable{ mutableStateOf("") }

    val quiz_scroe by rememberSaveable{ mutableStateOf(200) }


    Scaffold(
        topBar = {
            TopAppBar(title = {

                Row(
                     modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "Question: 2/3", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
                    Text(text = "score: $quiz_scroe", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
                }
            }, backgroundColor = LightOrange)
        }
    ) {



        Box(
            contentAlignment = Alignment.Center
        ){

            Column(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 8.dp)
                ) {

                    Column{

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    LightRed_1, RoundedCornerShape(
                                        bottomStart = 14.dp, bottomEnd = 14.dp
                                    )
                                )
                                .fillMaxWidth()
                                .height(200.dp), contentAlignment = Alignment.Center
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.quizim),
                                contentDescription = "",
                                modifier = Modifier.size(200.dp)
                            )

                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$quiz_type", style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight.W400, color = Color.Red, textAlign = TextAlign.Center
                            ), modifier = Modifier.padding(8.dp))
                    }
                }

                LazyColumn{

                 items(QuizeList){

                  quizItemShow(quize_respose = it) {

                  }
                 }
                }
            }
        }

    }
}



@Composable
fun quizItemShow(quize_respose: quizeRespose, onClick: () -> Unit ){


    val context = LocalContext.current



  val chiplist by remember {
        mutableStateOf(listOfNotNull(quize_respose.answers1,quize_respose.answers2,quize_respose.answers3,quize_respose.answers4))
    }


    var chipStatus by remember {
        mutableStateOf("")
    }

    var selected_Answer by remember { mutableStateOf (false) }

    var SelectColor by remember{ mutableStateOf (LightOrange) }


    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(18.dp),
        backgroundColor =if(selected_Answer) SelectColor else SelectColor,
        onClick = {
         onClick()

          }
        ) {

             Column(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(12.dp)
                     .padding(4.dp),
             ) {

                 Row (
                     modifier = Modifier
                         .fillMaxWidth(),
                     horizontalArrangement = Arrangement.SpaceBetween,

                     )   {

                     Text(text = quize_respose.question, fontSize = 16.sp,
                         fontWeight = FontWeight.W400, modifier = Modifier.width(260.dp))

                     AppIconComponent(icon = R.drawable.baseline_arrow_drop_down_24,)
                 }

                         Column {

                            //sortedBy

                             chiplist.sortedDescending().forEach {

                                SuggestionRow(label =it , select = it ==chipStatus){chip->
                                     chipStatus = chip

                                    if (quize_respose.correctAnswer.equals("A") && quize_respose.answers1.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                        Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()

                                    } else if (quize_respose.correctAnswer.equals("B") && quize_respose.answers2.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                        Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()

                                    } else if (quize_respose.correctAnswer.equals("C") && quize_respose.answers3.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                        Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()

                                    }else if (quize_respose.correctAnswer.equals("D") && quize_respose.answers4.equals(it)) {
                                        selected_Answer = !selected_Answer
                                        SelectColor = Color.Green

                                        Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()
                                    } else {

                                        Toast.makeText(context, "Not correctAnswer", Toast.LENGTH_SHORT).show()
                                        selected_Answer = selected_Answer
                                        SelectColor = Color.Red
                                    }

                                }
                             }

                         }
             }
    }
}


@Composable
fun SuggestionRow(
    label:String,
    select:Boolean,
    onChipChange:(String)->Unit
)  {

    SuggestionChip(onClick = {
        if (!select)
          onChipChange(label)
        else
            onChipChange(label)

    }, label = {
        Text(text = label, style = TextStyle(fontSize = 14.sp, color = if(select) Color.White else Color.Black))
    },
        shape = RoundedCornerShape(8.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = if(select) Purple500 else Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderWidth = 2.dp,
            borderColor = if(select) Color.Transparent else Purple500
        ),
        modifier = Modifier.padding(4.dp)

    )
}




