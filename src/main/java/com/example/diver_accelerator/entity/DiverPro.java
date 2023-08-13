package com.example.diver_accelerator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;


@Entity
@Getter
@Setter
@Table(name = "diverPro")
public class DiverPro extends Human {

    private Date dateWhenStartWorking;
    private int countOfDeclaration;
    private int maxCountOfDeclaration;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization", referencedColumnName = "id")
    private Specialization specialization;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "diverPro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Visit> visits = new ArrayList<>();

    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "diverPro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "diverPro_dec", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Declaration> declarations = new ArrayList<>();

    @OneToMany(mappedBy = "d_com", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "d_rate", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Rate> rates = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diveCenter")
    private DiveCenter diveCenter;

    public void changeCountOfDeclaration(int i) {
        int num = 0;

        num = this.getCountOfDeclaration() + i;
        if (this.getMaxCountOfDeclaration() >= num && num >= 0) {
            this.setCountOfDeclaration(num);
        }

    }
    public void setCountOfDeclaration() {
        this.countOfDeclaration = this.getMaxCountOfDeclaration() - declarations.size();
    }
    public void setSchedulesByRange(int day, String timeStart, String timeEnd, int interval) throws NoSuchAlgorithmException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        boolean uniq;
        while (LocalTime.parse(timeEnd).isAfter(LocalTime.parse(timeStart))) {
            Schedule schedule = new Schedule();
            schedule.setId(SecureRandom.getInstance("SHA1PRNG").nextInt());
            schedule.setDay(day);
            schedule.setTime(timeStart);

            uniq = true;
            for (Schedule s1 : this.getSchedules()) {
                if (s1.getDay() == schedule.getDay() && s1.getTime().equals(schedule.getTime())) {
                    uniq = false;
                    break;
                }
            }
            if (uniq) {
                this.addSchedule(schedule);
            }

            cal.setTime(df.parse(timeStart));
            cal.add(Calendar.MINUTE, interval);
            timeStart = df.format(cal.getTime());

        }
    }

