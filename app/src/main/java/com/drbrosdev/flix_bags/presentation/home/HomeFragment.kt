package com.drbrosdev.flix_bags.presentation.home

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.drbrosdev.flix_bags.R
import com.drbrosdev.flix_bags.presentation.components.*
import com.drbrosdev.flix_bags.util.vibrate
import com.google.android.material.transition.MaterialSharedAxis
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

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

    @OptIn(ExperimentalLifecycleComposeApi::class)
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
                        val (topBar, codeCard, submitButton, homeText, bottomBar) = createRefs()
                        val startCardGuideline = createGuidelineFromStart(0.12f)
                        val endCardGuideline = createGuidelineFromEnd(0.12f)
                        val bottomCardGuideline = createGuidelineFromTop(0.65f)
                        val endGuideline = createGuidelineFromEnd(0.05f)
                        val startGuideline = createGuidelineFromStart(0.05f)

                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            viewModel.events.collect {
                                when (it) {
                                    HomeEvents.CodeMatch -> {}
                                    HomeEvents.CodeNotMatch -> {
                                        launch {
                                            mediaPlayer?.start()
                                            delay(200)
                                            requireView().vibrate()
                                        }
                                    }
                                }
                            }
                        }

                        FlixTopBar(
                            text = "Izdavanje Prtljaga",
                            modifier = Modifier
                                .constrainAs(topBar) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(horizontal = 12.dp)
                        )

                        FlixCodeCard(
                            firstCodeContent = state.customerBagCode,
                            secondCodeContent = state.bagCode,
                            comparisonState = state.comparisonState,
                            modifier = Modifier
                                .constrainAs(codeCard) {
                                    start.linkTo(startCardGuideline)
                                    end.linkTo(endCardGuideline)
                                    top.linkTo(topBar.bottom, 18.dp)
                                    bottom.linkTo(bottomCardGuideline)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }
                        )

                        FlixHomeScreenInfoText(
                            modifier = Modifier.constrainAs(homeText) {
                                top.linkTo(bottomCardGuideline)
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                bottom.linkTo(submitButton.top)
                                width = Dimension.fillToConstraints
                            },
                            headerText = viewModel.setHeaderText(state.comparisonState),
                            text = viewModel.setText(state.comparisonState)
                        )

                        if (state.comparisonState == CodeComparisonState.TRUE ||
                            state.comparisonState == CodeComparisonState.FALSE
                        ) {
                            FlixBottomBar(
                                modifier = Modifier
                                    .constrainAs(bottomBar) {
                                        bottom.linkTo(parent.bottom, 8.dp)
                                        start.linkTo(startGuideline)
                                        end.linkTo(endGuideline)
                                        width = Dimension.fillToConstraints
                                    },
                                onFinish = {
                                    val action =
                                        HomeFragmentDirections.actionHomeFragmentToAdDialogFragment()
                                    findNavController().navigate(action)
                                }
                            ) {
                                viewModel.updateComparisonState(CodeComparisonState.IDLE)
                            }
                        } else {
                            FlixScanCodeButton(
                                actionText = "Skenirajte kod",
                                modifier = Modifier
                                    .constrainAs(submitButton) {
                                        bottom.linkTo(parent.bottom, 8.dp)
                                        start.linkTo(startGuideline)
                                        end.linkTo(endGuideline)
                                        width = Dimension.fillToConstraints
                                    },
                                supportText = "Pritisnite dugme da skenirate QR kod!"
                            ) {
                                scanCodeIntent.launch(scannerConfig)
                            }
                        }
                    }
                }
            }
        }
    }

}