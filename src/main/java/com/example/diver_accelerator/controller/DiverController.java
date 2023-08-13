package com.example.diver_accelerator.controller;

import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.example.diver_accelerator.transientClasses.ControllerMainTools.currentDate;
import static com.example.diver_accelerator.transientClasses.ControllerMainTools.dateToString;

@RestController
@RequestMapping("/diver")
@PreAuthorize("hasRole('ROLE_DIVER')")
public class DiverController {

    protected DiverService diverService;

    protected DiverProService diverProService;

    @Autowired
    private UserService userService;

    @Autowired
    private DirectionService directionService;

    @Autowired
    protected SpecializationService specializationService;

    @Autowired
    public void setDiverService(DiverService diverService) {
        this.diverService = diverService;
    }

    @Autowired
    public void setDiverProService(DiverProService diverProService) {
        this.diverProService = diverProService;
    }

    public void deleteExpiredVisits(Diver diver) {
        for (Visit visit : diver.expiredVisits()) {
            if (!Objects.equals(visit.getDiverPro().getSpecialization().getName(), specializationService.getById(1).getName())) {
                diver.findNotActiveDirection(visit.getDiverPro().getSpecialization()).setStatus(true);
            }
            diverService.deleteVisit(visit);

        }
        diver.removeExpiredVisits();
    }


    //diver
    public Diver getAuthDiver() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return diverService.diverByUser(user);
    }


    @GetMapping("/getUserInfo")
    public ResponseEntity<?> userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{sort_int}", method = RequestMethod.GET)
    public ModelAndView allDoneVisits(@PathVariable("sort_int") int sort_int) {

        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("visitsList", diver.getDoneVisits(sort_int));

        modelAndView.setViewName("diver/pages/visits");

        return modelAndView;
    }

    @RequestMapping(value = "/allActiveVisits", method = RequestMethod.GET)
    public ModelAndView allActiveVisits() {

        Diver diver = getAuthDiver();
        deleteExpiredVisits(diver);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("visitsList", diver.getActiveVisits());

        modelAndView.setViewName("diver/pages/active_visits");

        return modelAndView;
    }

    //graph
    @GetMapping("/graph")
    public ModelAndView PageGraph() {
        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("visitList", diver.getDoneVisits(1));
        modelAndView.addObject("diseaseList", diver.getImmersionDoneVisits());
        modelAndView.addObject("specializationList", diver.getSpecializationDoneVisits());
        modelAndView.setViewName("diver/pages/graph");

        return modelAndView;
    }

    //direction

    @RequestMapping(value = "/directions", method = RequestMethod.GET)
    public ModelAndView allDirection() {

        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();

        boolean exist = diver.findActiveVisitBySpecialization(specializationService.getById(1));

        modelAndView.addObject("exist", exist);
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("declaration", diver.getDeclaration());
        modelAndView.addObject("directionsList", diver.getActiveDirectionsWithoutTests());
        modelAndView.setViewName("diver/pages/directions");

        return modelAndView;
    }

    //tests
    @GetMapping(value = "/tests")
    public ModelAndView allTestDirections() {
        Diver diver = getAuthDiver();
        List<Direction> directions = directionService.getActiveBySpecializationAndDiver(specializationService.getById(8), diver);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("directionsList", directions);
        modelAndView.addObject("diver", diver);
        modelAndView.setViewName("diver/pages/test_directions");
        return modelAndView;
    }

    @GetMapping("/doneTests")
    public ModelAndView allTests() {
        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("testList", diver.getTests());
        modelAndView.addObject("diver", diver);
        modelAndView.setViewName("diver/pages/tests");
        return modelAndView;
    }

    @GetMapping("/test/{test_id}")
    public ModelAndView TestPage(@PathVariable("test_id") String test_id) {
        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test", diver.findTest(test_id));
        modelAndView.setViewName("diver/pages/test");
        return modelAndView;
    }

    //symptoms
    @GetMapping(value = "/symptoms/{sort_int}")
    public ModelAndView allSymptoms(@PathVariable("sort_int") int sort_int) {
        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("symptomsList", diver.getSymptomsHistories());
        modelAndView.setViewName("diver/pages/symptoms");

        return modelAndView;
    }

    @GetMapping(value = "/symptom/{id_symptom}")
    public ModelAndView PageSymptom(@ModelAttribute("message") String message,
                                    @PathVariable("id_symptom") String id_symptom) {

        Diver diver = getAuthDiver();
        SymptomsHistory record = diver.findSymptom(id_symptom);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("record", record);
        modelAndView.setViewName("diver/pages/symptom");

        return modelAndView;
    }

