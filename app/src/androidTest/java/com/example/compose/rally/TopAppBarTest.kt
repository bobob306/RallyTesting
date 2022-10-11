package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    // TODO: Add tests

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                // use this to set the theme, necessary for screenshot tests or theme tests
                // Material theme has good defaults so fine to test with
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { /*TODO*/ },
                    currentScreen = RallyScreen.Accounts
                )
            }
        }
        // Thread.sleep(5000)
        // lets you see the test happen in the emulator

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            // look for a content description
            .assertIsSelected()
            // see if it is selected

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        // using unmerged tree lets you test nodes inside a tree that otherwise would just be
        // bundled up together, often letting tests pass that should fail because assertExists
        // will pass as those nodes do exist but might not be doing the tested behaviour

        // useUnmergedTree gives a longer logcat because each node is printed separately, but each
        // node's properties are now more specific

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        // check for uppercase
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        // check the parent node which prevents another instance of some text to
                        // allow a test to pass with the wrong parent
                        // some text will appear on a screen a lot, e.g. account in a financial app
                        ),
                // use the unmerged tree when checking these tests
                useUnmergedTree = true
            )
            .assertExists()
    }

}