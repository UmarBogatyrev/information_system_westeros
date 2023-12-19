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
import ru.itmo.WesterosTax.models.Tax;
import ru.itmo.WesterosTax.models.TaxDistrict;
import ru.itmo.WesterosTax.models.TaxRegion;
import ru.itmo.WesterosTax.repositories.RegionRepository;
import ru.itmo.WesterosTax.repositories.TaxDistrictRepository;
import ru.itmo.WesterosTax.repositories.TaxRegionRepository;
import ru.itmo.WesterosTax.repositories.TaxRepository;
import ru.itmo.WesterosTax.repositories.UserRepository;

@ContextConfiguration(classes = {TaxController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxControllerTest {
  @MockBean
  private RegionRepository regionRepository;

  @Autowired
  private TaxController taxController;

  @MockBean
  private TaxDistrictRepository taxDistrictRepository;

  @MockBean
  private TaxRegionRepository taxRegionRepository;

  @MockBean
  private TaxRepository taxRepository;

  @MockBean
  private UserRepository userRepository;

  /**
   * Method under test: {@link TaxController#createTax(Tax, BindingResult)}
   */
  @Test
  public void testCreateTax() throws Exception {
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  /**
   * Method under test: {@link TaxController#createTaxDistrict(Tax)}
   */
  @Test
  public void testCreateTaxDistrict() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/createDistrict");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#createTaxRegion(TaxRegion)}
   */
  @Test
  public void testCreateTaxRegion() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/createRegion");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#deleteTax(Tax)}
   */
  @Test
  public void testDeleteTax() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/delete");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#finishTax(Tax)}
   */
  @Test
  public void testFinishTax() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/finish");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#submitTaxDistrict(TaxDistrict)}
   */
  @Test
  public void testSubmitTaxDistrict() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/submitDistrict");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#submitTaxRegion(TaxRegion, Tax)}
   */
  @Test
  public void testSubmitTaxRegion() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/submitRegion");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#unsubmitTaxDistrict(TaxDistrict)}
   */
  @Test
  public void testUnsubmitTaxDistrict() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/unsubmitDistrict");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link TaxController#unsubmitTaxRegion(TaxRegion)}
   */
  @Test
  public void testUnsubmitTaxRegion() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tax/unsubmitRegion");
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taxController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }
}
