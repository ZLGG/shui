package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.foundation.entity.SiteVideo;
import com.gs.lshly.biz.support.foundation.repository.ISiteVideoRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5SiteVideoService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteVideoQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteVideoVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-04
*/
@Component
public class BbbH5SiteVideoServiceImpl implements IBbbH5SiteVideoService {

    @Autowired
    private ISiteVideoRepository repository;

    @Override
    public List<BbbH5SiteVideoVO.ListVO> video(BbbH5SiteVideoQTO.QTO qto) {
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("pc_show", PcH5Enum.NO.getCode());
        wrapper.eq("terminal", TerminalEnum.BBB.getCode());
        wrapper.eq("subject",qto.getSubject());
        List<SiteVideo> siteVideoList = repository.list( wrapper);
        return ListUtil.listCover(BbbH5SiteVideoVO.ListVO.class,siteVideoList);
    }
}
