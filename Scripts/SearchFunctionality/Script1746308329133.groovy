import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

// --- Utilidad para realizar búsqueda ---
def realizarBusqueda(String terminoBusqueda) {
    WebUI.openBrowser('')
    WebUI.maximizeWindow()
    WebUI.navigateToUrl('https://www.bbc.com/search')

    WebUI.waitForElementVisible(findTestObject('input_SearchField'), 5)
    WebUI.waitForElementClickable(findTestObject('input_SearchField'), 5)
    WebUI.setText(findTestObject('input_SearchField'), terminoBusqueda)
    WebUI.comment("Entered search term: ${terminoBusqueda}")
    WebUI.sendKeys(findTestObject('input_SearchField'), Keys.chord(Keys.ENTER))
    WebUI.comment("Pressed Enter key to initiate search.")
}

String searchTerm1 = "cambio climático"
realizarBusqueda(searchTerm1)

// Esperar que se carguen los resultados
WebUI.waitForElementVisible(findTestObject('div_SearchResultsArea'), 10)

// Verificar que el área de resultados esté presente
boolean resultsDisplayed1 = WebUI.verifyElementPresent(findTestObject('div_SearchResultsArea'), 5, FailureHandling.STOP_ON_FAILURE)
assert resultsDisplayed1 == true

// Verificar que al menos un resultado mencione "cambio climático"
boolean contieneCambioClimatico = WebUI.verifyTextPresent('climate', false, FailureHandling.CONTINUE_ON_FAILURE)
if (contieneCambioClimatico) {
    WebUI.comment('Se encontraron artículos relacionados con "cambio climático".')
} else {
    WebUI.comment('No se encontraron artículos que mencionen "cambio climático".')
}

WebUI.closeBrowser()

// --- Caso 2: Buscar una palabra inexistente y verificar mensaje "No se encontraron resultados" ---
String searchTerm2 = "asldkjasldkjzxcasd" // Palabra que no debería existir
realizarBusqueda(searchTerm2)

// Esperar que se cargue la página de resultados
WebUI.waitForElementVisible(findTestObject('div_SearchResultsArea'), 10)

// Verificar que aparezca el mensaje de "No se encontraron resultados"
// Asegúrate de tener un TestObject llamado 'label_NoResultsMessage' que apunte al texto exacto
boolean noResultsMessagePresent = WebUI.verifyElementPresent(findTestObject('label_NoResultsMessage'), 10, FailureHandling.STOP_ON_FAILURE)

if (noResultsMessagePresent) {
    WebUI.comment('Mensaje de "No se encontraron resultados" se mostró correctamente.')
} else {
    WebUI.comment('El mensaje de "No se encontraron resultados" no se mostró como se esperaba.')
}

WebUI.closeBrowser()

println("Ejecución de ambos casos finalizada.")
