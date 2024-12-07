import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import cl.app.seguridad.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenWithLogo(
    onTimeExpired: () -> Unit,
    modifier: Modifier = Modifier
) {
    SplashScreen(
        modifier = modifier,
        onTimeExpired = onTimeExpired
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenWithLogoPreview(
    @PreviewParameter(SplashScreenBackgroundProvider::class) backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        SplashScreenWithLogo(onTimeExpired = {})
    }
}

class SplashScreenBackgroundProvider : PreviewParameterProvider<Color> {
    override val values: Sequence<Color>
        get() = sequenceOf(
            Color.White,
            Color.LightGray
        )
}


@Composable
fun SplashScreen(
    onTimeExpired: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF87CEEB),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "Splash Screen Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            LogoAnimation(
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Apply fillMaxWidth here
                    .aspectRatio(1f)
                    .wrapContentSize(Alignment.Center) // Wrap content size
            )
        }
    }
    LaunchedEffect(key1 = true) {
        delay(3000)
        onTimeExpired()
    }
}

@Composable
fun LogoAnimation(
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(initialValue = 0f) }
    val rotation = remember { Animatable(initialValue = 0f) }
    val alpha = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )
    }

    Image(
        painter = painterResource(id = R.drawable.splash_logo),
        contentDescription = "Logo",
        modifier = modifier
            .fillMaxWidth(0.9f)
            .aspectRatio(1f) // Maintain a 1:1 aspect ratio
            .scale(scale.value)
            .rotate(rotation.value)
            .alpha(alpha.value),
        contentScale = ContentScale.Fit // Scale to fit within bounds
    )
}