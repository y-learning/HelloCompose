package com.why.hellocompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun MyPlans() {
    val image = imageResource(R.drawable.header)

    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(modifier = Modifier.padding(16.dp)) {
            val imgModifier = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))

            Image(image, modifier = imgModifier, contentScale = Crop)
            Spacer(Modifier.preferredHeight(16.dp))

            Text(
                "Start your own business now and stop dreaming!",
                style = typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                Text("Workout", style = typography.body2)
                Spacer(modifier = Modifier.preferredWidth(4.dp))
                Text("Art", style = typography.body2)
            }
            Text("Meditation basics", style = typography.body2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPlans() {
    MyPlans()
}

@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Text(name, Modifier.clickable(onClick = { onClicked(name) }))
}

@Composable
fun NamePicker(
    header: String,
    names: List<String>,
    onNameClicked: (String) -> Unit
) {
    Column {
        // This will recompose when [header] changes, but not when [names]
        // changes.
        Text(header, style = MaterialTheme.typography.h5)
        Divider()

        LazyColumn {
            items(names) { name ->
                // When an item's [name] updates, the adapter for that item
                // will recompose. This will not recompose when [header]
                // changes.
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGreetings() {
    val header = mutableStateOf("Modern Android")
    MaterialTheme {
        NamePicker(
            header = header.value,
            names = listOf("Android 11", "Android X", "Jetpack", "Compose"),
            onNameClicked = { name -> header.value = "$name selected" })
    }
}

@Composable
fun CountButton(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

@Preview
@Composable
fun PreviewCountButton() {
    val clicks = mutableStateOf(0)

    MaterialTheme {
        CountButton(clicks.value) { clicks.value++ }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyPlans()
        }
    }
}
