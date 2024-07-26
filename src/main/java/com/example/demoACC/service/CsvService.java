package com.example.demoACC.service;

import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.opencsv.CSVReader;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvService {

    @PostConstruct
    public void init() {}

    public List<Mobile> getFidoMobileOffers() {
        List<Mobile> mobiles = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/fido.csv")))) {
            String[] line;
            boolean isMobile = true;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (line[0].length() == 0) break;
                if (line.length == 1) {
                    isMobile = false;
                    reader.readNext();
                    continue;
                }
                if (isMobile) {
                    Mobile mobile = new Mobile();
                    mobile.setName(line[0]);
                    mobile.setPrice(line[1]);
                    mobile.setImage(line[2]);
                    mobiles.add(mobile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobiles;
    }

    public List<Plan> getFidoPlanOffers() {
       List<Plan> plans = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/fido.csv")))) {
            String[] line;
            boolean isMobile = false;
            reader.readNext();
            while (!reader.readNext()[0].equals("Plan Name")) {
                continue;
            }

            while (((line = reader.readNext()) != null)) {
                // System.out.println(Arrays.toString(line));
                if (!isMobile && (line.length != 1)) {
                    System.out.println(Arrays.toString(line));
                    Plan plan = new Plan();
                    plan.setName(line[0]);
                    plan.setPrice(line[1]);
                    plan.setData(line[2]);
                    plans.add(plan);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return plans;
    }

    public List<Mobile> getFreedomMobilePlans() {
        List<Mobile> mobiles = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/freedom.csv")))) {
            String[] line;
            boolean isMobile = true;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (line[0].length() == 0) break;
                if (line.length == 1) {
                    isMobile = false;
                    reader.readNext();
                    continue;
                }
                if (isMobile) {
                    Mobile mobile = new Mobile();
                    mobile.setName(line[0]);
                    mobile.setPrice(line[1]);
                    mobile.setImage(line[2]);
                    mobiles.add(mobile);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return mobiles;
    }

    public List<Plan> getFreedomSimPlans() {
        List<Plan> plans = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/freedom.csv")))) {
            String[] line;
            boolean isMobile = false;
            reader.readNext();
            
            while (!reader.readNext()[0].equals("Plan Name")) {
                continue;
            }

            while (((line = reader.readNext()) != null)) {
                // System.out.println(Arrays.toString(line));
                if (!isMobile && (line.length != 1)) {
                    Plan plan = new Plan();
                    plan.setName(line[0]);
                    plan.setPrice(line[1]);
                    plan.setData(line[2]);
                    plans.add(plan);
                }
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return plans;
    }

    public List<Mobile> getKoodoMobilePlans() {
        List<Mobile> mobiles = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/koodo.csv")))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                Mobile mobile = new Mobile();
                mobile.setName(line[0]);
                mobile.setPrice(line[1]);
                mobile.setImage(line[2]);
                mobiles.add(mobile);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println(mobiles.size());
        return mobiles;
    }

    public List<Plan> getKoodoSimPlans() {
        List<Plan> plans = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/koodo.csv")))) {
            String[] line;
            boolean isMobile = false;
            reader.readNext();
            
            while (!reader.readNext()[0].equals("Plan Name")) {
                continue;
            }

            while (((line = reader.readNext()) != null)) {
                // System.out.println(Arrays.toString(line));
                if (!isMobile && (line.length != 1)) {
                    Plan plan = new Plan();
                    plan.setName(line[0]);
                    plan.setPrice(line[1]);
                    plan.setData(line[2]);
                    plans.add(plan);
                }
            }


        } catch (Exception e) {
            e.getMessage();
        }
        return plans;
    }
}