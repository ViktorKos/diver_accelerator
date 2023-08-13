package com.example.diver_accelerator.controller;


import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.service.*;
import com.example.diver_accelerator.transientClasses.DiverProStatistic;
import com.example.diver_accelerator.transientClasses.ImmersionStatistic;
import com.example.diver_accelerator.transientClasses.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.diver_accelerator.transientClasses.ControllerMainTools.firstMinusSecondArraysDiveCenter;
import static com.example.diver_accelerator.transientClasses.ControllerMainTools.firstMinusSecondArraysDiverPro;
import static com.example.diver_accelerator.transientClasses.ControllerMainTools.firstMinusSecondArraysSpecialization;
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    protected SpecializationService specializationService;

    @Autowired
    protected DiverService diverService;

    @Autowired
    private UnitService unitService;

    @Autowired
    protected DiverProService diverProService;

    @Autowired
    private DiveCenterService diveCenterService;

    @Autowired
    private StaffingSchemeService staffingSchemeService;


    @Autowired
    protected ImmersionService immersionService;

    protected Sort sort = new Sort();

    //Master

    @GetMapping("/instructor/{sort_num}")
    public ModelAndView allInstructorsPage(@PathVariable("sort_num") int sort_num) {
        List<DiverPro> diverPros = diverProService.diverProBySpecialization(specializationService.getById(1));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, sort_num));
        modelAndView.setViewName("master/tables/instructor");
        return modelAndView;
    }

    @GetMapping(value = "/instructor/1", params = "search")
    public ModelAndView allInstructorsWithCurrentTelephone_number(@ModelAttribute("telephone_number") int telephone_number) {

        List<DiverPro> diverProsByTelephone = new ArrayList<>();
        for (DiverPro diverPro : diverProService.diverProBySpecialization(specializationService.getById(1))) {
            if (diverPro.getTelephone_number() == telephone_number) {
                diverProsByTelephone.add(diverPro);
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverProsByTelephone, 1));
        modelAndView.setViewName("master/tables/instructor");

        return modelAndView;
    }

    @GetMapping("/{id}/edit_instructor")
    public ModelAndView editPageInstructorCountOfDeclaration(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverPro", diverProService.getById(id));
        modelAndView.setViewName("master/forms/form_instructor");
        return modelAndView;
    }

    @PostMapping(value = "/add_instructor", params = "edit")
    public ModelAndView editInstructorCountOfDeclaration(@ModelAttribute("maxCount") int maxCount,
                                                           @ModelAttribute("id_doc") Long id_doc) {
        DiverPro diverPro = diverProService.getById(id_doc);
        diverPro.setMaxCountOfDeclaration(maxCount);
        diverPro.setCountOfDeclaration();
        diverProService.add(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/instructor/1");

        return modelAndView;
    }
    @GetMapping("/add_instructor")
    public ModelAndView addPageAllInstructorCountOfDeclaration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverPro", new DiverPro());
        modelAndView.setViewName("master/forms/form_instructor");
        return modelAndView;
    }

    @PostMapping("/add_instructor")
    public ModelAndView addAllInstructorCountOfDeclaration(@ModelAttribute("maxCount") int maxCount) {

        for (DiverPro diverPro : diverProService.diverProBySpecialization(specializationService.getById(1))) {
            diverPro.setMaxCountOfDeclaration(maxCount);
            diverPro.setCountOfDeclaration();
            diverProService.add(diverPro);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/instructor/1");

        return modelAndView;
    }

    //diverPro
    @RequestMapping(value = "/diverPro/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allDiverProsPage(@PathVariable("sort_num") int sort_num) {
        List<DiverPro> diverPros = diverProService.diverProByNotSpecialization(specializationService.getById(10));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, sort_num));
        modelAndView.setViewName("master/tables/diverPro");
        return modelAndView;
    }

    @RequestMapping(value = "/diverPro/1", method = RequestMethod.GET, params = "search")
    public ModelAndView allDiverProsWithCurrentTelephone_number(@ModelAttribute("telephone_number") int telephone_number) {
        List<DiverPro> diverPros = diverProService.findTelephone_number(telephone_number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, 1));
        modelAndView.setViewName("master/tables/diverPro");

        return modelAndView;
    }

    @GetMapping("/{id_diverPro}/diverPro_info/done")
    public ModelAndView PageDiverProDone(@PathVariable("id_diverPro") Long id_diverPro,
                                       @ModelAttribute("message") String message) {

        LocalDate current_date = LocalDate.now();
        Month month = current_date.getMonth();

        DiverPro diverPro = diverProService.getById(id_diverPro);
        List<DiverProStatistic> countVisits = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            countVisits.add(new DiverProStatistic(i, diverPro.getDoneVisitsByWeek(i, month.getValue()).size()));
        }

        int maxCountImmersion = countVisits.stream()
                .max(Comparator.comparingInt(DiverProStatistic::getCount))
                .get().getCount();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("countVisits", countVisits);
        modelAndView.addObject("date", month.getValue());
        modelAndView.addObject("maxCountImmersion", maxCountImmersion);
        modelAndView.addObject("diverPro", diverProService.getById(id_diverPro));

        modelAndView.setViewName("master/tables/diverPro_info_done_visits");
      //  System.out.println(countVisits);
        return modelAndView;
    }

    @GetMapping(value = "/{id_diverPro}/diverPro_info/done", params = "search")
    public ModelAndView DiverProInfoByMonthDonePage(@PathVariable("id_diverPro") Long id_diverPro,
                                                  @ModelAttribute("message") String message,
                                                  @ModelAttribute("month") int month) {

        DiverPro diverPro = diverProService.getById(id_diverPro);
        List<DiverProStatistic> countVisits = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            countVisits.add(new DiverProStatistic(i, diverPro.getDoneVisitsByWeek(i, month).size()));
        }

        int maxCountImmersion = countVisits.stream()
                .max(Comparator.comparingInt(DiverProStatistic::getCount))
                .get().getCount();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("countVisits", countVisits);
        modelAndView.addObject("date", month);
        modelAndView.addObject("maxCountImmersion", maxCountImmersion);
        modelAndView.addObject("diverPro", diverProService.getById(id_diverPro));

        modelAndView.setViewName("master/tables/diverPro_info_done_visits");
        return modelAndView;
    }

    @GetMapping("/{id_diverPro}/diverPro_info")
    public ModelAndView PageDiverProInfo(@PathVariable("id_diverPro") Long id_diverPro,
                                       @ModelAttribute("message") String message) {

        LocalDate current_date = LocalDate.now();
        Month month = current_date.getMonth();

        DiverPro diverPro = diverProService.getById(id_diverPro);
        List<DiverProStatistic> countVisits = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            countVisits.add(new DiverProStatistic(i, diverPro.getActiveVisitsByWeek(i, month.getValue()).size()));
        }

        int maxCountImmersion = countVisits.stream()
                .max(Comparator.comparingInt(DiverProStatistic::getCount))
                .get().getCount();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("countVisits", countVisits);
        modelAndView.addObject("date", month.getValue());
        modelAndView.addObject("maxCountImmersion", maxCountImmersion);
        modelAndView.addObject("diverPro", diverProService.getById(id_diverPro));

        modelAndView.setViewName("master/tables/diverPro_info");
        return modelAndView;
    }

    @GetMapping(value = "/{id_diverPro}/diverPro_info", params = "search")
    public ModelAndView DiverProInfoByMonthPage(@PathVariable("id_diverPro") Long id_diverPro,
                                              @ModelAttribute("message") String message,
                                              @ModelAttribute("month") int month) {

        DiverPro diverPro = diverProService.getById(id_diverPro);
        List<DiverProStatistic> countVisits = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            countVisits.add(new DiverProStatistic(i, diverPro.getActiveVisitsByWeek(i, month).size()));
        }

        int maxCountImmersion = countVisits.stream()
                .max(Comparator.comparingInt(DiverProStatistic::getCount))
                .get().getCount();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("countVisits", countVisits);
        modelAndView.addObject("date", month);
        modelAndView.addObject("maxCountImmersion", maxCountImmersion);
        modelAndView.addObject("diverPro", diverProService.getById(id_diverPro));

        modelAndView.setViewName("master/tables/diverPro_info");
        return modelAndView;
    }


    //specialization
    @GetMapping("/specialization/{sort_num}")
    public ModelAndView allSpecializationPage(@PathVariable("sort_num") int sort_num) {
        List<Specialization> specializationList = specializationService.allSpecialization();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("specializationList", sort.sortSpecialization(specializationList, sort_num));
        modelAndView.setViewName("master/tables/specialization");
        return modelAndView;
    }

    @GetMapping("/add_specialization")
    public ModelAndView addPageSpecialization(@ModelAttribute("message") String message) {
        Specialization specialization = new Specialization();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("specialization", specialization);
        modelAndView.setViewName("master/forms/form_specialization");
        return modelAndView;
    }


    @PostMapping("/add_specialization")
    public ModelAndView addSpecialization(@ModelAttribute("specialization") Specialization specialization) {
        ModelAndView modelAndView = new ModelAndView();

        if (specializationService.checkId(specialization.getId())) {
            specializationService.add(specialization);
            modelAndView.setViewName("redirect:/master/specialization/1");
        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/master/add_specialization");
        }

        return modelAndView;
    }

    @GetMapping("/{id}/edit_specialization")
    public ModelAndView editPageSpecialization(@PathVariable("id") int id) {
        Specialization specialization = specializationService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("master/forms/form_specialization");
        modelAndView.addObject("specialization", specialization);
        return modelAndView;
    }

    @PostMapping(value = "/add_specialization", params = "edit")
    public ModelAndView editSpecialization(@ModelAttribute("specialization") Specialization specialization) {
        specializationService.add(specialization);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/specialization/1");

        return modelAndView;
    }

    @GetMapping("/{id}/delete_specialization")
    public ModelAndView deleteSpecialization(@PathVariable("id") int id) {
        Specialization specialization = specializationService.getById(id);
        specializationService.delete(specialization);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/specialization/1");

        return modelAndView;
    }

    //unit
    @GetMapping("/unit/{sort_num}")
    public ModelAndView allUnitsPage(@PathVariable("sort_num") int sort_num) {
        List<Unit> unitList = unitService.allUnit();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unitList", sort.sortUnit(unitList, sort_num));
        modelAndView.setViewName("master/tables/unit");
        return modelAndView;
    }

    @GetMapping("/add_unit")
    public ModelAndView addPageUnit(@ModelAttribute("message") String message) {
        Unit unit = new Unit();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("unit", unit);
        modelAndView.setViewName("master/forms/form_unit");
        return modelAndView;
    }


    @PostMapping("/add_unit")
    public ModelAndView addUnit(@ModelAttribute("unit") Unit unit) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView();
        String id_unit = SecureRandom.getInstance("SHA1PRNG").nextInt() + "";

        unit.setId(id_unit);
        unitService.add(unit);

        modelAndView.setViewName("redirect:/master/unit/1");

        return modelAndView;
    }

    @GetMapping("/{id}/edit_unit")
    public ModelAndView editPageUnit(@PathVariable("id") String id) {
        Unit unit = unitService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("master/forms/form_unit");
        modelAndView.addObject("unit", unit);
        return modelAndView;
    }

    @PostMapping(value = "/add_unit", params = "edit")
    public ModelAndView editUnit(@ModelAttribute("unit") Unit unit) {
        unitService.add(unit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/unit/1");

        return modelAndView;
    }

    @GetMapping("/{id}/delete_unit")
    public ModelAndView deleteUnit(@PathVariable("id") String id) {
        Unit unit = unitService.getById(id);
        unitService.delete(unit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/unit/1");

        return modelAndView;
    }

    @GetMapping("/{unit_id}/unit_diveCenter/{sort_num}")
    public ModelAndView allUnitDiveCentersPage(@PathVariable("sort_num") int sort_num,
                                               @PathVariable("unit_id") String unit_id) {
        Unit unit = unitService.getById(unit_id);
        List<DiveCenter> diveCenterList = unit.getDiveCentersList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diveCenterList", diveCenterList);
        modelAndView.addObject("canAdd", !diveCenterList.isEmpty());
        modelAndView.addObject("unit", unit);
        modelAndView.setViewName("master/tables/unit_diveCenter");
        return modelAndView;
    }


    @GetMapping("/{unit_id}/add_unit_to_diveCenter")
    public ModelAndView addPageDiveCenterToUnit(@ModelAttribute("message") String message,
                                                @PathVariable("unit_id") String unit_id) {

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        Unit unit = unitService.getById(unit_id);

        modelAndView.addObject("diveCenterList", firstMinusSecondArraysDiveCenter(diveCenterService.allDiveCenterWithoutUnit(), unit.getDiveCentersList()));
        modelAndView.addObject("unit", unit);
        modelAndView.setViewName("master/forms/form_diveCenter_for_unit");
        return modelAndView;
    }


    @PostMapping("/add_unit_to_diveCenter")
    public ModelAndView addDiveCenterToUnit(@ModelAttribute("diveCenter_id") String diveCenter_id,
                                            @ModelAttribute("id_unit") String id_unit) {
        ModelAndView modelAndView = new ModelAndView();

        Unit unit = unitService.getById(id_unit);

        unit.addDiveCenter(diveCenterService.getById(diveCenter_id));
        unitService.add(unit);
        modelAndView.setViewName("redirect:/master/" + id_unit + "/unit_diveCenter/1");

        return modelAndView;
    }

    @GetMapping("/{id_unit}/{diveCenter_id}/delete_diveCenter_from_unit")
    public ModelAndView deleteDiveCenterFromUnit(@PathVariable("diveCenter_id") String diveCenter_id,
                                                 @PathVariable("id_unit") String id_unit) {
        ModelAndView modelAndView = new ModelAndView();

        Unit unit = unitService.getById(id_unit);

        unit.removeDiveCenter(diveCenterService.getById(diveCenter_id));
        unitService.add(unit);
        modelAndView.setViewName("redirect:/master/" + id_unit + "/unit_diveCenter/1");

        return modelAndView;
    }

    //diveCenter
    @GetMapping("/diveCenter/{sort_num}")
    public ModelAndView allDiveCentersPage(@PathVariable("sort_num") int sort_num) {
        List<DiveCenter> diveCenterList = diveCenterService.allDiveCenter();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diveCenterList", diveCenterList);
        modelAndView.setViewName("master/tables/diveCenter");
        return modelAndView;
    }


    @GetMapping("/add_diveCenter")
    public ModelAndView addPageDiveCenter(@ModelAttribute("message") String message) {
        DiveCenter diveCenter = new DiveCenter();

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.setViewName("master/forms/form_diveCenter");
        return modelAndView;
    }


    @PostMapping("/add_diveCenter")
    public ModelAndView addDiveCenter(@ModelAttribute("diveCenter") DiveCenter diveCenter) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView();
        String id_diveCenter = SecureRandom.getInstance("SHA1PRNG").nextInt() + "";
        diveCenter.setId(id_diveCenter);


        if (diveCenterService.checkId(diveCenter.getId())) {
            diveCenterService.add(diveCenter);
            modelAndView.setViewName("redirect:/master/diveCenter/1");
        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/master/add_diveCenter");
        }

        return modelAndView;
    }


    @GetMapping("/{id}/edit_diveCenter")
    public ModelAndView editPageDiveCenter(@PathVariable("id") String id) {
        DiveCenter diveCenter = diveCenterService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("master/forms/form_diveCenter");
        modelAndView.addObject("diveCenter", diveCenter);
        return modelAndView;
    }

    @PostMapping(value = "/add_diveCenter", params = "edit")
    public ModelAndView editdiveCenter(@ModelAttribute("diveCenter") DiveCenter diveCenter) {
        diveCenterService.add(diveCenter);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/diveCenter/1");

        return modelAndView;
    }

    @GetMapping("/{id}/delete_diveCenter")
    public ModelAndView deletediveCenter(@PathVariable("id") String id) {
        DiveCenter diveCenter = diveCenterService.getById(id);
        diveCenterService.delete(diveCenter);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/diveCenter/1");

        return modelAndView;
    }

    @GetMapping("/{diveCenter_id}/diveCenter_diverPro/{sort_num}")
    public ModelAndView allDiveCenterdiverProsPage(@PathVariable("sort_num") int sort_num,
                                                 @PathVariable("diveCenter_id") String diveCenter_id) {
        DiveCenter diveCenter = diveCenterService.getById(diveCenter_id);
        List<DiverPro> diverPros = diveCenterService.getById(diveCenter_id).getDiverPros();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, sort_num));
        modelAndView.addObject("canAdd", !diveCenter.getStaffingSchemes().isEmpty());
        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.setViewName("master/tables/diveCenter_diverPro");
        return modelAndView;
    }

    @GetMapping(value = "/{diveCenter_id}/diveCenter_diverPro/1", params = "search")
    public ModelAndView alldiveCenterdiverProsWithCurrentTelephone_number(@ModelAttribute("telephone_number") int telephone_number,
                                                                        @PathVariable("diveCenter_id") String diveCenter_id) {
        DiveCenter diveCenter = diveCenterService.getById(diveCenter_id);
        List<DiverPro> diverPros = diveCenter.findByTelephone(telephone_number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("canAdd", !diveCenter.getStaffingSchemes().isEmpty());

        modelAndView.addObject("diverProList", sort.sortDiverPro(diverPros, 1));
        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.setViewName("master/tables/diveCenter_diverPro");

        return modelAndView;
    }


    @GetMapping("/{diveCenter_id}/add_diverPro_to_diveCenter")
    public ModelAndView addPagediverProTodiveCenter(@ModelAttribute("message") String message,
                                                  @PathVariable("diveCenter_id") String diveCenter_id) {

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        DiveCenter diveCenter = diveCenterService.getById(diveCenter_id);


        List<DiverPro> diverPros = new ArrayList<>();
        for (Specialization specialization : diveCenter.getSpecializationListByScheme()) {
            diverPros.addAll(diverProService.diverProBySpecialization(specialization));
        }

        modelAndView.addObject("diverPros", firstMinusSecondArraysDiverPro(diverPros, diveCenter.getDiverPros()));
        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.setViewName("master/forms/form_diverPro_for_diveCenter");
        return modelAndView;
    }


    @PostMapping("/add_diverPro_to_diveCenter")
    public ModelAndView addDiverProToDiveCenter(@ModelAttribute("diverPro_id") Long diverPro_id,
                                              @ModelAttribute("id_diveCenter") String id_diveCenter) {
        ModelAndView modelAndView = new ModelAndView();

        DiveCenter diveCenter = diveCenterService.getById(id_diveCenter);

        diveCenter.addDiverPro(diverProService.getById(diverPro_id));
        diveCenterService.add(diveCenter);
        modelAndView.setViewName("redirect:/master/" + id_diveCenter + "/diveCenter_diverPro/1");

        return modelAndView;
    }

    @GetMapping("/{diveCenter_id}/{diverPro_id}/delete_diverPro_from_diveCenter")
    public ModelAndView deleteDiverProFromDiveCenter(@PathVariable("diveCenter_id") String diveCenter_id,
                                                   @PathVariable("diverPro_id") Long diverPro_id) {
        ModelAndView modelAndView = new ModelAndView();

        DiveCenter diveCenter = diveCenterService.getById(diveCenter_id);

        diveCenter.removeDiverPro(diverProService.getById(diverPro_id));
        diveCenterService.add(diveCenter);

        modelAndView.setViewName("redirect:/master/" + diveCenter_id + "/diveCenter_diverPro/1");

        return modelAndView;
    }

    //staffing_scheme
    @GetMapping("/{diveCenter_id}/staffing_scheme/{sort_num}")
    public ModelAndView allStaffingSchemePage(@PathVariable("sort_num") int sort_num,
                                              @PathVariable("diveCenter_id") String diveCenter_id) {
        DiveCenter diveCenter = diveCenterService.getById(diveCenter_id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.addObject("staffingSchemeList",sort.sortStaffingScheme(diveCenter.getStaffingSchemes(), sort_num));
        modelAndView.setViewName("master/tables/staffing_scheme");
        return modelAndView;
    }


    @GetMapping("/{diveCenter_id}/add_staffing_scheme")
    public ModelAndView addStaffingScheme(@ModelAttribute("message") String message,
                                          @PathVariable("diveCenter_id") String diveCenter_id) {
        DiveCenter diveCenter = diveCenterService.getById(diveCenter_id);

        ModelAndView modelAndView = new ModelAndView();
        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }
        List<Specialization> specializationsList = specializationService.allSpecialization();
        specializationsList.remove(specializationService.getById(10));

        List<Specialization> specializations = firstMinusSecondArraysSpecialization(specializationsList, diveCenter.getSpecializationListByScheme());
        specializations.sort(Comparator.comparing(Specialization::getName));

        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.addObject("specializations", specializations);
        modelAndView.addObject("staffingScheme", new StaffingScheme());
        modelAndView.setViewName("master/forms/form_staffing_scheme");
        return modelAndView;
    }

    @PostMapping("/add_staffing_scheme")
    public ModelAndView addStaffingScheme(@ModelAttribute("staffingScheme") StaffingScheme staffingScheme,
                                          @ModelAttribute("id_diveCenter") String id_diveCenter,
                                          @ModelAttribute("selected_spec") int selected_spec) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView();

        DiveCenter diveCenter = diveCenterService.getById(id_diveCenter);
        staffingScheme.setNumber(SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + id_diveCenter + "_" + selected_spec);

        staffingScheme.setSpecialization(specializationService.getById(selected_spec));
        staffingScheme.setDiveCenter_s(diveCenter);

        staffingScheme.setCountDueToMaxCount(diveCenter.findBySpecialization(staffingScheme.getSpecialization()).size());
        diveCenter.addStaffingScheme(staffingScheme);

        diveCenterService.add(diveCenter);
        modelAndView.setViewName("redirect:/master/" + id_diveCenter + "/staffing_scheme/1");

        return modelAndView;
    }


    @GetMapping("/{id_diveCenter}/{id}/edit_staffing_scheme")
    public ModelAndView editPageStaffingScheme(@PathVariable("id") String id,
                                               @PathVariable("id_diveCenter") String id_diveCenter) {
        DiveCenter diveCenter = diveCenterService.getById(id_diveCenter);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("master/forms/form_staffing_scheme");
        modelAndView.addObject("diveCenter", diveCenter);
        modelAndView.addObject("specializations", specializationService.allSpecialization());
        modelAndView.addObject("staffingScheme", staffingSchemeService.getById(id));
        return modelAndView;
    }

    @PostMapping(value = "/add_staffing_scheme", params = "edit")
    public ModelAndView editStaffingScheme(@ModelAttribute("staffingScheme") StaffingScheme staffingScheme,
                                           @ModelAttribute("id_diveCenter") String id_diveCenter,
                                           @ModelAttribute("selected_spec") int selected_spec) {
        DiveCenter diveCenter = diveCenterService.getById(id_diveCenter);

        staffingScheme.setSpecialization(specializationService.getById(selected_spec));
        staffingScheme.setDiveCenter_s(diveCenterService.getById(id_diveCenter));
        staffingScheme.setCountDueToMaxCount(diveCenter.findBySpecialization(staffingScheme.getSpecialization()).size());

        staffingSchemeService.add(staffingScheme);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/" + id_diveCenter + "/staffing_scheme/1");

        return modelAndView;
    }

    @GetMapping("/{id_diveCenter}/{id}/delete_staffing_scheme")
    public ModelAndView deleteStaffingScheme(@PathVariable("id") String id,
                                             @PathVariable("id_diveCenter") String id_diveCenter) {
        DiveCenter diveCenter = diveCenterService.getById(id_diveCenter);
        diveCenter.removeStaffingScheme(id);
        diveCenterService.add(diveCenter);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/master/" + id_diveCenter + "/staffing_scheme/1");

        return modelAndView;
    }


    //immersion
    @GetMapping("/immersion")
    public ModelAndView allImmersionPage() {

        LocalDate current_date = LocalDate.now();
        Month month = current_date.getMonth();

        List<ImmersionStatistic> countImmersions = immersionService.countImmersionMonth(month.getValue());
        Long maxCountImmersion = countImmersions.size() > 0 ? countImmersions.stream()
                .max(Comparator.comparingLong(ImmersionStatistic::getCount))
                .get().getCount() : 0;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("countImmersions", countImmersions);
        modelAndView.addObject("date", month.getValue());
        modelAndView.addObject("maxCountImmersion", maxCountImmersion);
        modelAndView.setViewName("master/tables/immersion_chart");
        return modelAndView;
    }

    @GetMapping(value = "/immersion", params = "search")
    public ModelAndView allImmersionByMonthPage(@ModelAttribute("month") int month) {
        List<ImmersionStatistic> countImmersions = immersionService.countImmersionMonth(month);
        Long maxCountImmersion = countImmersions.size() > 0 ? countImmersions.stream()
                .max(Comparator.comparingLong(ImmersionStatistic::getCount))
                .get().getCount() : 0;

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("countImmersions", countImmersions);
        modelAndView.addObject("date", month);
        modelAndView.addObject("maxCountImmersion", maxCountImmersion);
        modelAndView.setViewName("master/tables/immersion_chart");
        return modelAndView;
    }
}
