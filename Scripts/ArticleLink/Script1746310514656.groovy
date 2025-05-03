import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.TestObject

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://www.bbc.com')

TestObject australiaLink = findTestObject('Link_Australia_Article')
WebUI.waitForElementClickable(australiaLink, 5)
WebUI.click(australiaLink)
WebUI.comment("Clicked the Australia article link.")

WebUI.waitForPageLoad(10)
TestObject articleContent = findTestObject('div_ArticleContent')
WebUI.waitForElementVisible(articleContent, 10)

boolean contenidoVisible = WebUI.verifyElementPresent(articleContent, 10, FailureHandling.STOP_ON_FAILURE)

if (contenidoVisible) {
    WebUI.comment('El contenido del artículo se muestra correctamente.')
} else {
    WebUI.comment('El contenido del artículo no se encontró.')
}

TestObject articleTitle = findTestObject('h1_ArticleTitle')
boolean tituloVisible = WebUI.verifyElementPresent(articleTitle, 5, FailureHandling.CONTINUE_ON_FAILURE)

if (tituloVisible) {
    WebUI.comment('El título del artículo está visible.')
} else {
    WebUI.comment('El título del artículo no se encontró.')
}


WebUI.delay(2)
WebUI.closeBrowser()