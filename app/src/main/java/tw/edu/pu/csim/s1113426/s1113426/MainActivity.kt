package tw.edu.pu.csim.s1113426.s1113426

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.s1113426.s1113426.ui.theme.S1113426Theme
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.draggable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1113426Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    Greeting(
                        name = "2024期末上機考(資管三B林彗靖)",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // 定義背景顏色的列表
    val colors = listOf(
        Color(0xff95fe95),
        Color(0xfffdca0f),
        Color(0xfffea4a4),
        Color(0xffa5dfed)
    )

    // 使用 remember 和 mutableStateOf 來儲存當前的顏色索引
    var currentColorIndex by remember { mutableStateOf(0) }
    val currentColor = colors[currentColorIndex]

    // 監聽拖曳手勢
    var dragOffset by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(currentColor) // 設置背景顏色
            .draggable(
                state = rememberDraggableState { delta ->
                    dragOffset += delta
                },
                orientation = androidx.compose.foundation.gestures.Orientation.Horizontal // 僅監聽水平拖曳
            ),
        contentAlignment = Alignment.Center // 讓內容置中顯示
    ) {
        // 如果拖曳結束並且足夠大，根據方向變換顏色
        LaunchedEffect(dragOffset) {
            if (dragOffset > 100) { // 向右滑動，更新顏色
                currentColorIndex = (currentColorIndex + 1) % colors.size
                dragOffset = 0f  // 重置拖曳偏移量
            } else if (dragOffset < -100) { // 向左滑動，更新顏色
                currentColorIndex = (currentColorIndex - 1 + colors.size) % colors.size
                dragOffset = 0f  // 重置拖曳偏移量
            }
        }

        // 顯示內容
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "$name")
            Image(
                painter = painterResource(id = R.drawable.class_b),
                contentDescription = "B班",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = "遊戲持續時間 0 秒", modifier = modifier.padding(top = 16.dp))
            Text(text = "您的成績 0 分", modifier = modifier)

            // 拿到 Activity 參照
            val activity = (LocalContext.current as? Activity)

            // 結束應用的按鈕
            Button(
                onClick = {
                    activity?.finish()  // 結束當前 Activity，關閉應用程式
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "結束App，按了會關閉這個應用程式")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S1113426Theme {
        Greeting("Android")
    }
}
