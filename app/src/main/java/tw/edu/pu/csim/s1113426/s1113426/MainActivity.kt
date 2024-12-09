package tw.edu.pu.csim.s1113426.s1113426

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import android.content.pm.ActivityInfo
import tw.edu.pu.csim.s1113426.s1113426.ui.theme.S1113426Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1113426Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    GameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    // 遊戲持續時間
    var gameTime by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }

    // 瑪利亞的x座標 (從左到右)
    var mariaX by remember { mutableStateOf(0f) }
    var currentColorIndex by remember { mutableStateOf(0) }

    // 顏色列表
    val colors = listOf(
        Color(0xff95fe95),
        Color(0xfffdca0f),
        Color(0xfffea4a4),
        Color(0xffa5dfed)
    )
    val currentColor = colors[currentColorIndex]

    // 隨機選擇瑪利亞圖片的ID
    var mariaImage by remember { mutableStateOf(R.drawable.maria0) }
    // 顯示分數
    var score by remember { mutableStateOf(0) }

    // CoroutineScope來啟動計時和移動瑪利亞
    val scope = rememberCoroutineScope()

    // 當遊戲未結束時啟動計時器
    if (!gameOver) {
        // 啟動計時器
        scope.launch {
            while (!gameOver) {
                delay(1000) // 每秒鐘執行一次
                if (!gameOver) {
                    gameTime += 1 // 每秒增加1秒
                    mariaX += 50f // 每秒向右移動50像素
                    if (mariaX > 1080f) { // 假設螢幕寬度是1080像素
                        gameOver = true
                    }
                }
            }
        }
    }

    // 更新顏色（可選）
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(currentColor) // 背景顏色
            .padding(16.dp),
        contentAlignment = Alignment.Center // 讓內容置中顯示
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Text(text = "2024期末上機考(資管三B林彗靖)")
            Image(
                painter = painterResource(id = R.drawable.class_b),
                contentDescription = "B班",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )
            Text(
                text = "遊戲持續時間: $gameTime 秒",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "您的成績: $score 分",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )

            // 顯示瑪利亞圖示，請確認已經放入圖片檔案 maria0…3.jpg
            Image(
                painter = painterResource(id = mariaImage), // 載入圖片資源
                contentDescription = "瑪利亞",
                modifier = Modifier
                    .offset(x = mariaX.dp) // 移動圖示位置
                    .width(200.dp)
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            // 顯示結束遊戲訊息
            if (gameOver) {
                Text(
                    text = "遊戲結束！",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = Color.Red
                )
            }

            // 拿到 Activity 參照
            val activity = (LocalContext.current as? android.app.Activity)

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

        // 監聽雙擊事件
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            // 判斷背景顏色是否與Box背景顏色相同
                            if (currentColor == colors[currentColorIndex]) {
                                score += 1 // 顏色相同，得分
                            } else {
                                score -= 1 // 顏色不同，扣分
                            }

                            // 隨機選擇瑪利亞圖片
                            val randomImageIndex = Random.nextInt(0, 4) // 隨機選擇0-3的圖片
                            mariaImage = when (randomImageIndex) {
                                0 -> R.drawable.maria0
                                1 -> R.drawable.maria1
                                2 -> R.drawable.maria2
                                else -> R.drawable.maria3
                            }

                            // 重置瑪利亞位置，從左下角開始
                            mariaX = 0f
                        }
                    )
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    S1113426Theme {
        GameScreen()
    }
}
