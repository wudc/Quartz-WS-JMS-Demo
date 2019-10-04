package https.www_cjet_com.xml.payment;

import https.www_cjet_com.xml.payment.Acknowledgement;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class AcknowledgementTest {
  @Rule
  public final Timeout globalTimeout = new Timeout(10000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void setAcknowledgementInputNotNullOutputVoid() {

    // Arrange
    final Acknowledgement acknowledgement = new Acknowledgement();

    // Act
    acknowledgement.setAcknowledgement("foo");

    // Assert side effects
    Assert.assertEquals("foo", acknowledgement.acknowledgement);

  }

  // Test written by Diffblue Cover.
  @Test
  public void getAcknowledgementOutputNull() {

    // Arrange
    final Acknowledgement acknowledgement = new Acknowledgement();

    // Act and Assert result
    Assert.assertNull(acknowledgement.getAcknowledgement());

  }
}