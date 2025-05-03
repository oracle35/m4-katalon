import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://www.bbc.com')

WebUI.waitForPageLoad(10)

TestObject businessLink = new TestObject('Link_Business_Section')

businessLink.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "//nav//a[normalize-space()='Business']")

WebUI.waitForElementClickable(businessLink, 10)
WebUI.click(businessLink)
WebUI.comment("Navigated to Business section.")

WebUI.waitForPageLoad(10)

TestObject articleList = new TestObject('Section_Article_List')

articleList.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS, "/html/body/div[2]/div/main/article/section[1]/div/div/div[1]/div/div/div/a/div")

WebUI.waitForElementVisible(articleList, 10)

boolean articulosVisibles = WebUI.verifyElementPresent(articleList, 10, FailureHandling.STOP_ON_FAILURE)

if (articulosVisibles) {
    WebUI.comment('Se muestra la lista de artículos de la sección Business.')
} else {
    WebUI.comment('No se encontró la lista de artículos en la sección Business.')
}

WebUI.delay(2)
WebUI.closeBrowser()