package com.st1.platform.pi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 뷰 엔드포인트 목록
 * /pi
 * /pi/{pi-parkno}
 * /pi/search
 * /pi/search-hashtag
 **/

//Pi 1.18 : Controller 생성
@RequiredArgsConstructor
@RequestMapping("/parkinginfos")
@Controller
public class ParkingInfoController {

    //Pi 1.19 : 현장정보 전체리스트 경로
    @GetMapping
    public String parkinginfos(ModelMap map){

        //Pi 1.21
        map. addAttribute ("parkinginfos", List.of());

        //Pi 1.20
        return "parkinginfos/index";
    }
}
