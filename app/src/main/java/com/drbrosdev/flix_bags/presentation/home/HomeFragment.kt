package com.drbrosdev.flix_bags.presentation.home

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.drbrosdev.flix_bags.R
import com.drbrosdev.flix_bags.presentation.components.FlixAddBaggageButton
import com.drbrosdev.flix_bags.presentation.components.FlixBaggageStatus
import com.drbrosdev.flix_bags.presentation.components.FlixCodeCard
import com.drbrosdev.flix_bags.presentation.components.FlixCompareButton
import com.drbrosdev.flix_bags.presentation.components.FlixHomeScreenInfoText
import com.drbrosdev.flix_bags.presentation.components.FlixSnackbarError
import com.drbrosdev.flix_bags.presentation.components.FlixSnackbarSuccess
import com.drbrosdev.flix_bags.presentation.components.FlixSubmitButton
import com.drbrosdev.flix_bags.presentation.components.FlixTopBar
import com.drbrosdev.flix_bags.presentation.components.Screen
import com.drbrosdev.flix_bags.util.vibrate
import com.google.android.material.transition.MaterialSharedAxis
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel() as T
            }
        }
    }

    private val scanCodeIntent = registerForActivityResult(ScanCustomCode()) {
        when (it) {
            is QRResult.QRSuccess -> {
                viewModel.submitCustomerBagCode(it.content.rawValue)
            }
            is QRResult.QRUserCanceled -> {}
            is QRResult.QRMissingPermission -> {}
            is QRResult.QRError -> {}
        }
    }

    private val scanCodeIntent2 = registerForActivityResult(ScanCustomCode()) {
        when (it) {
            is QRResult.QRSuccess -> {
                viewModel.compareBags(it.content.rawValue)
            }
            is QRResult.QRUserCanceled -> {}
            is QRResult.QRMissingPermission -> {}
            is QRResult.QRError -> {}
        }
    }

    private val scannerConfig = ScannerConfig.build {
        setBarcodeFormats(listOf(BarcodeFormat.FORMAT_QR_CODE))
        setOverlayStringRes(R.string.place_the_qr_code_in_the_indicated_rectangle)
        setShowTorchToggle(true)
        setHapticSuccessFeedback(true)
    }

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //transition animations
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    override fun onDetach() {
        super.onDetach()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val alertSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        mediaPlayer = MediaPlayer.create(requireContext(), alertSound)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            transitionName = "home_fragment"

            setContent {
                Screen {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    ) {
                        val snackbarHostStateError = remember { SnackbarHostState() }
                        val snackbarHostStateSuccess = remember { SnackbarHostState() }

                        val (topBar, codeCard, submitButton, homeText, snackbar, snackbarSuccess) = createRefs()
                        val startCardGuideline = createGuidelineFromStart(0.12f)
                        val endCardGuideline = createGuidelineFromEnd(0.12f)
                        val bottomCardGuideline = createGuidelineFromBottom(0.4f)
                        val bottomBaggageStatusGuideline = createGuidelineFromTop(0.70f)
                        val endGuideline = createGuidelineFromEnd(0.05f)
                        val startGuideline = createGuidelineFromStart(0.05f)

                        val state by viewModel.state.collectAsState(initial = HomeUiState())

                        LaunchedEffect(key1 = Unit) {
                            viewModel.events.collectLatest {
                                when (it) {
                                    HomeEvents.CodeMatch -> {
                                        snackbarHostStateSuccess.showSnackbar("Kodovi su identicni!")
                                    }
                                    HomeEvents.CodeNotMatch -> {
                                        launch {
                                            snackbarHostStateError.showSnackbar("Kodovi nisu identicni!")
                                        }
                                        launch {
                                            mediaPlayer?.start()
                                            delay(200)
                                            requireView().vibrate()
                                        }
                                    }
                                    HomeEvents.CustomerBagCodeScanned -> {

                                    }
                                    HomeEvents.CodesScanned -> {

                                    }
                                }
                            }
                        }

                        FlixTopBar(
                            text = "Flix Torbe",
                            modifier = Modifier
                                .constrainAs(topBar) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(horizontal = 12.dp)
                        )

                        FlixCodeCard(
                            codeContent = state.customerBagCode,
                            modifier = Modifier
                                .constrainAs(codeCard) {
                                    start.linkTo(startCardGuideline)
                                    end.linkTo(endCardGuideline)
                                    top.linkTo(topBar.bottom, 18.dp)
                                    bottom.linkTo(bottomCardGuideline)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }
                                .aspectRatio(4 / 5f),
                            onScanTicket = {
                                scanCodeIntent.launch(scannerConfig)
                            }
                        )

                        FlixHomeScreenInfoText(
                            modifier = Modifier.constrainAs(homeText) {
                                top.linkTo(bottomBaggageStatusGuideline)
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                bottom.linkTo(submitButton.top)
                                width = Dimension.fillToConstraints
                            }
                        )

                        FlixCompareButton(
                            text = "Uporedi torbe",
                            modifier = Modifier.constrainAs(submitButton) {
                                bottom.linkTo(parent.bottom, 8.dp)
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                width = Dimension.fillToConstraints
                            },
                            isCustomerBagScanned = state.isCustomerBagCodeScanned
                        ) {
                            scanCodeIntent2.launch(scannerConfig)
                        }

                        FlixSnackbarError(
                            snackbarHostState = snackbarHostStateError,
                            modifier = Modifier.constrainAs(snackbar) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )

                        FlixSnackbarSuccess(
                            snackbarHostState = snackbarHostStateSuccess,
                            modifier = Modifier.constrainAs(snackbarSuccess) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                }
            }
        }
    }

}