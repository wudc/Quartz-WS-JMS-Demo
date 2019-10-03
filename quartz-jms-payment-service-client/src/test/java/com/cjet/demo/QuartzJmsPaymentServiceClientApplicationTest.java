package com.cjet.demo;

import com.cjet.demo.QuartzJmsPaymentServiceClientApplication;
import com.cjet.demo.saop.client.SOAPConnector;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class QuartzJmsPaymentServiceClientApplicationTest {
  @Rule
  public final Timeout globalTimeout = new Timeout(10000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void lookupInputNotNullOutputNull() {

    // Arrange
    final QuartzJmsPaymentServiceClientApplication quartzJmsPaymentServiceClientApplication = new QuartzJmsPaymentServiceClientApplication();
    final SOAPConnector soapConnector = new SOAPConnector();

    // Act and Assert result
    Assert.assertNull(quartzJmsPaymentServiceClientApplication.lookup(soapConnector));

  }
}