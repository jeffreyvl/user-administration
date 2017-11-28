package com.capgemini.useradmin.util;

import com.capgemini.useradmin.model.DefaultEntry;
import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.model.ScheduleEntry;
import com.capgemini.useradmin.model.User;
import com.capgemini.useradmin.services.DefaultEntryService;
import com.capgemini.useradmin.services.RoleService;
import com.capgemini.useradmin.services.ScheduleEntryService;
import com.capgemini.useradmin.services.UserService;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Seeder {

    @Autowired
    private DefaultEntryService defaultEntryService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ScheduleEntryService scheduleEntryService;

    public void seedUserTwan(){
        User user = new User();
        user.setName("Twan");
        DefaultEntry defaultEntry = createDefaultEntry(user, DayOfWeek.THURSDAY, Shift.NIGHT);
        ScheduleEntry scheduleEntry = createScheduleEntry(user, Shift.MORNING, LocalDate.now());
        Role role = createRole(user, "Manager");
        addServices(user, role, scheduleEntry, defaultEntry);
    }

    public void seedUserRamon(){
        User user = new User();
        user.setName("Ramon");
        DefaultEntry defaultEntry = createDefaultEntry(user, DayOfWeek.TUESDAY, Shift.EVENING);
        ScheduleEntry scheduleEntry = createScheduleEntry(user, Shift.MORNING, LocalDate.now());
        Role role = createRole(user, "Receptionist");
        addServices(user, role, scheduleEntry, defaultEntry);
    }

    public void seedUserEmiel(){
        User user = new User();
        user.setName("Emiel");
        DefaultEntry defaultEntry = createDefaultEntry(user, DayOfWeek.WEDNESDAY, Shift.MORNING);
        ScheduleEntry scheduleEntry = createScheduleEntry(user, Shift.MORNING, LocalDate.now() );
        Role role = createRole(user, "Kok");
        addServices(user, role, scheduleEntry, defaultEntry);
    }

    public void seedUserAydin(){
        User user = new User();
        user.setName("Aydin");
        DefaultEntry defaultEntry = createDefaultEntry(user, DayOfWeek.MONDAY, Shift.NIGHT);
        ScheduleEntry scheduleEntry = createScheduleEntry(user, Shift.MORNING, LocalDate.now());
        Role role = createRole(user, "BUM");
        addServices(user, role, scheduleEntry, defaultEntry);
    }

    public void seedUserFrank(){
        User user = new User();
        user.setName("Frank");
        DefaultEntry defaultEntry = createDefaultEntry(user, DayOfWeek.THURSDAY, Shift.AFTERNOON);
        ScheduleEntry scheduleEntry = createScheduleEntry(user, Shift.MORNING, LocalDate.now());
        Role role = createRole(user, "Receptionist");
        addServices(user, role, scheduleEntry, defaultEntry);
    }

    public Role createRole(User user, String roleName) {
        Role role = new Role();
        List<User> userList = Arrays.asList(user);
        role.setUsers(userList);
        role.setName(roleName);
        user.setRole(role);
        return role;
    }

    public DefaultEntry createDefaultEntry(User user, DayOfWeek day, Shift shift){
        DefaultEntry defaultEntry = new DefaultEntry();
        List<DefaultEntry> defaultEntriesList = Arrays.asList(defaultEntry);
        defaultEntry.setUser(user);
        defaultEntry.setDay(day);
        defaultEntry.setShift(shift);
        user.setDefaultEntries(defaultEntriesList);
        return defaultEntry;
    }

    public ScheduleEntry createScheduleEntry(User user, Shift shift, LocalDate date){
        ScheduleEntry scheduleEntry = new ScheduleEntry();
        List<ScheduleEntry> scheduleEntriesList = Arrays.asList(scheduleEntry);
        scheduleEntry.setUser(user);
        scheduleEntry.setDate(date);
        scheduleEntry.setShift(shift);
        user.setScheduleEntries(scheduleEntriesList);
        return scheduleEntry;
    }

    public void addServices(User user, Role role, ScheduleEntry scheduleEntry, DefaultEntry defaultEntry) {
        userService.add(user);
        defaultEntryService.add(defaultEntry);
        roleService.add(role);
        scheduleEntryService.add(scheduleEntry);
    }
}
