package com.my.proxy.searchproxy;

import com.my.proxy.reqbody.SearchTerm;
import com.my.proxy.resbody.SearchResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.class)
public class ProxyControllerTests {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Environment env;

    @InjectMocks
    private ProxyController proxyController = new ProxyController();
    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }
    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        Mockito.when(env.getProperty("search.apikey")).thenReturn("testApi");
        Mockito.when(env.getProperty("search.engineId")).thenReturn("testEngine");
        Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.eq(String.class))).thenReturn("{items: []}");
        SearchTerm term = new SearchTerm();
        term.setSearchterm("test");
        ArrayList<SearchResult> results = proxyController.searchProxy(term);
        Assert.assertEquals(0, results.size());
    }

}
