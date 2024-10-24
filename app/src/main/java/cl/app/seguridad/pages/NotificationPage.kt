package cl.app.seguridad.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.app.seguridad.Message
import cl.app.seguridad.TopBox
import cl.app.seguridad.pages.chat.ChatBody
import cl.app.seguridad.pages.chat.Conversation
import cl.app.seguridad.pages.profile.PersonalData
import cl.app.seguridad.pages.profile.PictureProfile

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun NotificationPage(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 125.dp)
            .background(Color.White),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            TopBox(titulo = "Chat")
        }
        val listMensajes = listOf(
            Message("Colleague", "Hey, take a look at this!"),
            Message("Friend", "What are you up to?"),
            Message("Mom", "Don't forget to call me later."),
            Message("Boss", "We need to talk about the project."),
            Message("Sister", "Can you pick me up from school?"),
            Message("Doctor", "Your appointment is tomorrow at 10am, aslkd asdnliaksd alsikdjla asldkjas asld kjasl asl dk jals aslk djlas"),
            Message("Neighbor", "Did you see my cat?"),
            Message("Teacher", "Remember to study for the exam. aosidjoasi oiasjd jaso jiasj asdjoi adoji dsjoi ads josdjioa sdojios dij"),
            Message("Coach", "Great job at practice today!"),
            Message("Grandma", "I made your favorite cookies!")
        )


        item { Conversation(listMensajes) }

    }
}
