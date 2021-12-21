package by.mironenko.marketplace.service;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ServiceFactoryTest {

    @Test
    public void testGetInstanceNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance());
    }

    @Test
    public void testGetGameServiceNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance().getGameService());
    }

    @Test
    public void testGetBuyerServiceNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance().getBuyerService());
    }

    @Test
    public void testGetDeveloperServiceNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance().getDeveloperService());
    }

    @Test
    public void testGetBuyerTakeSaleNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance().getBuyerTakeSale());
    }

    @Test
    public void testGetPurchaseServiceNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance().getPurchaseService());
    }

    @Test
    public void testGetUserServiceNotNull() {
        Assert.assertNotNull(ServiceFactory.getInstance().getUserService());
    }
}