    public List<Schedule> getSchedulesByDay(LocalDate date) {
        Calendar c = Calendar.getInstance();
        c.setTime(Date.valueOf(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        List<Schedule> scheduleByDay = new ArrayList<>();

        for (Schedule schedule : this.getSchedules()) {
            if (schedule.getDay() == dayOfWeek) {
                scheduleByDay.add(schedule);
            }
        }

        return scheduleByDay;
    }

    public int getStanding() {
        return Period.between(this.getDateWhenStartWorking().toLocalDate(), LocalDate.now()).getYears();
    }

    public Schedule findSchedule(int id) {
        for (Schedule schedule : this.getSchedules()) {
            if (schedule.getId() == id) {
                return schedule;
            }
        }

        return null;
    }

   public List<Schedule> freeSchedule(LocalDate date) {
        List<Schedule> freeSchedule;
        if (date.equals(LocalDate.now())) {
            freeSchedule = this.getSchedulesByDayAndNotAfterCurrentTime(date);
        } else {
            freeSchedule = this.getSchedulesByDay(date);
        }

        for (Visit visit : this.getVisits()) {
            for (Schedule schedule : this.getSchedules()) {
                if (!visit.getStatus() && visit.getSchedule().getId() == schedule.getId() && visit.getDate().toLocalDate().equals(date)) {
                    freeSchedule.remove(schedule);
                    break;
                }
            }
        }
        freeSchedule.sort(Comparator.comparing(Schedule::getTime));

        return freeSchedule;
    }

    public List<Schedule> getSchedulesByDayAndNotAfterCurrentTime(LocalDate date) {
        Calendar c = Calendar.getInstance();
        c.setTime(Date.valueOf(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        List<Schedule> scheduleByDay = new ArrayList<>();

        for (Schedule schedule : this.getSchedules()) {
            if (schedule.getDay() == dayOfWeek && !LocalTime.now().isAfter(LocalTime.parse(schedule.getTime()))) {
                scheduleByDay.add(schedule);
            }
        }

        return scheduleByDay;
    }

    public void addSchedule(Schedule schedule) {
        schedule.setDiverPro(this);
        this.schedules.add(schedule);
    }

    public void addComment(Comment comment) {
        comment.setD_com(this);
        this.comments.add(comment);
    }

    public void addRate(Rate rate) {
        rate.setD_rate(this);
        this.rates.add(rate);
    }

    public double getAverageRate() {
        double sum = 0;
        for (Rate rate : this.rates) {
            sum += rate.getCount();
        }
        return sum / this.rates.size();
    }

    public void addDeclaration(Declaration declaration) {
        declaration.setDiverPro_dec(this);
        this.declarations.add(declaration);
    }

  public void addVisit(Visit visit) {
        visit.setDiverPro(this);
        this.visits.add(visit);
    }

    public List<Visit> getActiveVisitsByDay(LocalDate date) {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : this.getVisits()) {
            if (!visit.getStatus() && visit.getDate().toLocalDate().equals(date)) {
                visits.add(visit);
            }
        }
        visits.sort(Comparator.comparing(o -> o.getSchedule().getTime()));

        return visits;
    }

    public List<Visit> getDoneVisits() {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : this.getVisits()) {
            if (visit.getStatus()) {
                visits.add(visit);
            }
        }

        return visits;
    }



    public List<Visit> getDoneVisitsByDay(LocalDate date) {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : this.getVisits()) {
            if (visit.getStatus() && visit.getDate().toLocalDate().equals(date)) {
                visits.add(visit);
            }
        }

        return visits;
    }


    public List<Visit> getActiveVisitsByWeek(int week, int month) {
        Calendar calendar;
        int numberWeekOfYear;
        int monthOfDate;

        List<Visit> visits = new ArrayList<>();
        for (Visit visit : this.getActiveVisits()) {
            calendar = Calendar.getInstance();

            calendar.setTime(visit.getDate());

            monthOfDate = calendar.get(Calendar.MONTH) + 1;


            if (monthOfDate == month) {
                //System.out.println(visit.getDate());

                calendar = Calendar.getInstance();
                calendar.setFirstDayOfWeek(Calendar.MONDAY);
                calendar.setMinimalDaysInFirstWeek(4);
                calendar.setTime(visit.getDate());
                numberWeekOfYear = calendar.get(Calendar.WEEK_OF_MONTH);
                //System.out.println(numberWeekOfYear);

                if (numberWeekOfYear == week) {
                    visits.add(visit);
                }
            }
        }
        return visits;
    }

   public List<Visit> getDoneVisitsByWeek(int week, int month) {
        Calendar calendar;
        int numberWeekOfYear;
        int monthOfDate;

        List<Visit> visits = new ArrayList<>();
        for (Visit visit : this.getDoneVisits()) {
            calendar = Calendar.getInstance();

            calendar.setTime(visit.getDate());

            monthOfDate = calendar.get(Calendar.MONTH) + 1;


            if (monthOfDate == month) {
                //System.out.println(visit.getDate());

                calendar = Calendar.getInstance();
                calendar.setFirstDayOfWeek(Calendar.MONDAY);
                calendar.setMinimalDaysInFirstWeek(4);
                calendar.setTime(visit.getDate());
                numberWeekOfYear = calendar.get(Calendar.WEEK_OF_MONTH);
                //System.out.println(numberWeekOfYear);

                if (numberWeekOfYear == week) {
                    visits.add(visit);
                }
            }
        }

        return visits;
    }


    public List<Visit> getDoneVisitsByMonth(LocalDate date) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(Date.valueOf(date));
        int month = c1.get(Calendar.MONTH);

        List<Visit> visits = new ArrayList<>();
        for (Visit visit : this.getVisits()) {
            Calendar c = Calendar.getInstance();
            c.setTime(visit.getDate());
            if (visit.getStatus() && c.get(Calendar.MONTH) == month) {
                visits.add(visit);
            }
        }

        return visits;
    }

    public List<Visit> getActiveVisits() {
        List<Visit> active = new ArrayList<>();
        for (Visit visit : this.visits) {
            if (!visit.getStatus()) {
                active.add(visit);
            }
        }
        active.sort(Collections.reverseOrder(Comparator.comparing(Visit::getDate)));
        return active;
    }

    public List<Visit> expiredVisits() {
        List<Visit> expired = this.getActiveVisits();
        for (Visit visit : this.visits) {
            if (visit.getDate().equals(Date.valueOf(LocalDate.now().toString())) || visit.getDate().after(Date.valueOf(LocalDate.now().toString()))) {
                expired.remove(visit);
            }
        }
        return expired;
    }

    public void removeExpiredVisits() {
        List<Visit> expired = this.expiredVisits();
        for (Visit visit : expired) {
            this.visits.remove(visit);
        }
    }

    public List<User> getUsers(List<User> allUsers) {
        List<User> diverProUsers = new ArrayList<>();
        int id;

        switch (this.getSpecialization().getId()) {
            case 1:
                id = 1;
                break;

            case 8:
                id = 5;
                break;

            case 9:
                id = 6;
                break;

            case 10:
                id = 7;
                break;

            default:
                id = 4;
                break;
        }

        for (User user : allUsers) {
            Optional<Role> c_role = user.getRoles().stream().findFirst();
            if (c_role.orElse(new Role()).getId()==id && !user.isSelected()) {
                diverProUsers.add(user);
            }
        }
        return diverProUsers;
    }


}
