package https.www_cjet_com.xml.payment;

import https.www_cjet_com.xml.payment.GetPaymentResponse;
import https.www_cjet_com.xml.payment.ObjectFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class ObjectFactoryTest {
  @Rule
  public final Timeout globalTimeout = new Timeout(10000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void createGetPaymentResponseOutputNotNull() {

    // Arrange
    final ObjectFactory objectFactory = new ObjectFactory();

    // Act
    final GetPaymentResponse actual = objectFactory.createGetPaymentResponse();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getAcknowledgement());

  }
}