package com.heybys.optimusamicus.common.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {}
