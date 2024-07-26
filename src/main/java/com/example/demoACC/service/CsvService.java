package com.example.demoACC.service;

import com.example.demoACC.model.Mobile;
import com.example.demoACC.model.Plan;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvService {
    private List<Mobile> mobiles = new ArrayList<>();
    private List<Plan> plans = new ArrayList<>();

    

    public List<Mobile> getMobiles() {
//        System.out.println("MobilePlansRanNow");
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/fido.csv")))) {
            String[] line;
            boolean isMobile = true;
            reader.readNext(); // Skip header
            while ((line = reader.readNext()) != null) {
                if (line.length == 1) {
                    isMobile = false;
                    reader.readNext(); // Skip second header
                    continue;
                }
                if (isMobile) {
                    Mobile mobile = new Mobile();
                    mobile.setMobileName(line[0]);
                    mobile.setMobilePlanPrice(line[1]);
                    mobile.setMobileFullPrice(line[2]);
                    mobile.setMobileImageUrl(line[3]);
                    mobiles.add(mobile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobiles;
    }

    public List<Plan> getPlans() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/fido.csv")))) {
            String[] line;
            boolean isMobile = false;
            reader.readNext(); // Skip header
            while (!reader.readNext()[0].equals("Plan Name")) {
                continue;
            }

            while (((line = reader.readNext()) != null)) {
                System.out.println(Arrays.toString(line));
                if (!isMobile && (line.length != 1)) {
                    Plan plan = new Plan();
                    plan.setPlanName(line[0]);
                    plan.setPlanCharge(line[1]);
                    plan.setPlanBenefits(line[2]);
                    plans.add(plan);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return plans;
    }
}