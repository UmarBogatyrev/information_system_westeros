package ru.itmo.WesterosTax.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import ru.itmo.WesterosTax.models.Census;
import ru.itmo.WesterosTax.models.CensusDistrict;
import ru.itmo.WesterosTax.models.CensusRegion;
import ru.itmo.WesterosTax.repositories.CensusDistrictRepository;
import ru.itmo.WesterosTax.repositories.CensusRegionRepository;
import ru.itmo.WesterosTax.repositories.CensusRepository;
import ru.itmo.WesterosTax.repositories.RegionRepository;
import ru.itmo.WesterosTax.repositories.UserRepository;

@ContextConfiguration(classes = {CensusController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CensusControllerTest {
  @Autowired
  private CensusController censusController;

  @MockBean
  private CensusDistrictRepository censusDistrictRepository;

  @MockBean
  private CensusRegionRepository censusRegionRepository;

  @MockBean
  private CensusRepository censusRepository;

  @MockBean
  private RegionRepository regionRepository;

  @MockBean
  private UserRepository userRepository;

  /**
   * Method under test:
   * {@link CensusController#createCensus(Census, BindingResult)}
   */
  @Test
  public void testCreateCensus() throws Exception {
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  /**
   * Method under test: {@link CensusController#createCensusDistrict(Census)}
   */
  @Test
  public void testCreateCensusDistrict() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/createDistrict");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link CensusController#createCensusRegion(CensusRegion)}
   */
  @Test
  public void testCreateCensusRegion() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/createRegion");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link CensusController#deleteCensus(Census)}
   */
  @Test
  public void testDeleteCensus() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/delete");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link CensusController#finishCensus(Census)}
   */
  @Test
  public void testFinishCensus() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/finish");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test:
   * {@link CensusController#submitCensusDistrict(CensusDistrict)}
   */
  @Test
  public void testSubmitCensusDistrict() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/submitDistrict");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test:
   * {@link CensusController#submitCensusRegion(CensusRegion, Census)}
   */
  @Test
  public void testSubmitCensusRegion() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/submitRegion");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test:
   * {@link CensusController#unsubmitCensusDistrict(CensusDistrict)}
   */
  @Test
  public void testUnsubmitCensusDistrict() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/unsubmitDistrict");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test:
   * {@link CensusController#unsubmitCensusRegion(CensusRegion)}
   */
  @Test
  public void testUnsubmitCensusRegion() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/census/unsubmitRegion");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(censusController)
            .build()
            .perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }
}
