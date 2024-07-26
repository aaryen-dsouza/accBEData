package com.example.demoACC.service;

import com.example.demoACC.model.BestPlan;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Comparator;

@Service
public class BestPlanService {

    @PostConstruct
    public void init() {}

    public List<BestPlan> getBestPlan() {
        List<BestPlan> plans = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/plans.csv")))) {
            String[] nextLine;
            // Skip header line
            reader.readNext(); 

            while ((nextLine = reader.readNext()) != null) {
                BestPlan plan = new BestPlan();
                plan.setName(nextLine[0]);
                plan.setPrice(Integer.parseInt(nextLine[1]));
                plan.setData(Integer.parseInt(nextLine[2]));
                plans.add(plan);
            }

            // Find the cheapest plan
            BestPlan cheapestPlan = plans.stream()
                .min(Comparator.comparingInt(BestPlan::getPrice))
                .orElse(null);

            // Find the plan with the highest GB
            BestPlan planWithHighestGB = plans.stream()
                .max(Comparator.comparingInt(BestPlan::getData))
                .orElse(null);

            List<BestPlan> result = new ArrayList<>();
            if (cheapestPlan != null) result.add(cheapestPlan);
            if (planWithHighestGB != null) result.add(planWithHighestGB);
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
