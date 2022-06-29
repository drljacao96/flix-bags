package com.drbrosdev.flix_bags.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drbrosdev.flix_bags.data.TokenDataSource
import com.drbrosdev.flix_bags.presentation.components.CompanyLogo
import com.drbrosdev.flix_bags.presentation.components.FlixLoginLoadingIndicator
import com.drbrosdev.flix_bags.presentation.components.FlixPasswordTextField
import com.drbrosdev.flix_bags.presentation.components.FlixSnackbarError
import com.drbrosdev.flix_bags.presentation.components.FlixSubmitButton
import com.drbrosdev.flix_bags.presentation.components.FlixUserNameTextField
import com.drbrosdev.flix_bags.presentation.components.Screen
import com.drbrosdev.flix_bags.tokenDataStore
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.flow.collectLatest

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(TokenDataSource(requireContext().tokenDataStore)) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            transitionName = "login_fragment"

            setContent {
                Screen {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    ) {
                        val (userName, setUserName) = remember { mutableStateOf("") }
                        val (password, setPassword) = remember { mutableStateOf("") }
                        val snackbarHostState = remember { SnackbarHostState() }
                        val (companyLogo, userNameTextField, passwordTextField, loginButton, aboutText, snackbar, loadingIndicator) = createRefs()

                        val screenGuideline = createGuidelineFromTop(0.43f)
                        val endGuideline = createGuidelineFromEnd(0.10f)
                        val startGuideline = createGuidelineFromStart(0.10f)

                        val loginState by viewModel.state.collectAsState(initial = false)

                        LaunchedEffect(key1 = Unit) {
                            viewModel.events.collectLatest {
                                when (it) {
                                    is LoginEvents.NavigateToHome -> {
//                                        val action = LoginFragmentDirections.actionLoginToHome()
//                                        findNavController().navigate(action)
                                    }
                                    is LoginEvents.ShowInternetConnectionError -> {
                                        snackbarHostState.showSnackbar("No active internet connection!")
                                    }
                                    is LoginEvents.ShowNetworkCallError -> {
                                        snackbarHostState.showSnackbar("Network call error!")
                                    }
                                    is LoginEvents.InputFieldsEmpty -> {
                                        snackbarHostState.showSnackbar("Input fields cannot be empty!")
                                    }
                                    LoginEvents.LoginRequestError -> {
                                        snackbarHostState.showSnackbar("Login request error!")
                                    }
                                }
                            }
                        }

                        CompanyLogo(modifier = Modifier
                            .constrainAs(companyLogo) {
                                top.linkTo(parent.top, margin = 30.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(screenGuideline)
                            }
                            .padding()
                        )

                        FlixUserNameTextField(
                            hint = "User Name",
                            text = userName,
                            onTextChanged = setUserName,
                            modifier = Modifier.constrainAs(userNameTextField) {
                                top.linkTo(screenGuideline)
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                width = Dimension.fillToConstraints
                            }
                        )

                        FlixPasswordTextField(
                            text = password,
                            onTextChanged = setPassword,
                            modifier = Modifier.constrainAs(passwordTextField) {
                                start.linkTo(startGuideline)
                                end.linkTo(endGuideline)
                                top.linkTo(userNameTextField.bottom, margin = 8.dp)
                                width = Dimension.fillToConstraints
                            })

                        FlixSubmitButton(
                            text = "Login",
                            modifier = Modifier
                                .constrainAs(loginButton) {
                                    start.linkTo(startGuideline)
                                    end.linkTo(endGuideline)
                                    top.linkTo(passwordTextField.bottom, 32.dp)
                                    width = Dimension.fillToConstraints
                                }
                        ) {
                            viewModel.login(userName, password)
                        }

                        Text(
                            text = "App Version: 1.0 Lorem ipsum",
                            modifier = Modifier.constrainAs(aboutText) {
                                bottom.linkTo(parent.bottom, 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                            style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                        )

                        FlixSnackbarError(
                            snackbarHostState = snackbarHostState,
                            modifier = Modifier.constrainAs(snackbar) {
                                top.linkTo(parent.top, 12.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )

                        FlixLoginLoadingIndicator(
                            modifier = Modifier
                                .height(10.dp)
                                .fillMaxWidth()
                                .padding(top = 2.dp)
                                .clip(RoundedCornerShape(percent = 50))
                                .constrainAs(loadingIndicator) {
                                    top.linkTo(parent.top)
                                },
                            isLoading = loginState
                        )
                    }
                }
            }
        }
    }

}