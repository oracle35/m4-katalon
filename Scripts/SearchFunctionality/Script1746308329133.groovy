import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable // If using global variables
import org.openqa.selenium.Keys as Keys // Import Keys for Enter action
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

// Define the search term
String searchTerm = "Zapopan weather" // Or use GlobalVariable.searchTerm. Using location based on your context.

// Start Browser and Navigate
WebUI.openBrowser('') // Opens the default browser configured in Katalon
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://www.bbc.com/search')

// --- Interaction with Search ---

// 1. Find the search input field and enter text
WebUI.waitForElementVisible(findTestObject('input_SearchField'), 5)
WebUI.waitForElementClickable(findTestObject('input_SearchField'), 5)
WebUI.setText(findTestObject('input_SearchField'), searchTerm)
WebUI.comment("Entered search term: ${searchTerm}")

// 2. Simulate pressing the Enter key in the search field
WebUI.sendKeys(findTestObject('input_SearchField'), Keys.chord(Keys.ENTER))
WebUI.comment("Pressed Enter key to initiate search.")

// 3. Verify that search results page/area is loaded
WebUI.waitForElementVisible(findTestObject('div_SearchResultsArea'), 5)
boolean resultsDisplayed = WebUI.verifyElementPresent(findTestObject('div_SearchResultsArea'), 15, FailureHandling.STOP_ON_FAILURE)

if (resultsDisplayed) {
    WebUI.comment("Search results area is displayed.")
    // Add more specific result checks if needed
} else {
    WebUI.comment("Search results area was not found within the timeout.")
}

// --- Cleanup ---
WebUI.delay(3) // Optional pause
WebUI.closeBrowser()

println("Test Case Execution Finished.")
