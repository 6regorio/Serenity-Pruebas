package com.gettingstarted.tests;

import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class MyFirstTest {

  @Managed
  WebDriver driver;

  @Before
  public void cargarPaginaInicial() {
    driver.manage().window().maximize();
    driver.get("https://sescampre.jccm.es/portalsalud/app/inicio");

  }

  /**
   * Comprueba el popup del teléfono en la página principal
   * 
   * @param drivers
   */
  @Title("Comprueba el popup del teléfono en la página principal")
  @Test
  public void comprobarClickTelefono() {
    driver.findElement(By.cssSelector(".action-icon-telephone")).click();
    Assert.assertTrue(driver.findElement(By.linkText("900 12 21 12")).isDisplayed());
    driver.findElement(By.cssSelector(".ion-page > .ion-color")).click();
  }



  /**
   * Comprueba pulsar más y entrar en privacidad
   * 
   * @param drivers
   */
  @Title("Comprueba pulsar más y entrar en privacidad")
  @Test
  public void masPrivacidad() {
    driver.findElement(By.xpath("//ion-segment-button[contains(.,'Más')]")).click();
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.findElement(By.cssSelector(".popover-viewport > .md:nth-child(2)")).click();
    Assert.assertEquals(
        driver.findElement(By.xpath("//h1[contains(.,'Privacidad')]")).isDisplayed(), true);
  }



  /**
   * Comprueba el enlace del coronavirus en la página principal
   * 
   * @param drivers
   */
  @Title("Comprueba el enlace del coronavirus en la página principal")
  @Test
  public void comprobarEnlaceCoronavirus() {

    String handleVentana1 = driver.getWindowHandle();
    driver.findElement(By.cssSelector("a .action-icon")).click();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // WebDriverWait wait = new WebDriverWait(currentDriver, 30);
    By btnSi = By.xpath("//span[contains(.,'Sí')]");
    // wait.until(ExpectedConditions.invisibilityOfElementLocated(btnSi));
    driver.findElement(btnSi).click();

    // currentDriver.findElement(By.xpath("//span[contains(.,'Sí')]")).click();

    Set<String> listadoHanleVentanas = driver.getWindowHandles();
    listadoHanleVentanas.remove(handleVentana1);
    String handleVentana2 = listadoHanleVentanas.iterator().next();
    driver.switchTo().window(handleVentana1);
    driver.close();
    driver.switchTo().window(handleVentana2);
    Assert.assertEquals(handleVentana2, driver.getWindowHandle());
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Assert.assertTrue(
        driver.findElement(By.xpath("//span[contains(.,'Asistencia COVID-19')]")).isDisplayed());
  }



}
