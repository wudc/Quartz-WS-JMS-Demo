package https.www_cjet_com.xml.payment;

import https.www_cjet_com.xml.payment.GetPaymentRequest;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class GetPaymentRequestTest {
  @Rule
  public final Timeout globalTimeout = new Timeout(10000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void setMethodInputNotNullOutputVoid() {

    // Arrange
    final GetPaymentRequest getPaymentRequest = new GetPaymentRequest();

    // Act
    getPaymentRequest.setMethod("foo");

    // Assert side effects
    Assert.assertEquals("foo", getPaymentRequest.method);

  }

  // Test written by Diffblue Cover.
  @Test
  public void getStatusOutputNull() {

    // Arrange
    final GetPaymentRequest getPaymentRequest = new GetPaymentRequest();

    // Act and Assert result
    Assert.assertNull(getPaymentRequest.getStatus());

  }

  // Test written by Diffblue Cover.
  @Test
  public void getMethodOutputNull() {

    // Arrange
    final GetPaymentRequest getPaymentRequest = new GetPaymentRequest();

    // Act and Assert result
    Assert.assertNull(getPaymentRequest.getMethod());

  }

  // Test written by Diffblue Cover.
  @Test
  public void setStatusInputNotNullOutputVoid() {

    // Arrange
    final GetPaymentRequest getPaymentRequest = new GetPaymentRequest();

    // Act
    getPaymentRequest.setStatus("foo");

    // Assert side effects
    Assert.assertEquals("foo", getPaymentRequest.status);

  }
}