//diverPro_info

    @GetMapping("/{id_diverPro}/diverPro_info")
    public ModelAndView PagediverProInfo(@PathVariable("id_diverPro") Long id) {

        ModelAndView modelAndView = new ModelAndView();
        DiverPro diverPro = diverProService.getById(id);

        modelAndView.addObject("diverPro", diverPro);

        modelAndView.setViewName("diver/pages/diverPro_info");

        return modelAndView;
    }


    @GetMapping("/{id_diverPro}/diverPro_info/rate/{rate}")
    public ModelAndView addRate(@PathVariable("rate") int count,
                                @PathVariable("id_diverPro") Long id) throws NoSuchAlgorithmException {

        ModelAndView modelAndView = new ModelAndView();
        DiverPro diverPro = diverProService.getById(id);
        Diver diver = getAuthDiver();

        Rate rate = new Rate();
        String id_rate = SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + diverPro.getId() + "_" + getAuthDiver().getId();
        if (diver.getRateByDiverPro(diverPro) != null) {
            rate.setId(diver.getRateByDiverPro(diverPro).getId());
        } else {
            rate.setId(id_rate);
        }
        rate.setD_rate(diverPro);
        rate.setDiver(diver);
        rate.setCount(count);
        diverPro.addRate(rate);
        diverProService.add(diverPro);

        List<Schedule> schedules = diverPro.freeSchedule(LocalDate.now());
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("date", Date.valueOf(LocalDate.now()));
        modelAndView.addObject("dateV", dateToString(Date.valueOf(LocalDate.now())));
        modelAndView.addObject("schedules", schedules);
        modelAndView.setViewName("diver/pages/diverPro_info");

        return modelAndView;
    }

    @PostMapping("/add_comment")
    public ModelAndView addComment(@ModelAttribute("diverPro_id") Long diverPro_id,
                                   @ModelAttribute("comment") Comment comment) throws NoSuchAlgorithmException {

        ModelAndView modelAndView = new ModelAndView();
        DiverPro diverPro = diverProService.getById(diverPro_id);

        String id_rate = SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + diverPro.getId() + "_" + getAuthDiver().getId();
        comment.setId(id_rate);
        comment.setD_com(diverPro);
        comment.setDiver(getAuthDiver());
        comment.setDate(currentDate());

        diverPro.addComment(comment);
        diverProService.add(diverPro);

        modelAndView.setViewName("redirect:/diver/" + diverPro_id + "/diverPro_info");

        return modelAndView;
    }

