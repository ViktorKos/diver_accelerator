package com.example.diver_accelerator.controller;

import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.service.*;
import com.example.diver_accelerator.transientClasses.Equipment;
import com.example.diver_accelerator.transientClasses.Recipe;
import com.example.diver_accelerator.transientClasses.Recreation_list;
import com.example.diver_accelerator.transientClasses.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.example.diver_accelerator.transientClasses.ControllerMainTools.*;


@Controller
@SessionAttributes(value = {"recipeJSP", "listJSP"})
public class DiverProController {

    protected DiverService diverService;
    protected DiverProService DiverProService;
    @Autowired
    private UserService userService;

    @Autowired
    protected SpecializationService specializationService;

    @Autowired
    protected EquipmentCatalogService equipmentCatalogService;

    @Autowired
    protected DirectionService directionService;

    @Autowired
    private ImmersionService ImmersionService;

    @Autowired
    protected TestTypeService testTypeService;

    protected Sort sort = new Sort();

    @Autowired
    public void setDiverService(DiverService diverService) {
        this.diverService = diverService;
    }

    @Autowired
    public void setDiverProService(DiverProService diverProService) {
        this.DiverProService = diverProService;
    }

    public void deleteExpiredVisits(DiverPro diverPro) {
        for (Visit visit : diverPro.expiredVisits()) {
            if (!Objects.equals(visit.getDiverPro().getSpecialization().getName(), specializationService.getById(1).getName())) {
                visit.getDiver().findNotActiveDirection(visit.getDiverPro().getSpecialization()).setStatus(true);
            }
           diverService.deleteVisit(visit);
        }
        diverPro.removeExpiredVisits();
    }

