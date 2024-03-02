@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.jetpackQuizTask.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackQuizTask.R
import com.example.jetpackQuizTask.model.quizApiResponse.Question
import com.example.jetpackQuizTask.utils.Resource
import com.example.jetpackQuizTask.view.ui.theme.LightOrange
import com.example.jetpackQuizTask.view.ui.theme.LightRed_1
import com.example.jetpackQuizTask.view.ui.theme.Purple500
import com.example.jetpackQuizTask.viewmodel.quizViewModel
import com.example.starbucks_ui.component.AppIconComponent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ApiScreen(
    navHostController: NavHostController
){

    var chipStatus by remember {
        mutableStateOf("")
    }

    val viewModel: quizViewModel = hiltViewModel()

    var quiz_type by rememberSaveable{ mutableStateOf("") }

    var quiz_scroe by rememberSaveable{ mutableStateOf(0) }

    var questionImageUrl by rememberSaveable{ mutableStateOf("") }

    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(title = {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "Question: 2/3", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
                    Text(text = "score: ${quiz_scroe}", fontSize = 18.sp, fontStyle = FontStyle.Italic, modifier = Modifier.padding(14.dp))
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
                                painter = rememberAsyncImagePainter(questionImageUrl),
                                contentDescription = "Image Not Found",
                                modifier = Modifier.size(250.dp)
                            )

                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$quiz_type", style = TextStyle(
                                fontSize = 20.sp, fontWeight = FontWeight.W400, color = Color.Red, textAlign = TextAlign.Center
                            ), modifier = Modifier.padding(8.dp))
                    }
                }



                when (val result = viewModel._responselist.collectAsState().value) {

                    is Resource.Loading -> {

                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    }

                    is Resource.Error -> {
                        Text(text = "${result}")
                    }

                    is Resource.Success -> {

                        result.data?.let {
                            LazyColumn {


                                itemsIndexed(items = it) { index, item ->
                                    quiz_ItemShow(question = item){

                                        quiz_type=item.question
                                        quiz_scroe=item.score

                                        if(!item.questionImageUrl.isNullOrEmpty()){
                                            questionImageUrl=item.questionImageUrl
                                        }else{
                                            Toast.makeText(context, "Image Not Found", Toast.LENGTH_SHORT).show()
                                        }

                                    }
                                }
                            }
                        }

                    }

                }
            }
        }


    }
}



@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("ComposableNaming")
@Composable
fun quiz_ItemShow(question: Question, onClick: () -> Unit ){


    val context = LocalContext.current

    var  chiplist by remember {
        mutableStateOf(listOfNotNull(question.answers.A,question.answers.B,question.answers.C,question.answers.D))
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

                Text(text = question.question, fontSize = 16.sp,
                    fontWeight = FontWeight.W400, modifier = Modifier.width(260.dp))

                AppIconComponent(icon = R.drawable.baseline_arrow_drop_down_24,)
            }

            Column {

                //sortedBy

                chiplist.sortedDescending().forEach {

                    SuggestionRow(label =it , select = it ==chipStatus){chip->
                        chipStatus = chip

                        if (question.correctAnswer.equals("A") && question.answers.A.equals(it)) {
                            selected_Answer = !selected_Answer
                            SelectColor = Color.Green

                            Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()

                        } else if (question.correctAnswer.equals("B") && question.answers.B.equals(it)) {
                            selected_Answer = !selected_Answer
                            SelectColor = Color.Green

                            Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()

                        } else if (question.correctAnswer.equals("C") && question.answers.C.equals(it)) {
                            selected_Answer = !selected_Answer
                            SelectColor = Color.Green

                            Toast.makeText(context, "correctAnswer", Toast.LENGTH_SHORT).show()

                        }else if (question.correctAnswer.equals("D") && question.answers.D.equals(it)) {
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
fun Suggestion_Row(
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



