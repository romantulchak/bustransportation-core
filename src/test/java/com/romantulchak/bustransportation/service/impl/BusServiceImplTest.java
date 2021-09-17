package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapper;
import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.repository.BusRepository;
import com.romantulchak.bustransportation.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceImplTest {

    @Mock
    BusRepository busRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    EntityMapperInvoker<Bus, BusDTO> entityMapperInvoker;

    @Mock
    Authentication authentication;

    @InjectMocks
    BusServiceImpl busService;

    @Before
    public void setUp(){
        EntityMapper entityMapper = new EntityMapper();
        ReflectionTestUtils.setField(entityMapperInvoker, "entityMapper", entityMapper);
        ReflectionTestUtils.setField(busService, "entityMapperInvoker", entityMapperInvoker);
    }

    @Test
    public void create() {
        Bus bus = new Bus();
        bus.setId(1);
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "KzzxD", "test@gmail.com", "1234", null, true);
        User user = new User(1);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userRepository.findById(userDetails.getId())).thenReturn(Optional.of(user));
        when(busRepository.save(bus)).thenReturn(bus);
        BusDTO busDTO = busService.create(bus, authentication);

        assertNotNull(busDTO);
        assertEquals(bus.getId(), busDTO.getId());
        assertEquals(user.getId(), busDTO.getUser().getId());
    }

    @Test(expected = BusNotFoundException.class)
    public void createBusNotFoundException() {
        when(busRepository.existsBusByNameAndUserUsername("Test", "Kzz")).thenReturn(true);
        busService.create(null, authentication);
    }

    @Test(expected = BusNotFoundException.class)
    public void createBusAlreadyExistsException() {
        busService.create(null, authentication);
    }


    @Test
    public void edit() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getById() {
        Bus bus = new Bus();
        bus.setId(3);

        when(busRepository.findById(1L)).thenReturn(Optional.of(bus));
        BusDTO busDTO = busService.getById(1);

        assertNotNull(busDTO);
        assertEquals(bus.getId(), busDTO.getId());
    }

    @Test(expected = BusNotFoundException.class)
    public void getByIdThrowBusNotFoundException() {
        when(busRepository.findById(1L)).thenReturn(Optional.empty());
        busService.getById(1);
    }

    @Test
    public void getBuses() {
        List<Bus> buses = new ArrayList<>();
        Bus bus = new Bus();
        bus.setId(1);
        Bus bus1 = new Bus();
        bus1.setId(2);
        Bus bus2 = new Bus();
        bus2.setId(3);
        buses.add(bus);
        buses.add(bus1);
        buses.add(bus2);

        when(busRepository.findAll()).thenReturn(buses);
        List<BusDTO> busDTOS = busService.getBuses();

        assertNotNull(busDTOS);
        assertEquals(buses.size(), busDTOS.size());
        assertEquals(buses.get(0).getId(), busDTOS.get(0).getId());
    }

    @Test
    public void findBusesForUser() {
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "KzzxD", "test@gmail.com", "1234", null, true);
        List<Bus> buses = new ArrayList<>();
        Bus bus = new Bus();
        bus.setId(1);
        Bus bus1 = new Bus();
        bus1.setId(2);
        Bus bus2 = new Bus();
        bus2.setId(3);
        buses.add(bus);
        buses.add(bus1);
        buses.add(bus2);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(busRepository.findBusesByUserId(userDetails.getId())).thenReturn(buses);
        List<BusDTO> busesForUser = busService.findBusesForUser(authentication);

        assertNotNull(busesForUser);
        assertEquals(buses.size(), busesForUser.size());
        assertEquals(buses.get(0).getId(), busesForUser.get(0).getId());


    }

    @Test(expected = UserNotFoundException.class)
    public void findBusesThrowUserNotFoundException() {
        when(authentication.getPrincipal()).thenReturn(null);
        busService.findBusesForUser(authentication);
    }
}