    //diverPro
    public  DiverPro getAuthDoc() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return DiverProService.diverProByUser(user);
    }

    @RequestMapping(value = "/divers/DiverPro", method = RequestMethod.GET)
    public ModelAndView PageDiverPro() {
        DiverPro DiverPro = getAuthDoc();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("DiverPro", DiverPro);
        modelAndView.addObject("visitsList", DiverPro.getDoneVisitsByMonth(LocalDate.now()));
        modelAndView.addObject("todayVisitsList", DiverPro.getDoneVisitsByDay(LocalDate.now()));
        modelAndView.addObject("standing", DiverPro.getStanding());
        modelAndView.setViewName("DiverPro/pages/DiverPro");

        return modelAndView;
    }

    //diver
    @RequestMapping(value = "/{id_visit}/visits/edit_diver", method = RequestMethod.GET)
    public ModelAndView editPagediver(@PathVariable("id_visit") String id_visit,
                                        @ModelAttribute("message") String message) {
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("diverPro/form/edit_form/edit_diver");
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("id_visit", id_visit);
        return modelAndView;
    }

    @RequestMapping(value = "/edit_diver", method = RequestMethod.POST)
    public ModelAndView editdiver(@ModelAttribute("diver") Diver diver,
                                    @ModelAttribute("sex") String sex,
                                    @ModelAttribute("id_visit") String id_visit,
                                    @ModelAttribute("user_id") String user_id) {
        diver.setSex(Integer.parseInt(sex));
        if (!user_id.isEmpty()) {
            diver.setUser(userService.findUserById(user_id));
        }
        diverService.add(diver);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/" + id_visit + "/visits/1");

        return modelAndView;
    }

    //visits
    @RequestMapping(value = "/today_visits", method = RequestMethod.GET)
    public ModelAndView today_Visits() {
        DiverPro DiverPro = getAuthDoc();
        deleteExpiredVisits(DiverPro);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("visitsList", DiverPro.getActiveVisitsByDay(LocalDate.now()));
        modelAndView.addObject("DiverPro", DiverPro);


        modelAndView.setViewName("DiverPro/pages/today_visits");

        return modelAndView;
    }

    @RequestMapping(value = "/visitsByDay", method = RequestMethod.GET)
    public ModelAndView visitsByDay(@ModelAttribute("date1") String date) {
        DiverPro DiverPro = getAuthDoc();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("visitsList", DiverPro.getActiveVisitsByDay(Date.valueOf(date).toLocalDate()));
        modelAndView.addObject("DiverPro", DiverPro);
        modelAndView.setViewName("DiverPro/pages/today_visits");

        return modelAndView;
    }


    @RequestMapping(value = "/{id_visit}/visits/{sort_int}", method = RequestMethod.GET)
    public ModelAndView allVisits(@PathVariable("id_visit") String id_visit,
                                  @PathVariable("sort_int") int sort_int) {
        DiverPro DiverPro = getAuthDoc();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("isActive", !diver.findVisit(id_visit).getStatus());
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("visitsList", diver.getDoneVisits(sort_int));

        modelAndView.addObject("DiverPro", DiverPro);
        modelAndView.setViewName("diverPro/pages/visits");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_visit}/symptoms/{sort_int}", method = RequestMethod.GET)
    public ModelAndView allSymptoms(@PathVariable("id_visit") String id_visit,
                                    @PathVariable("sort_int") int sort_int) {
        DiverPro DiverPro = getAuthDoc();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("isActive", !diver.findVisit(id_visit).getStatus());
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("symptomsList", diver.getSymptomsHistories());
        modelAndView.addObject("DiverPro", DiverPro);
        modelAndView.setViewName("diverPro/pages/symptoms");

        return modelAndView;
    }

    @RequestMapping(value = "{id_c_visit}/{id_symptom}/symptom", method = RequestMethod.GET)
    public ModelAndView PageSymptom(@ModelAttribute("message") String message,
                                    @PathVariable("id_symptom") String id_symptom,
                                    @PathVariable("id_c_visit") String id_c_visit) {

        Diver diver = diverService.getById(getIdDiverSplit(id_c_visit));
        SymptomsHistory record = diver.findSymptom(id_symptom);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_c_visit);
        modelAndView.addObject("record", record);
        modelAndView.setViewName("diverPro/pages/symptom");

        return modelAndView;
    }
    //declaration
    @GetMapping("/{Id_diver}/add_declaration")
    public ModelAndView addPageDeclaration(@PathVariable("Id_diver") Long Id_diver,
                                           @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("declaration", new Declaration());
        modelAndView.addObject("diverPro", getAuthDoc());
        modelAndView.addObject("Id_diver", Id_diver);

        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.setViewName("diverPro/instructor/form/create_form/new_declaration");
        return modelAndView;
    }

    @PostMapping("/add_declaration")
    public ModelAndView addDeclaration(@ModelAttribute("Id_diver") Long Id_diver,
                                       @ModelAttribute("declaration") Declaration declaration,
                                       @ModelAttribute("consent") String consent) {
        ModelAndView modelAndView = new ModelAndView();
        boolean isConsent = Integer.parseInt(consent) == 1;

        if (isConsent) {
            DiverPro diverPro = getAuthDoc();
            Diver diver = diverService.getById(Id_diver);

            declaration.setDiverPro_dec(diverPro);
            declaration.setConsent(true);
            declaration.setDate(currentDate());
            declaration.setDiver(diver);
            declaration.setId(diver.getId() + "_" + diverPro.getId());

            diverPro.addDeclaration(declaration);
            diverPro.setCountOfDeclaration();

            DiverProService.add(diverPro);

            diver.setDeclaration(declaration);
            diverService.add(diver);

            modelAndView.setViewName("redirect:/diverPro/" + Id_diver + "/doc_declaration");

        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/diverPro/" + Id_diver + "/add_declaration");
        }
        return modelAndView;
    }

    @GetMapping("/{Id_diver}/doc_declaration")
    public ModelAndView documentPageDeclaration(@PathVariable("Id_diver") Long Id_diver,
                                                @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("declaration", diverService.getById(Id_diver).getDeclaration());
        modelAndView.addObject("date", dateToString(diverService.getById(Id_diver).getDeclaration().getDate()));


        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.setViewName("diverPro/instructor/document/declaration");
        return modelAndView;
    }

    //graph
    @GetMapping("/{id_visit}/graph")
    public ModelAndView PageGraph(@PathVariable("id_visit") String id_visit) {
        DiverPro DiverPro = getAuthDoc();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("isActive", !diver.findVisit(id_visit).getStatus());
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("visitList", diver.getDoneVisits(1));
        modelAndView.addObject("ImmersionList", diver.getImmersionDoneVisits());
        modelAndView.addObject("specializationList", diver.getSpecializationDoneVisits());
        modelAndView.addObject("DiverPro", DiverPro);
        modelAndView.setViewName("diverPro/pages/graph");

        return modelAndView;
    }


    @RequestMapping(value = "/{id_visit}/add_visit", method = RequestMethod.GET)
    public ModelAndView addPageVisit(@ModelAttribute("message") String message,
                                     @PathVariable("id_visit") String id_visit) {
        List<Immersion> Immersions = ImmersionService.allImmersion();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("visit", diver.findVisit(id_visit));
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("ImmersionsList", sort.sortImmersion(Immersions, 2));
        modelAndView.addObject("notes", "");

        modelAndView.setViewName("diverPro/form/create_form/new_visit");

        return modelAndView;
    }

    @RequestMapping(value = "/add_visit_act", method = RequestMethod.POST)
    public ModelAndView addVisit(@ModelAttribute("visit") Visit visit,
                                 @ModelAttribute("selected_Immersion") String selected_Immersion,
                                 @ModelAttribute("notes") String notes,
                                 @ModelAttribute("id_visit") String id_visit,
                                 @ModelAttribute("id_schedule") int id_schedule) {
        DiverPro DiverPro = getAuthDoc();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));
        Immersion Immersion = ImmersionService.getById(selected_Immersion);

        visit.setNumber(id_visit);
        visit.setSchedule(DiverPro.findSchedule(id_schedule));

        if (notes == null) {
            visit.setNotes("");
        }

        visit.setDiverPro(DiverPro);
        visit.setDiver(diver);
        visit.setImmersion(Immersion);
        visit.setStatus(true);
        diver.addVisit(visit);

        diverService.add(diver);
        DiverProService.add(DiverPro);

        ModelAndView modelAndView = new ModelAndView();
        if (DiverPro.getSpecialization() == specializationService.getById(1)) {
            modelAndView.setViewName("redirect:/diverPro1/" + visit.getNumber() + "/choose_action_direction");
        } else {
            modelAndView.setViewName("redirect:/" + visit.getNumber() + "/choose_action_lab_direction");
        }


        return modelAndView;
    }

    @RequestMapping(value = "{id_c_visit}/{id_visit}/visit", method = RequestMethod.GET)
    public ModelAndView PageVisit(@ModelAttribute("message") String message,
                                  @PathVariable("id_visit") String id_visit,
                                  @PathVariable("id_c_visit") String id_c_visit) {

        Diver diver = diverService.getById(getIdDiverSplit(id_c_visit));
        Visit visit = diver.findVisit(id_visit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_c_visit);
        modelAndView.addObject("visit", visit);
        modelAndView.setViewName("diverPro/pages/visit");

        return modelAndView;
    }



    //recipe
    @RequestMapping(value = "/{id_visit}/choose_action_med", method = RequestMethod.GET)
    public ModelAndView choose_actionMedPage(@ModelAttribute("message") String message,
                                             @ModelAttribute("recipeJSP") Recipe recipe,
                                             @PathVariable("id_visit") String id_visit) {
        recipe.setEquipmentList(new ArrayList<>());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("recipeJSP", new Recipe());
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.setViewName("diverPro/form/choose_form/choose_action_med");
        return modelAndView;
    }

    @ModelAttribute("recipeJSP")
    public Recipe createRecipe() {
        return new Recipe();
    }

    @RequestMapping(value = "/{id_visit}/add_new_equipment", method = RequestMethod.GET)
    public ModelAndView addPageequipment(@ModelAttribute("message") String message,
                                        @ModelAttribute("recipeJSP") Recipe recipe,
                                        @PathVariable("id_visit") String id_visit) {

        List<EquipmentCatalog> equipmentCatalogList = diverService.allEquipments();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("equipmentCatalogList", sort.sortEquipmentCatalog(equipmentCatalogList, 2));
        modelAndView.addObject("recipeJSP", recipe);
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.setViewName("diverPro/form/create_form/new_equipment");

        return modelAndView;
    }

    @RequestMapping(value = "/add_new_equipment_act", method = RequestMethod.POST, params = "add_new_equipment")
    public ModelAndView addNewEquipment(@ModelAttribute("equipment") Equipment equipment,
                                       @ModelAttribute("selected_equipment") String selected_equipment,
                                       @ModelAttribute("recipeJSP") Recipe recipe,
                                       @ModelAttribute("id_visit") String id_visit) {
        equipment.setName(selected_equipment);
        recipe.addEquipment(equipment);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/" + id_visit + "/add_new_equipment");
        return modelAndView;
    }


    @RequestMapping(value = "/add_new_equipment_act", method = RequestMethod.POST, params = "safe_Recipe")
    public ModelAndView safeRecipe(@ModelAttribute("recipeJSP") Recipe recipe,
                                   @ModelAttribute("id_visit") String id_visit) {
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));
        Visit visit = diver.findVisit(id_visit);

        visit.setEquipmentByList(recipe.getEquipmentList());
        diver.setCount_of_recipe(diver.getCount_of_recipe() + 1);
        diverService.add(diver);
        recipe.setVisit(visit);

        ModelAndView modelAndView = new ModelAndView();
        if (recipe.getEquipmentList().isEmpty()) {
            modelAndView.setViewName("redirect:/" + id_visit + "/choose_action_list");
        } else {
            modelAndView.setViewName("redirect:/" + id_visit + "/recipe");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/{id_visit}/recipe", method = RequestMethod.GET)
    public ModelAndView RecipePage(@ModelAttribute("message") String message,
                                   @ModelAttribute("recipeJSP") Recipe recipe,
                                   @PathVariable("id_visit") String id_visit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("date", dateToString(recipe.getVisit().getDate()));
        modelAndView.setViewName("diverPro/document/recipe");
        return modelAndView;
    }

    //Recreation_list
    @RequestMapping(value = "/{id_visit}/choose_action_recreation_list", method = RequestMethod.GET)
    public ModelAndView choose_actionPageRecreation_list(@ModelAttribute("message") String message,
                                                   @PathVariable("id_visit") String id_visit) {
        boolean is_family_role = getAuthDoc().getSpecialization().getId() == 1;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("is_family_role", is_family_role);

        modelAndView.setViewName("diverPro/form/choose_form/choose_action_recreation_list");
        return modelAndView;
    }

    @RequestMapping(value = "/{id_visit}/add_recreation_list", method = RequestMethod.GET)
    public ModelAndView addPageRecreation_list(@ModelAttribute("message") String message,
                                         @PathVariable("id_visit") String id_visit) {

        List<Immersion> immersions = ImmersionService.allImmersion();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));
        Visit visit = diver.findVisit(id_visit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("visit", visit);
        modelAndView.addObject("ImmersionsList", immersions);
        modelAndView.addObject("Recreation_listJSP", new Recreation_list());
        modelAndView.setViewName("diverPro/form/create_form/new_recreation_list");

        return modelAndView;
    }

    @ModelAttribute("Recreation_listJSP")
    public Recreation_list createRecreation_list() {
        return new Recreation_list();
    }

    //сравнение дат
    @RequestMapping(value = "/add_recreation_list_act", method = RequestMethod.POST)
    public ModelAndView addList(@ModelAttribute("selected_immersion1") String selected_Immersion1,
                                     @ModelAttribute("Recreation_listJSP") Recreation_list recreation_list,
                                     @ModelAttribute("id_visit") String id_visit) {
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));
        Visit visit = diver.findVisit(id_visit);
        Immersion immersion = ImmersionService.getById(selected_Immersion1);

        diver.setCount_of_recreation_list(diver.getCount_of_recreation_list() + 1);
        diverService.add(diver);
        recreation_list.setVisit(visit);
        recreation_list.setStart_immersion(immersion);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/" + id_visit + "/list");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_visit}/list", method = RequestMethod.GET)
    public ModelAndView list(@ModelAttribute("message") String message,
                                  // @ModelAttribute("recipeJSP") Recipe recipe,
                                  @ModelAttribute("Recreation_listJSP") Recreation_list recreation_list,
                                  @PathVariable("id_visit") String id_visit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Recreation_list", recreation_list);
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("date", dateToString(recreation_list.getVisit().getDate()));
        modelAndView.addObject("start_date", dateToString(recreation_list.getStart_date()));
        modelAndView.setViewName("diverPro/document/recreation_list");
        return modelAndView;
    }

    //directionToLab
    @GetMapping(value = "/{id_visit}/choose_action_lab_direction")
    public ModelAndView choose_actionPageLabDirection(@ModelAttribute("message") String message,
                                                      @PathVariable("id_visit") String id_visit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.setViewName("diverPro/form/choose_form/choose_action_lab_direction");
        return modelAndView;
    }

    @GetMapping(value = "/{id_visit}/add_new_lab_direction")
    public ModelAndView addPageLabDirection(@ModelAttribute("message") String message,
                                            @PathVariable("id_visit") String id_visit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("specialization", specializationService.getById(8));
        modelAndView.addObject("testTypeList", testTypeService.allTestTypes());
        modelAndView.addObject("direction", new Direction());
        modelAndView.setViewName("diverPro/form/create_form/new_lab_direction");

        return modelAndView;
    }

    @PostMapping(value = "/add_lab_direction")
    public ModelAndView addLabDirection(@ModelAttribute("selected_type") String selected_type,
                                        @ModelAttribute("direction") Direction direction,
                                        @ModelAttribute("id_visit") String id_visit) throws NoSuchAlgorithmException {
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));
        if (diver.isTestTypeExists(testTypeService.getTestsTypeById(selected_type).getName())) {
            direction.setNumber(String.valueOf(SecureRandom.getInstance("SHA1PRNG").nextInt()));
            direction.setSpecialization(specializationService.getById(8));
            direction.setStatus(true);

            System.out.println(selected_type);
            System.out.println(testTypeService.getTestsTypeById(selected_type));
            direction.setTestsType(testTypeService.getTestsTypeById(selected_type));

            direction.setDiver(diver);
            directionService.add(direction);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/" + id_visit + "/choose_action_med");

        return modelAndView;
    }

    //tests
    @GetMapping("/{id_visit}/tests/{sort_int}")
    public ModelAndView allTests(@PathVariable("id_visit") String id_visit,
                                 @PathVariable("sort_int") int sort_int) {
        DiverPro diverPro = getAuthDoc();
        Diver diver = diverService.getById(getIdDiverSplit(id_visit));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("isActive", !diver.findVisit(id_visit).getStatus());
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("id_visit", id_visit);
        modelAndView.addObject("testList", diver.getTests());
        modelAndView.addObject("DiverPro", diverPro);
        modelAndView.setViewName("diverPro/pages/tests");
        return modelAndView;
    }

    @GetMapping("/{id_c_visit}/{test_id}/test")
    public ModelAndView TestPage(@PathVariable("test_id") String test_id,
                                 @PathVariable("id_c_visit") String id_c_visit) {
        Diver diver = diverService.getById(getIdDiverSplit(id_c_visit));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", id_c_visit);
        modelAndView.addObject("test", diver.findTest(test_id));

        modelAndView.setViewName("diverPro/pages/test");
        return modelAndView;
    }
}
