package at.rtr.rmbt.service.impl;

import at.rtr.rmbt.TestConstants;
import at.rtr.rmbt.model.RtrClient;
import at.rtr.rmbt.request.SignalRequest;
import at.rtr.rmbt.constant.HeaderConstants;
import at.rtr.rmbt.repository.ClientRepository;
import at.rtr.rmbt.repository.RTRProviderRepository;
import at.rtr.rmbt.repository.TestRepository;
import at.rtr.rmbt.response.SignalResponse;
import at.rtr.rmbt.service.SignalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static at.rtr.rmbt.constant.URIConstants.SIGNAL_RESULT;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SignalServiceImplTest {
    private SignalService signalService;

    @MockBean
    private TestRepository testRepository;
    @MockBean
    private RTRProviderRepository providerRepository;
    @MockBean
    private UUIDGenerator uuidGenerator;
    @MockBean
    private ClientRepository clientRepository;

    @Mock
    private SignalRequest signalRequest;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private at.rtr.rmbt.model.Test savedTest;
    @Mock
    private RtrClient rtrClient;

    @Before
    public void setUp() {
        signalService = new SignalServiceImpl(testRepository, providerRepository, uuidGenerator, clientRepository);
    }

    @Test
    public void registerSignal_whenCommonRequest_expectSignalResponse() {
        var expectedResponse = getRegisterSignalResponse();
        when(httpServletRequest.getRemoteAddr()).thenReturn(TestConstants.DEFAULT_IP);
        when(httpServletRequest.getHeader(HeaderConstants.URL)).thenReturn(TestConstants.DEFAULT_URL);
        when(signalRequest.getUuid()).thenReturn(TestConstants.DEFAULT_CLIENT_UUID);
        when(signalRequest.getTimezone()).thenReturn(TestConstants.DEFAULT_TIMEZONE);
        when(clientRepository.findByUuid(TestConstants.DEFAULT_CLIENT_UUID)).thenReturn(Optional.of(rtrClient));
        when(providerRepository.getProviderNameByTestId(TestConstants.DEFAULT_UID)).thenReturn(TestConstants.DEFAULT_PROVIDER);
        when(testRepository.save(any())).thenReturn(savedTest);
        when(savedTest.getUid()).thenReturn(TestConstants.DEFAULT_UID);
        when(savedTest.getUuid()).thenReturn(TestConstants.DEFAULT_UUID);

        var actualResponse = signalService.registerSignal(signalRequest, httpServletRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    private SignalResponse getRegisterSignalResponse() {
        return SignalResponse.builder()
                .resultUrl(String.join(TestConstants.DEFAULT_URL, SIGNAL_RESULT))
                .clientRemoteIp(TestConstants.DEFAULT_IP)
                .provider(TestConstants.DEFAULT_PROVIDER)
                .testUUID(TestConstants.DEFAULT_UUID)
                .build();
    }
}