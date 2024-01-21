package ru.itmo.WesterosTax.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ru.itmo.WesterosTax.repositories.CensusDistrictRepository;
import ru.itmo.WesterosTax.repositories.CensusRegionRepository;
import ru.itmo.WesterosTax.repositories.CensusRepository;
import ru.itmo.WesterosTax.repositories.RegionRepository;
import ru.itmo.WesterosTax.repositories.TaxDistrictRepository;
import ru.itmo.WesterosTax.repositories.TaxRegionRepository;
import ru.itmo.WesterosTax.repositories.TaxRepository;
import ru.itmo.WesterosTax.repositories.TaxTypeRepository;
import ru.itmo.WesterosTax.repositories.UserRepository;

@ContextConfiguration(classes = {PageController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PageTest {
  @MockBean
  private CensusDistrictRepository censusDistrictRepository;

  @MockBean
  private CensusRegionRepository censusRegionRepository;

  @MockBean
  private CensusRepository censusRepository;

  @Autowired
  private PageController pageController;

  @MockBean
  private RegionRepository regionRepository;

  @MockBean
  private TaxDistrictRepository taxDistrictRepository;

  @MockBean
  private TaxRegionRepository taxRegionRepository;

  @MockBean
  private TaxRepository taxRepository;

  @MockBean
  private TaxTypeRepository taxTypeRepository;

  @MockBean
  private UserRepository userRepository;
  /**
   * Method under test: {@link PageController#landownerAnalytics(Model)}
   */
  @Test
  public void testLandownerAnalytics() throws Exception {
    // Arrange
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(pageController).build().perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