//schedule
    @RequestMapping(value = "/{id_diverPro}/schedule", method = RequestMethod.GET)
    public ModelAndView PageSchedule(@PathVariable("id_diverPro") Long id) {

        ModelAndView modelAndView = new ModelAndView();
        DiverPro diverPro = diverProService.getById(id);
        List<Schedule> schedules = diverPro.freeSchedule(LocalDate.now());
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("date", Date.valueOf(LocalDate.now()));
        modelAndView.addObject("dateV", dateToString(Date.valueOf(LocalDate.now())));
        modelAndView.addObject("schedules", schedules);
        modelAndView.setViewName("diver/pages/schedule");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_diverPro}/schedulesByDay", method = RequestMethod.GET)
    public ModelAndView schedulesByDay(@ModelAttribute("date1") String date,
                                       @PathVariable("id_diverPro") Long id) {

        ModelAndView modelAndView = new ModelAndView();
        DiverPro diverPro = diverProService.getById(id);
        List<Schedule> schedules = diverPro.freeSchedule(Date.valueOf(date).toLocalDate());
        modelAndView.addObject("date", Date.valueOf(date));
        modelAndView.addObject("dateV", dateToString(Date.valueOf(date)));
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("schedules", schedules);

        modelAndView.setViewName("diver/pages/schedule");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_diverPro}/add_visit/{date1}/{id_schedule}", method = RequestMethod.GET)
    public ModelAndView addPageVisit(@PathVariable("id_diverPro") Long id,
                                     @PathVariable("date1") String date,
                                     @PathVariable("id_schedule") int id_schedule) {

        ModelAndView modelAndView = new ModelAndView();
        DiverPro diverPro = diverProService.getById(id);
        modelAndView.addObject("visit", new Visit());
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.addObject("date", date);
        modelAndView.addObject("schedule", diverPro.findSchedule(id_schedule));

        modelAndView.setViewName("diver/pages/new_visit");

        return modelAndView;
    }


    @RequestMapping(value = "/add_visit_act", method = RequestMethod.POST)
    public ModelAndView addVisit(@ModelAttribute("visit") Visit visit,
                                 @ModelAttribute("id_diverPro") Long id,
                                 @ModelAttribute("id_schedule") int id_schedule) throws NoSuchAlgorithmException {
        Diver diver = getAuthDiver();
        DiverPro diverPro = diverProService.getById(id);

        String id_visit = SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + diver.getId();
        visit.setNumber(id_visit);
        visit.setStatus(false);
        visit.setDiver(diver);
        visit.setDiverPro(diverPro);
        visit.setSchedule(diverPro.findSchedule(id_schedule));

        if (!Objects.equals(diverPro.getSpecialization().getName(), specializationService.getById(1).getName())) {
            diver.findActiveDirection(diverPro.getSpecialization()).setStatus(false);
        }

        diverPro.addVisit(visit);

        diverService.add(diver);
        diverProService.add(diverPro);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/diver/directions");

        return modelAndView;
    }

    @RequestMapping(value = "/deleteVisit/{id_visit}", method = RequestMethod.GET)
    public ModelAndView deleteVisit(@PathVariable("id_visit") String id_visit) {
        Diver diver = getAuthDiver();
        Visit visit = diver.findVisit(id_visit);
        DiverPro diverPro = visit.getDiverPro();

        if (!Objects.equals(diverPro.getSpecialization().getName(), specializationService.getById(1).getName())) {
            diver.findNotActiveDirection(diverPro.getSpecialization()).setStatus(true);
            diverService.add(diver);
        }

        diverService.deleteVisit(visit);



        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/diver/directions");

        return modelAndView;
    }

    @RequestMapping(value = "/diverPros", method = RequestMethod.GET)
    public ModelAndView alldiverPros() {

        Diver diver = getAuthDiver();
        List<DiverPro> diverPros = diverProService.allDiverPros();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("diverProsList", diverPros);
        modelAndView.setViewName("diver/pages/diverPros");

        return modelAndView;
    }

    @RequestMapping(value = "/{specialization}/diverProsBySpecialization", method = RequestMethod.GET)
    public ModelAndView alldiverProsBySpecialization(@PathVariable("specialization") int specialization) {

        Diver diver = getAuthDiver();
        List<DiverPro> diverPros = diverProService.diverProBySpecialization(specializationService.getById(specialization));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("diver", diver);
        modelAndView.addObject("diverProsList", diverPros);

        modelAndView.setViewName("diver/pages/diverPros");

        return modelAndView;
    }

    @RequestMapping(value = "/{id_visit}/visit", method = RequestMethod.GET)
    public ModelAndView PageVisit(@ModelAttribute("message") String message,
                                  @PathVariable("id_visit") String id_visit) {
        Diver diver = getAuthDiver();
        Visit visit = diver.findVisit(id_visit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("visit", visit);
        modelAndView.addObject("id_diver", diver.getId());
        modelAndView.setViewName("diver/pages/visit");

        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView pagediver() {
        Diver diver = getAuthDiver();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diver", diver);
        modelAndView.setViewName("diver/pages/profile");

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
        modelAndView.setViewName("redirect:/diver/profile");

        return modelAndView;
    }
}
