package com.gs.lshly.biz.support.foundation.complex;


import com.gs.lshly.biz.support.foundation.entity.SiteFloor;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenu;
import com.gs.lshly.biz.support.foundation.entity.SiteFloorMenuGoods;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteFloorDTO;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public abstract class PcSiteFloorComlexEditor {

    @Data
    @RequiredArgsConstructor()
    public static class  FloorComplex {

        private SiteFloor floor;

        private SiteFloorDTO.PCSiteFloor floorDto;

        private List<SiteFloorMenu> linkList = new ArrayList<>();

        private List<FloorMenuComplex> menuComplexList = new ArrayList<>();

    }

    @Data
    public static class  FloorMenuComplex {

        private SiteFloor floor;

        private SiteFloorDTO.PCFloorMenu floorMenuDto;

        private SiteFloorMenu floorMenu;

        private List<SiteFloorMenuGoods> menuGoodsList = new ArrayList<>();

    }

}
