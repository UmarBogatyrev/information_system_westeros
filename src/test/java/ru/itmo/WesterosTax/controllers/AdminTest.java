package ru.itmo.WesterosTax.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.itmo.WesterosTax.models.District;
import ru.itmo.WesterosTax.models.Region;
import ru.itmo.WesterosTax.models.Role;
import ru.itmo.WesterosTax.models.User;
import ru.itmo.WesterosTax.repositories.DistrictRepository;
import ru.itmo.WesterosTax.repositories.RegionRepository;
import ru.itmo.WesterosTax.repositories.UserRepository;

@ContextConfiguration(classes = {AdminController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private DistrictRepository districtRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RegionRepository regionRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link AdminController#createDistrict(District, BindingResult, Model)}
     */
    @Test
    public void testCreateDistrict() throws Exception {
        // Arrange
        when(regionRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/createDistrict");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("district", "regions"))
                .andExpect(MockMvcResultMatchers.view().name("admin/District"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/District"));
    }

    /**
     * Method under test:
     * {@link AdminController#createRegion(Region, BindingResult, Model)}
     */
    @Test
    public void testCreateRegion() throws Exception {
        // Arrange
        when(userRepository.findByRolesContains(Mockito.<Role>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/createRegion");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lords", "region"))
                .andExpect(MockMvcResultMatchers.view().name("admin/Region"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/Region"));
    }

    /**
     * Method under test: {@link AdminController#district(District, Model)}
     */
    @Test
    public void testDistrict() throws Exception {
        // Arrange
        when(regionRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/district");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("district", "regions"))
                .andExpect(MockMvcResultMatchers.view().name("admin/District"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/District"));
    }

    /**
     * Method under test: {@link AdminController#lord(User)}
     */
    @Test
    public void testLord() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/lord");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("admin/Lord"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/Lord"));
    }

    /**
     * Method under test: {@link AdminController#lord(User)}
     */
    @Test
    public void testLord2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/lord");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("admin/Lord"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/Lord"));
    }

    /**
     * Method under test: {@link AdminController#region(Region, Model)}
     */
    @Test
    public void testRegion() throws Exception {
        // Arrange
        when(userRepository.findByRolesContains(Mockito.<Role>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/region");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lords", "region"))
                .andExpect(MockMvcResultMatchers.view().name("admin/Region"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/Region"));
    }

    /**
     * Method under test:
     * {@link AdminController#registerLord(User, BindingResult, Model)}
     */
    @Test
    public void testRegisterLord() throws Exception {
        // Arrange
        District district = new District();
        district.setHouseholds(new ArrayList<>());
        district.setId(1L);
        district.setName("Name");
        district.setRegion(new Region());
        district.setTotalCattleHeadcount(3);
        district.setTotalHouseholds(1);
        district.setTotalIncome(10.0d);
        district.setTotalResidents(1);
        district.setTotalTaxesPaid(10.0d);

        User landowner = new User();
        landowner.setActive(true);
        landowner.setDistrict(new District());
        landowner.setId(1L);
        landowner.setLandowner(new User());
        landowner.setLord(new User());
        landowner.setPassword("iloveyou");
        landowner.setRegion(new Region());
        landowner.setRoles(new HashSet<>());
        landowner.setUsername("janedoe");

        User lord = new User();
        lord.setActive(true);
        lord.setDistrict(new District());
        lord.setId(1L);
        lord.setLandowner(new User());
        lord.setLord(new User());
        lord.setPassword("iloveyou");
        lord.setRegion(new Region());
        lord.setRoles(new HashSet<>());
        lord.setUsername("janedoe");

        Region region = new Region();
        region.setDistricts(new ArrayList<>());
        region.setId(1L);
        region.setLord(new User());
        region.setName("Name");
        region.setTotalCattleHeadcount(3);
        region.setTotalHouseholds(1);
        region.setTotalIncome(10.0d);
        region.setTotalResidents(1);
        region.setTotalTaxesPaid(10.0d);

        User lord2 = new User();
        lord2.setActive(true);
        lord2.setDistrict(district);
        lord2.setId(1L);
        lord2.setLandowner(landowner);
        lord2.setLord(lord);
        lord2.setPassword("iloveyou");
        lord2.setRegion(region);
        lord2.setRoles(new HashSet<>());
        lord2.setUsername("janedoe");

        Region region2 = new Region();
        region2.setDistricts(new ArrayList<>());
        region2.setId(1L);
        region2.setLord(lord2);
        region2.setName("Name");
        region2.setTotalCattleHeadcount(3);
        region2.setTotalHouseholds(1);
        region2.setTotalIncome(10.0d);
        region2.setTotalResidents(1);
        region2.setTotalTaxesPaid(10.0d);

        District district2 = new District();
        district2.setHouseholds(new ArrayList<>());
        district2.setId(1L);
        district2.setName("Name");
        district2.setRegion(region2);
        district2.setTotalCattleHeadcount(3);
        district2.setTotalHouseholds(1);
        district2.setTotalIncome(10.0d);
        district2.setTotalResidents(1);
        district2.setTotalTaxesPaid(10.0d);

        User lord3 = new User();
        lord3.setActive(true);
        lord3.setDistrict(new District());
        lord3.setId(1L);
        lord3.setLandowner(new User());
        lord3.setLord(new User());
        lord3.setPassword("iloveyou");
        lord3.setRegion(new Region());
        lord3.setRoles(new HashSet<>());
        lord3.setUsername("janedoe");

        Region region3 = new Region();
        region3.setDistricts(new ArrayList<>());
        region3.setId(1L);
        region3.setLord(lord3);
        region3.setName("Name");
        region3.setTotalCattleHeadcount(3);
        region3.setTotalHouseholds(1);
        region3.setTotalIncome(10.0d);
        region3.setTotalResidents(1);
        region3.setTotalTaxesPaid(10.0d);

        District district3 = new District();
        district3.setHouseholds(new ArrayList<>());
        district3.setId(1L);
        district3.setName("Name");
        district3.setRegion(region3);
        district3.setTotalCattleHeadcount(3);
        district3.setTotalHouseholds(1);
        district3.setTotalIncome(10.0d);
        district3.setTotalResidents(1);
        district3.setTotalTaxesPaid(10.0d);

        Region region4 = new Region();
        region4.setDistricts(new ArrayList<>());
        region4.setId(1L);
        region4.setLord(new User());
        region4.setName("Name");
        region4.setTotalCattleHeadcount(3);
        region4.setTotalHouseholds(1);
        region4.setTotalIncome(10.0d);
        region4.setTotalResidents(1);
        region4.setTotalTaxesPaid(10.0d);

        District district4 = new District();
        district4.setHouseholds(new ArrayList<>());
        district4.setId(1L);
        district4.setName("Name");
        district4.setRegion(region4);
        district4.setTotalCattleHeadcount(3);
        district4.setTotalHouseholds(1);
        district4.setTotalIncome(10.0d);
        district4.setTotalResidents(1);
        district4.setTotalTaxesPaid(10.0d);

        District district5 = new District();
        district5.setHouseholds(new ArrayList<>());
        district5.setId(1L);
        district5.setName("Name");
        district5.setRegion(new Region());
        district5.setTotalCattleHeadcount(3);
        district5.setTotalHouseholds(1);
        district5.setTotalIncome(10.0d);
        district5.setTotalResidents(1);
        district5.setTotalTaxesPaid(10.0d);

        User landowner2 = new User();
        landowner2.setActive(true);
        landowner2.setDistrict(new District());
        landowner2.setId(1L);
        landowner2.setLandowner(new User());
        landowner2.setLord(new User());
        landowner2.setPassword("iloveyou");
        landowner2.setRegion(new Region());
        landowner2.setRoles(new HashSet<>());
        landowner2.setUsername("janedoe");

        User lord4 = new User();
        lord4.setActive(true);
        lord4.setDistrict(new District());
        lord4.setId(1L);
        lord4.setLandowner(new User());
        lord4.setLord(new User());
        lord4.setPassword("iloveyou");
        lord4.setRegion(new Region());
        lord4.setRoles(new HashSet<>());
        lord4.setUsername("janedoe");

        Region region5 = new Region();
        region5.setDistricts(new ArrayList<>());
        region5.setId(1L);
        region5.setLord(new User());
        region5.setName("Name");
        region5.setTotalCattleHeadcount(3);
        region5.setTotalHouseholds(1);
        region5.setTotalIncome(10.0d);
        region5.setTotalResidents(1);
        region5.setTotalTaxesPaid(10.0d);

        User landowner3 = new User();
        landowner3.setActive(true);
        landowner3.setDistrict(district5);
        landowner3.setId(1L);
        landowner3.setLandowner(landowner2);
        landowner3.setLord(lord4);
        landowner3.setPassword("iloveyou");
        landowner3.setRegion(region5);
        landowner3.setRoles(new HashSet<>());
        landowner3.setUsername("janedoe");

        District district6 = new District();
        district6.setHouseholds(new ArrayList<>());
        district6.setId(1L);
        district6.setName("Name");
        district6.setRegion(new Region());
        district6.setTotalCattleHeadcount(3);
        district6.setTotalHouseholds(1);
        district6.setTotalIncome(10.0d);
        district6.setTotalResidents(1);
        district6.setTotalTaxesPaid(10.0d);

        User landowner4 = new User();
        landowner4.setActive(true);
        landowner4.setDistrict(new District());
        landowner4.setId(1L);
        landowner4.setLandowner(new User());
        landowner4.setLord(new User());
        landowner4.setPassword("iloveyou");
        landowner4.setRegion(new Region());
        landowner4.setRoles(new HashSet<>());
        landowner4.setUsername("janedoe");

        User lord5 = new User();
        lord5.setActive(true);
        lord5.setDistrict(new District());
        lord5.setId(1L);
        lord5.setLandowner(new User());
        lord5.setLord(new User());
        lord5.setPassword("iloveyou");
        lord5.setRegion(new Region());
        lord5.setRoles(new HashSet<>());
        lord5.setUsername("janedoe");

        Region region6 = new Region();
        region6.setDistricts(new ArrayList<>());
        region6.setId(1L);
        region6.setLord(new User());
        region6.setName("Name");
        region6.setTotalCattleHeadcount(3);
        region6.setTotalHouseholds(1);
        region6.setTotalIncome(10.0d);
        region6.setTotalResidents(1);
        region6.setTotalTaxesPaid(10.0d);

        User lord6 = new User();
        lord6.setActive(true);
        lord6.setDistrict(district6);
        lord6.setId(1L);
        lord6.setLandowner(landowner4);
        lord6.setLord(lord5);
        lord6.setPassword("iloveyou");
        lord6.setRegion(region6);
        lord6.setRoles(new HashSet<>());
        lord6.setUsername("janedoe");

        User lord7 = new User();
        lord7.setActive(true);
        lord7.setDistrict(new District());
        lord7.setId(1L);
        lord7.setLandowner(new User());
        lord7.setLord(new User());
        lord7.setPassword("iloveyou");
        lord7.setRegion(new Region());
        lord7.setRoles(new HashSet<>());
        lord7.setUsername("janedoe");

        Region region7 = new Region();
        region7.setDistricts(new ArrayList<>());
        region7.setId(1L);
        region7.setLord(lord7);
        region7.setName("Name");
        region7.setTotalCattleHeadcount(3);
        region7.setTotalHouseholds(1);
        region7.setTotalIncome(10.0d);
        region7.setTotalResidents(1);
        region7.setTotalTaxesPaid(10.0d);

        User landowner5 = new User();
        landowner5.setActive(true);
        landowner5.setDistrict(district4);
        landowner5.setId(1L);
        landowner5.setLandowner(landowner3);
        landowner5.setLord(lord6);
        landowner5.setPassword("iloveyou");
        landowner5.setRegion(region7);
        landowner5.setRoles(new HashSet<>());
        landowner5.setUsername("janedoe");

        Region region8 = new Region();
        region8.setDistricts(new ArrayList<>());
        region8.setId(1L);
        region8.setLord(new User());
        region8.setName("Name");
        region8.setTotalCattleHeadcount(3);
        region8.setTotalHouseholds(1);
        region8.setTotalIncome(10.0d);
        region8.setTotalResidents(1);
        region8.setTotalTaxesPaid(10.0d);

        District district7 = new District();
        district7.setHouseholds(new ArrayList<>());
        district7.setId(1L);
        district7.setName("Name");
        district7.setRegion(region8);
        district7.setTotalCattleHeadcount(3);
        district7.setTotalHouseholds(1);
        district7.setTotalIncome(10.0d);
        district7.setTotalResidents(1);
        district7.setTotalTaxesPaid(10.0d);

        District district8 = new District();
        district8.setHouseholds(new ArrayList<>());
        district8.setId(1L);
        district8.setName("Name");
        district8.setRegion(new Region());
        district8.setTotalCattleHeadcount(3);
        district8.setTotalHouseholds(1);
        district8.setTotalIncome(10.0d);
        district8.setTotalResidents(1);
        district8.setTotalTaxesPaid(10.0d);

        User landowner6 = new User();
        landowner6.setActive(true);
        landowner6.setDistrict(new District());
        landowner6.setId(1L);
        landowner6.setLandowner(new User());
        landowner6.setLord(new User());
        landowner6.setPassword("iloveyou");
        landowner6.setRegion(new Region());
        landowner6.setRoles(new HashSet<>());
        landowner6.setUsername("janedoe");

        User lord8 = new User();
        lord8.setActive(true);
        lord8.setDistrict(new District());
        lord8.setId(1L);
        lord8.setLandowner(new User());
        lord8.setLord(new User());
        lord8.setPassword("iloveyou");
        lord8.setRegion(new Region());
        lord8.setRoles(new HashSet<>());
        lord8.setUsername("janedoe");

        Region region9 = new Region();
        region9.setDistricts(new ArrayList<>());
        region9.setId(1L);
        region9.setLord(new User());
        region9.setName("Name");
        region9.setTotalCattleHeadcount(3);
        region9.setTotalHouseholds(1);
        region9.setTotalIncome(10.0d);
        region9.setTotalResidents(1);
        region9.setTotalTaxesPaid(10.0d);

        User landowner7 = new User();
        landowner7.setActive(true);
        landowner7.setDistrict(district8);
        landowner7.setId(1L);
        landowner7.setLandowner(landowner6);
        landowner7.setLord(lord8);
        landowner7.setPassword("iloveyou");
        landowner7.setRegion(region9);
        landowner7.setRoles(new HashSet<>());
        landowner7.setUsername("janedoe");

        District district9 = new District();
        district9.setHouseholds(new ArrayList<>());
        district9.setId(1L);
        district9.setName("Name");
        district9.setRegion(new Region());
        district9.setTotalCattleHeadcount(3);
        district9.setTotalHouseholds(1);
        district9.setTotalIncome(10.0d);
        district9.setTotalResidents(1);
        district9.setTotalTaxesPaid(10.0d);

        User landowner8 = new User();
        landowner8.setActive(true);
        landowner8.setDistrict(new District());
        landowner8.setId(1L);
        landowner8.setLandowner(new User());
        landowner8.setLord(new User());
        landowner8.setPassword("iloveyou");
        landowner8.setRegion(new Region());
        landowner8.setRoles(new HashSet<>());
        landowner8.setUsername("janedoe");

        User lord9 = new User();
        lord9.setActive(true);
        lord9.setDistrict(new District());
        lord9.setId(1L);
        lord9.setLandowner(new User());
        lord9.setLord(new User());
        lord9.setPassword("iloveyou");
        lord9.setRegion(new Region());
        lord9.setRoles(new HashSet<>());
        lord9.setUsername("janedoe");

        Region region10 = new Region();
        region10.setDistricts(new ArrayList<>());
        region10.setId(1L);
        region10.setLord(new User());
        region10.setName("Name");
        region10.setTotalCattleHeadcount(3);
        region10.setTotalHouseholds(1);
        region10.setTotalIncome(10.0d);
        region10.setTotalResidents(1);
        region10.setTotalTaxesPaid(10.0d);

        User lord10 = new User();
        lord10.setActive(true);
        lord10.setDistrict(district9);
        lord10.setId(1L);
        lord10.setLandowner(landowner8);
        lord10.setLord(lord9);
        lord10.setPassword("iloveyou");
        lord10.setRegion(region10);
        lord10.setRoles(new HashSet<>());
        lord10.setUsername("janedoe");

        User lord11 = new User();
        lord11.setActive(true);
        lord11.setDistrict(new District());
        lord11.setId(1L);
        lord11.setLandowner(new User());
        lord11.setLord(new User());
        lord11.setPassword("iloveyou");
        lord11.setRegion(new Region());
        lord11.setRoles(new HashSet<>());
        lord11.setUsername("janedoe");

        Region region11 = new Region();
        region11.setDistricts(new ArrayList<>());
        region11.setId(1L);
        region11.setLord(lord11);
        region11.setName("Name");
        region11.setTotalCattleHeadcount(3);
        region11.setTotalHouseholds(1);
        region11.setTotalIncome(10.0d);
        region11.setTotalResidents(1);
        region11.setTotalTaxesPaid(10.0d);

        User lord12 = new User();
        lord12.setActive(true);
        lord12.setDistrict(district7);
        lord12.setId(1L);
        lord12.setLandowner(landowner7);
        lord12.setLord(lord10);
        lord12.setPassword("iloveyou");
        lord12.setRegion(region11);
        lord12.setRoles(new HashSet<>());
        lord12.setUsername("janedoe");

        District district10 = new District();
        district10.setHouseholds(new ArrayList<>());
        district10.setId(1L);
        district10.setName("Name");
        district10.setRegion(new Region());
        district10.setTotalCattleHeadcount(3);
        district10.setTotalHouseholds(1);
        district10.setTotalIncome(10.0d);
        district10.setTotalResidents(1);
        district10.setTotalTaxesPaid(10.0d);

        User landowner9 = new User();
        landowner9.setActive(true);
        landowner9.setDistrict(new District());
        landowner9.setId(1L);
        landowner9.setLandowner(new User());
        landowner9.setLord(new User());
        landowner9.setPassword("iloveyou");
        landowner9.setRegion(new Region());
        landowner9.setRoles(new HashSet<>());
        landowner9.setUsername("janedoe");

        User lord13 = new User();
        lord13.setActive(true);
        lord13.setDistrict(new District());
        lord13.setId(1L);
        lord13.setLandowner(new User());
        lord13.setLord(new User());
        lord13.setPassword("iloveyou");
        lord13.setRegion(new Region());
        lord13.setRoles(new HashSet<>());
        lord13.setUsername("janedoe");

        Region region12 = new Region();
        region12.setDistricts(new ArrayList<>());
        region12.setId(1L);
        region12.setLord(new User());
        region12.setName("Name");
        region12.setTotalCattleHeadcount(3);
        region12.setTotalHouseholds(1);
        region12.setTotalIncome(10.0d);
        region12.setTotalResidents(1);
        region12.setTotalTaxesPaid(10.0d);

        User lord14 = new User();
        lord14.setActive(true);
        lord14.setDistrict(district10);
        lord14.setId(1L);
        lord14.setLandowner(landowner9);
        lord14.setLord(lord13);
        lord14.setPassword("iloveyou");
        lord14.setRegion(region12);
        lord14.setRoles(new HashSet<>());
        lord14.setUsername("janedoe");

        Region region13 = new Region();
        region13.setDistricts(new ArrayList<>());
        region13.setId(1L);
        region13.setLord(lord14);
        region13.setName("Name");
        region13.setTotalCattleHeadcount(3);
        region13.setTotalHouseholds(1);
        region13.setTotalIncome(10.0d);
        region13.setTotalResidents(1);
        region13.setTotalTaxesPaid(10.0d);

        User landowner10 = new User();
        landowner10.setActive(true);
        landowner10.setDistrict(district3);
        landowner10.setId(1L);
        landowner10.setLandowner(landowner5);
        landowner10.setLord(lord12);
        landowner10.setPassword("iloveyou");
        landowner10.setRegion(region13);
        landowner10.setRoles(new HashSet<>());
        landowner10.setUsername("janedoe");

        User lord15 = new User();
        lord15.setActive(true);
        lord15.setDistrict(new District());
        lord15.setId(1L);
        lord15.setLandowner(new User());
        lord15.setLord(new User());
        lord15.setPassword("iloveyou");
        lord15.setRegion(new Region());
        lord15.setRoles(new HashSet<>());
        lord15.setUsername("janedoe");

        Region region14 = new Region();
        region14.setDistricts(new ArrayList<>());
        region14.setId(1L);
        region14.setLord(lord15);
        region14.setName("Name");
        region14.setTotalCattleHeadcount(3);
        region14.setTotalHouseholds(1);
        region14.setTotalIncome(10.0d);
        region14.setTotalResidents(1);
        region14.setTotalTaxesPaid(10.0d);

        District district11 = new District();
        district11.setHouseholds(new ArrayList<>());
        district11.setId(1L);
        district11.setName("Name");
        district11.setRegion(region14);
        district11.setTotalCattleHeadcount(3);
        district11.setTotalHouseholds(1);
        district11.setTotalIncome(10.0d);
        district11.setTotalResidents(1);
        district11.setTotalTaxesPaid(10.0d);

        Region region15 = new Region();
        region15.setDistricts(new ArrayList<>());
        region15.setId(1L);
        region15.setLord(new User());
        region15.setName("Name");
        region15.setTotalCattleHeadcount(3);
        region15.setTotalHouseholds(1);
        region15.setTotalIncome(10.0d);
        region15.setTotalResidents(1);
        region15.setTotalTaxesPaid(10.0d);

        District district12 = new District();
        district12.setHouseholds(new ArrayList<>());
        district12.setId(1L);
        district12.setName("Name");
        district12.setRegion(region15);
        district12.setTotalCattleHeadcount(3);
        district12.setTotalHouseholds(1);
        district12.setTotalIncome(10.0d);
        district12.setTotalResidents(1);
        district12.setTotalTaxesPaid(10.0d);

        District district13 = new District();
        district13.setHouseholds(new ArrayList<>());
        district13.setId(1L);
        district13.setName("Name");
        district13.setRegion(new Region());
        district13.setTotalCattleHeadcount(3);
        district13.setTotalHouseholds(1);
        district13.setTotalIncome(10.0d);
        district13.setTotalResidents(1);
        district13.setTotalTaxesPaid(10.0d);

        User landowner11 = new User();
        landowner11.setActive(true);
        landowner11.setDistrict(new District());
        landowner11.setId(1L);
        landowner11.setLandowner(new User());
        landowner11.setLord(new User());
        landowner11.setPassword("iloveyou");
        landowner11.setRegion(new Region());
        landowner11.setRoles(new HashSet<>());
        landowner11.setUsername("janedoe");

        User lord16 = new User();
        lord16.setActive(true);
        lord16.setDistrict(new District());
        lord16.setId(1L);
        lord16.setLandowner(new User());
        lord16.setLord(new User());
        lord16.setPassword("iloveyou");
        lord16.setRegion(new Region());
        lord16.setRoles(new HashSet<>());
        lord16.setUsername("janedoe");

        Region region16 = new Region();
        region16.setDistricts(new ArrayList<>());
        region16.setId(1L);
        region16.setLord(new User());
        region16.setName("Name");
        region16.setTotalCattleHeadcount(3);
        region16.setTotalHouseholds(1);
        region16.setTotalIncome(10.0d);
        region16.setTotalResidents(1);
        region16.setTotalTaxesPaid(10.0d);

        User landowner12 = new User();
        landowner12.setActive(true);
        landowner12.setDistrict(district13);
        landowner12.setId(1L);
        landowner12.setLandowner(landowner11);
        landowner12.setLord(lord16);
        landowner12.setPassword("iloveyou");
        landowner12.setRegion(region16);
        landowner12.setRoles(new HashSet<>());
        landowner12.setUsername("janedoe");

        District district14 = new District();
        district14.setHouseholds(new ArrayList<>());
        district14.setId(1L);
        district14.setName("Name");
        district14.setRegion(new Region());
        district14.setTotalCattleHeadcount(3);
        district14.setTotalHouseholds(1);
        district14.setTotalIncome(10.0d);
        district14.setTotalResidents(1);
        district14.setTotalTaxesPaid(10.0d);

        User landowner13 = new User();
        landowner13.setActive(true);
        landowner13.setDistrict(new District());
        landowner13.setId(1L);
        landowner13.setLandowner(new User());
        landowner13.setLord(new User());
        landowner13.setPassword("iloveyou");
        landowner13.setRegion(new Region());
        landowner13.setRoles(new HashSet<>());
        landowner13.setUsername("janedoe");

        User lord17 = new User();
        lord17.setActive(true);
        lord17.setDistrict(new District());
        lord17.setId(1L);
        lord17.setLandowner(new User());
        lord17.setLord(new User());
        lord17.setPassword("iloveyou");
        lord17.setRegion(new Region());
        lord17.setRoles(new HashSet<>());
        lord17.setUsername("janedoe");

        Region region17 = new Region();
        region17.setDistricts(new ArrayList<>());
        region17.setId(1L);
        region17.setLord(new User());
        region17.setName("Name");
        region17.setTotalCattleHeadcount(3);
        region17.setTotalHouseholds(1);
        region17.setTotalIncome(10.0d);
        region17.setTotalResidents(1);
        region17.setTotalTaxesPaid(10.0d);

        User lord18 = new User();
        lord18.setActive(true);
        lord18.setDistrict(district14);
        lord18.setId(1L);
        lord18.setLandowner(landowner13);
        lord18.setLord(lord17);
        lord18.setPassword("iloveyou");
        lord18.setRegion(region17);
        lord18.setRoles(new HashSet<>());
        lord18.setUsername("janedoe");

        User lord19 = new User();
        lord19.setActive(true);
        lord19.setDistrict(new District());
        lord19.setId(1L);
        lord19.setLandowner(new User());
        lord19.setLord(new User());
        lord19.setPassword("iloveyou");
        lord19.setRegion(new Region());
        lord19.setRoles(new HashSet<>());
        lord19.setUsername("janedoe");

        Region region18 = new Region();
        region18.setDistricts(new ArrayList<>());
        region18.setId(1L);
        region18.setLord(lord19);
        region18.setName("Name");
        region18.setTotalCattleHeadcount(3);
        region18.setTotalHouseholds(1);
        region18.setTotalIncome(10.0d);
        region18.setTotalResidents(1);
        region18.setTotalTaxesPaid(10.0d);

        User landowner14 = new User();
        landowner14.setActive(true);
        landowner14.setDistrict(district12);
        landowner14.setId(1L);
        landowner14.setLandowner(landowner12);
        landowner14.setLord(lord18);
        landowner14.setPassword("iloveyou");
        landowner14.setRegion(region18);
        landowner14.setRoles(new HashSet<>());
        landowner14.setUsername("janedoe");

        Region region19 = new Region();
        region19.setDistricts(new ArrayList<>());
        region19.setId(1L);
        region19.setLord(new User());
        region19.setName("Name");
        region19.setTotalCattleHeadcount(3);
        region19.setTotalHouseholds(1);
        region19.setTotalIncome(10.0d);
        region19.setTotalResidents(1);
        region19.setTotalTaxesPaid(10.0d);

        District district15 = new District();
        district15.setHouseholds(new ArrayList<>());
        district15.setId(1L);
        district15.setName("Name");
        district15.setRegion(region19);
        district15.setTotalCattleHeadcount(3);
        district15.setTotalHouseholds(1);
        district15.setTotalIncome(10.0d);
        district15.setTotalResidents(1);
        district15.setTotalTaxesPaid(10.0d);

        District district16 = new District();
        district16.setHouseholds(new ArrayList<>());
        district16.setId(1L);
        district16.setName("Name");
        district16.setRegion(new Region());
        district16.setTotalCattleHeadcount(3);
        district16.setTotalHouseholds(1);
        district16.setTotalIncome(10.0d);
        district16.setTotalResidents(1);
        district16.setTotalTaxesPaid(10.0d);

        User landowner15 = new User();
        landowner15.setActive(true);
        landowner15.setDistrict(new District());
        landowner15.setId(1L);
        landowner15.setLandowner(new User());
        landowner15.setLord(new User());
        landowner15.setPassword("iloveyou");
        landowner15.setRegion(new Region());
        landowner15.setRoles(new HashSet<>());
        landowner15.setUsername("janedoe");

        User lord20 = new User();
        lord20.setActive(true);
        lord20.setDistrict(new District());
        lord20.setId(1L);
        lord20.setLandowner(new User());
        lord20.setLord(new User());
        lord20.setPassword("iloveyou");
        lord20.setRegion(new Region());
        lord20.setRoles(new HashSet<>());
        lord20.setUsername("janedoe");

        Region region20 = new Region();
        region20.setDistricts(new ArrayList<>());
        region20.setId(1L);
        region20.setLord(new User());
        region20.setName("Name");
        region20.setTotalCattleHeadcount(3);
        region20.setTotalHouseholds(1);
        region20.setTotalIncome(10.0d);
        region20.setTotalResidents(1);
        region20.setTotalTaxesPaid(10.0d);

        User landowner16 = new User();
        landowner16.setActive(true);
        landowner16.setDistrict(district16);
        landowner16.setId(1L);
        landowner16.setLandowner(landowner15);
        landowner16.setLord(lord20);
        landowner16.setPassword("iloveyou");
        landowner16.setRegion(region20);
        landowner16.setRoles(new HashSet<>());
        landowner16.setUsername("janedoe");

        District district17 = new District();
        district17.setHouseholds(new ArrayList<>());
        district17.setId(1L);
        district17.setName("Name");
        district17.setRegion(new Region());
        district17.setTotalCattleHeadcount(3);
        district17.setTotalHouseholds(1);
        district17.setTotalIncome(10.0d);
        district17.setTotalResidents(1);
        district17.setTotalTaxesPaid(10.0d);

        User landowner17 = new User();
        landowner17.setActive(true);
        landowner17.setDistrict(new District());
        landowner17.setId(1L);
        landowner17.setLandowner(new User());
        landowner17.setLord(new User());
        landowner17.setPassword("iloveyou");
        landowner17.setRegion(new Region());
        landowner17.setRoles(new HashSet<>());
        landowner17.setUsername("janedoe");

        User lord21 = new User();
        lord21.setActive(true);
        lord21.setDistrict(new District());
        lord21.setId(1L);
        lord21.setLandowner(new User());
        lord21.setLord(new User());
        lord21.setPassword("iloveyou");
        lord21.setRegion(new Region());
        lord21.setRoles(new HashSet<>());
        lord21.setUsername("janedoe");

        Region region21 = new Region();
        region21.setDistricts(new ArrayList<>());
        region21.setId(1L);
        region21.setLord(new User());
        region21.setName("Name");
        region21.setTotalCattleHeadcount(3);
        region21.setTotalHouseholds(1);
        region21.setTotalIncome(10.0d);
        region21.setTotalResidents(1);
        region21.setTotalTaxesPaid(10.0d);

        User lord22 = new User();
        lord22.setActive(true);
        lord22.setDistrict(district17);
        lord22.setId(1L);
        lord22.setLandowner(landowner17);
        lord22.setLord(lord21);
        lord22.setPassword("iloveyou");
        lord22.setRegion(region21);
        lord22.setRoles(new HashSet<>());
        lord22.setUsername("janedoe");

        User lord23 = new User();
        lord23.setActive(true);
        lord23.setDistrict(new District());
        lord23.setId(1L);
        lord23.setLandowner(new User());
        lord23.setLord(new User());
        lord23.setPassword("iloveyou");
        lord23.setRegion(new Region());
        lord23.setRoles(new HashSet<>());
        lord23.setUsername("janedoe");

        Region region22 = new Region();
        region22.setDistricts(new ArrayList<>());
        region22.setId(1L);
        region22.setLord(lord23);
        region22.setName("Name");
        region22.setTotalCattleHeadcount(3);
        region22.setTotalHouseholds(1);
        region22.setTotalIncome(10.0d);
        region22.setTotalResidents(1);
        region22.setTotalTaxesPaid(10.0d);

        User lord24 = new User();
        lord24.setActive(true);
        lord24.setDistrict(district15);
        lord24.setId(1L);
        lord24.setLandowner(landowner16);
        lord24.setLord(lord22);
        lord24.setPassword("iloveyou");
        lord24.setRegion(region22);
        lord24.setRoles(new HashSet<>());
        lord24.setUsername("janedoe");

        District district18 = new District();
        district18.setHouseholds(new ArrayList<>());
        district18.setId(1L);
        district18.setName("Name");
        district18.setRegion(new Region());
        district18.setTotalCattleHeadcount(3);
        district18.setTotalHouseholds(1);
        district18.setTotalIncome(10.0d);
        district18.setTotalResidents(1);
        district18.setTotalTaxesPaid(10.0d);

        User landowner18 = new User();
        landowner18.setActive(true);
        landowner18.setDistrict(new District());
        landowner18.setId(1L);
        landowner18.setLandowner(new User());
        landowner18.setLord(new User());
        landowner18.setPassword("iloveyou");
        landowner18.setRegion(new Region());
        landowner18.setRoles(new HashSet<>());
        landowner18.setUsername("janedoe");

        User lord25 = new User();
        lord25.setActive(true);
        lord25.setDistrict(new District());
        lord25.setId(1L);
        lord25.setLandowner(new User());
        lord25.setLord(new User());
        lord25.setPassword("iloveyou");
        lord25.setRegion(new Region());
        lord25.setRoles(new HashSet<>());
        lord25.setUsername("janedoe");

        Region region23 = new Region();
        region23.setDistricts(new ArrayList<>());
        region23.setId(1L);
        region23.setLord(new User());
        region23.setName("Name");
        region23.setTotalCattleHeadcount(3);
        region23.setTotalHouseholds(1);
        region23.setTotalIncome(10.0d);
        region23.setTotalResidents(1);
        region23.setTotalTaxesPaid(10.0d);

        User lord26 = new User();
        lord26.setActive(true);
        lord26.setDistrict(district18);
        lord26.setId(1L);
        lord26.setLandowner(landowner18);
        lord26.setLord(lord25);
        lord26.setPassword("iloveyou");
        lord26.setRegion(region23);
        lord26.setRoles(new HashSet<>());
        lord26.setUsername("janedoe");

        Region region24 = new Region();
        region24.setDistricts(new ArrayList<>());
        region24.setId(1L);
        region24.setLord(lord26);
        region24.setName("Name");
        region24.setTotalCattleHeadcount(3);
        region24.setTotalHouseholds(1);
        region24.setTotalIncome(10.0d);
        region24.setTotalResidents(1);
        region24.setTotalTaxesPaid(10.0d);

        User lord27 = new User();
        lord27.setActive(true);
        lord27.setDistrict(district11);
        lord27.setId(1L);
        lord27.setLandowner(landowner14);
        lord27.setLord(lord24);
        lord27.setPassword("iloveyou");
        lord27.setRegion(region24);
        lord27.setRoles(new HashSet<>());
        lord27.setUsername("janedoe");

        Region region25 = new Region();
        region25.setDistricts(new ArrayList<>());
        region25.setId(1L);
        region25.setLord(new User());
        region25.setName("Name");
        region25.setTotalCattleHeadcount(3);
        region25.setTotalHouseholds(1);
        region25.setTotalIncome(10.0d);
        region25.setTotalResidents(1);
        region25.setTotalTaxesPaid(10.0d);

        District district19 = new District();
        district19.setHouseholds(new ArrayList<>());
        district19.setId(1L);
        district19.setName("Name");
        district19.setRegion(region25);
        district19.setTotalCattleHeadcount(3);
        district19.setTotalHouseholds(1);
        district19.setTotalIncome(10.0d);
        district19.setTotalResidents(1);
        district19.setTotalTaxesPaid(10.0d);

        District district20 = new District();
        district20.setHouseholds(new ArrayList<>());
        district20.setId(1L);
        district20.setName("Name");
        district20.setRegion(new Region());
        district20.setTotalCattleHeadcount(3);
        district20.setTotalHouseholds(1);
        district20.setTotalIncome(10.0d);
        district20.setTotalResidents(1);
        district20.setTotalTaxesPaid(10.0d);

        User landowner19 = new User();
        landowner19.setActive(true);
        landowner19.setDistrict(new District());
        landowner19.setId(1L);
        landowner19.setLandowner(new User());
        landowner19.setLord(new User());
        landowner19.setPassword("iloveyou");
        landowner19.setRegion(new Region());
        landowner19.setRoles(new HashSet<>());
        landowner19.setUsername("janedoe");

        User lord28 = new User();
        lord28.setActive(true);
        lord28.setDistrict(new District());
        lord28.setId(1L);
        lord28.setLandowner(new User());
        lord28.setLord(new User());
        lord28.setPassword("iloveyou");
        lord28.setRegion(new Region());
        lord28.setRoles(new HashSet<>());
        lord28.setUsername("janedoe");

        Region region26 = new Region();
        region26.setDistricts(new ArrayList<>());
        region26.setId(1L);
        region26.setLord(new User());
        region26.setName("Name");
        region26.setTotalCattleHeadcount(3);
        region26.setTotalHouseholds(1);
        region26.setTotalIncome(10.0d);
        region26.setTotalResidents(1);
        region26.setTotalTaxesPaid(10.0d);

        User landowner20 = new User();
        landowner20.setActive(true);
        landowner20.setDistrict(district20);
        landowner20.setId(1L);
        landowner20.setLandowner(landowner19);
        landowner20.setLord(lord28);
        landowner20.setPassword("iloveyou");
        landowner20.setRegion(region26);
        landowner20.setRoles(new HashSet<>());
        landowner20.setUsername("janedoe");

        District district21 = new District();
        district21.setHouseholds(new ArrayList<>());
        district21.setId(1L);
        district21.setName("Name");
        district21.setRegion(new Region());
        district21.setTotalCattleHeadcount(3);
        district21.setTotalHouseholds(1);
        district21.setTotalIncome(10.0d);
        district21.setTotalResidents(1);
        district21.setTotalTaxesPaid(10.0d);

        User landowner21 = new User();
        landowner21.setActive(true);
        landowner21.setDistrict(new District());
        landowner21.setId(1L);
        landowner21.setLandowner(new User());
        landowner21.setLord(new User());
        landowner21.setPassword("iloveyou");
        landowner21.setRegion(new Region());
        landowner21.setRoles(new HashSet<>());
        landowner21.setUsername("janedoe");

        User lord29 = new User();
        lord29.setActive(true);
        lord29.setDistrict(new District());
        lord29.setId(1L);
        lord29.setLandowner(new User());
        lord29.setLord(new User());
        lord29.setPassword("iloveyou");
        lord29.setRegion(new Region());
        lord29.setRoles(new HashSet<>());
        lord29.setUsername("janedoe");

        Region region27 = new Region();
        region27.setDistricts(new ArrayList<>());
        region27.setId(1L);
        region27.setLord(new User());
        region27.setName("Name");
        region27.setTotalCattleHeadcount(3);
        region27.setTotalHouseholds(1);
        region27.setTotalIncome(10.0d);
        region27.setTotalResidents(1);
        region27.setTotalTaxesPaid(10.0d);

        User lord30 = new User();
        lord30.setActive(true);
        lord30.setDistrict(district21);
        lord30.setId(1L);
        lord30.setLandowner(landowner21);
        lord30.setLord(lord29);
        lord30.setPassword("iloveyou");
        lord30.setRegion(region27);
        lord30.setRoles(new HashSet<>());
        lord30.setUsername("janedoe");

        User lord31 = new User();
        lord31.setActive(true);
        lord31.setDistrict(new District());
        lord31.setId(1L);
        lord31.setLandowner(new User());
        lord31.setLord(new User());
        lord31.setPassword("iloveyou");
        lord31.setRegion(new Region());
        lord31.setRoles(new HashSet<>());
        lord31.setUsername("janedoe");

        Region region28 = new Region();
        region28.setDistricts(new ArrayList<>());
        region28.setId(1L);
        region28.setLord(lord31);
        region28.setName("Name");
        region28.setTotalCattleHeadcount(3);
        region28.setTotalHouseholds(1);
        region28.setTotalIncome(10.0d);
        region28.setTotalResidents(1);
        region28.setTotalTaxesPaid(10.0d);

        User lord32 = new User();
        lord32.setActive(true);
        lord32.setDistrict(district19);
        lord32.setId(1L);
        lord32.setLandowner(landowner20);
        lord32.setLord(lord30);
        lord32.setPassword("iloveyou");
        lord32.setRegion(region28);
        lord32.setRoles(new HashSet<>());
        lord32.setUsername("janedoe");

        Region region29 = new Region();
        region29.setDistricts(new ArrayList<>());
        region29.setId(1L);
        region29.setLord(lord32);
        region29.setName("Name");
        region29.setTotalCattleHeadcount(3);
        region29.setTotalHouseholds(1);
        region29.setTotalIncome(10.0d);
        region29.setTotalResidents(1);
        region29.setTotalTaxesPaid(10.0d);

        User user = new User();
        user.setActive(true);
        user.setDistrict(district2);
        user.setId(1L);
        user.setLandowner(landowner10);
        user.setLord(lord27);
        user.setPassword("iloveyou");
        user.setRegion(region29);
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/createLord");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user", "usernameError"))
                .andExpect(MockMvcResultMatchers.view().name("admin/Lord"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("admin/Lord"));
    }
}
