
package com.example.diver_accelerator.controller;

import com.example.diver_accelerator.entity.Diver;
import com.example.diver_accelerator.entity.Role;
import com.example.diver_accelerator.entity.User;
import com.example.diver_accelerator.service.DiverService;
import com.example.diver_accelerator.service.RoleService;
import com.example.diver_accelerator.service.UserService;
import com.example.diver_accelerator.transientClasses.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.service.*;


import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    protected DirectionService directionService;

    @Autowired
    protected DiverService diverService;

    @Autowired
    protected DiverProService diverProService;

    @Autowired
    protected EquipmentCatalogService equipmentCatalogService;

    @Autowired
    protected ImmersionService immersionService;

    @Autowired
    protected SpecializationService specializationService;

    @Autowired
    protected TestTypeService testTypeService;

    protected Sort sort = new Sort();

    //diver
    @RequestMapping(value = "/divers/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allDiversPage(@PathVariable("sort_num") int sort_num) {
        List<Diver> divers = diverService.allDivers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diversList", sort.sortDiver(divers, sort_num));
        modelAndView.setViewName("admin/tables/divers");
        return modelAndView;
    }

    @RequestMapping(value = "/divers/1", method = RequestMethod.GET, params = "search")
    public ModelAndView allDiversWithCurrentTelephone_number(@ModelAttribute("telephone_number") int telephone_number) {

        List<Diver> divers = diverService.findTelephone_number(telephone_number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diversList", sort.sortDiver(divers, 1));
        modelAndView.setViewName("admin/tables/divers");

        return modelAndView;
    }


    @GetMapping("/{id_diver}/set_user_for_diver")
    public ModelAndView addPageSetUserDiver(@PathVariable("id_diver") Long id_diver) {
        Diver diver = diverService.getById(id_diver);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("isDoc", false);
        modelAndView.addObject("entity", diver);
        modelAndView.addObject("users", diver.getUsers(userService.allUsers(), 3));
        modelAndView.setViewName("admin/forms/form_user_for_entity");

        return modelAndView;
    }

    @PostMapping("/setUserDiver")
    public ModelAndView setUserDiver(@ModelAttribute("selected_user") String user_id,
                                       @ModelAttribute("id_diver") Long id_diver) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserById(user_id);
        user.setSelected(true);

        Diver diver = diverService.getById(id_diver);
        diver.setUser(user);
        diverService.add(diver);

        modelAndView.setViewName("redirect:/admin/divers/1");

        return modelAndView;
    }


    @RequestMapping(value = "/add_diver", method = RequestMethod.GET)
    public ModelAndView addPageDiver(@ModelAttribute("message") String message) {
        Diver diver = new Diver();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diver", diver);

        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.setViewName("admin/forms/form_divers");
        return modelAndView;
    }


    @RequestMapping(value = "/add_diver", method = RequestMethod.POST)
    public ModelAndView addDiver(@ModelAttribute("diver") Diver diver,
                                   @ModelAttribute("sex") String sex) {
        ModelAndView modelAndView = new ModelAndView();

        if (diverService.checkId(diver.getId()) && diverProService.checkId(diver.getId())) {
            diver.setSex(Integer.parseInt(sex));

            diverService.add(diver);
            modelAndView.setViewName("redirect:/admin/divers/1");

        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_diver");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{id_diver}/edit_diver", method = RequestMethod.GET)
    public ModelAndView editPageDiver(@PathVariable("id_diver") Long id_diver) {
        Diver diver = diverService.getById(id_diver);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_divers");
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("id_diver", id_diver);
        return modelAndView;
    }

    @RequestMapping(value = "/add_diver", method = RequestMethod.POST, params = "edit")
    public ModelAndView editDiver(@ModelAttribute("diver") Diver diver,
                                    @ModelAttribute("sex") String sex,
                                    @ModelAttribute("Blood_type") String Blood_type,
                                    @ModelAttribute("rh") String rh,
                                    @ModelAttribute("user_id") String user_id) {
        diver.setSex(Integer.parseInt(sex));
        if (!user_id.isEmpty()) {
            diver.setUser(userService.findUserById(user_id));
        }

        diverService.add(diver);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/divers/1");

        return modelAndView;
    }


    @RequestMapping(value = "/{id_diver}/delete_diver", method = RequestMethod.GET)
    public ModelAndView deleteDiver(@PathVariable("id_diver") Long id_diver) {
        diverService.deleteDiver(id_diver);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/divers/1");

        return modelAndView;
    }



    //diverPro
    @RequestMapping(value = "/diverPro/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allDiverProsPage(@PathVariable("sort_num") int sort_num) {
        List<DiverPro> diverPros = diverProService.allDiverPros();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, sort_num));
        modelAndView.setViewName("admin/tables/diverPro");
        return modelAndView;
    }

    @RequestMapping(value = "/diverPro/1", method = RequestMethod.GET, params = "search")
    public ModelAndView allDiverProsWithCurrentTelephone_number(@ModelAttribute("telephone_number") int telephone_number) {
        List<DiverPro> diverPros = diverProService.findTelephone_number(telephone_number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, 1));
        modelAndView.setViewName("admin/tables/diverPro");

        return modelAndView;
    }

    @GetMapping("/{id_diverPro}/set_user_for_diverPro")
    public ModelAndView addPageSetUserDiverPro(@PathVariable("id_diverPro") Long id_diverPro) {
        DiverPro diverPro = diverProService.getById(id_diverPro);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("isDoc", true);
        modelAndView.addObject("entity", diverPro);
        modelAndView.addObject("users", diverPro.getUsers(userService.allUsers()));
        modelAndView.setViewName("admin/forms/form_user_for_entity");

        return modelAndView;
    }

    @RequestMapping(value = "/setUserDiver", method = RequestMethod.POST, params = "editD")
    public ModelAndView setUserDiverPro(@ModelAttribute("selected_user") String user_id,
                                      @ModelAttribute("id_diver") Long id_diverPro) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserById(user_id);
        user.setSelected(true);

        DiverPro diverPro = diverProService.getById(id_diverPro);
        diverPro.setUser(user);
        diverProService.add(diverPro);

        modelAndView.setViewName("redirect:/admin/diverPro/1");

        return modelAndView;
    }

    @RequestMapping(value = "/add_diverPro", method = RequestMethod.GET)
    public ModelAndView addPageDiverPro(@ModelAttribute("message") String message) {
        DiverPro diverPro = new DiverPro();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("specializations", specializationService.allSpecialization());

        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.setViewName("admin/forms/form_diverPro");
        return modelAndView;
    }


    @RequestMapping(value = "/add_diverPro", method = RequestMethod.POST)
    public ModelAndView addDiverPro(@ModelAttribute("diverPro") DiverPro diverPro,
                                  @ModelAttribute("sex") String sex,
                                  @ModelAttribute("selected_spec") int spec_id) {
        ModelAndView modelAndView = new ModelAndView();

        if ((diverService.checkId(diverPro.getId()) && diverProService.checkId(diverPro.getId()))) {
            diverPro.setSex(Integer.parseInt(sex));
            diverPro.setSpecialization(specializationService.getById(spec_id));

            diverProService.add(diverPro);
            modelAndView.setViewName("redirect:/admin/diverPro/1");

        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_diverPro");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{id_diverPro}/edit_diverPro", method = RequestMethod.GET)
    public ModelAndView editPageDiverPro(@PathVariable("id_diverPro") Long id_diverPro) {
        DiverPro diverPro = diverProService.getById(id_diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_diverPro");
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("id_diverPro", id_diverPro);
        //modelAndView.addObject("user_id", diverPro.getUser().getId());
        modelAndView.addObject("specializations", specializationService.allSpecialization());

        return modelAndView;
    }

    @RequestMapping(value = "/add_diverPro", method = RequestMethod.POST, params = "edit")
    public ModelAndView editDiverPro(@ModelAttribute("diverPro") DiverPro diverPro,
                                   @ModelAttribute("sex") String sex,
                                   @ModelAttribute("selected_spec") int spec_id) {
        diverPro.setSex(Integer.parseInt(sex));
        diverPro.setSpecialization(specializationService.getById(spec_id));

        diverProService.add(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/diverPro/1");

        return modelAndView;
    }


    @RequestMapping(value = "/{id_diverPro}/delete_diverPro", method = RequestMethod.GET)
    public ModelAndView deleteDiverPro(@PathVariable("id_diverPro") Long id_diverPro) {
        DiverPro diverPro = diverProService.getById(id_diverPro);
        diverProService.delete(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/diverPro/1");

        return modelAndView;
    }

    //schedule
    @RequestMapping(value = "/{id_diverPro}/schedule/{sort_num}", method = RequestMethod.GET)
    public ModelAndView PageSchedule(@PathVariable("id_diverPro") Long id_diverPro,
                                     @PathVariable("sort_num") int sort_num) {
        DiverPro diverPro = diverProService.getById(id_diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("schedules", sort.sortSchedule(diverPro.getSchedules(), sort_num));

        modelAndView.setViewName("admin/tables/schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/{id_diverPro}/add_schedule", method = RequestMethod.GET)
    public ModelAndView addPageSchedule(@PathVariable("id_diverPro") Long id_diverPro) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_diverPro", id_diverPro);
        // modelAndView.addObject("schedule", new Schedule());
        modelAndView.setViewName("admin/forms/form_new_schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/add_schedule", method = RequestMethod.POST)
    public ModelAndView addSchedule(@ModelAttribute("id_diverPro") long id_diverPro,
                                    @ModelAttribute("day") int day,
                                    @ModelAttribute("timeStart") String timeStart,
                                    @ModelAttribute("timeEnd") String timeEnd,
                                    @ModelAttribute("interval") int interval) throws NoSuchAlgorithmException, ParseException {

        DiverPro diverPro = diverProService.getById(id_diverPro);
        diverPro.setSchedulesByRange(day, timeStart, timeEnd, interval);
        diverProService.add(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + id_diverPro + "/schedule/2");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_diverPro}/{id_schedule}/edit_schedule", method = RequestMethod.GET)
    public ModelAndView editPageSchedule(@PathVariable("id_diverPro") Long id_diverPro,
                                         @PathVariable("id_schedule") int id_schedule) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_schedule");
        modelAndView.addObject("id_diverPro", id_diverPro);
        modelAndView.addObject("schedule", diverProService.getScheduleById(id_schedule));
        return modelAndView;
    }

    @RequestMapping(value = "/add_schedule", method = RequestMethod.POST, params = "edit")
    public ModelAndView editEquipment(@ModelAttribute("schedule") Schedule schedule,
                                     @ModelAttribute("id_diverPro") Long id_diverPro) {
        DiverPro diverPro = diverProService.getById(id_diverPro);
        diverPro.addSchedule(schedule);
        diverProService.add(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + id_diverPro + "/schedule/2");

        return modelAndView;
    }


    @RequestMapping(value = "/{id_diverPro}/{id_schedule}/delete_schedule", method = RequestMethod.GET)
    public ModelAndView deleteSchedule(@PathVariable("id_schedule") int id_schedule,
                                       @PathVariable("id_diverPro") Long id_diverPro) {

        diverProService.deleteSchedule(id_schedule);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + id_diverPro + "/schedule/2");

        return modelAndView;
    }

    //equipmentCatalog

    @RequestMapping(value = "/equipmentCatalog/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allEquipmentCatalogPage(@PathVariable("sort_num") int sort_num) {
        List<EquipmentCatalog> equipmentCatalogList = diverService.allEquipments();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("equipmentCatalogList", sort.sortEquipmentCatalog(equipmentCatalogList, sort_num));
        modelAndView.setViewName("admin/tables/equipmentCatalog");
        return modelAndView;
    }
    @RequestMapping(value = "/add_equipmentCatalog", method = RequestMethod.GET)
    public ModelAndView addPageEquipment(@ModelAttribute("message") String message) {
        EquipmentCatalog equipment = new EquipmentCatalog();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("equipment", equipment);
        modelAndView.setViewName("admin/forms/form_equipment");
        return modelAndView;
    }


    @RequestMapping(value = "/add_equipmentCatalog", method = RequestMethod.POST)
    public ModelAndView addEquipment(@ModelAttribute("equipment") EquipmentCatalog equipment) {
        ModelAndView modelAndView = new ModelAndView();

        if (equipmentCatalogService.checkID(equipment.getId())) {

            equipmentCatalogService.add(equipment);
            modelAndView.setViewName("redirect:/admin/equipmentCatalog/1");

        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_equipmentCatalog");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{ID}/edit_equipmentCatalog", method = RequestMethod.GET)
    public ModelAndView editPageEquipment(@PathVariable("ID") String ID) {
        EquipmentCatalog equipmentCatalog = equipmentCatalogService.getById(ID);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_equipment");
        modelAndView.addObject("equipment", equipmentCatalog);
        return modelAndView;
    }

    @RequestMapping(value = "/add_equipmentCatalog", method = RequestMethod.POST, params = "edit")
    public ModelAndView editEquipment(@ModelAttribute("equipment") EquipmentCatalog equipment) {
        equipmentCatalogService.add(equipment);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/equipmentCatalog/1");

        return modelAndView;
    }

    @RequestMapping(value = "/{ID}/delete_equipmentCatalog", method = RequestMethod.GET)
    public ModelAndView deleteEquipment(@PathVariable("ID") String ID) {
        EquipmentCatalog equipment = equipmentCatalogService.getById(ID);
        equipmentCatalogService.delete(equipment);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/equipmentCatalog/1");

        return modelAndView;
    }

    //immersion

    @RequestMapping(value = "/immersion/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allImmersionPage(@PathVariable("sort_num") int sort_num) {
        List<Immersion> immersionList = immersionService.allImmersion();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("immersionList", sort.sortImmersion(immersionList, sort_num));
        modelAndView.setViewName("admin/tables/immersion");
        return modelAndView;
    }

    @RequestMapping(value = "/add_immersion", method = RequestMethod.GET)
    public ModelAndView addPageImmersion(@ModelAttribute("message") String message) {
        Immersion immersion = new Immersion();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }
        modelAndView.addObject("immersion", immersion);
        modelAndView.setViewName("admin/forms/form_immersion");
        return modelAndView;
    }


    @RequestMapping(value = "/add_immersion", method = RequestMethod.POST)
    public ModelAndView addImmersion(@ModelAttribute("immersion") Immersion immersion) {
        ModelAndView modelAndView = new ModelAndView();
        //immersion.se

        if (immersionService.checkId(immersion.getMax_deep())) {
            immersionService.add(immersion);
            modelAndView.setViewName("redirect:/admin/immersion/1");
        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_immersion");

        }

        return modelAndView;
    }
//todo rename ICD to ID
    @RequestMapping(value = "/{ICD}/edit_immersion", method = RequestMethod.GET)
    public ModelAndView editPageImmersion(@PathVariable("ICD") String ICD) {
        Immersion immersion = immersionService.getById(ICD);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_immersion");
        modelAndView.addObject("immersion", immersion);
        return modelAndView;
    }

    @RequestMapping(value = "/add_immersion", method = RequestMethod.POST, params = "edit")
    public ModelAndView editImmersion(@ModelAttribute("immersion") Immersion immersion) {
        immersionService.add(immersion);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/immersion/1");

        return modelAndView;
    }
//todo done
    @RequestMapping(value = "/{id}/delete_immersion", method = RequestMethod.GET)
    public ModelAndView deleteImmersion(@PathVariable("id") String id) {
        Immersion immersion = immersionService.getById(id);
        immersionService.delete(immersion);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/immersion/1");

        return modelAndView;
    }

    //testType
    @GetMapping("/testType/{sort_num}")
    public ModelAndView allTestTypePage(@PathVariable("sort_num") int sort_num) {
        List<TestsType> testsTypes = testTypeService.allTestTypes();


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testTypeList", testsTypes);
        modelAndView.setViewName("admin/tables/testType");
        return modelAndView;
    }

    @GetMapping("/add_testType")
    public ModelAndView addPageTestType(@ModelAttribute("message") String message) {
        TestsType testType = new TestsType();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("testType", testType);
        modelAndView.setViewName("admin/forms/form_testType");
        return modelAndView;
    }


    @PostMapping("/add_testType")
    public ModelAndView addTestType(@ModelAttribute("testType") TestsType testType) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView();
        String id = SecureRandom.getInstance("SHA1PRNG").nextInt() + "";

        testType.setId(id);

        if (testTypeService.checkId(testType.getId())) {
            testTypeService.add(testType);
            modelAndView.setViewName("redirect:/admin/testType/1");
        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_testType");
        }

        return modelAndView;
    }

    @GetMapping("/{id}/edit_testType")
    public ModelAndView editPageTestType(@PathVariable("id") String id) {
        TestsType testsType = testTypeService.getTestsTypeById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_testType");
        modelAndView.addObject("testType", testsType);
        return modelAndView;
    }

    @PostMapping(value = "/add_testType", params = "edit")
    public ModelAndView editTestType(@ModelAttribute("testType") TestsType testType) {
        testTypeService.add(testType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/testType/1");

        return modelAndView;
    }

    @GetMapping("/{id}/delete_testType")
    public ModelAndView deleteTestType(@PathVariable("id") String id) {
        TestsType testsType = testTypeService.getTestsTypeById(id);

        testTypeService.delete(testsType);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/testType/1");

        return modelAndView;
    }


    //specialization
    @RequestMapping(value = "/specialization/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allSpecializationPage(@PathVariable("sort_num") int sort_num) {
        List<Specialization> specializationList = specializationService.allSpecialization();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("specializationList", sort.sortSpecialization(specializationList, sort_num));
        modelAndView.setViewName("admin/tables/specialization");
        return modelAndView;
    }

    @RequestMapping(value = "/add_specialization", method = RequestMethod.GET)
    public ModelAndView addPageSpecialization(@ModelAttribute("message") String message) {
        Specialization specialization = new Specialization();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("specialization", specialization);
        modelAndView.setViewName("admin/forms/form_specialization");
        return modelAndView;
    }


    @RequestMapping(value = "/add_specialization", method = RequestMethod.POST)
    public ModelAndView addSpecialization(@ModelAttribute("specialization") Specialization specialization) {
        ModelAndView modelAndView = new ModelAndView();

        if (specializationService.checkId(specialization.getId())) {
            specializationService.add(specialization);
            modelAndView.setViewName("redirect:/admin/specialization/1");
        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_specialization");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/{id}/edit_specialization", method = RequestMethod.GET)
    public ModelAndView editPageSpecialization(@PathVariable("id") int id) {
        Specialization specialization = specializationService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_specialization");
        modelAndView.addObject("specialization", specialization);
        return modelAndView;
    }

    @RequestMapping(value = "/add_specialization", method = RequestMethod.POST, params = "edit")
    public ModelAndView editSpecialization(@ModelAttribute("specialization") Specialization specialization) {
        specializationService.add(specialization);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/specialization/1");

        return modelAndView;
    }

    @RequestMapping(value = "/{id}/delete_specialization", method = RequestMethod.GET)
    public ModelAndView deleteSpecialization(@PathVariable("id") int id) {
        Specialization specialization = specializationService.getById(id);
        specializationService.delete(specialization);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/specialization/1");

        return modelAndView;
    }

    //user
    @RequestMapping(value = "/user/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allUserPage(@PathVariable("sort_num") int sort_num) {
        List<User> userList = userService.allUsers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", sort.sortUser(userList, sort_num));
        modelAndView.setViewName("admin/tables/user");
        return modelAndView;
    }

    @GetMapping("/add_user")
    public ModelAndView addPageUser(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();

        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", roleService.allRole());
        modelAndView.setViewName("admin/forms/form_user");

        return modelAndView;
    }

    @PostMapping("/add_user")
    public ModelAndView addUser(@ModelAttribute("user") @Valid User user,
                                @ModelAttribute("selected_role") int role_id) throws NoSuchAlgorithmException {
        user.setId (SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + user.getUsername());
        user.setSelected(false);

        ModelAndView modelAndView = new ModelAndView();

        if (!userService.saveUser(user, role_id)) {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_user");
        } else {
            modelAndView.setViewName("redirect:/admin/user/1");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/change_password/{id}", method = RequestMethod.GET)
    public ModelAndView editPageUserPassword(@PathVariable("id") String id) {
        User user = userService.findUserById(id);
        Optional<Role> role = user.getRoles().stream().findFirst();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_user_password");
        modelAndView.addObject("user", user);
        modelAndView.addObject("my_role_id", role.orElse(new Role()).getId());

        return modelAndView;
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST, params = "edit")
    public ModelAndView editUserPassword(@ModelAttribute("user") @Valid User user,
                                         @ModelAttribute("my_role_id") int my_role_id) {
        userService.editUserPassword(user, my_role_id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + user.getUsername() + "/edit_user");
        return modelAndView;
    }

    @RequestMapping(value = "/{username}/edit_user", method = RequestMethod.GET)
    public ModelAndView editPageUser(@PathVariable("username") String username) {
        User user = (User) userService.loadUserByUsername(username);
        Optional<Role> role = user.getRoles().stream().findFirst();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_user");
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roleService.allRole());
        modelAndView.addObject("my_role", role.orElse(new Role()));

        return modelAndView;
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST, params = "edit")
    public ModelAndView editUser(@ModelAttribute("user") @Valid User user,
                                 @ModelAttribute("selected_role") int role_id) {

        userService.editUser(user, role_id);

        Optional<Role> role = user.getRoles().stream().findFirst();

        if (!user.isSelected()) {
            if (role.orElse(new Role()).getId() == 1 || role.orElse(new Role()).getId() == 4) {
                System.out.println("doc");

                DiverPro diverPro = diverProService.diverProByUser(user);
                if (diverPro != null) {
                    diverPro.setUser(null);
                    diverProService.add(diverPro);
                }

            } else if (role.orElse(new Role()).getId() == 3) {
                System.out.println("pat");
                Diver diver = diverService.diverByUser(user);
                if (diver != null) {
                    diver.setUser(null);
                    diverService.add(diver);
                }

            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/user/1");
        return modelAndView;
    }
    @RequestMapping(value = "/{id}/delete_user", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/user/1");

        return modelAndView;
    }

    //role
    @RequestMapping(value = "/role/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allRolePage(@PathVariable("sort_num") int sort_num) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList", sort.sortRole(roleService.allRole(), sort_num));
        modelAndView.setViewName("admin/tables/role");
        return modelAndView;
    }

    @RequestMapping(value = "/add_role", method = RequestMethod.GET)
    public ModelAndView addPageRole(@ModelAttribute("message") String message) {
        Role role = new Role();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }
        modelAndView.addObject("role", role);
        modelAndView.setViewName("admin/forms/form_role");
        return modelAndView;
    }

    @RequestMapping(value = "/add_role", method = RequestMethod.POST)
    public ModelAndView addRole(@ModelAttribute("role") Role role,
                                @ModelAttribute("name") String s_name) {
        ModelAndView modelAndView = new ModelAndView();
        role.setName("ROLE_" + s_name);

        if (roleService.checkId(role.getId())) {
            roleService.add(role);
            modelAndView.setViewName("redirect:/admin/role/1");
        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/admin/add_role");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/{id}/edit_role", method = RequestMethod.GET)
    public ModelAndView editPageRole(@PathVariable("id") int id) {
        Role role = roleService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_role");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @RequestMapping(value = "/add_role", method = RequestMethod.POST, params = "edit")
    public ModelAndView editRole(@ModelAttribute("role") Role role) {
        roleService.add(role);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/role/1");

        return modelAndView;
    }

    @RequestMapping(value = "/{id}/delete_role", method = RequestMethod.GET)
    public ModelAndView deleteRole(@PathVariable("id") int id) {
        Role role = roleService.getById(id);
        roleService.delete(role);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/role/1");

        return modelAndView;
    }

    //directions
    @RequestMapping(value = "/{id_diver}/direction/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allDirection(@PathVariable("id_diver") Long id_diver,
                                     @PathVariable("sort_num") int sort_num) {
        ModelAndView modelAndView = new ModelAndView();
        Diver diver = diverService.getById(id_diver);

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("directionList", sort.sortDirection(diver.getDirections(), sort_num));
        modelAndView.setViewName("admin/tables/direction");
        return modelAndView;
    }

    @RequestMapping(value = "/{id_diver}/add_direction", method = RequestMethod.GET)
    public ModelAndView addPageDirection(@PathVariable("id_diver") Long id_diver) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_diver", id_diver);
        modelAndView.addObject("specializationsList", specializationService.allSpecialization());
        modelAndView.addObject("direction", new Direction());
        modelAndView.setViewName("admin/forms/form_direction");
        return modelAndView;
    }

    @RequestMapping(value = "/add_direction", method = RequestMethod.POST)
    public ModelAndView addDirection(@ModelAttribute("selected_spec") int selected_spec,
                                     @ModelAttribute("direction") Direction direction,
                                     @ModelAttribute("id_diver") Long id_diver) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView();
       Diver diver = diverService.getById(id_diver);
        if (diver.isDirectionExists(specializationService.getById(selected_spec).getName())) {
            direction.setNumber(String.valueOf(SecureRandom.getInstance("SHA1PRNG").nextInt()));
            direction.setSpecialization(specializationService.getById(selected_spec));
            direction.setStatus(true);
            direction.setDiver(diver);
            directionService.add(direction);
        }

        modelAndView.setViewName("redirect:/admin/" + diver.getId() + "/direction/2");

        return modelAndView;
    }


    @RequestMapping(value = "/{id_diver}/{id_direction}/edit_direction", method = RequestMethod.GET)
    public ModelAndView editPageDirection(@PathVariable("id_diver") Long id_diver,
                                          @PathVariable("id_direction") String id_direction) {
        Direction direction = directionService.getById(id_direction);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/forms/form_direction");
        modelAndView.addObject("id_diver", id_diver);
        modelAndView.addObject("specializationsList", specializationService.allSpecialization());
        modelAndView.addObject("direction", direction);
        return modelAndView;
    }

    @RequestMapping(value = "/add_direction", method = RequestMethod.POST, params = "edit")
    public ModelAndView editDirection(@ModelAttribute("selected_spec") int selected_spec,
                                      @ModelAttribute("direction") Direction direction,
                                      @ModelAttribute("id_diver") Long id_diver) {
      Diver diver = diverService.getById(id_diver);

        direction.setSpecialization(specializationService.getById(selected_spec));
        direction.setDiver(diver);

        directionService.add(direction);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + diver.getId() + "/direction/2");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_diver}/{id}/delete_direction", method = RequestMethod.GET)
    public ModelAndView deleteDirection(@PathVariable("id_diver") Long id_diver,
                                        @PathVariable("id") String id) {
        Direction direction = directionService.getById(id);
        Diver diver = diverService.getById(id_diver);
        diver.getDirections().remove(direction);

        directionService.delete(direction);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + id_diver + "/direction/2");

        return modelAndView;
    }

    //visit

    @RequestMapping(value = "/{id_diver}/visit/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allVisits(@PathVariable("id_diver") Long id_diver,
                                  @PathVariable("sort_num") int sort_num) {

        Diver diver = diverService.getById(id_diver);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("visitsList", sort.sortVisit(diver.getVisits(), sort_num));

        modelAndView.setViewName("admin/tables/visit");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_diver}/add_visit", method = RequestMethod.GET)
    public ModelAndView addPageVisits(@PathVariable("id_diver") Long id_diver) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("visit", new Visit());
        modelAndView.addObject("id_diver", id_diver);
        modelAndView.addObject("diverProList", diverProService.allDiverPros());
        modelAndView.addObject("immersionsList", immersionService.allImmersion());

        modelAndView.setViewName("admin/forms/form_visit");

        return modelAndView;
    }

    @RequestMapping(value = "/add_visits", method = RequestMethod.POST)
    public ModelAndView addVisit(@ModelAttribute("visit") Visit visit,
                                 @ModelAttribute("selected_immersion") String selected_immersion,
                                 @ModelAttribute("selected_diverPro") Long id_diverPro,
                                 @ModelAttribute("id_diver") Long id_diver,
                                 @ModelAttribute("notes") String notes) throws NoSuchAlgorithmException {
        Diver diver = diverService.getById(id_diver);
        DiverPro diverPro = diverProService.getById(id_diverPro);
        Immersion immersion = immersionService.getById(selected_immersion);

        String id_visit = SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + diver.getId();

        visit.setNumber(id_visit);

        if (notes == null) {
            visit.setNotes("");
        }

        visit.setDiverPro(diverPro);
        visit.setDiver(diver);
        visit.setImmersion(immersion);
        visit.setStatus(true);
        diver.addVisit(visit);

        diverService.add(diver);
        diverProService.add(diverPro);//?

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + id_visit + "/" + diver.getId() + "/setSchedule");


        return modelAndView;
    }

    @RequestMapping(value = "/{id_diver}/{id_visit}/edit_visit", method = RequestMethod.GET)
    public ModelAndView editPageVisits(@PathVariable("id_diver") Long id_diver,
                                       @PathVariable("id_visit") String id_visit) {

        ModelAndView modelAndView = new ModelAndView();
        Diver diver = diverService.getById(id_diver);


        modelAndView.addObject("visit", diver.findVisit(id_visit));
        modelAndView.addObject("id_diver", id_diver);
        modelAndView.addObject("diverProList", diverProService.allDiverPros());
        modelAndView.addObject("immersionsList", immersionService.allImmersion());

        modelAndView.setViewName("admin/forms/form_visit");

        return modelAndView;
    }


    @RequestMapping(value = "/add_visits", method = RequestMethod.POST, params = "edit")
    public ModelAndView editVisit(@ModelAttribute("visit") Visit visit,
                                  @ModelAttribute("selected_immersion") String selected_immersion,
                                  @ModelAttribute("selected_diverPro") Long id_diverPro,
                                  @ModelAttribute("id_diver") Long id_diver,
                                  @ModelAttribute("notes") String notes,
                                  @ModelAttribute("id_schedule") int id_schedule) {
        Diver diver = diverService.getById(id_diver);
        DiverPro diverPro = diverProService.getById(id_diverPro);
        Immersion immersion = immersionService.getById(selected_immersion);

        if (notes == null) {
            visit.setNotes("");
        }

        visit.setDiverPro(diverPro);
        visit.setDiver(diver);
        visit.setImmersion(immersion);
        visit.setSchedule(diverPro.findSchedule(id_schedule));
        diver.addVisit(visit);

        diverService.add(diver);
        diverProService.add(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + diver.getId() + "/visit/1");
        return modelAndView;
    }


    @RequestMapping(value = "/{id_visit}/{id_diver}/setSchedule", method = RequestMethod.GET)
    public ModelAndView PageSetSchedule(@PathVariable("id_visit") String id_visit,
                                        @PathVariable("id_diver") Long id_diver) {

        ModelAndView modelAndView = new ModelAndView();
        Diver diver = diverService.getById(id_diver);
        Visit visit = diver.findVisit(id_visit);
        DiverPro diverPro = visit.getDiverPro();
        LocalDate date;
        date = (visit.getDate().toLocalDate());

        modelAndView.addObject("visit", visit);
        modelAndView.addObject("date", date);
        modelAndView.addObject("schedules", diverPro.getSchedulesByDay(date));

        modelAndView.setViewName("admin/forms/form_set_schedule_to_visit");
        return modelAndView;
    }

    @RequestMapping(value = "/setSchedule", method = RequestMethod.POST)
    public ModelAndView setSchedule(@ModelAttribute("id_visit") String id_visit,
                                    @ModelAttribute("id_diver") Long id_diver,
                                    @ModelAttribute("selected_schedule") int selected_schedule) {

        ModelAndView modelAndView = new ModelAndView();
        Diver diver = diverService.getById(id_diver);
        Visit visit = diver.findVisit(id_visit);
        visit.setSchedule(visit.getDiverPro().findSchedule(selected_schedule));
        diver.addVisit(visit);
        diverService.add(diver);


        modelAndView.setViewName("redirect:/admin/" + diver.getId() + "/visit/1");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_diver}/{id}/delete_visit", method = RequestMethod.GET)
    public ModelAndView deleteVisit(@PathVariable("id_diver") Long id_diver,
                                    @PathVariable("id") String id) {
        Diver diver = diverService.getById(id_diver);
        diverService.deleteVisit(diver.findVisit(id));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/" + id_diver + "/visit/1");

        return modelAndView;
    }



}
