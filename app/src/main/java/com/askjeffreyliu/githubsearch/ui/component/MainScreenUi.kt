package com.askjeffreyliu.githubsearch.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.askjeffreyliu.githubsearch.extension.exhaustive
import com.askjeffreyliu.githubsearch.model.QueryItem
import com.askjeffreyliu.githubsearch.other.Status
import com.askjeffreyliu.githubsearch.viewmodel.MainViewModel

@Composable
fun GithubSearchUI(
    viewModel: MainViewModel,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
//        val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBar(onSelected = {
            onSearch(it)
        })

        val result by viewModel.queryResultFlow.collectAsState()
        when (result.status) {
            Status.LOADING -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            Status.SUCCESS -> {
                result.data?.items?.let {
                    SearchResultList(it)
                } ?: kotlin.run {
                    SearchResultList(emptyList())
                }
            }
            else -> {
                result.message?.let { msg ->
                    LaunchedEffect(msg) {
                        println("jeff error is $msg")
                        snackBarHostState.showSnackbar(msg)
                    }
                }
                result.data?.items?.let {
                    SearchResultList(it)
                } ?: kotlin.run {
                    SearchResultList(emptyList())
                }
            }
        }.exhaustive
    }
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = modifier
            .wrapContentHeight(Alignment.Bottom)
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        keyboardActions = KeyboardActions(onDone = {
            onSelected(name)
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        value = name,
        onValueChange = {
            name = it
        },
        trailingIcon = {
            TextButton(
                onClick = {
                    onSelected(name)
                    focusManager.clearFocus()
                }) {
                Text(stringResource(com.askjeffreyliu.githubsearch.R.string.search))
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(stringResource(com.askjeffreyliu.githubsearch.R.string.enter_github_repo))
        },
        modifier = modifier
            .fillMaxWidth()

    )
}

@Composable
fun SearchResultList(queryItems: List<QueryItem>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(queryItems) {
            ComposeQueryResult(it)
        }
    }
}


@Composable
fun ComposeQueryResult(queryItem: QueryItem) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(queryItem.owner.avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(com.askjeffreyliu.githubsearch.R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(com.askjeffreyliu.githubsearch.R.string.app_name),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            ComposeListItemRightSide(
                title = queryItem.fullName,
                queryItem.description,
                queryItem.stargazersCount
            )
        }
    }
}

@Composable
fun ComposeListItemRightSide(title: String, description: String?, star: Int) {
    Column {
        Row {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = "★$star",
                maxLines = 1,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
            )
        }
        description?.let {
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .width(1.dp)
                    .padding(vertical = 4.dp)
            )
            Text(text = description)
        }
    }
}