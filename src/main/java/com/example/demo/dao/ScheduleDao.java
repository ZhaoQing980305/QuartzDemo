package com.example.demo.dao;

import com.example.demo.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao {
    List<Schedule> findAll();
}
