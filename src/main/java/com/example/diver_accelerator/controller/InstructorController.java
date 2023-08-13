
package com.example.diver_accelerator.controller;


import com.example.diver_accelerator.entity.*;
import com.example.diver_accelerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;


import static com.example.diver_accelerator.transientClasses.ControllerMainTools.currentDate;
import static com.example.diver_accelerator.transientClasses.ControllerMainTools.dateToString;
import static com.example.diver_accelerator.transientClasses.ControllerMainTools.getIdDiverSplit;


@RestController
@RequestMapping("/diverPro1")
public class InstructorController extends DiverProController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/divers/{sort_num}", method = RequestMethod.GET)
    public ModelAndView allDiversPage(@PathVariable("sort_num") int sort_num) {
        List<Diver> divers = diverService.allDivers();
        DiverPro diverPro = getAuthDoc();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diversList", sort.sortDiver(divers, sort_num));
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.setViewName("diverPro/instructor/pages/divers");
        return modelAndView;
    }

    @RequestMapping(value = "/divers/searchTelephone_number", method = RequestMethod.GET)
    public ModelAndView allDiversWithCurrentTelephone_number(@ModelAttribute("telephone_number") int telephone_number) {
        DiverPro diverPro = getAuthDoc();
        List<Diver> divers = diverService.findTelephone_number(telephone_number);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diversList", divers);
        modelAndView.addObject("diverPro", diverPro);
        modelAndView.setViewName("diverPro/instructor/pages/divers");
        return modelAndView;
    }

    @RequestMapping(value = "/add_diver", method = RequestMethod.GET)
    public ModelAndView addPageDiver(@ModelAttribute("message") String message) {
        Diver diver = new Diver();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("diver", diver);
        modelAndView.addObject("declaration", new Declaration());

        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.setViewName("diverPro/instructor/form/create_form/new_diver");
        return modelAndView;
    }

    @RequestMapping(value = "/add_diver", method = RequestMethod.POST)
    public ModelAndView addDiver(@ModelAttribute("diver") Diver diver,
                                   @ModelAttribute("sex") String sex,
                                   @ModelAttribute("Max_deep") String Max_deep,
                                   @ModelAttribute("lvl") String lvl
                                  ) {
        ModelAndView modelAndView = new ModelAndView();


        if (diverService.checkId(diver.getId()) && DiverProService.checkId(diver.getId())) {
            diver.setSex(Integer.parseInt(sex));
            diver.setMax_deep(Integer.parseInt(Max_deep));
            diver.setDiveLevel(lvl);
            diverService.add(diver);


            modelAndView.setViewName("redirect:/diverPro1/" + diver.getId() + "/add_user");

        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/diverPro1/add_diver");
        }
        return modelAndView;
    }

    //declaration
    @GetMapping("/{Id_diver}/add_declaration")
    public ModelAndView addPageDeclaration(@PathVariable("Id_diver") Long Id_diver,
                                           @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("declaration", new Declaration());
        modelAndView.addObject("diverPro", getAuthDoc());
        System.out.println(getAuthDoc());
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

            modelAndView.setViewName("redirect:/diverPro1/" + Id_diver + "/doc_declaration");

        } else {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("redirect:/diverPro1/" + Id_diver + "/add_declaration");
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


    @GetMapping("/{Id_diver}/add_user")
    public ModelAndView addPageUser(@PathVariable("Id_diver") Long Id_diver,
                                    @ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();

        if (message.equals("y")) {
            modelAndView.addObject("message", message);
        } else {
            modelAndView.addObject("message", null);
        }

        modelAndView.addObject("user", new User());
        modelAndView.addObject("Id_diver", Id_diver);
        modelAndView.setViewName("diverPro/instructor/form/create_form/new_user");

        return modelAndView;
    }

    @PostMapping("/add_user")
    public ModelAndView addUser(@ModelAttribute("user") @Valid User user,
                                @ModelAttribute("Id_diver") Long Id_diver) throws NoSuchAlgorithmException {

        ModelAndView modelAndView = new ModelAndView();

        user.setId(SecureRandom.getInstance("SHA1PRNG").nextInt() + "_" + user.getUsername());
        user.setSelected(true);

        if (!userService.saveUser(user, 3)) {
            modelAndView.addObject("message", "y");
            modelAndView.setViewName("diverPro/instructor/form/create_form/new_user");
        } else {
            Diver diver = diverService.getById(Id_diver);
            diver.setUser(user);
            diverService.add(diver);
            modelAndView.setViewName("redirect:/diverPro1/divers/1");
        }


        return modelAndView;
    }

    //direction
    @RequestMapping(value = "/{Id_visit}/choose_action_direction", method = RequestMethod.GET)
    public ModelAndView choose_actionPageDirection(@ModelAttribute("message") String message,
                                                   @PathVariable("Id_visit") String Id_visit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Id_visit", Id_visit);
        modelAndView.setViewName("diverPro/instructor/form/choose_form/choose_action_direction");
        return modelAndView;
    }

    @RequestMapping(value = "/{Id_visit}/add_new_direction", method = RequestMethod.GET)
    public ModelAndView addPageDirection(@ModelAttribute("message") String message,
                                         @PathVariable("Id_visit") String Id_visit) {
        List<Specialization> specializationsList = specializationService.allSpecialization();
        specializationsList.remove(specializationService.getById(8));
        specializationsList.remove(specializationService.getById(9));
        specializationsList.remove(specializationService.getById(1));
        specializationsList.remove(specializationService.getById(10));

        specializationsList.sort(Comparator.comparing(Specialization::getName));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_visit", Id_visit);
        modelAndView.addObject("specializationsList", specializationsList);
        modelAndView.addObject("direction", new Direction());
        modelAndView.setViewName("diverPro/instructor/form/create_form/direction");

        return modelAndView;
    }

    @RequestMapping(value = "/add_direction", method = RequestMethod.POST)
    public ModelAndView addDirection(@ModelAttribute("selected_spec") int selected_spec,
                                     @ModelAttribute("direction") Direction direction,
                                     @ModelAttribute("Id_visit") String Id_visit) throws NoSuchAlgorithmException {
        Diver diver = diverService.getById(getIdDiverSplit(Id_visit));
        if (diver.isDirectionExists(specializationService.getById(selected_spec).getName())) {
            direction.setNumber(String.valueOf(SecureRandom.getInstance("SHA1PRNG").nextInt()));
            direction.setSpecialization(specializationService.getById(selected_spec));
            direction.setStatus(true);
            direction.setDiver(diver);
            directionService.add(direction);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/" + Id_visit + "/choose_action_lab_direction");

        return modelAndView;
    }

}
