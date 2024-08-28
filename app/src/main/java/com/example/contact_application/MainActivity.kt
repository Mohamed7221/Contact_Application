package com.example.contact_application

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import com.example.contact_application.ui.theme.Contact_ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Contact_ApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MyTopBar() }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        DataList(data = DataSource().getDataItems())
                    }
                }
            }
        }
    }
}

@Composable
fun DataList(data: List<Data>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
    ) {
        items(data) { item ->
            PhoneCall(data = item)
        }
    }
}

@Composable
fun PhoneCall(data: Data, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${data.number}")
                context.startActivity(intent)
            },
            painter = painterResource(id = data.image),
            contentDescription = null
        )

        Text(
            text = data.name,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        SelectionContainer(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 8.dp),
                text = data.number,
                fontSize = 20.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Contacts App") },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.icon_home),
                contentDescription = "Home",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(30.dp)
                    .clickable {
                        val phoneNumber = "123548"
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                        context.startActivity(intent)
                    }
            )
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    MyTopBar()
}

@Preview(showBackground = true)
@Composable
fun PhoneCallPreview() {
    PhoneCall(data = DataSource().getDataItems()[0])
}

class Data(
    val name: String,
    val number: String,
    val image: Int
)

class DataSource {
    @Composable
    fun getDataItems(): List<Data> {
        val dataCall = mutableListOf<Data>()

        dataCall.add(Data(stringResource(id = R.string.name_My_frind2), stringResource(id = R.string.num_DAD), R.drawable.granny))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind), stringResource(id = R.string.num_mohamed), R.drawable.brother))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind3), stringResource(id = R.string.num_mohamed), R.drawable.sister))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind2), stringResource(id = R.string.num_ahmeed), R.drawable.grandfather))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind2), stringResource(id = R.string.num_mohamed), R.drawable.daughter))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind3), stringResource(id = R.string.num_mohamed), R.drawable.friend_1))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind3), stringResource(id = R.string.num_ahmed_Mohamed), R.drawable.friend_2))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind3), stringResource(id = R.string.num_mohamed), R.drawable.friend_1))
        dataCall.add(Data(stringResource(id = R.string.name_My_frind3), stringResource(id = R.string.num_ahmed_Mohamed), R.drawable.friend_2))

        return dataCall
    }
}