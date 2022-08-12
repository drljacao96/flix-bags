package com.drbrosdev.flix_bags.presentation.ad

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.DialogFragment
import com.drbrosdev.flix_bags.presentation.components.FlixAddDialogFragmentInfoText
import com.drbrosdev.flix_bags.presentation.components.FlixCloseApplicationButton

class AdDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {

                    val activity = (LocalContext.current as? Activity)

                    val (adText, exitButton) = createRefs()
                    val endGuideline = createGuidelineFromEnd(0.05f)
                    val startGuideline = createGuidelineFromStart(0.05f)

                    FlixAddDialogFragmentInfoText(
                        modifier = Modifier.constrainAs(adText) {
                            top.linkTo(parent.top)
                            start.linkTo(startGuideline)
                            end.linkTo(endGuideline)
                            bottom.linkTo(exitButton.top)
                            width = Dimension.fillToConstraints
                        },
                        headerText = "Hvala vam sto koristite nase PRTLJAZNE KARTE kao i aplikaciju za kontrolu izdavanja prtljaga.\n" + "\n" + "\n" +
                                "www.e-5.rs\n" + "\n" + "\n" + "\n" + "+381 63 520 761",
                        text = ""
                    )

                    FlixCloseApplicationButton(
                        actionText = "Zatvorite aplikaciju",
                        modifier = Modifier
                            .constrainAs(exitButton) {
                                bottom.linkTo(parent.bottom, 8.dp)
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                width = Dimension.fillToConstraints
                            }
                    ) {
                        activity?.finish()
                    }
                }
            }
        }
    }

}