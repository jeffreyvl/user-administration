package com.capgemini.useradmin.util;

import com.capgemini.useradmin.model.DefaultEntry;
import com.capgemini.useradmin.model.Role;
import com.capgemini.useradmin.model.ScheduleEntry;
import com.capgemini.useradmin.model.User;
import com.capgemini.useradmin.services.DefaultEntryService;
import com.capgemini.useradmin.services.RoleService;
import com.capgemini.useradmin.services.ScheduleEntryService;
import com.capgemini.useradmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Seeder {

    @Autowired
    private DefaultEntryService defaultEntryService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ScheduleEntryService scheduleEntryService;

    public void seedUsers(){
        createUser("Kok","Twan","Rozay", LocalDate.now().minusDays(2), DayOfWeek.MONDAY, Shift.AFTERNOON, Shift.AFTERNOON, LocalDate.now().minusDays(2));
        createUser("Kok","Ramon","Vaan de", LocalDate.now().minusDays(10), DayOfWeek.SATURDAY, Shift.MORNING, Shift.AFTERNOON, LocalDate.now().minusDays(2));
        createUser("Big Cock","Aydin","Erdas", LocalDate.now().minusDays(1), DayOfWeek.THURSDAY, Shift.NIGHT, Shift.MORNING, LocalDate.now().minusDays(2));
        createUser("Kok","Emiel","Rossum van", LocalDate.now().minusDays(1), DayOfWeek.TUESDAY, Shift.AFTERNOON, Shift.EVENING, LocalDate.now().minusDays(2));
        createUser("Kok","Frank","Noorloos", LocalDate.now().minusDays(6), DayOfWeek.WEDNESDAY, Shift.NIGHT, Shift.NIGHT, LocalDate.now().minusDays(2));
        createUser("Kok","Jeffrey","Laarhoven van", LocalDate.now().minusDays(9), DayOfWeek.SUNDAY, Shift.AFTERNOON, Shift.MORNING, LocalDate.now().minusDays(2));
        createUser("G","Chaouki","Tiouassiouine", LocalDate.now().minusDays(7), DayOfWeek.MONDAY, Shift.EVENING, Shift.NIGHT, LocalDate.now().minusDays(2));
        createUser("Manager","Raymond","Lomman", LocalDate.now().minusDays(4), DayOfWeek.MONDAY, Shift.EVENING, Shift.MORNING, LocalDate.now().minusDays(2));
    }

    public void createUser(String roleName, String firstName, String lastName, LocalDate startDate, DayOfWeek dayOfWeek, Shift shiftDefault, Shift shiftSchedule, LocalDate startSchedule){
        User user = new User();
        Role role = createRole(user, roleName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStartDate(startDate);
        user.setScheduleEntries(new ArrayList<>());
        user.setDefaultEntries(new ArrayList<>());
        userService.add(user);
        DefaultEntry defaultEntry = createDefaultEntry(user, dayOfWeek, shiftDefault);
        ScheduleEntry scheduleEntry = createScheduleEntry(user, shiftSchedule, startSchedule);
        addServices(user, role, scheduleEntry, defaultEntry);
    }

    public Role createRole(User user, String roleName) {
        Role role = new Role();
        role.setUsers(new ArrayList<>());
        role.setName(roleName);
        roleService.add(role);

        user.setRole(role);
        role.setUsers(Arrays.asList(user));
        return role;
    }

    public DefaultEntry createDefaultEntry(User user, DayOfWeek day, Shift shift){
        DefaultEntry defaultEntry = new DefaultEntry();
        defaultEntry.setUser(user);
        defaultEntry.setDay(day);
        defaultEntry.setShift(shift);
        user.setDefaultEntries(Arrays.asList(defaultEntry));
        return defaultEntry;
    }

    public ScheduleEntry createScheduleEntry(User user, Shift shift, LocalDate date){
        ScheduleEntry scheduleEntry = new ScheduleEntry();
        scheduleEntry.setUser(user);
        scheduleEntry.setDate(date);
        scheduleEntry.setShift(shift);
        user.setScheduleEntries(Arrays.asList(scheduleEntry));
        return scheduleEntry;
    }

    public void addServices(User user, Role role, ScheduleEntry scheduleEntry, DefaultEntry defaultEntry) {
        defaultEntryService.add(defaultEntry);
        scheduleEntryService.add(scheduleEntry);
        userService.add(user);
        roleService.add(role);
    }
}
