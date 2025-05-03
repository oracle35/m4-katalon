import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://www.bbc.com')

WebUI.waitForPageLoad(10)

// Navigate to Business section
TestObject businessLink = new TestObject('Link_Business_Section')
businessLink.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "//nav//a[normalize-space()='Business']")

WebUI.waitForElementClickable(businessLink, 10)
WebUI.click(businessLink)
WebUI.comment("Navigated to Business section.")

WebUI.waitForPageLoad(10)


TestObject bodyObject = new TestObject('Page_Body')
bodyObject.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, '//body')
WebUI.waitForElementVisible(bodyObject, 3)

String pageText = WebUI.getText(bodyObject)

boolean foundTariffs = pageText.toLowerCase().contains('tariffs')
boolean foundTrump = pageText.toLowerCase().contains('trump')
boolean foundWallStreet = pageText.toLowerCase().contains('wall street')

if (foundTariffs || foundTrump || foundWallStreet) {
    WebUI.comment('Se encontró al menos una de las palabras clave: Tariffs, Trump, o Wall Street.')
} else {
    WebUI.comment('No se encontraron las palabras clave: Tariffs, Trump, ni Wall Street.')
}

WebUI.delay(2)
WebUI.closeBrowser()
