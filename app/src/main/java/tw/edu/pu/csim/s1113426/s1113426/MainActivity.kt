package tw.edu.pu.csim.s1113426.s1113426

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.s1113426.s1113426.ui.theme.S1113426Theme
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.Bottom
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.text.style.LineHeightStyle.Alignment.Companion.Bottom
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp


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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff95fe95)), // 背景顏色設置
        contentAlignment = Alignment.Center // Box 內的所有元素居中
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // 讓內容水平方向居中
            verticalArrangement = Arrangement.Center, // 讓內容垂直方向居中
            modifier = Modifier.padding(16.dp) // 添加一些內邊距，防止內容太緊密
        ) {
            Text(
                text = "$name!",
                modifier = modifier
            )
            Image(
                painter = painterResource(id = R.drawable.class_b),
                contentDescription = "B班",
                contentScale = ContentScale.FillBounds,  // 縮放符合螢幕寬度
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp) // 圖片填滿寬度並加入上邊距
            )
            Text(
                text = "遊戲持續時間 0 秒",
                modifier = modifier.padding(top = 16.dp)
            )
            Text(
                text = "您的成績 0 分",
                modifier = modifier
            )

            // 拿到 Activity 參照
            val activity = (LocalContext.current as? Activity)

            // 結束應用的按鈕
            Button(
                onClick = {
                    activity?.finish()  // 結束當前 Activity，關閉應用程式
                },
                modifier = Modifier.padding(top = 16.dp) // 按鈕與其他內容保持間距
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

