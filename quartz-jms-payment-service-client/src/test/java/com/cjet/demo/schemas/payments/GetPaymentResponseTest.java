package com.cjet.demo.schemas.payments;

import com.cjet.demo.schemas.payments.GetPaymentResponse;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class GetPaymentResponseTest {
  @Rule
  public final Timeout globalTimeout = new Timeout(10000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void getAcknowledgementOutputNull() {

    // Arrange
    final GetPaymentResponse getPaymentResponse = new GetPaymentResponse();

    // Act and Assert result
    Assert.assertNull(getPaymentResponse.getAcknowledgement());

  }

  // Test written by Diffblue Cover.
  @Test
  public void constructorOutputNotNull() {

    // Act, creating object to test constructor
    final GetPaymentResponse actual = new GetPaymentResponse();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.acknowledgement);

  }
}