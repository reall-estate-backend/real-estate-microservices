package com.example.realestate.payment.service;


import com.example.realestate.payment.exception.PlanNotFoundException;
import com.example.realestate.payment.mapper.PlanMapper;
import com.example.realestate.payment.repository.PlanRepository;
import com.example.realestate.payment.request.PlanRequest;
import com.example.realestate.payment.response.PlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.String.format;


@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanMapper planMapper;


    public String createPlan(PlanRequest planRequest) {
        var plan = planRepository.save(planMapper.toPlan(planRequest));
        return plan.getId();
    }

    public PlanResponse getPlanByName(String name){
        return planRepository.findPlanByName(name)
                .map(planMapper::fromPlan)
                .orElseThrow(()-> new PlanNotFoundException(format("Cannot find plan :: %s", name)));
    }

    public PlanResponse findByIdPlan(String id){
        return planRepository.findById(id)
                .map(planMapper::fromPlan)
                .orElseThrow(()-> new PlanNotFoundException(format("Cannot find plan :: %s", id)));

    }


    public List<PlanResponse> planList(){
        return planRepository.findAll()
                .stream()
                .map(planMapper :: fromPlan)
                .collect(Collectors.toList());
    }



    public boolean existsById(String id){
        return planRepository.existsById(id);
    }




}
